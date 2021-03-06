/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.test_util.default_game.ui_sw;

import adv17w_fw.game_gui.IGameG;

import java.util.Collection;



/*******************************************************************************
 * Instance třídy {@code ThingButtonPane} představují panely,
 * v nichž se v průběhu hry zobrazují h-objekty v aktuálním prostoru.
 * <br><br>
 * Třída je upravena tak, aby její instance uměly pracovat s prvky
 * definovanými jako tlačítka, jejichž stiskem lze daný předmět zvednout.
 * K tomu bylo třeba přidat atributy pro zapamatování si příkazu ke zvednutí
 * předmětu a také GUI, jehož prostřednictvím bude příkaz hře zadán.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 14.00.4526
 */
@SuppressWarnings("serial")
class ThingButtonPane extends AItemContainerButtonPane
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
     * Vytvoří nový panel pro zobrazení h-objektů v aktuálním prostoru,
     * přičemž předpokládá, že h-objekty budou na panelu zobrazovány
     * jako tlačítka, jejichž stiskem můžeme daný h-objekt zvednout.
     *
     * @param gui  GUI, prostřednictvím nějž je hra ovládána
     */
    ThingButtonPane(IMyGUI gui)
    {
        super("Předměty v místnosti", gui);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Inicializuje panel pro práci s novou hrou.
     *
     * @param game Hra, s níž bude panel od této chvíle komunikovat
     */
    @Override
    @SuppressWarnings("unchecked")
    public void inicialize(IGameG game)
    {
        action = game.getBasicActions().TAKE_CMD_NAME;

        //Přetypování má pouze odstranit námitky překladače
        update((Collection)(game.getWorld().getAllPlaces()));
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
