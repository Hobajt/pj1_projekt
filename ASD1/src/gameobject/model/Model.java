/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.model;

import gameobject.Direction;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;

/**
 * Set of images, that represent GameObject on screen (as an animation)
 * @author Radek
 */
public class Model {
    //TODO: [Resources] Update class Model
    
    private final Map<Direction,List<Image>> imgs;
    
    /**
     * Takes images mapped to direction, each direction has n images (n = state count)
     * @param imgs 
     */
    Model(Map<Direction, List<Image>> byDirection) {
        
        
        //TODO: ATTEMPT TO BUILD AND RUN JAR TO CHECK IF RESOURCE LOADING WORKS CORRECTLY
        
        
        
        this.imgs= byDirection;
    }
    
//    /**
//     * Takes images mapped by a state (each state has image in every direction)
//     * @param imgs 
//     */
//    Model(Map<Integer, List<Image>> byState) {
//        
//        this.imgs= new HashMap<>();
//        for(List<Image> inDir : byState.values()) {
//            
//        }
//    }
    
    /**
     * Get image for a given state in direction
     * @param state State which you want to retrieve
     * @param rot Rotation of the image
     * @return Image
     */
    public Image getImage(int state, Direction rot) {
        return imgs.get(rot).get(state);
    }
}
