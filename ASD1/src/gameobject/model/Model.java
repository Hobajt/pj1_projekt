/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.model;

import gameobject.state.ObjectState;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import util.Rotation;
import util.resource.ResourceType;
import util.resource.Resources;

/**
 * Graphical representation of GameObject on screen.
 * @author Radek
 */
public abstract class Model {
    
    public static final Image IMAGE_MISSING= imageMissingLoad();
    
    private final boolean valid;
    private final Point2D sizeOffset;

    public abstract Image getImage();
    public abstract Image getImage(ObjectState state, Rotation rot);
    
    public Model() {
        this.valid = false;
        this.sizeOffset = new Point2D(IMAGE_MISSING.getWidth(), IMAGE_MISSING.getHeight());
    }

    public Model(boolean valid, Point2D sizeOffset) {
        this.valid = valid;
        this.sizeOffset = sizeOffset;
    }

    public Point2D getSizeOffset() {
        return sizeOffset;
    }
    
    /**
     * Initializes static final empty image
     * @return 
     */
    private static Image imageMissingLoad() {
        try{ 
            return new Image(Resources.openStream(ResourceType.IMAGE, "none"));
        } catch (Exception e) {
            e.printStackTrace();
            return new Image("none");
        }
    }
}
