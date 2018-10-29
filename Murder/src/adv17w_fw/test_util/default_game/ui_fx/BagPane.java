/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.test_util.default_game.ui_fx;

import adv17w_fw.game_gui.IBagG;
import adv17w_fw.game_gui.IGameG;
import adv17w_fw.game_txt.INamed;

import java.util.Collection;



/*******************************************************************************
 * Instance třídy {@code BagPane} představují panely tlačítek
 * reprezentujících objekty v batohu
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 0.00.0000 — 20yy-mm-dd
 */
class       BagPane
    extends AButtonPane
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
     * Pro zadané GUI vytvoří vytvoří panel tlačítek
     * reprezentujících objekty v batohu.
     *
     * @param gui   GUI, pro něž je panel vytvářen
     */
    BagPane(IMyGUI gui)
    {
        super(gui, "Objekty v batohu");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí název akce realizující položení h-objektu.
     *
     * @return Název akce realizující položení h-objektu
     */
    @Override
    String getCommandName()
    {
        return gui.getGame().getBasicActions().PUT_DOWN_CMD_NAME;
    }


    /***************************************************************************
     * Zjistí nový stav zobrazovaného kontejneru a vrátí kolekci jeho prvků,
     * v tomto případě h-objekty v batohu.
     *
     * @return Aktuální kolekce prvků zobrazovaného kontejneru
     */
    @Override
    Collection<? extends INamed> getObjects()
    {
        IBagG bag = gui.getGame().getBag();
        Collection<? extends INamed> objects = bag.getItems();
        return objects;
    }


    /***************************************************************************
     * Inicializuje se pro zadanou (nově spuštěnou) hru —
     * přihlásí se u ní jako posluchač objektů v batohu a
     * zapamatuje si název příkazu zadávaného stiskem tlačítka.
     *
     * @param game Nově spouštěná hra
     */
    @Override
    public void initializeForGame(IGameG game)
    {
        String commandName = game.getBasicActions().PUT_DOWN_CMD_NAME;
        initializationForGame(game, commandName);
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}