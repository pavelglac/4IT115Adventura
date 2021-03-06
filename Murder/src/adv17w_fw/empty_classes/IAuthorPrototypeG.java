/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.empty_classes;

import adv17w_fw.game_gui.IGSMFactoryG;
import adv17w_fw.game_gui.IGSMFactoryProductG;



/*******************************************************************************
 * Instances of interface {@code IAuthorPrototype} know to return on request
 * the name and identification string of the author of its class;
 * these values are saved in their static constants
 * {@link #AUTHOR_NAME} a {@link #AUTHOR_ID}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2017-Summer
 */
public interface IAuthorPrototypeG
         extends IGSMFactoryProductG
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================

    /** The name of the author in format <b>SURNAME First-name</b>,
     * i.e. firstly the surname in capital letters followed by first name,
     * with only the first capital letter, other letters will be small.
     * If the program author has more first names, he can quote all of them. */
    String AUTHOR_NAME = "PECINOVSKÝ Rudolf";

    /** Author's identification string written in CAPITAL LETTERS.
     *  It is mostly the login into the school information system. */
    String AUTHOR_ID = "RUP15P";

    /** Factory class the instances of which are factory objects providing
     *  the instance of the game, its associated scenario manager
     *  and the textual user interface created by this author. */
    Class<? extends IGSMFactoryG> FACTORY_G_CLASS = EmptyGSMFactoryG.class;



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
    @Override
    default public String getAuthorName()
    {
        return AUTHOR_NAME;
    }


    /***************************************************************************
     * Returns the program author identification string
     * written in CAPITAL LETTERS.
     * It is mostly the login into the school information system.
     *
     * @return Identification string (login) of the program author
     */
    @Override
    default public String getAuthorID()
    {
        return AUTHOR_ID;
    }


    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which also the mother class of this instance is.
     *
     * @return The class-object of the factory class
     */
    @Override
    default public Class<? extends IGSMFactoryG> getFactoryClass()
    {
        return FACTORY_G_CLASS;
    }



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
