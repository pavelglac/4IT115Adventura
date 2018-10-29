/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.test_util.default_game;

import adv17w_fw.game_txt.IGSMFactory;
import adv17w_fw.test_util.default_game.game.IMyGSMFactory;

/**
 *
 * @author pavel
 */
@SuppressWarnings({"rawtypes", "unchecked"})
/*******************************************************************************
//%L+ CZ
 * Instance třídy {@code InitProperties} představují přepravky
 * uchovávající inicializační informace pro konstruktory zaváděných tříd.
//%Lx EN
 * The {@code InitProperties} instances represent crate
 * with the initializing information for the constructors of loaded classes.
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class InitProperties
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** The only instance of this class. */
    private static volatile InitProperties SINGLETON;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
//%L+ CZ
     * Vrátí odkaz na jedinou instanci dané třídy.
     *
     * @return Odkaz na jedinou instanci dané třídy
//%Lx EN
     * Returns the reference to the only instance of this class.
     */
    public static InitProperties getInstance()
    {
        if (SINGLETON == null) {
            throw new IllegalStateException(
                "\nTřída InitProperties ještě nemá vytvořenou instanci");
        }
        return SINGLETON;
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** The name of the created game. */
    public final String gameName;

    /** The class-object of the corresponding factory class. */
    public final Class<? extends IMyGSMFactory> factoryClass;

    /** An instance of the corresponding factory class. */
    public final IMyGSMFactory factory;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     *
     * @param gameName      The name of the created game
     * @param factoryClass  The class-object of the corresponding factory class
     */
    public InitProperties(String gameName,
                          Class<? extends IMyGSMFactory> factoryClass)
    {
        if (SINGLETON == null)
        {
            synchronized(InitProperties.class)
            {
                if (SINGLETON == null)
                {
                    this.gameName     = gameName;
                    this.factoryClass = factoryClass;
                    this.factory      = (IMyGSMFactory)IGSMFactory
                                       .getInstanceOfFactory(factoryClass);

                    InitProperties.SINGLETON = this;
                    return;
                }
            }
        }
        throw new IllegalStateException(
                "\nOpakovaný pokus o vytvoření instance třídy InitProperties."
              + "\nInstance této třídy smí být vztvářena jen jednou.");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
