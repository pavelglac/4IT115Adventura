/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adv17w.glap00_glac.textui;

import adv17w.glap00_glac.util.IObserverZmenyBatohu;
import adv17w_fw.game_txt.IBag;
import adv17w_fw.game_txt.IItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author pavel
 */
public class UI_PanelBatohu
    implements IObserverZmenyBatohu
{
    private IBag bag;
    ListView<String> list;
    ObservableList<String> data;
    private ImageView imageView;
    
       public UI_PanelBatohu(IBag bag) {
        this.bag = bag;
        bag.zaregistrujPozorovatele(this);
        init();
    }

    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(130);
        list.setPrefHeight(150);
        for (IItem item : bag.getItems()) {
            data.add(item.getName());
        }
        list.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty)
            {

                if (empty)
                {
                    setText(null);
                    setGraphic(null);
                    
                } else
                {
                    
                    super.updateItem(name, empty);
                    imageView.setImage(new Image(UI_PanelBatohu.class.getResourceAsStream("/adv17w/glap00_glac/DATA/itemsImg/"+name+".jpg"), 20, 20, false, false));
                    setText(name);
                    setGraphic(imageView);

                }
            }

        });

        
    }
    
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */
    public void nastaveniHernihoPlanu (IBag bag){
        this.bag = bag;
        bag.zaregistrujPozorovatele(this);
        this.aktualizuj();
    }
    
    @Override
    public void aktualizuj() {
        data.clear();
        for (IItem item : bag.getItems()) {
            data.add(item.getName());
        }
    }

    public ListView<String> getList() {
        return list;
    }
    
}
