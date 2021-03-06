/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.test_util.default_game;

import adv17w_fw.game_txt.IUI;
import adv17w_fw.scenario.AScenarioManager;
import adv17w_fw.test_util.default_game.game.IMyGSMFactory;
import adv17w_fw.test_util.default_game.game.version_t.GameT_Icebox;
import adv17w_fw.test_util.default_game.smanagers.ScenMan_TLoc_Icebox;

/**
 *
 * @author pavel
 */
@SuppressWarnings({"rawtypes", "unchecked"})
/*******************************************************************************
 * {@code DemoGSMFactory} instances represent the factory objects
 * which are able to deliver the game instance,
 * an instance of scenario manager of this game
 * and an instance of the text user interface.
 * As long as some of these objects are not yet fully defined,
 * the methods throw the
 * {@link eu.pedu.adv17s_fw.utilities.UncompletedMethodException}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public   class GSMFactoryTLoc_Apartment
    implements IMyGSMFactory, IAuthorDemo
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================

    static {
        new InitProperties("Implicitní textová hra - Služební byt s ledničkou",
                           GSMFactoryTLoc_Apartment.class);
    }



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
    public GSMFactoryTLoc_Apartment()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which also the mother class of this instance is.
     *
     * @return The class-object of the factory class
     */
    @Override
    public Class<GSMFactoryTLoc_Apartment> getFactoryClass()
    {
        return GSMFactoryTLoc_Apartment.class;
    }

//
//    /***************************************************************************
//     * Returns the instance of the scenario manager.
//     *
//     * @return Required scenario manager
//     */
//    @Override
//    public DemoScenarioManagerCon getScenarioManager()
//    {
//        return DemoScenarioManagerCon.getInstance();
//    }
//

    /***************************************************************************
     * Returns the instance of the scenario manager.
     *
     * @return Required scenario manager
     */
    @Override
    public AScenarioManager getScenarioManager()
    {
        return ScenMan_TLoc_Icebox.getInstance();
    }


    /***************************************************************************
     * Returns the instance of text version of the game.
     *
     * @return Required game
     */
    @Override
    public GameT_Icebox getGame()
    {
        return GameT_Icebox.getInstance();
    }


    /***************************************************************************
     * Returns the object executing the user interface.
     *
     * @return Required user interface
     */
    @Override
    public IUI getUI()
    {
        IUI ui = null;
//                 new UIA_JOptionPane();
//                 new UIB_Console();
//                 new UIC_GamePlayer(new UIC_GamePlayer.ByJOptionPane());
//                 new UIC_GamePlayer(new UIC_GamePlayer.ByScanner());
//                 new UID_Multiplayer(new UID_Multiplayer.ByJOptionPane());
//                 new UID_Multiplayer(new UID_Multiplayer.ByScanner());
        return ui;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================



//##############################################################################
//\MM== MAIN METHOD ============================================================

    /***************************************************************************
     * Metoda spouštějící celou aplikaci.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        GSMFactoryTLoc_Apartment factory = new GSMFactoryTLoc_Apartment();
        AScenarioManager scenarioManager = factory.getScenarioManager();
//        scenarioManager.autoTest();
        scenarioManager.testGame();
    }
}
