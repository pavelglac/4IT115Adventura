/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adv17w.glap00_glac.game;

import java.util.Optional;

/**
 *
 * @author  Pavel Glac
 * @version 2017-Winter
 */
public class Text {
    static String result;
    
    /***************************************************************************
     * Response from the game when player examied a item.
     * 
     * @param itemName item which was examined
     * @return text associated to the action
     */   
    public static String answerOnExamining(String itemName){
        
        switch(itemName){
            case "MRTVOLA" :
                result = "Záchranář: Máte policejní odznak?" +
                "\nJistě, že mám. A teď mě nechte pracovat. "+
                 "\nNěkdo toho nebožáka podřízl ve spánku. ";
                break;
                
            case "ODZNAK" :
                result = "Moc pěkný odznak. Možná by se mi mohl na něco hodit"+
                "\nNebylo by od věci si ho vzít sebou";
                break;
                
                
            case "SEKERA" :
                result = "Pěkná sekera a evidentně často používaná"+
                         "Ten kdo ji používá musí být velice zručný.";
                break; 
            default : result ="Nedefinovaná odpověď" ;
                      break;
        }
        
    return result;
    }
    
    /***************************************************************************
     * Response from the person.
     * 
     * @param itemName person you want to talk to
     * @return text associated to the action
     */    
    public static String answerOnTalk(String itemName){
        
        switch(itemName){
            case "UČITELKA" :
                result = "Dobrý den, paní učitelko."+
            "\n Omlouvám se, ale hráč nepochopil hru a zavedl mne sem" +
            "\nUčitelka: Tak už běžtě. Strašítě tu děti";
                break;
                
            case "KOLEGA" :
                result = "Nezávidím ti, že musíš vyřešit takhle těžký případ";
                break;                
                
            case "OPILEC" :
                result = "Proč bych s Váma měl bavit?";
                break; 
                
            case "HOSTINSKÝ" :
                result = "Co dáte?";
                break; 
                
            default : result ="Nechtě mě být." ;
                      break;
        }
        
    return result;
    }    
    /***************************************************************************
     * Response from the game when player picked a item.
     * 
     * @param itemName item which was picked
     * @return text associated to the action
     */ 
    public static String answerOnPicking(String itemName){
        switch(itemName){
            
            case "NŮŽ" :
                result =
                   "\nTen nůž mi přidá stejný, jako používal náš hospodský."+
                   "\nOvšem ho údajně prodal před měsícem.";
                break;
                
            case "KAMERA" :
                result =
                   "\nTak to je jackpot." + 
                   "\nMěl bych to vzít na služebu, ať se na to podívá kolega.";
                break;  
                
            case "HRAČKA" :
                AAction.stopGame();
                result = "\nUkrást dítěti hračku může jen bezcitný člověk"+
                        "\nProto jste prohrál/a tuto hru.";
                break;  
                
            case "PAPÍR" :
                result = 
            "\n                 SEZNAM LIDÍ A JEJÍCH VÁHY                   "
            +"\n                                                             "
            +"\n                                                             "
            +"\n                                                             " 
            +"\n    Učitelka........................................57Kg     "
            +"\n                                                             " 
            +"\n    Opilec..........................................64Kg     "
            +"\n                                                             "
            +"\n    Kolega..........................................85Kg     "
            +"\n                                                             "
            +"\n    Hostinský......................................110Kg     " 
            +"\n                                                             "
            +"\n                                                             "
            +"\n                                                             "
            +"\n                                                             ";
                break;                 
                
            default : result ="Zvedli jste "+itemName ;
                      break;
        }
        
    return result;
    } 
    
    /***************************************************************************
     * Response from the game when player accused person.
     * 
     * @param itemName person who was accused
     * @return text associated to the action
     */    
    public static String answerOnAccuse(String itemName){
        switch(itemName){
            
            case "HOSTINSKÝ" :
                result =
                   "Vyhrál jste tuto hru!"+
              "\nVrahem byl opravdu hostiný, který zabíjel pouze pro potěšení"+
            "\nDíky Vám bude toto město bezpečnější";
                break;
                
            case "KOLEGA" :
                result =
                    "Vyhrál jste! Jen si z Vás dělám srandu." + 
                    "\nSamozřejmě, že jste prohrál.";
                break;  
                
            case "UČITELKA" :
                
                result = "Dobrý vtip, ale prohrál jste."+
                   "\n To jste musel vědět už při psaní takovéhle nemyslnosti.";
                break; 
                
            case "OPILEC" :
                result =
                    "Škoda, že jste více nedával pozor, ale byl jste blízko"; 
                break;                 
                
            default : result ="";
                      break;
        }
    return result;
    }
    
    /***************************************************************************
     * Message from person who obtain the item.
     * @param itemName a given item
     * @param itemName2 person who recieving the item
     * @return text associated to the action
     */     

    public static String answerOnForwarding(String itemName, String itemName2){
        String item = itemName.toUpperCase()+itemName2.toUpperCase();
        Pockets bag = Pockets.getInstance(); 
        Optional<Component> oItem = bag.getOItem(itemName);
        Component remove = oItem.get();
        switch(item){
            
            case "KAMERAKOLEGA" :
                result =
            "Ahoj, mohl by ses kouknout na tudle kameru?" +
            "\nKolega: Na záznamu je neznámá postava, která je velmi obézní."+
            "\nDíky moc!.";
            bag.removeItem(remove);
                break;
                
            case "ODPADKYKOLEGA" :
                result =
                    "To si děláš legraci? Proč do kanceláře nosíš odpadky" + 
                    "\nNevím co s nima chceš dělat, ale já je rozhodně nechci";
                break;

            case "DROBÁKYOPILEC" :
                result =
                    "Jste strašně moc hodný!" + 
                    "\nVšiml jste si, že náš hostinský je z vás nervózní?";
                break;

            case "ODPADKYUČITELKA" :
                result =
                    "Vy jste naprostý šílenec!" + 
                    "\nProč jste přinesl mezi děti odpadky? Vypadněte!";
                break;                
                
            default : result ="K čemu mi to bude?" ;
                      break;
        }
        
    return result;
    }      
}
