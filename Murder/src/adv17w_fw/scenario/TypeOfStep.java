/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.scenario;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;



/*******************************************************************************
 * {@code TypeOfStep} enum type instances represent
 * the possible types of scenario steps.
 * The knowledge of these types allows to check the correctness
 * of data specified for particular steps.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public enum TypeOfStep
{
//\CE== VALUES OF THE ENUMERATION TYPE =========================================

    /** The type of step, which is not determined for insertion into certain
     *  scenario, but for supplement of the error message and for auxiliary
     *  actions. */                               tsNOT_SET  (6,-1, null),

//Type of steps that have to be in the happy scenario. */
    /** Start step with the empty name.         */tsSTART    (0,-1, null),
    /** The player moves into neighboring place.*/tsMOVE     (0, 1, null),
    /** Successful taking of item from place.   */tsTAKE     (0, 1, null),
    /** Successful putting down of item in bag. */tsPUT_DOWN (0, 1, null),

//The following types of steps may not crash the game, i.e. the game should
//correctly react to them => they have to be tested in the mistake scenario

    /** Immediate finishing the game.           */tsEND      (1, 0, null),
    /** Help.                                   */tsHELP     (1, 0, null),

//Problems with correct command entering
    /** Starting command is not empty string.   */tsNOT_START(2,-1, null),
    /** Empty string not starting the game.     */tsEMPTY    (2,-1, tsSTART),
    /** Unknown action.                         */tsUNKNOWN  (2,-1, null),

//Calling some of the basic one-parametric action without the argument
    /** I do not know where to move.     */tsMOVE_WA         (2, 0, tsMOVE),
    /** I do not know what to take.      */tsTAKE_WA         (2, 0, tsTAKE),
    /** I do not know what to put down.  */tsPUT_DOWN_WA     (2, 0, tsPUT_DOWN),

//Problems with changing the place
    /** The target place is not a neighbor.  */tsBAD_NEIGHBOR(2, 1, tsMOVE),

//Problems with taking and putting down the item
    /** The item is not in the current place.*/tsBAD_ITEM    (2, 1, tsTAKE),
    /** The item is not takeable.            */tsUNMOVABLE   (2, 1, tsTAKE),
    /** The item cannot fit into the bag.    */tsBAG_FULL    (2, 1, tsTAKE),
    /** The item is not in the bag.          */tsNOT_IN_BAG  (2, 1, tsPUT_DOWN),

//Mandatory types of additional steps
    /** Nullary (parameter-less) action not belonging to the mandatory ones;
     *  it only changes certain internal state. */tsNON_STANDARD0(3, 0, null),

    /** Unary (one-parametric) action not belonging to the mandatory ones;
     *  it only changes certain internal state. */tsNON_STANDARD1(3, 1, null),

    /** Binary (two-parametric) action not belonging to the mandatory ones;
     *  it only changes certain internal state. */tsNON_STANDARD2(3, 2, null),

    /** Ternary (three-parametric) action not belonging to the mandatory ones;
     *  it only changes certain internal state. */tsNON_STANDARD3(3, 3, null),

//Wrogly entered commands for facultative additional actions. */
    /** Unary action without arg.          */tsNS1_0Args
                                             (4, 0, tsNON_STANDARD1),
    /** Too few args for binary action.    */tsNS2_01Args
                                             (4, 1, tsNON_STANDARD2),
    /** Too few args for ternary action.   */tsNS3_012Args
                                             (4, 2, tsNON_STANDARD3),
    /** Unary action with wrong arg.       */tsNS1_WRONG_ARG
                                             (4, 1, tsNON_STANDARD1),
    /** Binary action with wrong 1st arg.  */tsNS2_WRONG_1stARG
                                             (4, 2, tsNON_STANDARD2),
    /** Binary action with wrong 2nd arg.  */tsNS2_WRONG_2ndARG
                                             (4, 2, tsNON_STANDARD2),
    /** Ternary action with wrong 1st arg. */tsNS3_WRONG_1stARG
                                             (4, 3, tsNON_STANDARD3),
    /** Ternary action with wrong 2nd arg. */tsNS3_WRONG_2ntARG
                                             (4, 3, tsNON_STANDARD3),
    /** Ternary action with wrong 3rd arg. */tsNS3_WRONG_3rdARG
                                             (4, 3, tsNON_STANDARD3),


    /** The state does not describe an action, it is a part of a dialogue
     *  or some similar action.                 */tsDIALOG       (5,-1, null),

//Type of steps not usable for testing the reaction to the command
    /** Steps of this type are meant for game demonstration and therefore
     *  it does not contain any information about the game state and thus
     *  it cannot be used as the testing one.    */tsDEMO(6,-1, null),
     ;



//##############################################################################
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Basic types of the mandatory actions. */
    public static final Set<TypeOfStep> BASIC_ACTIONS;

    /** Step types, which have to be incorporated into happy scenario. */
    public static final Set<TypeOfStep> REGULAR_ACTIONS;

    /** Step types, which have to be incorporated into mistake scenario.
     *  The help + immediate end belong here, various types of the
     * incorrectly entered commands that may not crash the game.
     * The game should correctly react to all these type of steps
     * and the mistake scenario tests it. */
    public static final Set<TypeOfStep> MISTAKE_ACTIONS;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================

    static {
        Set<TypeOfStep> basic   = EnumSet.noneOf(TypeOfStep.class);
        Set<TypeOfStep> regular = EnumSet.noneOf(TypeOfStep.class);
        Set<TypeOfStep> mistake = EnumSet.noneOf(TypeOfStep.class);
        for (TypeOfStep stepType : TypeOfStep.values()) {
            if (null != stepType.SUBTYPE) {
                switch (stepType.SUBTYPE)
                {
                    case stCORRECT:
                        basic  .add(stepType);
                        regular.add(stepType);
                        break;
                    case stHELPSTOP:
                        basic  .add(stepType);
                        mistake.add(stepType);
                        break;
                    case stMISTAKE:
                        mistake.add(stepType);
                        break;
                    default:
                        break;
                }
            }
        }
        //Define all sets as immutable
        REGULAR_ACTIONS = Collections.unmodifiableSet(regular);
        MISTAKE_ACTIONS = Collections.unmodifiableSet(mistake);
        BASIC_ACTIONS   = Collections.unmodifiableSet(basic);
    }



//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Subtype of this type of step. */
    public final Subtype SUBTYPE;

    /** Required number of arguments (parameters). */
    public final int PARAM_COUNT;

    /** The group of types, which activate the same action. */
    public final TypeOfStep GROUP;


//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Defines the new type of step according to the argument values
     * it assigns the subtype and the required type of parameters.
     *
     * @param subtype    Subtype ordinal number (to have the code shorter)
     * @param paramCount Required number of parameters/arguments. <br>
     *                   The value {@code -1} is used with types of commands,
     *                   where it is not usable to study this number.
     * @param group      The leader of the group of equivalent types
     */
    private TypeOfStep(int subtype, int paramCount, TypeOfStep group)
    {
        this.SUBTYPE     = Subtype.values()[subtype];
        this.PARAM_COUNT = paramCount;
        this.GROUP       = (group == null)  ?  this  :  group;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the subtype of this type.
     *
     * @return Subtype of this type
     */
    public Subtype getSubtype()
    {
        return SUBTYPE;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

    /***************************************************************************
     * The scenario step types are selected into several subtypes.
     * This nested enum type defines them.
     */
    public enum Subtype
    {
        /** Correctly entered mandatory command from HAPPY. 0 */ stCORRECT,
        /** Correctly entered HELP or STOP.                 1 */ stHELPSTOP,
        /** Compulsorily tested wrong command.              2 */ stMISTAKE,
        /** Facultative action.                             3 */ stNONSTANDARD,
        /** Compulsorily tested wrong facultative commands. 4 */ stNSMISTAKE,
        /** Part of the dialogue.                           5 */ stDIALOG,
        /** Demo-step without accompanying information
          * which must not be used in testing scenarios.    6 */ stDEMO,
        /** Step created for auxiliary activities.          7 */ stUNDEFINED;
    }

}
