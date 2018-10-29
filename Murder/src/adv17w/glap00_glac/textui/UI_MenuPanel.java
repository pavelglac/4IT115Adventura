/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adv17w.glap00_glac.textui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author pavel
 */
public class UI_MenuPanel
{
    private MenuBar menuBar;
    private UI_JavaFX gui;
    
    public UI_MenuPanel() {
         gui = new UI_JavaFX();
    }
    
    public MenuBar initMenu() {
        menuBar = new MenuBar();

        // --- Menu Soubor
        Menu menuSoubor = new Menu("Soubor");

//         MenuItem novaHra = new MenuItem("Nová hra");
        MenuItem novaHra = new MenuItem("Nová hra",
        new ImageView(new Image(UI_JavaFX.class.getResourceAsStream("../zdroje/new.gif"))));
        novaHra.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                gui.newGame();
//                game.executeCommand("konec");
//                String text = game.executeCommand("");
//                text = decorate(text, game);
//                centerTextArea.clear();
//                centerTextArea.appendText("\n" + text + "\n");
//                commandTextField.setText("");
//                oknoProstoru.nastaveniHernihoPlanu(game.getWorld());
//                panelVychodu.nastaveniHernihoPlanu(game.getWorld());

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
        return menuBar;
    }    
}
