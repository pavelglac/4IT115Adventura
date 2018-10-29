/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.scenario;

import adv17w_fw.game_txt.INamed;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static adv17w_fw.utilities.FormatStrings.*;



/*******************************************************************************
 * The {@code Scenario} class instances represent scenarios
 * according to which we can play the game for which they are determined.
 * To be able to distinguish individual scenarios, each scenario has the name
 * that unambiguously identifies it. In addition each scenario has a type,
 * which allows to specify possible usage of it.
 * Each scenario is managed by a scenario manager which is known
 * to the scenario.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public class Scenario implements INamed, Iterable<ScenarioStep>
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================


    /***************************************************************************
     * According to the given array with sequence of command
     * it creates an array of corresponding demonstration scenario steps.
     * These steps do not allow testing, they serve only for
     * the simulation and demonstration of the possible course of the game.
     *
     * @param  commands Array with a sequence of the entered commands
     * @return Array of demonstration scenario steps
     */
    private static ScenarioStep[] prepareSteps(String[] commands)
    {
        ScenarioStep.clearIndex();
        ScenarioStep[] steps = new ScenarioStep[commands.length+1];
        ScenarioStep step = new ScenarioStep("");
        int i = 0;
        for(;;) {
            steps[i] = step;
            if (i >= commands.length) { break; }     //==========>
            step = new ScenarioStep(commands[i]);
            i++;
        }
        steps[i] = new ScenarioStep(step);
        return steps;
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Name identifying the scenario. */
    private final String name;

    /** Type of scenario. */
    private final TypeOfScenario type;

    /** Scenario manager managing this scenario. */
    private final AScenarioManager scenarioManager;

    /** List of individual steps. */
    private final List<ScenarioStep> steps;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Scenario signature - it is created on demand. */
    private String toStringValue;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * For the given scenario manager it creates a new scenario
     * as a copy of given scenario.
     *
     * @param scenarioManager  Destination scenario manager
     * @param scenario         Original scenario to be copied
     */
    public Scenario(AScenarioManager scenarioManager, Scenario scenario)
    {
        this(scenario.name, scenario.type, scenarioManager,
             scenario.steps.toArray(new ScenarioStep[scenario.steps.size()]));
    }


    /***************************************************************************
     * For the given scenario manager it creates a new scenario of given name.
     * Verifies, if the given scenario step meets the requirements
     * at least formally.
     *
     * @param name  Scenario name - it has to be a non-empty string
     *              containing neither leading nor trailing whiteplaces
     * @param type  Type of scenario
     * @param scenarioManager Destination scenario manager (the manager
     *              for which the scenario is created and which will manage it)
     * @param steps Individual scenario steps; the command in the zeroth item
     *              has to be an empty string with exclusion the case
     *              when it is a command of {@link TypeOfStep#tsNOT_START} type
     */
    public Scenario(String name, TypeOfScenario type,
                    AScenarioManager scenarioManager, ScenarioStep... steps)
    {
        this.name             = name.trim();
        this.type             = type;
        this.scenarioManager  = scenarioManager;

        ScenarioStep[] closedSteps = new ScenarioStep[steps.length];
        int iLast = steps.length-1;
        System.arraycopy(steps, 0, closedSteps, 0, iLast);
        closedSteps[iLast] = new ScenarioStep(steps[iLast], true);
        List<ScenarioStep> resulSteps  = Arrays.asList(closedSteps);
        verifyArguments(resulSteps);
        this.steps = Collections.unmodifiableList(resulSteps);
    }


    /***************************************************************************
     * Creates a new demonstration scenario with given name.
     * This scenario steps will remember only the commands and therefore
     * the scenario will not be usable as the testing one.
     * <p>
     * Arguments have to meet the requirements mentioned at the
     * four-parametric version
     *
     * @param name             Unambiguous scenario name
     * @param scenarioManager  Destination scenario manager (the manager
     *                         for which the scenario is created)
     * @param commands         Sequence of commands constituting this scenario
     */
    public Scenario(String name, AScenarioManager scenarioManager,
                    String... commands)
    {
        this(name, TypeOfScenario.scDEMO, scenarioManager,
             prepareSteps(commands));
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the scenario name.
     *
     * @return Scenario name
     */
    @Override
    public String getName()
    {
        return name;
    }


    /***************************************************************************
     * Returns the type of this scenario.
     *
     * @return  Type of this scenario
     */
    public TypeOfScenario getType()
    {
        return type;
    }


    /***************************************************************************
     * Returns the scenario manager which manages this scenario.
     *
     * @return  Scenario manager managing this scenario
     */
    public AScenarioManager getManager()
    {
        return scenarioManager;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Returns an iterator over the steps in this scenario.
     *
     * @return  Iterator over the steps in this scenario
     */
    @Override
    public Iterator<ScenarioStep> iterator()
    {
        return steps.iterator();
    }


    /***************************************************************************
     * Writes the simulated game course running according this scenario
     * on the standard output.
     */
    public void simulate()
    {
        String managerAndAuthor =
                  "\nScenario manager " + scenarioManager.getClass().getName()
                + "\nAuthor: " + scenarioManager.getAuthorName() + " — "
                               + scenarioManager.getAuthorID();

        if (type == TypeOfScenario.scDEMO) {
            throw new IllegalStateException(
                "\nDemonstration scenario does not allow to play simulation");
        }
        System.out.println(N_HASHES_N
            + "Simulation according the scenario: " + name
            + managerAndAuthor + N_DOUBLELINE_N);
        stream().forEach(
            (step) -> {
                String s = "Command: " + step.command
                         + N_LINE_N + step.message + N_DOUBLELINE_N;
                System.out.println(s);
            });
        System.out.println(N_DOUBLELINE_N
            + "End of simulation accoring to the scenario: " + name
            + managerAndAuthor + N_HASHES_N);
    }


    /***************************************************************************
     * Returns a sequential {@link Stream} of steps
     * with this scenario as its source.
     *
     * @return {@link Stream} of steps
     */
    public Stream<ScenarioStep> stream()
    {
        return steps.stream();
    }


    /***************************************************************************
     * Returns the textual signature of this scenario containing
     * the scenario name, its type and its manager's signature.
     *
     * @return Textual signature of this scenario
     */
    @Override
    public String toString()
    {
        if (toStringValue == null) {
            AScenarioManager asm = getManager();
            toStringValue = "Scenario(Name="    + getName()
                          + ", Type="           + getType()
                          + ", Manager class=" + asm.getClass().getName()
                          + ")";
        }
        return toStringValue;
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Checks if the given argument values formally meet the requirements.
     *
     * @param name  Name of scenario must not be an empty string and
     *              must contain neither leading nor trailing places.
     * @param type  Type of scenario
     * @param scenarioManager Destination scenario manager (the manager
     *              for which the scenario is created and which will manage it)
     * @param steps Individual scenario steps; the command in the zeroth item
     *              has to be an empty string with exclusion the case
     *              when it is a command of {@link TypeOfStep#tsNOT_START} type
     */
    private void verifyArguments(List<ScenarioStep> steps)
            throws IllegalArgumentException
    {
        int     STEPS;      //Počet kroků scénáře
        String  error;
        ERROR:
        {
            if (steps == null) {
                STEPS = 0;
                error = "List of scenario steps must not be null";
                break ERROR;
            }
            STEPS = steps.size();
            if (name == null) {
                error = "Scenario name must not be null";
                break ERROR;
            }
            if (! name.trim().equals(name)) {
                error = "Scenario name contains leading and/or trailing places";
                break ERROR;
            }
            if ("".equals(name)) {
                error = "Scenario name is an empty string";
                break ERROR;
            }
            if (type == null) {
                error = "Type of scenario has not been defined";
                break ERROR;
            }
            if (scenarioManager == null) {
                error = "Scenario manager has not been defined";
                break ERROR;
            }
            if (steps.isEmpty()) {
                error = "Scenario has no defined step";
                break ERROR;
            }
            if (type == TypeOfScenario.scDEMO) {
                //We do not check the demo steps
                return;
            }
            if ((error = testItems(steps)).length() > 0) {
                break ERROR;
            }
            if ((error = testStartSteps(steps)).length() > 0) {
                break ERROR;
            }
            error = null;
        }
        if ((error != null) && (error.length() > 0)) {
            throw new IllegalArgumentException(
                "\nThe revealed error: «" + error + "»"
              + "\nArgument values do not meet the contract."
              + "\n   Name: «" + name + "»"
              + "\n   Number of steps: " + (steps == null  ?  "NULL"  :  STEPS)
              + "\n   Starting command: «" + ((STEPS==0) || (steps == null)
                                           ? "NULL"
                                           : steps.get(0).command + "»"));
        }
    }


    /***************************************************************************
     * Checks if all members of all steps are formally correct, i.e.:
     * <ul>
     *   <li>array items do not contain {@code null},</li>
     *   <li>attributes of steps do not contain {@code null},</li>
     *   <li>attributes of steps with exclusion of {@code command }
     *       do not contain empty strings,</li>
     *   <li>items of {@code neighbors}, {@code items} and {@code bag} arrays
     *       do not contain empty strings.</li>
     * </ul>
     *
     * @param steps Scenario steps
     * @return The error message;
     *         if everything is OK, returns an empty string
     */
    private String testItems(List<ScenarioStep> steps)
    {
        Optional<String> obtained = //for (ScenarioStep step : steps) {
            steps.stream()
                 .map   (step -> testItem(step))
                 .filter(msg  -> ! msg.isEmpty())   //Zpráva o chybě
                 .findFirst();
        return obtained.orElse("");
    }


    /***************************************************************************
     * Checks that the given scenario step is formally correct, i.e.:
     * <ul>
     *   <li>its attributes do not contain {@code null},</li>
     *   <li>its attributes of steps with exclusion of {@code command }
     *       do not contain empty strings,</li>
     *   <li>items of {@code neighbors}, {@code items} and {@code bag} arrays
     *       do not contain empty strings.</li>
     * </ul>
     *
     * @param step Scenario step
     * @return The error message;
     *         if everything is OK, returns an empty string
     */
    private String testItem(ScenarioStep step)
    {
        if (step == null) {
            return "The next scenario step is {@code null}";
        }
        if ((step.bag        == null)  ||
            (step.command    == null)  ||
            (step.message    == null)  ||
            (step.neighbors  == null)  ||
            (step.items      == null)  ||
            (step.place       == null)  ||
            (step.typeOfStep == null)  )
        {
            return step.index
                     + ". scenario step contain a {@code null} values";
        }
        String  message;
        boolean correct =
            ((message = testSubItem("the bag content",
                                        step.bag      )).isEmpty())  &&
            ((message = testSubItem("the current place neighbors",
                                        step.neighbors)).isEmpty())  &&
            ((message = testSubItem("the current place items",
                                        step.items    )).isEmpty());
        if (! correct) {
            return step.index + ". scenario step - " + message;
        }
        return "";
    }


    /***************************************************************************
     * Checks that the members of the given array do not contain
     * {@code null} nor empty strings.
     *
     * @param step Testing scenario step
     * @return The error message;
     *         if everything is OK, returns an empty string
     */
    private String testSubItem(String description, String[] vector)
    {
        Optional<String> obtained = Arrays.stream(vector)
            .map(s ->
                {   //Převede položku na chybové hlášení
                    boolean ref;
                    if ((ref = (s == null))  ||  s.isEmpty())
                    {
                        return "vector defining " + description
                             + " contains " + (ref ? "null"
                                                   : "empty string");
                    }
                    return "";
                })
            .filter(s -> !s.isEmpty())
            .findFirst();
        return obtained.orElse("");
    }


    /***************************************************************************
     * Checks that the list starts with the step with empty command
     * which may be preceded by the step testing wrong starting of game.
     *
     * @param steps The tested list
     * @return The error message;
     *         if everything is OK, returns an empty string
     */
    private String testStartSteps(List<ScenarioStep> steps)
    {
        boolean started = false;
        for (ScenarioStep step : steps) {
            //Předstartovní příkazy
            if (started) {
                if ((step.typeOfStep == TypeOfStep.tsNOT_START)  ||
                    (step.typeOfStep == TypeOfStep.tsSTART))
                {
                    return "After game start it must not be entered the "
                         + "command of the type " + step.typeOfStep;
                }                                               //==========>
            }
            //Není odstartováno
            else if (step.typeOfStep == TypeOfStep.tsNOT_START) {
                if (step.command.isEmpty()) {
                    return "The command testing wrong start is an empty string";
                }                                               //==========>
                else {
                    continue;       //^^^^^^^^^^
                }
            }
            //Startovní příkaz
            else if (step.typeOfStep == TypeOfStep.tsSTART) {
                if (step.command.isEmpty()) {
                    started = true;
                    continue;       //^^^^^^^^^^
                }
                else {
                    return "The starting command is not an empty string";
                }                                               //==========>
            }
            //Špatný start
            else {
                return "The starting step type must be " + TypeOfStep.tsSTART;
            }                                                   //==========>
        }
        return "";
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
