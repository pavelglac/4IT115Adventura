/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac.textui;
import adv17w_fw.game_txt.IBag;
import adv17w_fw.game_txt.IGame;
import adv17w_fw.game_txt.IItem;
import adv17w_fw.game_txt.IWorld;
import java.util.Collection;
import java.util.Scanner;
import javax.swing.JOptionPane;
import adv17w_fw.game_txt.IPlace;



/*******************************************************************************
 * The {@code UIE_Informing} instances realize the user's interface,
 * that enhances the abilities of instances of {@link UID_Multiplayer}
 * by informing the user about the current game state.
 *
 * @author  Pavel Glac
 * @version 2017-Winter
 */
public class UIE_Informing extends UID_Multiplayer
{
//== CONSTANT CLASS FIELDS =====================================================
//== VARIABLE CLASS FIELDS =====================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE FIELDS ==================================================
//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Creates object realizing the user's interface
     * that utilizes the given object for solving certain details.
     *
     * @param multiplayer Object defining solution of certain details
     */
    public UIE_Informing(IGameMultiplayer multiplayer)
    {
        super(multiplayer);
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Return the answer decorated with supplement information
     * describing the current game state.
     *
     * @param answer Game answer to the last command
     * @param game   The played game
     * @return The decorated answer
     */
    @Override
    public String decorate(String answer, IGame game)
    {
        IBag bag = game.getBag();
        Collection<? extends IItem> bagItems = bag.getItems();

        IWorld world = game.getWorld();
        IPlace place = world.getCurrentPlace();
        Collection<? extends IPlace> neighbors  = place.getNeighbors();
        Collection<? extends IItem>  placeItems = place.getItems();

        String result = answer
                      +"\n\n\n"
                      + "\nPrávě se nacházíte: " + place
                      + "\nMísta kam můžete jít: " + neighbors
                      + "\nVěci v této lokaci: "     + placeItems
                      + "\nVěci v kapsách "   + bagItems
                      + "\nPro nápovědu zadeje ? "
                      + "\n";
        return result;
    }




//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================


//##############################################################################
//== NESTED DATA TYPES =========================================================



//##############################################################################
//== MAIN METHOD ===============================================================

    /***************************************************************************
     * Method starting the default game and enabling to enter
     * if it  will be used
     * the {@link JOptionPane} utilizing communication
     * or the {@link Scanner} class and standard input and output.
     *
     * @param args Parameters of the command line
     */
    public static void main(String[] args)
    {
        IGameMultiplayer gameMultiplayer;
        gameMultiplayer = new ByJOptionPane();
  
        new UIE_Informing(gameMultiplayer).multistartGame();
        System.exit(0);
    }

}
//==============================================================================