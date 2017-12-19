/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.data;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import util.resource.ResourceType;
import util.resource.Resources;

/**
 *
 * @author Radek
 */
public class TileFactory {
    
    private static TileFactory instance;
    
    private TileFactory() {
        cache= new HashMap<>();
    }
    
    public static TileFactory inst() {
        if(instance == null)
            instance= new TileFactory();
        return instance;
    }
    
    private final Map<Integer, Image> cache;
    
    public Image getImage(int id) {
        if(cache.containsKey(id))
            return cache.get(id);
        return loadImage(id);
    }
    
    private Image loadImage(int id) {
        try {
            Image img= new Image(Resources.openStream(ResourceType.TILE, Integer.toString(id)));
            cache.put(id, img);
            return img;
            
        } catch (Exception e) {
            if(id == -1)
                return null;
            return getImage(-1);
        }
    }
}
