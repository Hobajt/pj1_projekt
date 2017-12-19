/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.view;

import gameobject.GameObject;
import gameobject.model.Model;
import util.Rotation;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import main.Window;
import gameobject.state.ObjectState;

/**
 * Represents gameObject as it gets drawn on the screen
 * @author Radek
 */
class ScreenObject {
    
    private final GameObject gameObject;
    private final Model model;
    private final ImageView img;

    private Group parent;
    
    //MAYBE CONTROL INSTANTIATION THROUH A FACTORY
    
    /**
     * Switches this screen object into a specified group
     * @param p Parent group that this object is moving into
     */
    public final void changeParent(Group p) {
        if(p == parent)
            return;
        
        if(parent != null)
            parent.getChildren().remove(img);
        if(p != null) {
            p.getChildren().add(img);
        }
        parent= p;
    }
    
    /**
     * Updates the position of this ScreenObject to correspond with its GameObject
     * @param center Center of the screen
     * @param goPosition Position of GameObject
     */
    public void updatePosition(Point2D center, Point2D goPosition) {
        Point2D screenPos= Window.inst().getScreenPoint(center, goPosition).subtract(model.getSizeOffset());
        img.setX(screenPos.getX());
        img.setY(screenPos.getY());
        
        Point2D sSize= Window.inst().getScreenSize();
        if(screenPos.getX() < -60 || screenPos.getX() > sSize.getX()+75 ||
                screenPos.getY() < -75 || screenPos.getY() > sSize.getY()+75) {
            
            img.setVisible(false);
        }
        else
            img.setVisible(true);
    }
    
    /**
     * Changes the active image for this object. Image is taken from
     * this object's model.
     * @param state Action state of GameObject
     * @param rotation Rotation of the image
     */
    public void setImage(ObjectState state, Rotation rotation) {
        try {
            this.img.setImage(model.getImage(state, rotation));
        } catch (NullPointerException e) {
            this.img.setImage(Model.IMAGE_MISSING);
        }
    }
    
    /**
     * Constructor- requires model and initial group to fall under
     * @param model Model that will be drawn on screen
     * @param parent Parent FX group that specifies what will be in front and back
     */
    public ScreenObject(GameObject gameObject, Group parent) {
        this.img = new ImageView();
        this.gameObject= gameObject;
        this.model= gameObject.getData().getModel();
        
        changeParent(parent);
    }
    
    public ImageView getImg() {
        return img;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
    
    public Model getModel() {
        return model;
    }
}
