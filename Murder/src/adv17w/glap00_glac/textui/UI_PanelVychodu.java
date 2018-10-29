/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adv17w.glap00_glac.textui;

import adv17w.glap00_glac.util.IObserverZmenyProstoru;
import adv17w_fw.game_txt.IGame;
import adv17w_fw.game_txt.IPlace;
import adv17w_fw.game_txt.IWorld;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author pavel
 */
public class UI_PanelVychodu
    implements IObserverZmenyProstoru       
{

    private IWorld plan;
    ListView<String> list;
    ObservableList<String> data;

    public UI_PanelVychodu(IWorld plan) {
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        init();
    }

    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(130);
        list.setPrefHeight(150);
        for (IPlace neighbors : plan.getCurrentPlace().getNeighbors()) {
            data.add(neighbors.getName());
        }
    }
    
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */
    public void nastaveniHernihoPlanu (IWorld plan){
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        this.aktualizuj();
    }
    
    @Override
    public void aktualizuj() {
        data.clear();
        for (IPlace neighbors : plan.getCurrentPlace().getNeighbors()) {
            data.add(neighbors.getName());
        }
    }

    public ListView<String> getList() {
        return list;
    }
}

