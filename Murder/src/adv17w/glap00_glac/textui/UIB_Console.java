/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac.textui;

////////////////////////////////////////////////////////////////////////////////
//%P-  +++++ End of ignored starting text - place for imports +++++

import adv17w_fw.game_txt.IGame;
import adv17w_fw.game_txt.IUI;
import adv17w.glap00_glac.game.IPGAuthor;

import java.util.Scanner;



/*******************************************************************************
 * Instance třídy {@code UIB_Console} realizují uživatelské rozhraní,
 * které využívá standardní vstup a výstup.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 15J
 */
public class UIB_Console
  implements IUI, IPGAuthor
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    /** Aktuálně hraná hra. */
    private IGame game;



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public UIB_Console()
    {
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí aktuálně hranou hru.
     *
     * @return Aktuálně hraná hra
     */
    @Override
    public IGame getGame()
    {
        return game;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Spustí komunikaci mezi implicitní hrou
     * a danou instancí uživatelského rozhraní.
     */
    @Override
    public void startGame()
    {
        startGame(getFactory().getGame());
    }


    /***************************************************************************
     * Spustí komunikaci mezi zadanou hrou a danou instancí
     * mající na starosti komunikaci s uživatelem.
     *
     * @param game Hra, kterou ma dané UI spustit
     */
    @Override
    public void startGame(IGame game)
    {
        Scanner scanner = new Scanner(System.in);
        String  command = "";
        String  answer;
        for(;;) {
            answer  = game.executeCommand(command);
            System.out.println(answer);
            if (! game.isAlive()) { break; }    //---------->
            command =  scanner.nextLine();
        }
    }
//    public void startGame(IGame game)
//    {
//        Scanner scanner = new Scanner(System.in);
//        String  command = "";
//        String  answer  = game.executeCommand("");
//        System.out.println(answer);
//        do {
//            command =  scanner.nextLine();
//            answer  = game.executeCommand(command);
//            System.out.println(answer);
//        } while (game.isAlive());
//    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================



//##############################################################################
//== MAIN METHOD ===============================================================

    /***************************************************************************
     * Metoda spouštějící hru
     * s uživatelským rozhraním využívajícím služeb
     * standardního vstupu a výstupu a třídy {@link Scanner}.
     *
     * @param args Parametry příkazového řádku - prozatím nepoužívané
     */
    public static void main(String[] args)
    {
        new UIB_Console().startGame();
    }
}
