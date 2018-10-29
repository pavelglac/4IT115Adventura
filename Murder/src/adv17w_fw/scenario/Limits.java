/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.scenario;



/*******************************************************************************
 * Instances of the {@code Limits} class serve as crates
 * containing the basic requirements to the happy scenario;
 * its "minimal sizes".
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public class Limits
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

    /** Minimal number of happy scenario steps. */
    public final int minSteps;

    /** Minimal number of game places. */
    public final int minPlaces;

    /** Minimal number of visited places. */
    public final int minVisited;

    /** Minimal number of non-standard actions. */
    public final int minOwnActions;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Create the crate containing the basic requirements to the happy scenario;
     * its "minimal sizes".
     *
     * @param minSteps      Minimal number of happy scenario steps
     * @param minPlaces     Minimal number of game places
     * @param minVisited    Minimal number of visited places
     * @param minOwnActions Minimal number of non-standard actions
     */
    public Limits(int minSteps, int minPlaces, int minVisited,
                  int minOwnActions)
    {
        this.minSteps      = minSteps;
        this.minPlaces     = minPlaces;
        this.minVisited    = minVisited;
        this.minOwnActions = minOwnActions;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Returns the textual signature of this instance
     * containing information about the requirements.
     *
     * @return Text with description of the requirements
     */
    @Override
    public String toString()
    {
        return "Minimal requirements to the happy scenario:"
           + "\n   Minimal number of steps = "               + minSteps
           + "\n   Minimal number of game places = "         + minPlaces
           + "\n   Minimal number of visited places = "      + minVisited
           + "\n   Minimal number of non-standard actions = "+ minOwnActions;
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
