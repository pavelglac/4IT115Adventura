/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package adv17w.glap00_glac.game;

import adv17w_fw.scenario.AScenarioManager;
import adv17w_fw.scenario.ScenarioStep;
import adv17w_fw.scenario.TypeOfScenario;
import static adv17w_fw.scenario.TypeOfStep.*;

/*******************************************************************************
 * Instance of the {@code RUPScenarioManagerLit} class serves as
 * scenario manager, that has to manage the scenarios of the associated game.
 * These scenarios should allow to test and demonstrate
 * the functionality of the associated game.
 * Each manager has to offer:
 * <ul>
 *   <li>The happy scenario</li><li>The mistake scenario</li></ul><p>
 * Individual managed scenarios have to differ by their names;
 * the names of the happy scenario and the mistake one
 * are firmly pre-determined and cannot be arbitrarily set.
 * </p><p>
 * Individual scenarios are iterable and "streamable" sequences of steps
 * specified by instances of the framework class
 * {@link adv17w_fw.scenario.ScenarioStep},
 * to which the designed game should associated.
 * </p>
 * Scenario manager is a singleton, that is responsible
 * for all scenarios concerning the game associated with it.
 *
 * @author  Pavel Glac
 * @version 2017-Winter
 */
public   class PGScenarioManager
       extends AScenarioManager
    implements IPGAuthor
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /***************************************************************************
     * The initial game step identical for all scenarios.
     * <p>
     * Constructor of the full-fledged {@link ScenarioStep} class instance
     * requires the following parameters:
     <pre> {@code
TypeOfStep typeOfStep; //Type of the given scenario step
String     command;    //Command realizing this scenario step
String     message;    //Message written after entering the command
String     place;      //Current place after entering the command
String[]   neighbors;  //Neighbors of the current place (= exits)
String[]   items;      //Items occuring in the current place
String[]   bag;        //Current bag content
     }</pre>
     =======================================================================<br>
     * Scenario steps have to comply with the following requirements:
     * <ul>
     *   <li>None the items may contain the {@code null} value.</li>
     *   <li>With the exception of {@code command} no string may be
     *     empty (i.e. {@code ""})</li>
     *   <li>Empty string may occur neither as an item in the array
     *     {@code neighbors}, nor {@code items} nor {@code bag}</li>
     * </ul>
     * <br>
     **************************************************************************/
     public static final ScenarioStep START_STEP =
        new ScenarioStep(0, tsSTART, "", //Název spouštěcího příkazu = ""
            "A sakra. Zase další vražda v tomhle malém městě" + 
                    "\nMěl bych se jít na to podívat."
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Odznak", "Kolega" },
            new String[] { }
        );
     
    /***************************************************************************
     * Steps of the happy scenario
     * describing the expectable successful game running. It is not necessary
     * for the scenario compiled of these steps to be the shortest possible
     * (it implies, that it has not to be the true basic successful scenario),
     * but it has to comply with all marginal conditions of the assignment,
     * i.e. it has to contain minimal number of steps,
     * pass through the required minimal number of places
     * and demonstrate using of all required actions.
     */
    private static final ScenarioStep[] HAPPY_SCENARIO_STEPS =
    {
        START_STEP,
        new ScenarioStep(tsTAKE, "VEZMI odznak",
            "Zvedli jste ODZNAK"
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Kolega"},
            new String[] { "Odznak" }
        )
        ,
        new ScenarioStep(tsMOVE, "JDI ulice",
            "Nacházíte se na místě: ULICE"
            ,
            "Ulice",
            new String[] { "STANICE","HOSPODA","MÍSTO_ČINU","RADNICE","ŠKOLKA"},
            new String[] { "DROBÁKY", "ODPADKY" },
            new String[] { "ODZNAK"}
        )
        ,
        new ScenarioStep(tsMOVE, "JDI místo_činu",
            "Nacházíte se na místě: místo_činu"
            ,
            "Místo_činu",
            new String[] { "Ulice" },
            new String[] { "Mrtvola", "Nůž", "Kamera" },
            new String[] { "Odznak"}
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "Prozkoumat mrtvola",
            "Záchranář: Máte policejní odznak?" +
            "\nJistě, že mám. A teď mě nechte pracovat. "+
            "\nNěkdo toho nebožáka podřízl ve spánku. "
            ,
            "Místo_činu",
            new String[] { "Ulice" },
            new String[] { "Mrtvola", "Nůž", "Kamera" },
            new String[] { "Odznak"}
        )
        ,         
        new ScenarioStep(tsTAKE, "VEZMI nůž",
            "\nTen nůž mi přidá stejný, jako používal náš hospodský."+
            "\nOvšem ho údajně prodal před měsícem."
            ,
            "Místo_činu",
            new String[] { "Ulice" },
            new String[] { "Mrtvola", "Kamera" },
            new String[] { "Odznak", "Nůž"}
        )            
        ,
        new ScenarioStep(tsTAKE, "VEZMI kamera",
            "\nTak to je jackpot." + 
            "\nMěl bych to vzít na služebu, ať se na to podívá kolega."
            ,
            "Místo_činu",
            new String[] { "Ulice" },
            new String[] { "Mrtvola" },
            new String[] { "Odznak", "Nůž", "Kamera"}
        )  
        ,
        new ScenarioStep(tsMOVE, "JDI ulice",
            "Nacházíte se na místě: ULICE"
            ,
            "Ulice",
            new String[] { "STANICE","HOSPODA","MÍSTO_ČINU","RADNICE","ŠKOLKA"},
            new String[] { "DROBÁKY", "ODPADKY" },
            new String[] { "Odznak", "Nůž", "Kamera"}
        )
        ,
        new ScenarioStep(tsMOVE, "JDI stanice",
            "Nacházíte se na místě: STANICE"
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Kolega" },
            new String[] { "Odznak", "Nůž", "Kamera"}
        )
        ,
        new ScenarioStep(tsNON_STANDARD2, "Předat kamera kolega",
            "Ahoj, mohl by ses kouknout na tudle kameru?" +
            "\nKolega: Na záznamu je neznámá postava, která je velmi obézní."+
            "\nDíky moc!. "
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Kolega"},
            new String[] { "Odznak", "Nůž"}
        )
        ,
        new ScenarioStep(tsMOVE, "JDI ulice",
            "Nacházíte se na místě: ULICE"
            ,
            "Ulice",
            new String[] { "STANICE","HOSPODA","MÍSTO_ČINU","RADNICE","ŠKOLKA"},
            new String[] { "DROBÁKY", "ODPADKY" },
            new String[] { "Odznak", "Nůž"}
        )
        ,
        new ScenarioStep(tsMOVE, "JDI školka",
            "Nacházíte se na místě: ŠKOLKA"
            ,
            "Školka",
            new String[] { "Ulice" },
            new String[] { "Dítě", "Učitelka", "Hračka" },
            new String[] { "Odznak", "Nůž"}
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "Promluvit učitelka",
            "Dobrý den, paní učitelko."+
            "\n Omlouvám se, ale hráč nepochopil hru a zavedl mne sem" +
            "\nUčitelka: Tak už běžtě. Strašítě tu děti"
            ,
            "Školka",
            new String[] { "Ulice" },
            new String[] { "Dítě", "Učitelka", "Hračka" },
            new String[] { "Odznak", "Nůž"}
        )
        ,        
        new ScenarioStep(tsMOVE, "JDI ulice",
            "Nacházíte se na místě: ULICE"
            ,
            "Ulice",
            new String[] { "STANICE","HOSPODA","MÍSTO_ČINU","RADNICE","ŠKOLKA"},
            new String[] { "DROBÁKY", "ODPADKY" },
            new String[] { "Odznak", "Nůž"}
        ) 
        ,    
        new ScenarioStep(tsMOVE, "JDI radnice",
        "Nacházíte se na místě: RADNICE"
            ,
            "Radnice",
            new String[] { "Ulice" },
            new String[] { "Papír" },
            new String[] { "Odznak", "Nůž"}
        )
        ,
        new ScenarioStep(tsTAKE, "VEZMI papír",
            "\n                 SEZNAM LIDÍ A JEJÍCH VÁHY                   "
            +"\n                                                             " 
            +"\n                                                             "
            +"\n                                                             " 
            +"\n    Učitelka........................................57Kg     "
            +"\n                                                             "
            +"\n    Policista.......................................82Kg     " 
            +"\n                                                             " 
            +"\n    Opilec..........................................64Kg     " 
            +"\n                                                             " 
            +"\n    Kolega..........................................85Kg     "
            +"\n                                                             " 
            +"\n    Hostinský......................................110Kg     " 
            +"\n                                                             " 
            +"\n                                                             " 
            +"\n                                                             " 
            +"\n                                                             "
            ,
            "Radnice",
            new String[] { "Ulice" },
            new String[] { },
            new String[] { "Odznak", "Nůž", "Papír"}
        )
        ,
        new ScenarioStep(tsMOVE, "JDI ulice",
            "Nacházíte se na místě: ULICE"
            ,
            "Ulice",
            new String[] { "STANICE","HOSPODA","MÍSTO_ČINU","RADNICE","ŠKOLKA"},
            new String[] { "DROBÁKY", "ODPADKY" },
            new String[] { "Odznak", "Nůž", "PAPÍR"}
        )                  
        ,
        new ScenarioStep(tsMOVE, "JDI hospoda",
            "Nacházíte se na místě: HOSPODA"
            ,
            "Hospoda",
            new String[] { "Ulice" },
            new String[] { "Opilec", "HOSTINSKÝ", "Sekera" },
            new String[] { "Odznak", "Nůž", "PAPÍR"}
        )
        ,
        new ScenarioStep(tsPUT_DOWN, "POLOŽ ODZNAK",
            "Položili jste item:ODZNAK"
            ,
            "Hospoda",
            new String[] { "Ulice" },
            new String[] { "Opilec", "HOSTINSKÝ", "Sekera", "Odznak" },
            new String[] { "Nůž", "PAPÍR"}
        )            
        ,
        new ScenarioStep(tsNON_STANDARD1, "Obvinit hostinský",
            "Vyhrál jste tuto hru!"+
            "\nVrahem byl opravdu hostiný, který zabíjel pouze pro potěšení"+
            "\nDíky Vám bude toto město bezpečnější"
            ,
            "Hospoda",
            new String[] { "Ulice" },
            new String[] { "Opilec", "HOSTINSKÝ", "Sekera", "Odznak" },
            new String[] {"NŮŽ", "PAPÍR"}
        )

    };


    /** Step testing the incorrect game starting is defined separately,
     *  so that the indexes of the following steps could be simply adjusted. */
    private static final ScenarioStep WRONG_START =
    new ScenarioStep(-1,
            tsNOT_START, "Start",
            "Hra se spouští prázdým příkazem."
            ,
            "",
            new String[] {},
            new String[] {},
            new String[] {}
        );


    static { ScenarioStep.setIndex(1); }


    /***************************************************************************
     * Mistake scenario defining reactions
     * to mandatory set of types of incorrectly given commands.
     */
    private static final ScenarioStep[] MISTAKE_SCENARIO_STEPS =
    {
        WRONG_START,

        START_STEP,
        new ScenarioStep(tsTAKE_WA,"VEZMI",
            "Nezadali jste platný item pro zvednutí."
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Odznak", "Kolega" },
            new String[] {  }
        )
        ,        new ScenarioStep(tsBAD_ITEM, "VEZMI nůž",
            "Item nůž se nenachází v tomto prostoru" 
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Odznak", "Kolega" },
            new String[] {  }
        )
        ,
        new ScenarioStep(tsPUT_DOWN_WA, "POLOŽ",
            "Nezadali jste platný item k položení."
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Odznak", "Kolega" },
            new String[] {  }
        )
        ,
        new ScenarioStep(tsUNMOVABLE, "VEZMI kolega",
            "kolega je nepřenositelný."
            ,
        
                "Stanice",
            new String[] { "Ulice" },
            new String[] { "Odznak", "Kolega" },
            new String[] {  }
        )
        ,
        new ScenarioStep(tsNOT_IN_BAG, "POLOŽ odznak",
            "Item odznak se nenachází v kapsách."
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Odznak", "Kolega" },
            new String[] {  }
        )
        ,
        new ScenarioStep(tsMOVE_WA, "JDI",
            "Nelze určit kam jít."
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Odznak", "Kolega" },
            new String[] {  }
        )
        ,
        new ScenarioStep(tsBAD_NEIGHBOR, "JDI radnice",
            "Tento prostor nesousedí s radnice"
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Odznak", "Kolega" },
            new String[] {  }
        )
        ,
        new ScenarioStep(tsEMPTY, "",
        "zadali jste prázdný příkaz"
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Odznak", "Kolega" },
            new String[] {  }
        )
        ,
        new ScenarioStep(tsTAKE, "VEZMI ODZNAK",
        "Zvedli jste ODZNAK"
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Kolega" },
            new String[] { "Odznak"}
        )
        ,
        new ScenarioStep(tsMOVE, "JDI ulice",
            "Nacházíte se na místě: ULICE"
            ,
            "Ulice",
            new String[] { "STANICE","HOSPODA","MÍSTO_ČINU","RADNICE","ŠKOLKA"},
            new String[] { "DROBÁKY", "ODPADKY" },
            new String[] { "ODZNAK"}
        )
        ,
        new ScenarioStep(tsTAKE, "VEZMI DROBÁKY",
            "Zvedli jste DROBÁKY"
            ,
            "Ulice",
            new String[] { "STANICE","HOSPODA","MÍSTO_ČINU","RADNICE","ŠKOLKA"},
            new String[] { "ODPADKY" },
            new String[] { "ODZNAK", "DROBÁKY"}
        ) 
        ,
        new ScenarioStep(tsTAKE, "VEZMI ODPADKY",
            "Zvedli jste ODPADKY"
            ,
            "Ulice",
            new String[] { "STANICE","HOSPODA","MÍSTO_ČINU","RADNICE","ŠKOLKA"},
            new String[] { },
            new String[] { "ODZNAK", "DROBÁKY", "ODPADKY"}
        )            
        ,
        new ScenarioStep(tsMOVE, "JDI místo_činu",
            "Nacházíte se na místě: místo_činu"
            ,
            "Místo_činu",
            new String[] { "Ulice" },
            new String[] { "Mrtvola", "Nůž", "Kamera" },
            new String[] { "ODZNAK", "DROBÁKY", "ODPADKY"}
        )
        ,
        new ScenarioStep(tsTAKE, "VEZMI KAMERA",
            "\nTak to je jackpot." + 
            "\nMěl bych to vzít na služebu, ať se na to podívá kolega."
            ,
            "Místo_činu",
            new String[] { "ULICE"},
            new String[] { "Mrtvola", "Nůž", },
            new String[] { "ODZNAK", "DROBÁKY", "ODPADKY", "KAMERA"}
        )             
        ,
        new ScenarioStep(tsBAG_FULL, "VEZMI NŮŽ",
            "Kapsy jsou plné. Můžete zde něco zahodit."
            ,
            "Místo_činu",
            new String[] { "ULICE"},
            new String[] { "Mrtvola", "Nůž", },
            new String[] { "ODZNAK", "DROBÁKY", "ODPADKY", "KAMERA"}
        )
        ,
        new ScenarioStep(tsUNKNOWN, "kulomet",
        "Zadali jste neznámý příkaz"
            ,
            "Místo_činu",
            new String[] { "ULICE"},
            new String[] { "Mrtvola", "Nůž", },
            new String[] { "ODZNAK", "DROBÁKY", "ODPADKY", "KAMERA"}
        )
        ,
        new ScenarioStep(tsHELP, "?",
            "PŘEDAT\n" +
"Lze prozkoumat pouze objekty, které jsou v aktuální lokaci.\n" +
"Prozkoumání může pomoci k dopadení vraha\n" +
"\n" +
"POLOŽ\n" +
"Přesune zadaný h-objekt z batohu do aktuálního prostoru.\n" +
"\n" +
"PROZKOUMAT\n" +
"Lze prozkoumat pouze objekty, které jsou v aktuální lokaci.\n" +
"Prozkoumání může pomoci k dopadení vraha\n" +
"\n" +
"VEZMI\n" +
"Přesune zadaný h-objekt z aktuálního prostoru do batohu.\n" +
"Přesouvá přitom pouze přenositelné h-objekty.\n" +
"\n" +
"PROMLUVIT\n" +
"Vyslechnou si může pouze živé bytosti ve hře\n" +
"\n" +
"OBVINIT\n" +
"Obvinit lze pouze živou bytost\n" +
"Pokud obviníte nesprávnou osobu, tak prohrajete.\n" +
"\n" +
"JDI\n" +
"Metoda přesune hráče do sousední místnosti zadané v parametru.\n" +
"Vyžaduje však, aby tato místnost byla sousedem aktuální místnosti,\n" +
"protože jinak přesun neprovede a bude příkaz považovat za chybný\n" +
"\n" +
"KONEC\n" +
"Ukončení hry.\n" +
"\n" +
"?\n" +
"Vypíše nápovědu s názvy a stručnými popisy dostupných příkazů."
            ,
            "Místo_činu",
            new String[] { "ULICE"},
            new String[] { "Mrtvola", "Nůž", },
            new String[] { "ODZNAK", "DROBÁKY", "ODPADKY", "KAMERA"}
        )
        ,
        new ScenarioStep(tsEND, "Konec",
            "Děkuji za zahrání hry."
            ,
            "Místo_činu",
            new String[] { "ULICE"},
            new String[] { "Mrtvola", "Nůž", },
            new String[] { "ODZNAK", "DROBÁKY", "ODPADKY", "KAMERA"}
        )
    };

    static { ScenarioStep.setIndex(1); }
    
    private static final ScenarioStep[] REQUIRED_STEPS =
    {
        START_STEP,
        
        new ScenarioStep(tsHELP, "?",
            "Příkazy: Jdi, Vezmi, Polož, Přečíst, Promluvit, Předat. "+    
            "\nJdi: stanice, hospoda, místo_činu, radnice, ulice"+
            "\nVezmi: odznak, nůž, kamera, hračka, odpadky"+
            "\nPolož: odznak, nůž, kamera, hračka, odpadky"+
            "\nPřečíst: papír"+
            "\nPromluvit: učitelka"+
            "\nPředat papír kolega"+
            "\nObjekty. Vypíše seznam objektu v lokaci"
            ,
            "Stanice",
            new String[] { "Ulice" },
            new String[] { "Odznak", "Kolega" },
            new String[] {  }
        ),
        
        new ScenarioStep(tsMOVE, "Jdi ulice",
            "Krásné město, ale teď měl spíš pracovat."
            ,
            "Ulice",
            new String[] { "Stanice", "Hospoda", "Místo_činu", "Radnice" },
            new String[] { "Drobáky", "Odpadky" },
            new String[] { }
        ),
            
        new ScenarioStep(tsTAKE, "Vezmi Odpadky",
            "K čemu to potřebuji netuším, ale třeba se to bude hodit."
            ,
            "Ulice",
            new String[] { "Stanice", "Hospoda", "Místo_činu", "Radnice" },
            new String[] { "Drobáky"},
            new String[] { "Odpadky" }
        ),
        
        new ScenarioStep(tsPUT_DOWN, "Polož Odpadky",
            "K čemu to potřebuji netuším, ale třeba se to bude hodit."
            ,
            "Ulice",
            new String[] { "Stanice", "Hospoda", "Místo_činu", "Radnice" },
            new String[] { "Drobáky", "Odpadky"},
            new String[] { }
        ), 
        
        new ScenarioStep(tsEND, "konec",
            "Děkuji za hraní mé hry."
            ,
            "Ulice",
            new String[] { "Stanice", "Hospoda", "Místo_činu", "Radnice" },
            new String[] { "Drobáky", "Odpadky"},
            new String[] { }
        )
    
        
        
    };

    /** The only instance of this class.
     *  It manages all scenarios of the associated game. */
    private static final PGScenarioManager MANAGER =
                                          new PGScenarioManager();

//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Returns scenario manager - the only instance of this class.
     *
     * @return Scenario manager
     */
    public static PGScenarioManager getInstance()
    {
        return MANAGER;
    }

//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================
//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
    
        private static final String REQUIRED_STEPS_SCENARIO_NAME = "REQUIRED";
        
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============
//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates an instance representing the game scenario manager.
     * Within the constructor framework it is suitable to create all scenarios
     * and seal the scenario manager after it.
     * <p>
     * Individual managed scenarios have to differ by their names,
     * the names of the happy scenario and the mistake one
     * are firmly pre-determined and cannot be changed.
     * Names given to them in the
     * {@link #addScenario(String, TypeOfScenario, ScenarioStep...)} method
     * are therefore only formal, because the called method assignes to them
     * the names defined in advance in appropriate constants.
     * <p>
     *´Contract of the
     * {@link #addScenario(String, TypeOfScenario, ScenarioStep...)} method
     * requires so that the happy scenario, i.e. scenario of the
     * {@link TypeOfScenario.scHAPPY}) type, would be added as the first one,
     * and the mistake scenario, i.e. the scenario of the
     * {@link MISTAKE_SCENARIO_NAME} type, as the second one.
     * The order of the subsequently added scenarios is not decisive.
      */
    private PGScenarioManager()
    {
        super(FACTORY_CLASS);

        addScenario(HAPPY_SCENARIO_NAME,
                    TypeOfScenario.scHAPPY,    HAPPY_SCENARIO_STEPS);
        addScenario(MISTAKE_SCENARIO_NAME,
                    TypeOfScenario.scMISTAKES, MISTAKE_SCENARIO_STEPS);
        addScenario(REQUIRED_STEPS_SCENARIO_NAME, 
                    TypeOfScenario.scGENERAL,  REQUIRED_STEPS); 
        seal();
    }

//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================
//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
//##############################################################################
//== TEST METHODS AND CLASSES ==================================================

    /***************************************************************************
     * Method verifying the given scenario manager and the associated game
     * by scenarios of this manager.
     * <p>
     * The scenario manager is verified if it complies
     * with the following conditions:
     * <ul>
     *   <li>It knows to return properly formated name of the game author
     *       and his/her ID.</li><li>It defines the happy scenario and the mistake one.</li><li>The mistake scenario has the following properties:</li>

     *       <li>Starting command, the name of which is an empty string</li><li>Minimal required number of steps</li><li>Minimal number of visited places</li><li>Minimal number of "glimpsed" places</li><li>Minimal number of own (optional) actions</li><li>Usage of actions for moving from the current place
     *         to a neighboring place, taking item and putting down item</li><li>Cross consistence of actions and states after execution
     *         of the actions</li><li>The mistake scenario has the following properties:</li>

     *       <li>Incorrect starting by a not empty command,</li><li>Starting command the name of which is an empty string</li><li>Usage of all mandatory error step types defined in the<br>
     *         {@link adv17w_fw.scenario.TypeOfStep} enum type</li><li>Asking for a help</li><li>Premature game termination</li>
     * </ul>
     * The game is verified if it can be played exactly
     * as is planned in scenarios.
     *
     * @param args Command line parameters - unused.
     */
    public static void main(String[] args)
    {
        //Tests if the scenario manager and its scenarios
        //comply with requirements
        MANAGER.autoTest();

        //Simulates playing the game according to happy scenario
        MANAGER.getHappyScenario().simulate();

        //Game testing made gradually according to both mandatory scenarios,
        //the happy scenario is passed twice one after the other
        MANAGER.testGame();

        //Game testing according to scenarios with the given names
//       MANAGER.testGameByScenarios(REQUIRED_STEPS_SCENARIO_NAME);

        //Playing the game according to the scenario with the given name
//        MANAGER.playGameByScenario(REQUIRED_STEPS_SCENARIO_NAME);

        System.exit(0);
    }

    
    
}
