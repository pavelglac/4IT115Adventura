/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.game_txt;



/*******************************************************************************
 * Instances of interface {@code IAuthorRUP} know to return on request
 * the name and identification string of the author of its class.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public interface IAuthor
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the program author name in format <b>SURNAME First-name</b>,
     * i.e. firstly the surname in capital letters followed by first name,
     * with only the first capital letter, other letters will be small.
     * If the program author has more first names, he can quote all of them.
     *
     * @return Program author name in format SURNAME First-name
     */
//    @Override
    public String getAuthorName()
    ;


    /***************************************************************************
     * Returns the program author identification string
     * written in CAPITAL LETTERS.
     * It is mostly the login into the school information system.
     *
     * @return Identification string (login) of the program author
     */
//    @Override
    public String getAuthorID()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================

    /***************************************************************************
     * Returns the string composed from the author ID followed by
     * m-dash and the author's name.
     * It serves for elusion of the impossibility to define a default method
     * overriding the {@code toString()} method.
     *
     * @return Author ID and name
     */
    public default String getAuthorString()
    {
        return getAuthorID() + " — " + getAuthorName();
    }




//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
