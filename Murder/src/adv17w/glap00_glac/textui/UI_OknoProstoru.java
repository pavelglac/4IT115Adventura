/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adv17w.glap00_glac.textui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import adv17w.glap00_glac.util.IObserverZmenyProstoru;
import adv17w_fw.game_txt.IGame;
import adv17w_fw.game_txt.IWorld;

/**
 *
 * @author pavel
 */
public class UI_OknoProstoru
    implements IObserverZmenyProstoru 

    {

    private Circle pointer;
    private IWorld plan;
    private AnchorPane topAnchorPane;

    public UI_OknoProstoru(IWorld plan) {
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        setTopPanel();
    }

    private void setTopPanel() {
        topAnchorPane = new AnchorPane();

        ImageView mapImageView = new ImageView(new Image(UI_JavaFX.class.getResourceAsStream("/adv17w/glap00_glac/DATA/SpaceMap.png"), 540, 410, false, false));
        pointer = new Circle(10, Paint.valueOf("red"));

        AnchorPane.setTopAnchor(pointer, plan.getCurrentPlace().getPosTop());
        AnchorPane.setLeftAnchor(pointer, plan.getCurrentPlace().getPosLeft());

        topAnchorPane.getChildren().addAll(mapImageView, pointer);

    }
    
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * 
     * @param plan
     */
    public void nastaveniHernihoPlanu (IWorld plan){
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        this.aktualizuj();
    }
    
    public void aktualizuj() {
     AnchorPane.setTopAnchor(pointer, plan.getCurrentPlace().getPosTop());
     AnchorPane.setLeftAnchor(pointer, plan.getCurrentPlace().getPosLeft());

  }


    public AnchorPane getPanel() {
        return topAnchorPane;
    }

}
