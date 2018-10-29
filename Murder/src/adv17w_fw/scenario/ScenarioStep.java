/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.scenario;

import adv17w_fw.game_txt.IGame;
import adv17w_fw.utilities.Util;

import java.util.Arrays;
import java.util.Objects;

import static adv17w_fw.utilities.ConditionalPrinter.*;
import static adv17w_fw.utilities.FormatStrings.*;
import static adv17w_fw.utilities.Util.*;



/*******************************************************************************
 * Instances of the {@code ScenarioStep} class serve as crates
 * for saving information about individual commands and expected states
 * after execution of these commands.
 * <p>
 * Scenario steps contain information serving for verification of the
 * correct game reaction to the command entered in the appropriate step
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public class ScenarioStep
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Scenario step message, which will be compared with the message
     *  obtained from the game as an answer to the given command. */
    public static final String IGNORED_MESSAGE = "¤¤¤ TEST ¤¤¤";

    /** Mock pre-start step used by certain tests. */
    public static final ScenarioStep NOT_START_STEP;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Index of the last created scenario step.
     *  The index of the next step is mostly derived from it. */
    private static int lastIndex = 0;



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================

    static {
        String[] EMPTY_STRING_ARRAY = {};
        String   TEXT = "NOT_START_STEP";
        NOT_START_STEP = new ScenarioStep(-1, TypeOfStep.tsNOT_START,
                         TEXT, TEXT, TEXT,
                         EMPTY_STRING_ARR, EMPTY_STRING_ARR, EMPTY_STRING_ARR);
    }



//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Returns the value to which the index of the next step will follow.
     *
     * @return The required value
     */
    public static int getLastIndex()
    {
        return lastIndex;
    }


    /***************************************************************************
     * Sets the index of the next step and returns the index
     * of the last created step.
     *
     * @param next Index of the next step
     * @return Index of the last created step
     */
    public static int setIndex(int next)
    {
        int   ret = lastIndex;
        lastIndex = next - 1;
        return ret;
    }


    /***************************************************************************
     * Resets the counted index of the scenario step =&gt;
     * if nothing will change, the next created step will have the index 1.
     */
    public static void clearIndex()
    {
        lastIndex = 0;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================

    /***************************************************************************
     * Returns the textual signature of the step with given arguments.
     *
     * @param index      Index of the step - it should identify the order
     *                   of the given step in its scenario
     * @param typeOfStep The type of the created step
     * @param prologue   Prolog of the signature
     * @param command    The command of this step
     * @param message    The message returning as the answer to the command
     * @param place      The current place name after the command execution
     * @param neighbors  Name of the neighboring places
     * @param items      Names of the items in the current place
     * @param bag        Names of the items in the bag
     * @return  Textual signature of the step with given arguments
     */
    private static String toString(int index, TypeOfStep typeOfStep,
            String   prologue,  String command, String message,
            String   place,
            String[] neighbors,  String[] items,  String[] bag)
    {
        return  N_LINE_N                + prologue +
                "\nStep No.:          " + index +
                "\nType of the step:  " + typeOfStep +
                "\nCommand:           " + command +
                "\nCurrent place:     " + place +
                "\nExits (neighbors): " + objArr2String(neighbors) +
                "\nItems:             " + objArr2String(items ) +
                "\nBag:               " + objArr2String(bag     ) +
                N_LINE +
                "\nMessage:"     +
                N_CIRCUMFLEXES_N +
                message   +
                N_LINE;
    }


    /***************************************************************************
     * Returns double-character string representing one or two digit number.
     * If the number is less then 10, it adds a place before the digit.
     * It does not check, if the number is negative or less than 100.
     *
     * @param  i Number converted to string
     * @return The required string
     */
    private static String twoDigits(int i)
    {
        return ((i > 9) ? "" : " ") + i;
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Index of the scenario step, which should determine its order
     *  inside the scenario. */
    public final int index;

    /** Type of the scenario step. */
    public final TypeOfStep typeOfStep;

    /** The entered command. */
    public final String command;

    /** The message returned as the answer to the command.
     *  If it is {@code null}, the check is not performed. */
    public final String message;

    /** Current place after executing the command. */
    public final String place;

    /** Names of the neighboring places of the current place. */
    public final String[] neighbors;

    /** Names of items in the current place. */
    public final String[] items;

    /** Names of items in the bag after command execution. */
    public final String[] bag;

    /** Flag indicating the last step in the scenario. */
    public final boolean theLast;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** The reason of the exception thrown by creating of the step. */
    private String errorMsg;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates a step based on the current state of the given game;
     * its index will be greater by one as index of the previously created step,
     * more precisely as the last remembered index.
     *
     * @param command  The entered command
     * @param message  Message returned as the answer to the command
     * @param game     Game, from which state the step is creating
     *                 and which state it therefore describes
     */
    public ScenarioStep(String command,  String message,  IGame game)
    {
        this(++lastIndex, TypeOfStep.tsNOT_SET, command, message,
             game.getWorld().getCurrentPlace().getName(),
             Util.colINamed2StringArr(
                     game.getWorld().getCurrentPlace().getNeighbors()),
             Util.colINamed2StringArr(
                     game.getWorld().getCurrentPlace().getItems()),
             Util.colINamed2StringArr(game.getBag().getItems()));
    }


    /***************************************************************************
     * Creates a step allowing test of the game functionality;
     * its index will be greater by one as of the previously created step,
     * more precisely as the last remembered index.
     *
     * @param typeOfStep The type of the created step
     * @param command    The command of this step
     * @param message    The message returning as the answer to the command
     * @param place      The current place name after the command execution
     * @param neighbors  Name of the neighboring places
     * @param items      Names of the items in the current place
     * @param bag        Names of the items in the bag
     */
    public ScenarioStep(TypeOfStep typeOfStep, String command, String message,
                        String place,
                        String[] neighbors,  String[] items,  String[] bag)
    {
        this(++lastIndex, typeOfStep,
             command, message, place, neighbors, items, bag);
    }


    /***************************************************************************
     * Creates a step allowing test of the game functionality;
     * its index will be greater by one as index of the previously created step,
     * more precisely as the last remembered index.
     *
     * @param typeOfStep The type of the created step
     * @param command    The command of this step
     * @param message    The message returning as the answer to the command
     * @param place      The current place name after the command execution
     * @param neighbors  Name of the neighboring places
     * @param items      Names of the items in the current place
     * @param bag        Names of the items in the bag
     * @param theLast    Flag indicating the last step in the scenario
     */
    public ScenarioStep(TypeOfStep typeOfStep, String command, String message,
                        String   place,
                        String[] neighbors,  String[] items,  String[] bag,
                        boolean  theLast)
    {
        this(++lastIndex, typeOfStep,
             command, message, place, neighbors, items, bag, theLast);
    }


    /***************************************************************************
     * Creates a step allowing test of the game functionality;
     * this step will have the given index which will be followed
     * by the subsequently created steps.
     *
     * @param index      Index of the scenario step, which should determine
     *                   its order inside the scenario
     * @param typeOfStep The type of the created step
     * @param command    The command of this step
     * @param message    The message returning as the answer to the command
     * @param place      The current place name after the command execution
     * @param neighbors  Name of the neighboring places
     * @param items      Names of the items in the current place
     * @param bag        Names of the items in the bag
     */
    public ScenarioStep(int     index,      TypeOfStep typeOfStep,
                       String   command,    String   message,  String   place,
                       String[] neighbors,  String[] items,  String[] bag)
    {
        this(index, typeOfStep, command, message, place, neighbors, items,
             bag,   false);
    }


    /***************************************************************************
     * Creates a next step identical with the given one including the index.
     *
     * @param step The copied step
     */
    public ScenarioStep(ScenarioStep step)
    {
        this(step.index, step);
    }


    /***************************************************************************
     * Creates a next step identical with the given one excluding the index
     * which will be set to the given value.
     *
     * @param index Index of the scenario step, which should determine
     *              its order inside the scenario
     * @param step  The copied step
     */
    public ScenarioStep(int index, ScenarioStep step)
    {
        this(index, step, step.theLast);
    }


    /***************************************************************************
     * Creates a next step identical with the given one including the index
     * but excluding the {@link #theLast} attribute which will be set
     * to the given value.
     *
     * @param step    The copied step
     * @param theLast The set value of the {@link #theLast} attribute
     */
    public ScenarioStep(ScenarioStep step, boolean theLast)
    {
        this(step.index, step, theLast);
    }


    /***************************************************************************
     * Creates a next step identical with the given one
     * but excluding the index and the {@link #theLast} attribute
     * which will be set to the given values.
     *
     * @param index   Index of the scenario step, which should determine
     *                its order inside the scenario
     * @param step    The copied step
     * @param theLast The set value of the {@link #theLast} attribute
     */
    public ScenarioStep(int index, ScenarioStep step, boolean theLast)
    {
        this(index, step.typeOfStep, step.command, step.message,
             step.place, step.neighbors,  step.items, step.bag, theLast);
    }


    /***************************************************************************
     * Creates a step allowing test of the game functionality;
     * this step will have the given index which will be followed
     * by the subsequently created steps.
     *
     * @param index      Index of the scenario step, which should determine
     *                   its order inside the scenario
     * @param typeOfStep The type of the created step
     * @param command    The command of this step
     * @param message    The message returning as the answer to the command
     * @param place      The current place name after the command execution
     * @param neighbors  Name of the neighboring places
     * @param items      Names of the items in the current place
     * @param bag        Names of the items in the bag
     * @param theLast    The set value of the {@link #theLast} attribute
     */
    public ScenarioStep(int     index,      TypeOfStep typeOfStep,
                       String   command,    String   message,  String   place,
                       String[] neighbors,  String[] items,  String[] bag,
                       boolean  theLast)
    {
        this.index     = lastIndex = index;
        this.command   = command;
        this.message   = message;
        this.typeOfStep= typeOfStep;
        this.theLast   = theLast;

        if (invalidCommand()         ||
            invalidMessage()         ||
            (typeOfStep != TypeOfStep.tsNOT_START)  &&
               (invalidName (place)  ||
                invalidNames(neighbors, items, bag)) )
        {
            String error =
                "By constructing a scenario step an exception was thrown " +
                "caused by:\n" + errorMsg +  "\nEntered:";
            String notification = toString(index, typeOfStep, error, command,
                                         message, place,
                                         neighbors, items, bag);
            prln(notification);
            throw new IllegalArgumentException(notification);
        }
        if (typeOfStep == TypeOfStep.tsNOT_START) {
            this.place      = "";
            this.neighbors =
            this.items     =
            this.bag       = new String[]{};
        }
        else {
            this.place      = place;
            Arrays.sort(this.neighbors = neighbors, CIC);
            Arrays.sort(this.items     = items,   CIC);
            Arrays.sort(this.bag       = bag,       CIC);
        }
    }


    /***************************************************************************
     * Create the next scenario step which will NOT serve for testing;
     * this step will have the given index which will be followed
     * by the subsequently created steps.
     *
     * @param index      Index of the scenario step, which should determine
     *                   its order inside the scenario
     * @param game       Game, from which state the step is creating
     *                   and which state it therefore describes
     * @param command    The command of this step
     * @param message    The message returning as the answer to the command
     */
    public ScenarioStep(int index, IGame game, String command, String message)
    {
        this(index, TypeOfStep.tsUNKNOWN, command, message,
             game.getWorld().getCurrentPlace().getName(),
             Util.colINamed2StringArr(
                     game.getWorld().getCurrentPlace().getNeighbors()),
             Util.colINamed2StringArr(
                     game.getWorld().getCurrentPlace().getItems()),
             Util.colINamed2StringArr(game.getBag().getItems()));
    }


    /***************************************************************************
     * Create the next scenario step which will NOT serve for testing
     * but only for demonstration;
     * its index will be greater by one as index of the previously created step,
     * more precisely as the last remembered index.
     *
     * @param command    The command of this step
     */
    public ScenarioStep(String command)
    {
        this(++lastIndex, command);
    }


    /***************************************************************************
     * Create the next scenario step which will NOT serve for testing
     * but only for demonstration;
     * this step will have the given index which will be followed
     * by the subsequently created steps.
     *
     * @param index      Index of the scenario step, which should determine
     *                   its order inside the scenario
     * @param command    The command of this step
     */
    public ScenarioStep(int index, String command)
    {
        if (command == null)  {
            throw new NullPointerException(
                      "\nAction has to be the valid non-empty string");
        }
        this.index     = lastIndex = index;
        this.command   = command;
        this.typeOfStep= TypeOfStep.tsDEMO;

        this.message   = null;
        this.place      = null;
        this.neighbors = null;
        this.items     = null;
        this.bag       = null;
        this.theLast   = false;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the index of this step.
     *
     * @return The index of this step
     */
    public int getIndex()
    {
        return index;
    }


    /***************************************************************************
     * Returns the command of this step.
     *
     * @return The command of this step
     */
    public String getCommand()
    {
        return command;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Returns the command of this step followed by newline
     * and the message returned from game as answer to the command.
     *
     * @return  The required text
     */
    public String commandAndMessage()
    {
        return "\n" + command + "\n" + message + "\n";
    }


    /***************************************************************************
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The object with which to compare
     * @return {@code true} if this object is equal to the {@code obj} argument;
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ScenarioStep other = (ScenarioStep) obj;
        if (this.index != other.index) {
            return false;
        }
        if (this.typeOfStep != other.typeOfStep) {
            return false;
        }
        if (!Objects.equals(this.command, other.command)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.place, other.place)) {
            return false;
        }
        if (!Arrays.deepEquals(this.neighbors, other.neighbors)) {
            return false;
        }
        if (!Arrays.deepEquals(this.items, other.items)) {
            return false;
        }
        if (!Arrays.deepEquals(this.bag, other.bag)) {
            return false;
        }
        if (this.theLast != other.theLast) {
            return false;
        }
        return true;
    }


    /***************************************************************************
     * Returns a hash code value for the step. .
     *
     * @return A hash code value for this step
     */
    @Override
    public int hashCode()
    {
        int hash =
            7;
        hash = 97 * hash + this.index;
        hash = 97 * hash + Objects.hashCode(this.typeOfStep);
        hash = 97 * hash + Objects.hashCode(this.command);
        hash = 97 * hash + Objects.hashCode(this.message);
        hash = 97 * hash + Objects.hashCode(this.place);
        hash = 97 * hash + Arrays.deepHashCode(this.neighbors);
        hash = 97 * hash + Arrays.deepHashCode(this.items);
        hash = 97 * hash + Arrays.deepHashCode(this.bag);
        hash = 97 * hash + (this.theLast ? 1 : 0);
        return hash;
    }


    /***************************************************************************
     * The detailed textual signature of the step.
     * If the {@link #message} attribute contains {@code null},
     * the text with only the index and the command will be returned.
     *
     * @return Textual signature of the step
     */
    @Override
    public String toString()
    {
        String ret =
        "\nStep No. " + index + ":  " + A_ + command   + _Z +
            (
            toString(index, typeOfStep, "Expected state after executed action:",
                            command, message, place, neighbors, items, bag)
            ) +
            "\n";
        return ret;
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Verify, if the given command is correct and if not,
     * it saves the appropriate error message.
     * The statement should fulfill the following rules:
     * <ul>
     *    <li>it must not be ({@code null}),</li>
     *    <li>if it is not the starting step (including the mistaken),
     *        it must not be empty,</li>
     *    <li>it must not have more than 3 arguments,</li>
     *    <li>it must not be longer than 80 characters.</li>
     * </ul>
     *
     * @return {@code true} reports error (incorrect command),
     *         {@code false} reports the correct command.
     */
    private boolean invalidCommand()
    {
        if (invalidNull("Command", command)) {
        }
        else if (((typeOfStep == TypeOfStep.tsSTART) ||
                  (typeOfStep == TypeOfStep.tsEMPTY) ) && !command.isEmpty())
        {
            errorMsg = "In the step of the " + typeOfStep
                     + " type the command should be an empty string";
        }
        else if ((typeOfStep != TypeOfStep.tsSTART)    &&
                 (typeOfStep != TypeOfStep.tsEMPTY)    &&
                 (typeOfStep != TypeOfStep.tsNOT_SET)  &&
                 command.trim().isEmpty())
        {
            errorMsg = "Non starting statement was entered as empty";
        }
        else if (command.length() > 80) {
            errorMsg = "Command is too long, it has more than 80 characters";
        }
        else {
            int arguments = command.split("\\s+").length - 1;
            if ((typeOfStep.PARAM_COUNT >= 0)        &&
                (arguments != typeOfStep.PARAM_COUNT))
            {
                errorMsg =   "The command has wrong number of arguments"
                         + "\n  Requested: " + typeOfStep.PARAM_COUNT
                         + "\n  Entered:  " + arguments;
            }
            else {
                //No error detected
                return false;
            }
        }
        return true;
    }


    /***************************************************************************
     * Finds out, if the given argument is an empty string
     * or string containing only the whiteplaces.
     * In such case it returns {@code true} and sets the error message.
     *
     * @param textTypeDecription Description of the type of the tested text
     * @param text               Tested text
     * @return {@code true} reports error, {@code false} reports correct text
     */
    private boolean invalidEmpty(String textTypeDecription, String text)
    {
        if ("".equals(text.trim())) {
            errorMsg = textTypeDecription + " was entered as the empty string";
            return true;
        }
        return false;
    }


    /***************************************************************************
     * Tests, if the given game answer is correct; if not, the error message
     * will be saved.
     * The tested text has to fulfill the following requirements:
     * <ul>
     *    <li>it must not be ({@code null}),</li>
     *    <li>it must not be empty,</li>
     *    <li>it must not be longer than 80 characters.</li>
     * </ul>
     *
     * @return {@code true} reports error, {@code false} reports correct message
     */
    private boolean invalidMessage()
    {
        String type = "Text of the answer to command:";
        if (!invalidNull (type, message)  &&
            !invalidEmpty(type, message))
        {
            String[] lines      = message.split("\\\n");
            boolean  shortLines = Arrays.stream(lines)
                                  .noneMatch(line -> (line.length() > 80));
            if (shortLines) {
                return false;
            }
            errorMsg = type + " contains a line longer than 80 characters";
        }
        return true;
    }


    /***************************************************************************
     * Find out, if the given argument may be a name,
     * i.e. it is a non empty string containing only one word.
     * By detecting an error it saves the error message.
     *
     * @param text Tested string
     * @return If the argument is an empty string or string containing
     *         more than one word, it returns {@code true},
     *         otherwise returns {@code false}.
     */
    private boolean invalidName(String text)
    {
        String type = "Name of the named item (place or item)";
        if (invalidNull(type, text)  ||  invalidEmpty(type, text)) {
            return true;
        }
        String[] lines = text.trim().split("\\s");
        if (lines.length != 1) {
            errorMsg = type + " is not created by one word: «" + text + "»";
            return true;
        }
        return false;
    }


    /***************************************************************************
     * Finds out, if all arguments are not empty strings
     * containing only one word.
     *
     * @param texts The tested strings
     * @return If some of the tested string violates the requirements,
     *         it returns {@code true}, otherwise returns {@code false}.
     */
    private boolean invalidNames(String... texts)
    {
        for (String text : texts) {
            if (invalidName(text)) {
                return true;
            }
        }
        return false;
    }


    /***************************************************************************
     * Finds out, if all arguments are not string arrays containing only
     * one word strings.
     *
     * @param texts The tested string arrays
     * @return If some of the tested arguments violates the requirements,
     *         it returns {@code true}, otherwise returns {@code false}.
     */
    private boolean invalidNames(String[]... sets)
    {
        for (String[] set : sets) {
            if (set == null) {
                errorMsg = "Name array is entered as {@code null}";
                return true;
            }
            for (String text : set) {
                if (invalidName(text)) {
                    return true;
                }
            }
        }
        return false;
    }


    /***************************************************************************
     * Finds out, if the given argument is {@code null}.
     *
     * @param textTypeDecription Description of the type of the tested text
     * @param text Tested string
     * @return If the argument is {@code null}, it returns {@code true},
     *         otherwise returns {@code false}.
     */
    private boolean invalidNull(String textTypeDecription, String text)
    {
        if (text == null) {
            errorMsg = textTypeDecription + " was entered as {@code null}";
            return true;
        }
        return false;
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
