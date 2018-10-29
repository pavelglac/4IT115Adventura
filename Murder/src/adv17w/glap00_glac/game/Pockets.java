/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac.game;

import adv17w.glap00_glac.util.IObserverZmenyBatohu;
import adv17w_fw.game_txt.IBag;
import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * Instance of the {@code EmptyBag} class represents the repository,
 * to which the players store the items picked up in individual places,
 * so that they could be moved to other places and/or used.
 * The disposal site has a final capacity defining the maximal permitted
 * sum of weights of items occuring in the repository.
 * <p>
 * In this game the bag is ...
 * with capacity ....
 * The item weight represents
 *
 * @author  Pavel Glac
 * @version 2017-Winter
 */
public   class Pockets
    extends  AItemContainer
    implements IBag
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** The only instance of the bag in the bag. */
    private static final Pockets SINGLETON = new Pockets();
    
    private List<IObserverZmenyBatohu> seznamPozorovatelu;

//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Factory method returning the only existing instance of the bag.
     *
     * @return The only instance of the bag
     */
    static Pockets getInstance()
    {
        return SINGLETON;
    }

//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
    
    /** Capacity of the bag. */
    static final int CAPACITY = 4;
    
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================
//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============
    
    /** Free capacity of the bag. */
    private int remains;

//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     */
    public Pockets()
    {
        super("Pockets");
        seznamPozorovatelu = new ArrayList<>();
    }

//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the bag capacity, i.e. the maximal permitted sum
     * of weights of items, that can be put into the bag at the same time.
     *
     * @return Capacity of the bag
     */
    @Override
    public int getCapacity()
    {
        return CAPACITY;
    }

//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
    
    /***************************************************************************
     * The method initializing the bag.
     * As the player of this game has an empty bag at the game beginning,
     * this method can only clean the {@link #items} collection.
     */

    public void initialize()
    {
        initializeItems();
        remains = CAPACITY;
    }
    
    /***************************************************************************
     * Removes the given item from the bag
     * and increases the free capacity of the bag.
     *
     * @param item Item taken away
     */
    public void removeItem(Component item)
    {
        super.removeItem(item);
        remains += item.getWeight();
        upozorniPozorovatele();
    }
    
    /***************************************************************************
     * If the given item fits to the bag, it adds it;
     * after that it returns the message on the result.
     *
     * @param item The item that has to be added into the bag
     * @return The message on the result: {@code true} = was added,
     *         {@code false} = was not added
     */
    public boolean tryAddItem(Component item)
    {
        if (item.getWeight() > remains) {
            return false;
        }
        addItem(item);
        remains -= item.getWeight();
        upozorniPozorovatele();
        return true;
    }
    
    public void zaregistrujPozorovatele(IObserverZmenyBatohu pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }

    public void odregistrujPozorovatele(IObserverZmenyBatohu pozorovatel) {
        seznamPozorovatelu.remove(pozorovatel);
    }

    public void upozorniPozorovatele() {
        for (IObserverZmenyBatohu pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj();
        }
    }
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================
//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
