/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac.game;

import adv17w_fw.game_txt.INamed;
import java.util.Optional;

/*******************************************************************************
 * @author  Pavel Glac
 * @version 2017-Winter
 */
public class ActionPickUp
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
     * taking an item away from the current place and putting it into the bag.
     */
    public ActionPickUp()
    {
        super ("VEZMI",
               "Přesune zadaný h-objekt z aktuálního prostoru do batohu."+
               "\nPřesouvá přitom pouze přenositelné h-objekty.");  
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
             return "Nezadali jste platný item pro zvednutí.";}
         String itemName = arguments[1];
         Place   currentRoom     = Town.getInstance().getCurrentPlace();
         Optional<Component> oItem = INamed.getO(itemName,
                                                    currentRoom.getItems());
         if (! oItem.isPresent()) {
             return"Item "+itemName+" se nenachází v tomto prostoru";}
         Component item = oItem.get();
         Pockets bag = Pockets.getInstance(); 
         if (item.getWeight() >= bag.getCapacity()){
                return itemName+" je nepřenositelný.";}
         boolean added = bag.tryAddItem(item);
         if (added) { 
                        currentRoom.removeItem(item);
                        return Text.answerOnPicking(itemName.toUpperCase());
                    }
         else { return "Kapsy jsou plné. Můžete zde něco zahodit."; }
    }

//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================
//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
