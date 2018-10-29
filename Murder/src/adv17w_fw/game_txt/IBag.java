/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.game_txt;

import adv17w.glap00_glac.util.IObserverZmenyBatohu;
import adv17w.glap00_glac.util.IObserverZmenyProstoru;
import java.util.Collection;



/*******************************************************************************
 * Instance of the {@code Bag} interface represents the repository,
 * to which the players store the items picked up in individual places,
 * so that they could be moved to other places and/or used.
 * The disposal site has a final capacity defining the maximal permitted
 * sum of weights of items occuring in the repository.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public interface IBag
         extends IItemContainer
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the bag capacity, i.e. the maximal permitted sum
     * of weights of items, that can be put into the bag at the same time.
     *
     * @return Capacity of the bag
     */
//    @Override
    public int getCapacity()
    ;
    /**
     * Metoda slouží k zaregistrování pozorovatele, musí to být instance třídy,
     * která implementuje rozhraní ObserverZmenyBatohu.
     *
     * @param pozorovatel registrovaný objekt
     */
    public void zaregistrujPozorovatele(IObserverZmenyBatohu pozorovatel);

    /**
     * Metoda slouží k zrušení registrace pozorovatele, musí to být instance
     * třídy, která implementuje rozhraní ObserverZmenyBatohu.
     *
     * @param pozorovatel objekt, který již nechce být informován o změnách
     */
    public void odregistrujPozorovatele(IObserverZmenyBatohu pozorovatel);

    /**
     * Metoda, která se volá vždy, když dojde ke změně této instance. Upozorní
     * všechny pozorovatele, že došlo ke změně tak, že zavolá jejich metodu
     * aktualizuj.
     */
    public void upozorniPozorovatele();
//
//    /***************************************************************************
//     * Returns the collection of items saved in the bag.
//     *
//     * @return Collection of items in the bag
//     */
//    @Override
//    public Collection<? extends IItem> getItems()
//    ;
//
//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
