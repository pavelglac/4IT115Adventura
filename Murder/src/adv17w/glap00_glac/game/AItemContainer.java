/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac.game;

import adv17w_fw.game_txt.IItemContainer;
import adv17w_fw.game_txt.INamed;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;



/*******************************************************************************
 * Instances of the abstract class {@code AItemContainer} are
 * parent sub-objects of the objects performing as item containers.
 *
 * @author  Pavel Glac
 * @version 2017-Winter
 */
abstract class AItemContainer
       extends ANamed
    implements IItemContainer        
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

    /** Names of items in this container at the game beginning. */
    private final String[] itemNames;

    /** Names of items currently present in this container. */
    private final Collection<Component> items;

    /** Immutable collection of items currently present in this container,
     *  that continuously maps the {@link #items} collection content. */
    private final Collection<Component> exportedItems;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates the parent sub-object of the container of items
     * with the given names of its initial items.
     *
     * @param name      Name of the child object
     * @param itemNames Names of items in the container at the game beginning
     */
    AItemContainer(String name, String... itemNames)
    {
        super(name);
        this.itemNames     = itemNames;
        this.items         = new ArrayList<>();
        this.exportedItems = Collections.unmodifiableCollection(items);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns a collection of items located in the given container.
     *
     * @return Collection of items located in the given container
     */
    @Override
    public Collection<Component> getItems()
    {
        return exportedItems;
    }


    /***************************************************************************
     * Returns the item with the given name wrapped into     *
     * @param  name Name of the asked item
     * @return The item with the given name wrapped into
     */

    protected Optional<Component> getOItem(String name)
    {
        return INamed.getO(name, items);
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Adds the given item into the container.
     *
     * @param item The item that has to be added into the container
     */
    protected void addItem(Component item)
    {
        items.add(item);
    }


    /***************************************************************************
     * Cleans the container and saves into it the items
     * located in the given container at the game beginning.
     */
    protected void initializeItems()
    {
        items.clear();
        Arrays.stream(itemNames)
              .map(Component::new)
              .forEach(items::add);
    }


    /***************************************************************************
     * Removes the given item from this container.
     *
     * @param item Item to be removed
     */
    void removeItem(Component item)
    {
        items.remove(item);
    }   
    



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
