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
public class ActionMove
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
     * Moves the player into the place (room) given in an argument.
     * Requires that this place has to be a neighbor of the current place,
     * otherwise nothing will be done and the command is reported as wrong.
     */
    public ActionMove()
    {
        super ("JDI",
               "Metoda přesune hráče do sousední místnosti zadané v parametru."+
    "\nVyžaduje však, aby tato místnost byla sousedem aktuální místnosti," +
    "\nprotože jinak přesun neprovede a bude příkaz považovat za chybný");   
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
         if (arguments.length < 2) {return "Nelze určit kam jít.";}
         String destinationName = arguments[1];
         Place   currentRoom     = Town.getInstance().getCurrentPlace();
         Optional<Place> oDestination = INamed.getO(destinationName,
                                                    currentRoom.getNeighbors());
         if (! oDestination.isPresent()) {return "Tento prostor nesousedí s " 
                 + destinationName;}
         Place destinationRoom = oDestination.get();
         Town.getInstance().setCurrentArea(destinationRoom);
         return "Nacházíte se na místě: " + destinationRoom.getName();   
    }


//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================
//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
