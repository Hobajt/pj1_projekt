/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.model;

import gameobject.state.ObjectState;
import javafx.scene.image.Image;
import util.Rotation;

/**
 * Simple model composed of 1 image- has no states or rotations. (for static objects)
 * @author Radek
 */
public class ModelSimple extends Model {
    
    private final Image img;

    public ModelSimple() {
        super();
        img= null;
    }

    public ModelSimple(Image img) {
        super(true, ModelFactory.setOffset(img));
        this.img = img;
    }

    /**
     * Returns image representing a GameObject
     * @return 
     */
    @Override
    public Image getImage() {
        return img;
    }

    /**
     * Returns image representing a GameObject
     * @param state Useless, only here cuz of inheritance
     * @param rot Useless, only here cuz of inheritance
     * @return Returns image representing a GameObject
     */
    @Override
    public Image getImage(ObjectState state, Rotation rot) {
        return getImage();
    }
}
