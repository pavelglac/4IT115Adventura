/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adv17w.glap00_glac.textui;

import adv17w.glap00_glac.game.IPGAuthor;
import adv17w_fw.game_txt.IBag;
import adv17w_fw.game_txt.IGame;
import adv17w_fw.game_txt.IItem;
import adv17w_fw.game_txt.IUI;
import adv17w_fw.game_txt.IWorld;
import java.util.Collection;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import adv17w_fw.game_txt.IPlace;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

/**
 *
 * @author glap00
 */
public class UI_JavaFX
        extends Application
            implements IUI, IPGAuthor
{

    private IGame game;
    private BorderPane borderPane;
    private TextArea centerTextArea;
    private FlowPane bottomFlowPane;
    private Label executecommandLabel;
    private TextField commandTextField;
    private UI_OknoProstoru oknoProstoru;
    private UI_PanelVychodu panelVychodu;
    private UI_PanelBatohu panelBatohu;
//    private UI_MenuPanel menuPanel;
    private MenuBar menuBar;


    /**
     * *************************************************************************
     * Vrátí aktuálně hranou hru.
     *
     * @return Aktuálně hraná hra
     */
    @Override
    public IGame getGame() {
        return game;
    }

    /**
     * *************************************************************************
     * Spustí komunikaci mezi implicitní hrou a danou instancí uživatelského
     * rozhraní.
     */
    @Override
    public void startGame() {
        startGame(getFactory().getGame());
    }

    /**
     * *************************************************************************
     * Spustí komunikaci mezi zadanou hrou a danou instancí mající na starosti
     * komunikaci s uživatelem.
     *
     * @param game Hra, kterou ma dané UI spustit
     */
    @Override
    public void startGame(IGame game) {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        String answer;
        for (;;) {
            answer = game.executeCommand(command);
            System.out.println(answer);
            if (!game.isAlive()) {
                break;
            }    //---------->
            command = scanner.nextLine();
        }
    }

    @Override
    public void start(Stage primaryStage) {
       
        game = getFactory().getGame();
        borderPane = new BorderPane();
        BorderPane inventory = new BorderPane();
        BorderPane neighbors = new BorderPane();
        BorderPane plan = new BorderPane();
        Label invenoryLabel = new Label("Inventář");
        Label neighborsLabel = new Label("Sousední lokace");
        Label planLabel = new Label("");
        setTextArea();
        setBottomPanel();
        planLabel.setMinWidth(130);
        
        oknoProstoru = new UI_OknoProstoru(game.getWorld());
        panelVychodu = new UI_PanelVychodu(game.getWorld());
        panelBatohu = new UI_PanelBatohu(game.getBag());
        
        inventory.setBottom(panelBatohu.getList());
        inventory.setTop(invenoryLabel);
        neighbors.setBottom(panelVychodu.getList());
        neighbors.setTop(neighborsLabel);
        plan.setLeft(planLabel);
        plan.setCenter(oknoProstoru.getPanel());
        borderPane.setRight(neighbors);
        borderPane.setTop(plan);
        borderPane.setLeft(inventory);
        borderPane.setCenter(centerTextArea);
        borderPane.setBottom(bottomFlowPane);
        initMenu();
        
        VBox vBox = new VBox();
        
        vBox.getChildren().addAll(menuBar, borderPane);
        Scene scene = new Scene(vBox, 800, 650);

        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        commandTextField.requestFocus();
        primaryStage.show();
    }

    private void setTextArea() {

        centerTextArea = new TextArea();
        String command = "";
        String answer;
        answer = game.executeCommand(command);
        answer = decorate(answer, game);
        centerTextArea.setText(answer);
        centerTextArea.setEditable(false);

    }

    private void setBottomPanel() {
        bottomFlowPane = new FlowPane();
        executecommandLabel = new Label("Zadej příkaz");
        executecommandLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        commandTextField = new TextField();
        commandTextField.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event)
            {
                
                commandExecute(commandTextField.getText());
                

            }

        });
        bottomFlowPane.setAlignment(Pos.CENTER);
        bottomFlowPane.getChildren().addAll(executecommandLabel, commandTextField);

    }
    
    private void initMenu()
    {
        menuBar = new MenuBar();
        Menu menuSoubor = new Menu("Soubor");

        MenuItem novaHra = new MenuItem("Nová hra",
        new ImageView(new Image(UI_JavaFX.class.getResourceAsStream("../zdroje/new.gif"))));
        novaHra.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t)
            
            {
                newGame();
                

            }
        });

        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        MenuItem konec = new MenuItem("Konec");
        konec.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });

        menuSoubor.getItems().addAll(novaHra, new SeparatorMenuItem(), konec);

        Menu menuNapoveda = new Menu("Nápověda");
        MenuItem oProgramu = new MenuItem("O programu");
        MenuItem napovedaKAplikaci = new MenuItem("Nápověda k aplikaci");

        menuNapoveda.getItems().addAll(oProgramu, napovedaKAplikaci);
        menuBar.getMenus().addAll(menuSoubor, menuNapoveda);
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // obsluha události O programu
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Grafická adventura");
                alert.setHeaderText("JavaFX adventura");
                alert.setContentText("verze ZS 2018");

                alert.showAndWait();
            }
        });

        napovedaKAplikaci.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // obsluha události Nápověda k aplikaci
                // sekundární okno
                Stage stage = new Stage();
                stage.setTitle("Nápověda k aplikaci");
                WebView webview = new WebView();
                webview.getEngine().load(
                        UI_JavaFX.class.getResource("/zdroje/napoveda.htm").toExternalForm()
                );
                stage.setScene(new Scene(webview, 500, 500));
                stage.show();
            }
        });

    }
    
    public void newGame()
    {
        
        if(game.isAlive()){game.executeCommand("konec");};
        String text = game.executeCommand("");
        text = decorate(text, game);
        centerTextArea.clear();
        centerTextArea.appendText("\n" + text + "\n");
        commandTextField.setText("");
        oknoProstoru.nastaveniHernihoPlanu(game.getWorld());
        panelVychodu.nastaveniHernihoPlanu(game.getWorld());
        panelBatohu.nastaveniHernihoPlanu(game.getBag());
        
    }
    
    public void commandExecute(String line)
    {
        
        String text = game.executeCommand(line);
        text = decorate(text, game);
        centerTextArea.clear();
        centerTextArea.appendText("\n" + text + "\n");
        commandTextField.setText("");
        if (!game.isAlive())
        {
            
            commandTextField.setEditable(false);

        }
        
    }

    /**
     * *************************************************************************
     * Return the answer decorated with supplement information describing the
     * current game state.
     *
     * @param answer Game answer to the last command
     * @param game The played game
     * @return The decorated answer
     */
    public String decorate(String answer, IGame game) {

        IBag bag = game.getBag();
        Collection<? extends IItem> bagItems = bag.getItems();
        IWorld world = game.getWorld();
        IPlace place = world.getCurrentPlace();
        Collection<? extends IItem> placeItems = place.getItems();

        String result = answer
                + "\n\n"
                + "\nPrávě se nacházíte: " + place
                + "\nVěci v této lokaci: " + placeItems
                + "\nVěci v kapsách " + bagItems
                + "\nPro nápovědu zadeje ? "
                + "\n";
        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                new UI_JavaFX().startGame();
            } else {
                System.out.println("Neplatny parametr");
            }
            Platform.exit();
        }
    }

}
