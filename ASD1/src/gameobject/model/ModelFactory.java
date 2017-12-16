/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.model;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import util.resource.ResourceType;
import util.resource.Resources;

/**
 * Manages Model caching and instantiation
 * @author Radek
 */
public class ModelFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton- inst(), cons()">
    private static ModelFactory instance;

    private ModelFactory() {
        cache= new HashMap<>();
    }
    
    public static ModelFactory inst() {
        if(instance == null) 
            instance= new ModelFactory();
        return instance;
    }
    //</editor-fold>
    
    private final Map<Integer, Model> cache;
    
    //TODO: [Model] Rework ModelFactory
    
    private Model loadModel(int id) {
        
        //ID is a directory -> subDirs contain different states
        
        
        return new Model(null);
    }
    
    public Model getModel(int id) {
        return null;
    }
}
