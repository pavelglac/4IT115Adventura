/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.game_txt;

import adv17w.glap00_glac.util.IObserverZmenyProstoru;
import java.util.Collection;



/*******************************************************************************
 * An instance of the {@code IWorld} interface represents the game world.
 * It should be defined as a singleton.
 * It is responsible for arrangement of individual places and keeps information,
 * in which place the player is just situated.
 * The mutual arrangement may change during the game,
 * the places can gain and/or lose their neighbors.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public interface IWorld
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the collection of all places of the game.
     *
     * @return Collection of all places performing in the game
     */
//    @Override
    public Collection<? extends IPlace> getAllPlaces()
    ;


    /***************************************************************************
     * Returns the current place,
     * i.e. to the place in which the player is just situated.
     *
     * @return The place in which the player is just situated
     */
//    @Override
    public IPlace getCurrentPlace();
    
    /**
 * Metoda slouží k zaregistrování pozorovatele, musí to být instance třídy,
 *  která implementuje rozhraní ObserverZmenyProstoru.
 *  
 * @param pozorovatel registrovaný objekt
 */
public void zaregistrujPozorovatele(IObserverZmenyProstoru pozorovatel);

/**
 * Metoda slouží k zrušení registrace pozorovatele, musí to být instance třídy,
 *  která implementuje rozhraní ObserverZmenyProstoru.
 * 
 * @param pozorovatel objekt, který již nechce být informován o změnách 
 */
public void odregistrujPozorovatele(IObserverZmenyProstoru pozorovatel);


/**
 * Metoda, která se volá vždy, když dojde ke změně této instance.
 * Upozorní všechny pozorovatele, že došlo ke změně tak, že zavolá jejich metodu aktualizuj.
 */
public void upozorniPozorovatele();




//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
