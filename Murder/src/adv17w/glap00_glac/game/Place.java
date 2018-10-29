/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac.game;;

import java.util.Collection;
import adv17w_fw.game_txt.IPlace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Collection;
import adv17w_fw.game_txt.IPlace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Collection;
import adv17w_fw.game_txt.IPlace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Collection;
import adv17w_fw.game_txt.IPlace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

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
 * In this program the places are Stanice, Hospoda, Místo_činu, Radnice a Ulice
 *
 * @author  Pavel Glac
 * @version 2017-Winter
 */
public   class Place
       extends AItemContainer
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
    
    /** Názvy sousedů prostoru na počátku hry. */ 
    private final String[] neighborNames;
    
    /** Názvy sousedů místnosti. */
    private final Collection<Place> neighbors;
    
    /** Immutable collection of current neighbors of the given place,
     *  that continuously maps the {@link #neighbors} collection content. */
    private final Collection<Place> exportedNeighbors;
    
    private double posLeft;
    private double posTop;

    
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============
//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates a new place with the given name and
     * with the given names of its initial neighbors and items.
     *
     * @param name          Name of the given place
     * @param neighborNames Names of place neighbors at the game beginning
     * @param itemNames     Names of items in the place at the game beginning
     */
    Place(String name, double posLeft, double posTop, String[] neighborNames, String... itemNames)
    {
        super(name, itemNames);
        this.neighborNames = neighborNames;
        this.neighbors        = new ArrayList<>();
        this.exportedNeighbors= Collections.unmodifiableCollection(neighbors);
        this.posLeft = posLeft;
        this.posTop = posTop;

        
    }

//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the collection of current neighbors of this place, i.e. the
     * collection of places, to which we can move from this place with the
     * TypeOfStep.tsMOVE} type.
     *
     * @return Collection of neighbors
     */
    @Override
    public Collection<Place> getNeighbors()
    {
        return exportedNeighbors;
    }
    
    public Double getPosLeft() {
        return posLeft;
    }

    public Double getPosTop() {
        return posTop;
    }


    /***************************************************************************
     * Method initializing the given place.
     * Based on the names remembered by the constructor
     * it initializes the neighbors of the given place and its items.
     */

    public void initialize()
    {
        initializeItems();
        initializeNeighbors();
    }

//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Cleans the collection {@link #neighbors} and saves into it the items
     * representing the places neighboring with the given place
     * at the game beginning.
     */
    private void initializeNeighbors()
    {
        Town town = Town.getInstance(); 
        neighbors.clear();
        Arrays.stream(neighborNames)
                .map(town::getOPlace)
                .map(Optional<Place>::get)
                .forEach(neighbors::add);       
    }

//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
