/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.scenario;



/*******************************************************************************
 * {@code TypeOfScenario} instances represent various types of scenario.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public enum TypeOfScenario
{
//\CE== VALUES OF THE ENUMERATION TYPE =========================================

    /** Scenario defining the possible path to the goal
     *  and containing information for testing
     *  the game reaction to the entered commands.
     *  Each scenario manager has to offer it.
     *  This scenario has to fulfill a set of requirements including:
     *  <ul>
     *  <li>minimal number of steps,</li>
     *  <li>minimal number of visited places,</li>
     *  <li>minimal number of various type of actions,</li>
     *  <li>incorporation of basic mandatory actions:
     *  <ul><li>moving to the neighboring place,</li>
     *      <li>taking an item in the current place,</li>
     *      <li>putting an item down in the current place.</li>
     *  </ul>
     *  <li>successful game finishing.</li>
     *  </ul>
     *  The scenario manager has to offer exactly one scenario of this type.
     */
    scHAPPY,

    /** Scenario serving for testing the game reactions to the wrongly entered
     *  commands. It has to contain all types of steps with the
     *  {@code TypeOfStep.Subtype.stMISTAKE} subtype.
     *  Beside the steps testing the wrongly entered basic mandatory commands
     *  also the steps asking help and immediate game termination belongs here.
     *  The scenario manager has to offer exactly one scenario of this type.
     */
    scMISTAKES,

    /** Scenario serving for testing the game reactions to the wrongly entered
     *  non standard commands. It has to contain all types of steps with the
     *  {@code TypeOfStep.Subtype.stMISTAKE_NS} subtype.
     *  The scenario manager has to offer exactly one scenario of this type.
     */
    scMISTAKE_NS,

    /** General scenario allowing testing the game behavior
     *  and not comming under previous two types.
     *  To be testable, this scenario must not contain any step
     *  of the {@link TypeOfStep#tsDEMO} type.
     *  The scenario manager can offer any number of scenarios of this type.
     */
    scGENERAL,

    /** Scenario serving only for demonstration of the possible path.
     *  Scenarios of this type do not allow standard game testing.
     *  This scenario may contain steps of any type.
     *  The scenario manager can offer any number of scenarios of this type.
     */
    scDEMO;



//##############################################################################
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
     *
     */
    private TypeOfScenario()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
