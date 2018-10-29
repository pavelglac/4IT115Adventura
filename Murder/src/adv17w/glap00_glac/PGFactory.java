/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac;

import adv17w.glap00_glac.game.IPGAuthor;
import adv17w.glap00_glac.game.PGMurderGame;
import adv17w.glap00_glac.game.PGScenarioManager;
import adv17w_fw.game_txt.IGSMFactory;
import adv17w_fw.scenario.AScenarioManager;



/*******************************************************************************
 * Instances of the {@code RUPFactory } class represent
 * the factory objects which are able to deliver the game instance,
 * an instance of scenario manager of this game
 * and an instance of the text user interface.
 * As long as some of these objects are not yet fully defined,
 * the methods throw the
 * {@link adv17w_fw.utilities.UncompletedMethodException}.
 * <p>
 * In the first stage of the whole application development only the method
 * {@link #getScenarioManager()} allowing to obtain
 * the scenario manager instance is active.
 * Bodies of remaining methods are commented.
 * In the next development stages also these methods are uncommented.
 *
 * @author  Pavel Glac
 * @version 2017-Winter
 */
public   class PGFactory
    implements IGSMFactory, IPGAuthor
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
     * Creates the factory object providing the key application objects.
     */
    public PGFactory()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the instance of the scenario manager.
     *
     * @return Required scenario manager
     */
    @Override
    public AScenarioManager getScenarioManager()
    {
        return PGScenarioManager.getInstance();
    }

    /***************************************************************************
     * Returns the instance of text version of the game.
     *
     * @return Required game
     */
    @Override
    public PGMurderGame getGame()
    {
        return PGMurderGame.getInstance();
    }


    /***************************************************************************
     * Returns the object executing the user interface.
     *
     * @return Required user interface
     */
//    @Override
//    public IUI getUI()
//    {
//        return TextUI_Instance;
//    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
