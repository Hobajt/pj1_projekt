/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.model;

import util.Rotation;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;
import gameobject.state.ObjectState;

/**
 * More complex version of model. Allows switching between various states
 * and has image for every rotation.
 * @author Radek
 */
public class ModelComplex extends Model {
    
    private final Map<ObjectState, List<Image>> imgs;
    
    /**
     * Constructor for cases, when model completely fails to load (not even img fillers)
     */
    public ModelComplex() {
        super();
        imgs= null;
    }
    
    /**
     * Takes images mapped to state.<br>
     * For each state, there is N images (N = rotation value count)
     * @param byState Map of images
     * @param valid True if no image in the picture is missing.
     */
    ModelComplex(Map<ObjectState, List<Image>> byState, boolean valid) {
        super(valid, ModelFactory.setOffset(byState.values().iterator().next().get(0)));
        
        this.imgs= byState;
    }

    /**
     * Returns image for default state
     * @return Returns an Image of default state
     */
    @Override
    public Image getImage() {
        return imgs.get(ObjectState.IDLE).get(Rotation.getDefault().ordinal());
    }
    
    /**
     * Get image for a given state in direction.<br>
     * If this model doesn't contain specified state, IDLE state is chosen instead.<br>
     * If there is no image for specified direction, IMG_MISSING image is given instead.
     * @param state ObjectState which you want to retrieve
     * @param rot Rotation of the image
     * @return Image representing state in direction.
     */
    @Override
    public Image getImage(ObjectState state, Rotation rot) {
        try {
            if(imgs.containsKey(state))
                return imgs.get(state).get(rot.ordinal());
            else
                return imgs.get(ObjectState.IDLE).get(rot.ordinal());
        } catch (NullPointerException e) {
            return IMAGE_MISSING;
        }
    }

    Map<ObjectState, List<Image>> getImgs() {
        return imgs;
    }
}
