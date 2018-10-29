/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.game_txt;

import adv17w_fw.scenario.Scenario;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;



/*******************************************************************************
 * {@code IScenarioManager} interface instances serve as
 * scenario managers that have to manage the scenarios of the associated game.
 * These scenarios should allow to test and demonstrate
 * the functionality of the associated game.
 * <ul>
 *   <li>The happy scenario</li><li>The mistake scenario</li></ul><p>
 * Individual managed scenarios have to differ by their names;
 * the names of the happy scenario and the mistake one
 * are firmly pre-determined and cannot be arbitrarily set.
 * </p><p>
 * Individual scenarios are iterable and "streamable" sequences of steps
 * specified by instances of the framework class
 * {@link adv17w_fw.scenario.ScenarioStep},
 * to which the designed game should associated.
 * </p>
 * Scenario manager is a singleton, that is responsible
 * for all scenarios concerning the game associated with it.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public interface IScenarioManager
         extends IAuthor, IGSMFactoryProduct, Iterable<Scenario>
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================

    /** Name of the basic successful scenario = happy scenario. */
    public static final String HAPPY_SCENARIO_NAME = "_HAPPY_";

    /** Name of the scenario for testing game reaction to typical
     *  user mistakes = mistake scenario. */
    public static final String MISTAKE_SCENARIO_NAME = "_MISTAKE_";



//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns collection with names of all managed scenarios.
     *
     * @return Collection with names of all managed scenarios
     */
    public Collection<String> getAllScenarioNames()
    ;


    /***************************************************************************
     * Returns scenario with the given name.
     * If there is no such scenario, it returns {@code null}.
     *
     * @param name Name of the requested scenario
     * @return The requested scenario or {@code null}
     */
    public Scenario getScenario(String name)
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================

    /***************************************************************************
     * The basic test verifying if the scenario manager meets all the
     * requirements.
     * The result will be printed on the standard output.
     */
    public void autoTest()
    ;


    /***************************************************************************
     * Verifies that the game successfully works according to both
     * mandatory scenarios, i.e. according to the happy one, once more
     * according to the happy one and then according to the mistake one.
     */
    public void testGame()
    ;


    /***************************************************************************
     * Verify the game by playing it according to the scenarios
     * with given names. These scenarios has to be the testing ones.
     * The particular name may be entered more than once.
     *
     * @param names Names of the used scenarios
     */
    public void testGameByScenarios(String... names)
    ;


    /***************************************************************************
     * Returns an iterator over the managed scenarios.
     * The iterator provides the scenarios sequentially in the order
     * in which they were added.
     *
     * @return Iterator over the managed scenarios
     */
    @Override
    public Iterator<Scenario> iterator()
    ;


    /***************************************************************************
     * Returns a stream of the managed scenarios.
     * The stream provides the scenarios sequentially in the order
     * in which they were added.
     *
     * @return Stream of the managed scenarios
     */
    public Stream<Scenario> stream()
    ;


//\DG== DEFAULT GETTERS AND SETTERS ============================================

    /***************************************************************************
     * Returns the happy scenario.
     *
     * @return Happy scenario
     */
    default Scenario getHappyScenario()
    {
        return getScenario(HAPPY_SCENARIO_NAME);
    }


    /***************************************************************************
     * Returns the mistake scenario.
     *
     * @return Mistake scenario
     */
    default Scenario getMistakeScenario()
    {
        return getScenario(MISTAKE_SCENARIO_NAME);
    }



//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
