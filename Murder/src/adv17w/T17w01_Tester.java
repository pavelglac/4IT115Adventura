/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w;

import adv17w.glap00_glac.PGFactory;
import adv17w_fw.game_txt.IAction;
import adv17w_fw.game_txt.IGSMFactory;
import adv17w_fw.scenario.AScenarioManager;
import adv17w_fw.scenario.Scenario;
import adv17w_fw.scenario.ScenarioStep;
import adv17w_fw.scenario.TypeOfStep;
import adv17w_fw.test_util.common.TestException;
import adv17w_fw.test_util.game_txt_test.ScenarioSummary;
import adv17w_fw.test_util.game_txt_test.ATestVisitor;
import adv17w_fw.test_util.game_txt_test.ASolutionTester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



/*******************************************************************************
 * Instance třídy {@code Tester_17w01} testují správné zapracování<br>
 * modifikace s kódem <b>17w01</b>, požadující upravit odevzdaný program<br>
 * následovně:<br>
 * <br>
 * Na konec každé zprávy vypisované jako odpověď hry na zadání příkazu<br>
 * přidat řádek s textem:<br><br>
 * <b>
 * §Not entered: [Name1, Name2, Name3, ..., NameN]
 * </b><br>
 * <br>
 * Kde v hranatých závorkách bude abecedně seřazený seznam<br>
 * čárkami oddělených názvů akcí,<br>
 * které hráč během aktuální hry ještě <b>nezadal</b>.<br>
 * Názvy <b>Name1</b>, <b>Name2</b> atd. ve výše  uvedeném textu<br>
 * zastupují názvy akcí testované hry.<br>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2017-Winter
 */
public class T17w01_Tester
     extends ASolutionTester
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Identifikátor testovaného zadání. */
    public static final String TEST_ID = "17w01";

    /** Požadovaný tvar začátku posledního řádku. */
    protected final static String START = "Not entered: [";

    /** Požadovaný oddělovač názvů. */
    protected final static String SEPARATOR = ", ";

    /** Požadovaný ukončovací řetězec. */
    protected final static String END = "]";

    /** Seznam výsledků provedených testů. */
    private static final List<ScenarioSummary> INFOS = new ArrayList<>();



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
     * Vytvoří tester prověřující řešení zadání specifikovaného
     * v dokumentačním komentáři třídy.
     */
    public T17w01_Tester()
    {
        super(TEST_ID, "", Visitor17w01::new);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

////////////////////////////////////////////////////////////////////////////////
//\N1C /////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
     * Instance třídy {@code Visitor17w01} představují návštěvníky prověřující
     * splnění zadání definovaného v dokumentačním komentáři vnější třídy.
     */
    private static class Visitor17w01
                 extends ATestVisitor
    {
    //\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==========
    //\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==========



    //##########################################################################
    //\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =====================
    //\CF== CLASS (STATIC) FACTORY METHODS =====================================
    //\CG== CLASS (STATIC) GETTERS AND SETTERS =================================
    //\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS =======================
    //\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS =======================



    //##########################################################################
    //\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===========

        /** Množina doposud nezadaných příkazů dané hry. */
        public final Set<String> notEntered = new HashSet<>();



    //\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===========



    //##########################################################################
    //\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===============================

        /***********************************************************************
         * Vytvoří návštěvníka metod testujících fungování hry podle
         * modifikovaného zadání definovaného v konstantě {@link #DESCRIPTION}.
         *
         * @param myTest  Zadavatel požadující vyřešení zadání
         *                definovaného v konstantě {@link #DESCRIPTION}
         * @param factory Tovární objekt poskytující základní objekty
         *                prověřované aplikace
         */
        Visitor17w01(ASolutionTester myTest, IGSMFactory factory)
        {
            super(myTest, factory);
        }



    //\IA== INSTANCE ABSTRACT METHODS ==========================================
    //\IG== INSTANCE GETTERS AND SETTERS =======================================

        /***********************************************************************
         * Vrátí sdružený tester, jehož zadání prověřuje.
         *
         * @return Sdružený tester
         */
        @Override
        public ASolutionTester getTester()
        {
            return myTest;
        }



    //\IM== INSTANCE REMAINING NON-PRIVATE METHODS =============================

        /***********************************************************************
         * Akce, která se má provést po spuštění hry před testem prvního kroku:
         * zapamatuje si názvy všech akcí hry převedené na malá písmena.
         *
         * @param scenario  Scénář definující proces testování
         */
        @Override
        public void afterGameStart(Scenario scenario)
        {
            notEntered.clear();
            Collection<? extends IAction> allActions;
            allActions = game.getAllActions();
            for (IAction iAction : allActions) {
                String name = iAction.getName().toLowerCase();
                notEntered.add(name);
            }
        }


        /***********************************************************************
         * Akce, která se má provést po testu aktuálního kroku:
         * odebere z kolekce název právě provedené akce
         * a zkontroluje poslední řádek zprávy.
         *
         * @param step      Aktuálně testovaný krok scénáře
         * @param message   Zpráva vrácená hrou v daném kroku
         */
        @Override
        public void afterStepTest(ScenarioStep step, String message)
        {
            String   command    = step.command;
            String[] words      = command.trim()
                                         .split("\\s+");
            String   actionName = words[0];
            notEntered.remove(actionName.toLowerCase());

            //Najde a zkontroluje poslední řádek
            String[] parts  = message.split("§");
            int      number = parts.length;
            if (number < 2) {
                throw new TestException(
                        "\nChybí požadovaný závěrečný řádek");
            }
            if (number > 2) {
                throw new TestException(
                        "\nNečekaný znak § v závěrečné zprávě");
            }
            String line = parts[1];
            if (! line.startsWith(START)) {
                throw new TestException(
                        "\nZávěrečný řádek nemá požadovaný začátek");
            }
            line = line.trim();
            if (! line.endsWith(END)) {
                throw new TestException(
                        "\nZávěrečný řádek nemá požadovaný konec");
            }
            if (step.typeOfStep == TypeOfStep.tsNOT_START) {
                return;
            }
            String answer = line.substring(START.length(),
                                           line.length() - END.length()).trim();
            String[] nameArr;
            if (answer.isEmpty()) {
                nameArr = new String[0];
            }
            else {
                nameArr = answer.split(SEPARATOR);
            }
            Set<String> names = new HashSet<>(Arrays.asList(nameArr));

            if (notEntered.size() != names.size()) {
                throw new TestException(
                        "\nNesouhlasí počet uvedených nezadaných akcí");
            }
            for (Iterator<String> it = names.iterator(); it.hasNext();) {
                String name   = it.next();
                String tested = name.toLowerCase();
                if (notEntered.contains(tested)) {
                    it.remove();
                }
                else {
                    throw new TestException(
                        "\nChybně uvedená nezadaná akce: " + name);
                }
            }
        }


        /***********************************************************************
         * Akce, která se má provést po testu posledního kroku.
         *
         * @param summary Přepravka s kompletními informacemi o průběhu hry
         */
        @Override
        public void afterGameEnd(Throwable exception, String verboseMessage)
        {
            super.afterGameEnd(exception, verboseMessage);
        }



    //\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =============================



    //##########################################################################
    //\NT== NESTED DATA TYPES ==================================================
    }



//##############################################################################
//\MM== MAIN METHOD ============================================================

    /***************************************************************************
     * Metoda spouštějící celou aplikaci.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        T17w01_Tester tester = new T17w01_Tester();
        tester.test(
//            AScenarioManager.getDefaultFactory
            new
            PGFactory()
        );
        System.exit(0);
    }
}
