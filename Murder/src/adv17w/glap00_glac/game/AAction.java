/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac.game;

import adv17w_fw.game_txt.IAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;



/*******************************************************************************
 * The {@code AAction} abstract class is a common superclass of all classes,
 * the instances of which are responsible for interpretation of commands
 * entered by the user playing the game.
 * Name of the executed action is usually the first word of the entered command.
 * The further words are interpreted as arguments.
 * <p>
 * You can define also a command that opens the conversation
 * (e.g. with a person present in the room) and thus switch to the mode,
 * in which the entered texts are not interpreted as commands,
 * but are passed to the defined object up to moment,
 * when the conversation ends and the object controlling the dialogue
 * switches the game back to the basic command mode.
 *
 * @author  Pavel Glac
 * @version 2017-Winter
 */
abstract class AAction
       extends ANamed
    implements IAction
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
    
    /** Map mediating the conversion of the action name to its instance. */
    private static final Map<String, AAction> NAME_2_ACTION; 
    
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============
    
    /** Keeps information if the game is just played
     *  or only waits for being started. */
    private static boolean isAlive = false;
    
//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
    
    static {
        NAME_2_ACTION = new HashMap<>();      
        new ActionMove();
        new ActionPickUp();
        new ActionPutDown();
        new ActionExit();
        new ActionHelp();
        new ActionExamine();
        new ActionForward();
        new ActionAccuse();
        new ActionTalk();
    }
    
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
    
     /**************************************************************************
     * Processes the given command and returns the answer to the user.
     *
     * @param command The entered command
     * @return The answer of the game after processing the command
     */
    public static String executeCommand(String command)
    {
        command = command.trim();
        String answer;
        if (isAlive) { answer = executeCommonComand(command);}
        else {answer = startGame(command);}
        
        String[] words = command.toLowerCase().split("\\s+");
        String actionName = words[0];
        AAction action = NAME_2_ACTION.get(actionName);

        /*if (State.notUsedActions.contains(action)) {
            State.notUsedActions.remove(action);
        }
        answer += State.notUsedActions.stream()
                .map(ac -> ac.getName())
                .sorted()
                .collect(Collectors.joining(", ", "\nNezadané příkazy: [", "]"));
        */
        return answer;
    }
    
    
    /***************************************************************************
     * Ensures the correct ending of the game
     * and saves information that the game is finished.
     */
    static void stopGame()
    {
        isAlive = false;
    }


//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================
    
    /***************************************************************************
     * The method gradually initializes all key objects of the game.
     */
    private static void initialize()
    {   
        Town.getInstance().initialize();
        Pockets.getInstance().initialize();
        State.initialize();
    }

    /***************************************************************************
     * Processes the given command and returns the answer to the user.
     *
     * @param command The entered command
     * @return The answer of the game after processing the command
     */
     private static String executeCommonComand(String command)
     {
      if (command.isEmpty()) { return "zadali jste prázdný příkaz";}
      String[] words       = command.toLowerCase().split("\\s+");
      String   acttionName = words[0];
      AAction  action      = NAME_2_ACTION.get(acttionName);
      String   answer;
      if (action == null) { answer = "Zadali jste neznámý příkaz";}
      else {answer = action.execute(words);}
      return answer;
     }
     
    /***************************************************************************
     * Verifies if the game is started with the proper (= empty) command,
     * and if yes, starts the game.
     *
     * @param command Command starting the game
     * @return  Game answer to the entered command
     */
    private static String startGame(String command) 
    {
        String answer;
        if (command.isEmpty()) {initialize();
                                answer = 
                    "A sakra. Zase další vražda v tomhle malém městě" + 
                    "\nMěl bych se jít na to podívat.";
                                isAlive = true;}
        else {answer = "Hra se spouští prázdým příkazem.";}
        return answer;
    }


//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Brief description of the given action. */
    private final String description;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates the parent sub-object of the created action.
     *
     * @param name  Name of the created action = text, which the player has to
     *              enter as an initial word of the entered command
     * @param description Brief description of the created action
     */
    public AAction(String name, String description)
    {
        super(name);
        this.description = description;
        AAction previous = NAME_2_ACTION.put(name.toLowerCase(), this);
        if (previous != null) { 
            throw new IllegalArgumentException(
            "\nAkce s názvem «" + name + "» byl již vytvořena"); 
        }
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================

    /***************************************************************************
     * Processes the command composed from the given words
     * and returns the game answer to the user.
     * Number of word depends on particular action, however it must be
     * at least one, because the zeroth element contains the action name.
     * The remaining words are arguments of this action and they may differ:
     * the actions of <i>end</i> and <i>help</i> type do not have any,
     * the actions of <i>go</i> and <i>take</i> type have one,
     * the actions of <i>apply</i> type ) can have two (e.g. apply key lock)
     * or three (e.g. apply key to lock) etc.
     *
     * @param arguments Action arguments –
     *                  their number can be different for each action,
     *                  but for all execution of the same action is the same
     * @return The answer of the game after processing the command
     */
    @Override
    abstract
    public String execute(String... arguments)
    ;




//\IG== INSTANCE GETTERS AND SETTERS ===========================================
    
    /***************************************************************************
     * Returns information if the game is currently running.
     * The once started game cannot be started again.
     * If you want to start the game again you have to finish it firstly.
     *
     * @return If the game is running, it returns {@code true},
     *         otherwise it returns {@code false}
     */
    public static boolean isAlive()
    {
        return isAlive;
    }


    /***************************************************************************
     * Returns the action description with explanation of its function
     * and the meaning of individual parameters.
     *
     * @return Action description
     */
    @Override
    public String getDescription()
    {
        return description;
    }    
    
    /***************************************************************************
     * Returns a collection of all actions that can be used in the game.
     *
     * @return A collection of all actions usable in the game
     */
    
    public static Collection<AAction> getAllActions()
    {
        Collection<AAction> collection, result;
        collection = NAME_2_ACTION.values();
        result     = Collections.unmodifiableCollection(collection);
        return result;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
