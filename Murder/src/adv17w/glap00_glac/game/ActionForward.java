/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac.game;

import adv17w_fw.game_txt.INamed;
import java.util.Arrays;
import java.util.Optional;

/*******************************************************************************
 * @author  Pavel Glac
 * @version 2017-Winter
 */
public class ActionForward
     extends AAction
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
    
    private final String[] itemsForForwarding = {"KAMERA", "ODPADKY","DROBÁKY"};
    
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================
//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============
//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates an action instance for
     * taking an item away from the current place and putting it into the bag.
     */
    public ActionForward()
    {
        super ("PŘEDAT",
               "Lze prozkoumat pouze objekty, které jsou v aktuální lokaci."+
               "\nProzkoumání může pomoci k dopadení vraha");                   
    }

//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

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
    public String execute(String... arguments)
    {
        
         if (arguments.length < 2) {
             return "Chybí paremetry předání (CO PŘEDAT a KOMU PŘEDAT)";}
         if (arguments.length < 3) {
             return "Chybí jeden paremetr předání"+
               "\npříklad: PŘEDAT ODZNAK UČITELKA";}         
         String itemName = arguments[1];
         String itemName2 = arguments[2];
         Pockets bag = Pockets.getInstance();
         Place   currentRoom     = Town.getInstance().getCurrentPlace();
         Optional<Component> oItem = INamed.getO(itemName2,
                                                    currentRoom.getItems());   
         Optional<Component> oItem1 = bag.getOItem(itemName);
         if (! oItem1.isPresent()) {
             return"Item "+itemName+" se nenachází v kapsách";}
         if (! oItem.isPresent()) {
             return itemName2+" se nenachází v tomto prostoru";}
         if(!Arrays.asList(itemsForForwarding).contains(itemName.toUpperCase()))
         {return "Tento item nelze předávat";}
         if(! Arrays.asList(Component.people).contains(itemName2.toUpperCase()))
         {return "Neživému objektu opravdu nemá smysl cokoliv předávat";}      
         return Text.answerOnForwarding(itemName, itemName2);
    }

//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================
//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
