/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.empty_classes;

import adv17w_fw.game_txt.IItem;

import java.util.Collection;
import adv17w_fw.utilities.UncompletedMethodException;
import adv17w_fw.game_txt.IPlace;



/*******************************************************************************
 * Instances of the {@code EmptyPlace} class represent the places in the game.
 *
 * We can take the place visiting as a partial goal,
 * which the player tries to reach.
 * The places can be rooms, planets, life stages etc.
 * The places can contain various items.that may help user to reach the goal.
 * Each place knows its current neighboring places and it knows
 * which items it currently contains.
 * The neighbors as well as the contained items can change during the game.
 * <p>
 * In this program the places are ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2017-Summer
 */
public   class EmptyPlace
       extends ANamed
    implements IPlace
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
     *
     */
    EmptyPlace(String name)
    {
        super(name);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the collection of current neighbors of this place.
     *
     * @return Collection of neighbors
     */
    @Override
    public Collection<? extends IPlace> getNeighbors()
    {
        //TODO EmptyPlace.getNeighbors - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }
    
    public Double getPosLeft() {
        throw new UncompletedMethodException();
    }

    public Double getPosTop() {
       throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Returns a collection of items located in the given place.
     *
     * @return Collection of items located in the given place
     */
    @Override
    public Collection<? extends IItem> getItems()
    {
        //TODO EmptyPlace.getItems - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
