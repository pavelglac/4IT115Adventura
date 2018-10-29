/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac.game;

import adv17w_fw.game_txt.INamed;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;



/*******************************************************************************
 * @author  Pavel Glac
 * @version 2017-Winter
 */
public class ActionAccuse
     extends AAction
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
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
     * accusing people.
     */
    public ActionAccuse()
    {
        super ("OBVINIT",
               "Obvinit lze pouze živou bytost"+
               "\nPokud obviníte nesprávnou osobu, tak prohrajete."); }

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
                                return "Nezadali jste koho chcete obvinit";}
         String itemName = arguments[1];
         Place   currentRoom     = Town.getInstance().getCurrentPlace();  
         Optional<Component> oPlaceItem = INamed.getO(itemName,
                 currentRoom.getItems());
         if (! oPlaceItem.isPresent()) {
             return itemName+" se nenachází v tomto prostoru";}
         if(! Arrays.asList(Component.people).contains(itemName.toUpperCase()))
         {return "Neživá věc to rozhodně udělat nemohla, ale to moc dobře víte."
                 +"Přestaňte zkoumat funkčnost hry a začněte hrát.";
         }
         AAction.stopGame();
         return Text.answerOnAccuse(itemName.toUpperCase());    }

//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================
//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
