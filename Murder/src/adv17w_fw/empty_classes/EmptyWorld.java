/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.empty_classes;

import adv17w.glap00_glac.util.IObserverZmenyProstoru;
import java.util.Collection;
import adv17w_fw.game_txt.IWorld;
import adv17w_fw.utilities.UncompletedMethodException;
import adv17w_fw.game_txt.IPlace;
import java.util.List;



/*******************************************************************************
 * An instance of the {@code EmptyWorld} class represents the game world.
 * It should be defined as a singleton.
 * It is responsible for arrangement of individual places and keeps information,
 * in which place the player is just situated.
 * The mutual arrangement may change during the game,
 * the places can gain and/or lose their neighbors.
 * <p>
 * In this game the world is ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2017-Summer
 */
public   class EmptyWorld
    implements IWorld
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** The only instance (singleton) of this world. */
    private static final EmptyWorld SINGLETON = new EmptyWorld();
    private List<IObserverZmenyProstoru> seznamPozorovatelu;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * The factory method returning the only existing instance of the game.
     *
     * @return The only instance of the given game
     */
    static EmptyWorld getInstance()
    {
        return SINGLETON;
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * The private constructor creating the only instance of the place world.
     * Within this manager definition it creates all game places.
     */
    private EmptyWorld()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the collection of all places of the game.
     *
     * @return Collection of all places performing in the game
     */
    @Override
    public Collection<? extends IPlace> getAllPlaces()
    {
        //TODO EmptyGame.getAllPlaces - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Returns the current place,
     * i.e. to the place in which the player is just situated.
     *
     * @return The place in which the player is just situated
     */
    @Override
    public IPlace getCurrentPlace()
    {
        //TODO EmptyGame.getCurrentPlace - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }
    
           @Override
    public void zaregistrujPozorovatele(IObserverZmenyProstoru pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }

    @Override
    public void odregistrujPozorovatele(IObserverZmenyProstoru pozorovatel) {
        seznamPozorovatelu.remove(pozorovatel);
    }

    @Override
    public void upozorniPozorovatele() {
        for (IObserverZmenyProstoru pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj();
        }
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
