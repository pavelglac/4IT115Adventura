/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w_fw.game_txt;



/*******************************************************************************
 * Interface {@code IUI} declares the requirements to the class
 * defining the game user interface.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public interface IUI
         extends IGSMFactoryProduct
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the currently played game.
     *
     * @return The currently played game
     */
//    @Override
    public IGame getGame()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================

    /***************************************************************************
     * Starts the the given game
     * and runs the communication between this game and the user.
     *
     * @param game The game, which should be run
     */
//    @Override
    public void startGame(IGame game)
    ;



//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================

    /***************************************************************************
     * Starts the default game, which means the game returned by the instance
     * of this GUI's factory class,
     * and runs the communication between this game and the user.
     */
//    @Override
    default void startGame()
    {
        IGSMFactory factory = getFactory();
        IGame       game    = factory.getGame();
        startGame(game);
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
