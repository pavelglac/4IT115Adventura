/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.test_util.default_game.game;

import adv17w_fw.game_txt.IGSMFactory;
import adv17w_fw.game_txt.IUI;
import adv17w_fw.scenario.AScenarioManager;
import adv17w_fw.utilities.UncompletedMethodException;



/*******************************************************************************
//%L+ CZ
 * Instance interfejsu {@code IMyGSMFactory} představují tovární objekty
 * schopné dodat instance klíčových objektů hry, které je možno použít
 * ve společných objektech definovaných v balíčku
 * {@code eu.pedu.adv17s_fw.test_util.default_game.game.common}.
//%Lx EN
 * The {@code IMyGSMFactory} interface instances represent ...
 *
 * @param <TI> Type of items
 * @param <TP> Type of places
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 0.00.0000 — 20yy-mm-dd
 */
public interface IMyGSMFactory<TI extends IMyItem, TP extends IMyPlace<TI, TP>>
         extends IGSMFactory
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================
//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================

    /***************************************************************************
     * Returns the instance of the scenario manager.
     * Until the appropriate class is not fully defined,
     * it throws the {@link UncompletedMethodException}.
     *
     * @return Required scenario manager
     * @throws UncompletedMethodException
     *         The class defining the textual user interface is not yet
     *         acceptable (= testable).
     */
    @Override
    public default AScenarioManager getScenarioManager()
    {
        throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Returns the instance of text version of the game.
     * Until the appropriate class is not fully defined,
     * it throws the {@link UncompletedMethodException}.
     *
     * @return Required game
     * @throws UncompletedMethodException
     *         The class defining the textual user interface is not yet
     *         acceptable (= testable).
     */
    @Override
    public default IMyGame<TI, TP> getGame()
    {
        throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Returns the object executing the user interface.
     * Until the appropriate class is not fully defined,
     * it throws the {@link UncompletedMethodException}.
     *
     * @return Required user interface
     * @throws UncompletedMethodException
     *         The class defining the textual user interface is not yet
     *         acceptable (= testable).
     */
    @Override
    public default IUI getUI()
    {
        throw new UncompletedMethodException();
    }



//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
