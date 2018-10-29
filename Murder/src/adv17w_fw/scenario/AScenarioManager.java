/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.scenario;

import adv17w_fw.Framework;
import adv17w_fw.empty_classes.EmptyGame;
import adv17w_fw.game_txt.IGSMFactory;
import adv17w_fw.game_txt.IGame;
import adv17w_fw.game_txt.IScenarioManager;
import adv17w_fw.test_util.default_game.GSMFactoryTLit_Apartment;
import adv17w_fw.test_util.factory_test.FactoryTester;
import adv17w_fw.test_util.game_txt_test.BasicSetTester;
import adv17w_fw.test_util.game_txt_test.GivenSetTester;
import adv17w_fw.test_util.scenario_test.ScenarioManagerTester;
import adv17w_fw.test_util.scenario_test.SMSummary;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static adv17w_fw.utilities.ConditionalPrinter.*;
import static adv17w_fw.utilities.FormatStrings.*;



/*******************************************************************************
 * Instances of the {@code AScenarioManager} class serve as
 * scenario managers that have to manage the scenarios of the associated game.
 * These scenarios should allow to test and demonstrate
 * the functionality of the associated game.
 * <p>
 * Each manager has to offer:
 * <ul>
 *   <li>The <b>happy scenario</b> (the basic successful one)
 *     demonstrating certain successful path through the game possibilities
 *     leading to the game goal.
 *   </li>
 *   <li>The <b>mistake scenario</b>
 *     demonstrating the game reaction to the wrongly entered commands.
 *   </li>
 * </ul>
 * <p>
 * Individual managed scenarios have to differ by their names;
 * the names of the happy scenario and the mistake one
 * are firmly pre-determined and cannot be arbitrarily set.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public abstract class AScenarioManager
           implements IScenarioManager
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Crate with the "minimal sizes" of the happy scenario. */
    public static final Limits LIMITS = new Limits(
           10, //Minimal number of happy scenario steps
            6, //Minimal number of game places
            4, //Minimal number of visited places
            4  //Minimal number of non-standard actions
    );

    /**Half-empty game class used as a proxy until the right game class
     * will start to be developed. */
    private static final Class<EmptyGame> EGS = EmptyGame.class;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Vrátí tovární objekt poskytující klíčové objekty ukázkové hry.
     *
     * @return Požadovaný tovární objekt
     */
    public static IGSMFactory getDefaultFactory()
    {
        IGSMFactory factory = new GSMFactoryTLit_Apartment();
        return factory;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Creates a new manager derived from the manager obtained in argument.
     * The created manager overtakes from the obtained manager its data,
     * i.e. author name and ID, associated game class and all its scenarios.
     * This new manager is created as not sealed. It is therefore able
     * to add new scenarios.
     *
     * @param  extendedSM The extended scenario manager
     * @return The required scenario manager
     */
    public static AScenarioManager extend(AScenarioManager extendedSM)
    {
        ExtendingSM manager = new ExtendingSM(extendedSM);
        return manager;
    }



//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Factory class, the instances of which are factory objects providing
     *  the instance of scenario manager and the game,
     *  the scenario of which the manager manages. */
    private final Class<? extends IGSMFactory> factoryClass;

    /** The factory object mediating the access to game key objects. */
    private final IGSMFactory factory;

    /** Map converting scenario name to appropriate scenarios. */
    private final Map<String, Scenario> name2scenario;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Flag indicating if it is able to add next scenarios.
     *  {@code true} indicates that the scenario container is sealed
     *  and no other scenario can be added. */
    private boolean sealed;

    /** The initial step of the testable scenarios. */
    private ScenarioStep START_STEP;

    /** Name of the scenario with remembered starting step. */
    private String START_NAME;

    /** The associated game (obtained from {@link #factoryClass})
     *  which will be tested by scenarios of this manager. */
    private IGame game;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates the superclass sub-object of the new scenario managing
     * all scenarios of the game obtainable by the given factory class.
     * <p>
     * The created manager is not yet fully functional.
     * It needs to add all managed scenarios and seal the addition for it.
     *
     * @param factoryClass  Class-object of the factory class, instance of
                   ´        which mediates access to application key objects
     */
    protected AScenarioManager(Class<? extends IGSMFactory> factoryClass)
    {
        if (factoryClass == null) {
            throw new IllegalArgumentException(
                "\nArgument with factory class class-object must not be null");
        }
        this.factoryClass  = factoryClass;
        this.factory       = IGSMFactory.getInstanceOfFactory(factoryClass);
        this.name2scenario = new LinkedHashMap<>();
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which is also the mother class of this instance.
     *
     * @return The class-object of the factory class
     */
    @Override
    public Class<? extends IGSMFactory> getFactoryClass()
    {
        return factoryClass;
    }


    /***************************************************************************
     * Returns collection of all managed scenarios.
     *
     * @return Collection of all managed scenarios
     */
    @Override
    public final Collection<String> getAllScenarioNames()
    {
        verifySealed();
        return Collections.unmodifiableCollection(name2scenario.keySet());
    }


    /***************************************************************************
     * Returns scenario with the given name.
     * If there is no such scenario, it returns {@code null}.
     *
     * @param name Name of the requested scenario
     * @return The requested scenario or {@code null}
     */
    @Override
    public final Scenario getScenario(String name)
    {
        verifySealed();
        Scenario result = name2scenario.get(name);
        if (result == null) {
            throw new IllegalArgumentException(
                    "\nScénář s názvem «" + name + "» nemám ve správě");
        }
        return result;
    }


    /***************************************************************************
     * Returns the start step of the testable scenarios.
     * Its message should shortly describe the subject of the game.
     *
     * @return The start step of the testable scenarios
     */
    public final ScenarioStep getStartStep()
    {
        return START_STEP;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Returns an iterator over the managed scenarios.
     * The iterator provides the scenarios sequentially in the order
     * in which they were added.
     *
     * @return Iterator over the managed scenarios
     */
    @Override
    public final Iterator<Scenario> iterator()
    {
        verifySealed();
        return name2scenario.values().iterator();
    }


    /***************************************************************************
     * Returns a stream of the managed scenarios.
     * The stream provides the scenarios sequentially in the order
     * in which they were added.
     *
     * @return Stream of the managed scenarios
     */
    @Override
    public final Stream<Scenario> stream()
    {
        verifySealed();
        return name2scenario.values().stream();
    }


    /***************************************************************************
     * Creates a new scenario with the given name and containing the given
     * step sequence. Add this scenario to the managed ones.
     * Besides that it checks fulfilling of the following requirements:
     * <ul>
     *   <li>The collection of scenarios is not yet sealed and therefore
     *       a new scenario can be added.</li>
     *   <li>The first added scenario has to be the happy one
     *       (the scenario of the {@link TypeOfScenario#scHAPPY} type).
     *       The obtained name is ignored and the name from the
     *       {@link #HAPPY_SCENARIO_NAME} constant is assigned to it.</li>
     *   <li>The second added scenario has to be the mistake one
     *       (the scenario of the {@link TypeOfScenario#scMISTAKES} type).
     *       The obtained name is ignored and the name from the
     *       {@link #MISTAKE_SCENARIO_NAME} constant is assigned to it.</li>
     *   <li>The name of each further added scenario has to differ from the
     *       names of the previously added ones.</li>
     *   <li>Each added scenario has to start with the "empty command",
     *       which is the command starting the game. After processing this
     *       starting command the game comes in its initial state.</li>
     * </ul>
     *
     * @param name  The name of the added scenario (in the first and the second
     *              one the names are ignored)
     * @param type  Type of the added scenario (the requirements to the first
     *              and the second ones are described above)
     * @param steps The steps of the created and added scenario
     * @throws IllegalStateException
     *         The scenario container is sealed and the scenario addition
     *         is therefore closed
     * @throws IllegalArgumentException
     *         If certain from the other requirements is not fulfilled
     */
    protected final void addScenario(String name, TypeOfScenario type,
                                     ScenarioStep... steps)
    {
        Scenario scenario = new Scenario(name, type, this, steps);
        addScenario(scenario);
    }


    /***************************************************************************
     * Creates a new scenario with the given name and containing the
     * step sequence with the given commands.
     * Add this scenario to the managed ones.
     * The scenario obtains the {@link TypeOfScenario#scDEMO} type.
     * The rules described in the
     * {@link #addScenario(String, TypeOfScenario, ScenarioStep...)} method
     * comment implies that scenario added by this method has to be
     * at least the third.
     *
     * @param name      The name of the added scenario
     * @param commands  The commands entered for particular step
     * @throws IllegalStateException
     *         The scenario container is sealed and the scenario addition
     *         is therefore closed
     * @throws IllegalArgumentException
     *         If certain from the other requirements is not fulfilled
     */
    protected final void addScenario(String name, String... commands)
    {
        Scenario scenario = new Scenario(name, this, commands);
        addScenario(scenario);
    }


    /***************************************************************************
     * Add the given scenario among the managed ones. Before the addition
     * it checks, that the addition has not been closed and that the
     * scenario name is unique.
     *
     * @param scenario The added scenario
     * @exception IllegalArgumentException
     *            If certain from the requirements is not fulfilled
     */
    protected final void addScenario(Scenario scenario)
    {
        if (sealed) {
            throw new IllegalStateException(
                "\nPassing of new scenarios to management has been closed");
        }
        String name = scenario.getName();
        if ((name == null)  ||  "".equals(name)) {
            throw new IllegalArgumentException(
                    "\nScenario name has to be not empty string");
        }
        Scenario previous = name2scenario.put(name, scenario);
        if (previous != null) {
            throw new IllegalArgumentException(
                "\nScenario with the entered name already exists: " + name);
        }
        ScenarioStep startStep = scenario.stream()
                    .filter(s -> (s.typeOfStep == TypeOfStep.tsSTART))
                    .findFirst()
                    .get();
        if (START_STEP == null) {
            START_STEP = startStep;
            START_NAME = scenario.getName();
        }
        else if (! START_STEP.equals(startStep)) {
            throw new IllegalArgumentException(
                "\nInitial scenario steps " + START_NAME +
                " a " + scenario.getName() + " differ");
        }
    }

    /***************************************************************************
     * Seals the  container with the managed scenarios and thus it closes
     * the ability to add another scenario.
     * After this method execution the scenario manager is fully functional.
     */
    protected final void seal()
    {
        if ((null == name2scenario.get(HAPPY_SCENARIO_NAME  ))  ||
            (null == name2scenario.get(MISTAKE_SCENARIO_NAME))  )
        {
            throw new IllegalStateException(
                "\nMandatory scenarios "
                + HAPPY_SCENARIO_NAME + " and " + MISTAKE_SCENARIO_NAME
                + " were not defined");
        }
        sealed = true;
    }


    /***************************************************************************
     * The basic test verifying if the scenario manager meets all the
     * requirements.
     * The result will be printed on the standard output.
     */
    @Override
    public final void autoTest()
    {
        prepareSummary();
    }


    /***************************************************************************
     * Creates the crate with basic information about the requirements to the
     * associated game obtained from the scenario manager and its scenarios.
     *
     * @return Crate with basic information about the associated game
     */
    public final SMSummary prepareSummary()
    {
        prf("\nFramework version: %s\n%s\n", Framework.VERSION, LIMITS);

        FactoryTester factoryTester = new FactoryTester(getFactory());
        factoryTester.testForLevel(FactoryTester.SM_LEVEL);

        ScenarioManagerTester tester  = new ScenarioManagerTester(this);
        SMSummary             summary = tester.verify();
        String s = (summary.ok)
                 ? "PASSED successfully through autotest"
                 : "DID NOT PASS - at least one scenario does not COMPLY!";
        prf( N_HASHES_N + "Scenario manager %s" + N_HASHES_N, s);
        return summary;
    }


    /***************************************************************************
     * Verify that the game works according to both mandatory scenarios,
     * ie. twice according to the happy scenario
     * and then according to the mistake scenario.
     */
    @Override
    public final void testGame()
    {
        BasicSetTester tester = new BasicSetTester();
        tester.test(factory);
//        TGameTester tester = new TGameTester(
//                             IGSMFactory.getInstanceOfFactory(factoryClass),
//                             BasicSetTester::new);
//        tester.testGame();
    }


    /***************************************************************************
     * Test the game by playing it according the scenarios with given names.
     * All these scenarios have to be the testing ones.
     *
     * @param names Names of the testing scenarios
     */
    @Override
    public final void testGameByScenarios(String... names)
    {
        GivenSetTester tester = new GivenSetTester(names);
        tester.test(factory);
    }


    /***************************************************************************
     * Returns the textual signature of this scenario manager
     * where it will publish the author name and ID, game class name
     * and list of the managed scenario names.
     * This signature is split into several lines.
     *
     * @return The textual signature of this scenario manager
     */
    @Override
    public String toString()
    {
        return "\nScenario manager author: " + getAuthorID()   + " - " +
                                               getAuthorName() +
               "\nFactory class: "     + factory.getClass().getName()  +
               "\nManager class: "     + this   .getClass().getName()  +
               "\nManaged scenarios: " + getAllScenarioNames();
    }


    /***************************************************************************
     * Verify that the scenarios addition is not closed,
     * i.e. that the container of scenarios is not yet sealed
     * and further scenarios can be added.
     *
     * @throws IllegalStateException The addition is closed
     */
    protected final void verifyNotSealed() throws IllegalStateException
    {
        if (sealed) {
            throw new IllegalStateException(
                    "\nThe container of scenarios is sealed => "
                  + "next scenarios cannot be added");
        }
    }


    /***************************************************************************
     * Verify that the scenarios addition is closed,
     * i.e. that the container of scenarios is sealed
     * and the scenario manager is fully functional.
     *
     * @throws IllegalStateException The addition is still open
     */
    protected final void verifySealed()
    {
        if (! sealed) {
            throw new IllegalStateException(
                "\nThe scenario manager cannot be used yet -"
              + "\nthe addition of scenarios is still open"
              + "\n(in other words: the manager is not yet sealed)");
        }
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

    /***************************************************************************
     * Instances of the {@code ExtendingSM} class are the scenario managers
     * derived from the existing scenarios and they are added among them.
     * They are used in test methods which create them synthetically.
     *
     * @author  Rudolf PECINOVSKÝ
     * @version 2016-Winter
     */
    private static class ExtendingSM extends AScenarioManager
    {
        /** The original extended manager. */
        final AScenarioManager source;


        /***********************************************************************
         * Creates new scenario manager
         * which will be an extension of the given one
         * and which will have an extended set of the managed scenarios.
         *
         * @param source The original Scenario which should be extended
         */
        ExtendingSM(AScenarioManager source)
        {
            super(source.factory.getClass());
            this.source = source;
            //Auxiliary variable allowing calling the private parent method
            //If we use this instead of aux in the loop, syntax error appears
            AScenarioManager aux = this;
            for (Scenario scenario : source) {
                //The original scenario has its steps yet checked
                //thus I need not check it again and in addition I save
                //deriving the original arguments of scenario constructor
                aux.addScenario(scenario);
            }
        }


        /***********************************************************************
         * Returns the author ID.
         *
         * @return The author ID
         */
        @Override
        public String getAuthorID()
        {
            return source.getAuthorID();
        }


        /***********************************************************************
         * Returns the author name.
         *
         * @return The author name
         */
        @Override
        public String getAuthorName()
        {
            return source.getAuthorName();
        }


        /***********************************************************************
         * Returns the factory object able to provide
         * all application key objects.
         *
         * @return Factory object
         */
        @Override
        public Class<? extends IGSMFactory> getFactoryClass()
        {
            return source.getFactoryClass();
        }


        /***********************************************************************
         * Returns the original scenario manager extended by this one.
         *
         * @return Original scenario manage
         */
        public AScenarioManager getSource()
        {
            return source;
        }
    }
}
