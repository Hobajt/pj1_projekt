/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.menu;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import util.Const;

/**
 *
 * @author Radek
 */
public class ControlList {
    
    private Group parent;
    
    private Group inParent;
    private MenuButton back;
    private MenuButton background;
    
    private MainMenu menu;
    
    private MenuButton t1,t2,t3,t4;
    
    public void visible(boolean val) {
        if(!val) {
            parent.getChildren().remove(inParent);
        }
        else {
            parent.getChildren().add(inParent);
        }
        inParent.setVisible(val);
    }
    
    private void goBack() {
        visible(false);
        menu.setInnerBlock(false);
    }
    
    public ControlList(MainMenu menu, Group parent) {
        this.menu= menu;
        this.parent= parent;
        inParent= new Group();
        
        
        background= new MenuButton("", this::goBack, Const.BUTTON1_S1, 0.5, 0.5, 0.6, 0.6);
        back= new MenuButton("Back", this::goBack, Const.BUTTON1_S2, 0.5, 0.8, 0.15, 0.05);
        t1= new MenuButton("Pohyb:      WASD", this::goBack, Const.BUTTON1_S1, 0.5, 0.3, 0.5, 0.05);
        t2= new MenuButton("Utok:      SPACE", this::goBack, Const.BUTTON1_S1, 0.5, 0.4, 0.5, 0.05);
        t3= new MenuButton("Zmenit zbran:  R", this::goBack, Const.BUTTON1_S1, 0.5, 0.5, 0.5, 0.05);
        t4= new MenuButton("Cil hry- zabit vse co se hybe", this::goBack, Const.BUTTON1_S1, 0.5, 0.6, 0.5, 0.05);
        
        inParent.getChildren().addAll(background.getButton(), back.getButton(), t1.getButton(), 
                t2.getButton(), t3.getButton(), t4.getButton());
    }
}
