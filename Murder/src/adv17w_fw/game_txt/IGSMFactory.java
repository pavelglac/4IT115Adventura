/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.game_txt;

import adv17w_fw.scenario.AScenarioManager;
import adv17w_fw.utilities.UncompletedMethodException;



/*******************************************************************************
 * {@code IGSMFactory } interface instances represent
 * the factory objects which are able to deliver the game instance,
 * an instance of scenario manager of this game
 * and an instance of the text user interface.
 * As long as some of these objects are not yet fully defined,
 * the methods may throw the
 * {@link adv17w_fw.utilities.UncompletedMethodException}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public interface IGSMFactory
         extends IAuthor
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================

    /***************************************************************************
     * Returns the instance of the given factory class with a nullary
     * (=&nbsp;parameterless) constructor.
     *
     * @param <T>           Factory class type
     * @param factoryClass  Factory class class-object
     * @return Factory object
     * @throws IllegalArgumentException Creation of the instance failed
     */
    public static <T extends IGSMFactory>
           T getInstanceOfFactory(Class<T> factoryClass)
    {
        T result;
        try {
            result = factoryClass.newInstance();
        }
        catch (InstantiationException | IllegalAccessException ex) {
            throw new IllegalArgumentException(
                "\nFailed creation of instance of the " + factoryClass, ex);
        }
        return result;
    }



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
//    @Override
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
//    @Override
    public default IGame getGame()
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
//    @Override
    public default IUI getUI()
    {
        throw new UncompletedMethodException();
    }



//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
