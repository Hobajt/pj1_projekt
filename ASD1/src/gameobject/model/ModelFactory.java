/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;
import gameobject.state.ObjectState;
import javafx.geometry.Point2D;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import util.Const;
import util.Rotation;
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
    private boolean validation;     //value used during loading
    
    /**
     * Loads model either from cache or from local files
     * @param id Identification for the model
     * @return Returns Model based on given ID
     */
    public Model getModel(int id) {
        if(cache.containsKey(id))
            return cache.get(id);
        return loadModel(id, true);
    }
    
    public void distinctPlayer(ImageView iv) {
        iv.setEffect(new ColorAdjust(0.2,0,0,1));
    }
    
    /**
     * Clears the cache
     */
    public void drop() {
        cache.clear();
    }
    
    /**
     * Loads a simple model from the files
     * @param id Id of the searched model
     * @return Returns the loaded Model (or filler model if not found)
     */
    private Model loadSimpleModel(int id) {
        InputStream in= Resources.openStream(ResourceType.MODEL.buildPath("simple/" + id));
        if(in == null) {
            in= Resources.openStream(ResourceType.IMAGE.buildPath("models/simple/" + id));
            if(in == null)
                return new ModelSimple();
        }
        
        Model m= new ModelSimple(new Image(in));
        cache.put(id, m);
        return m;
    }
    
    /**
     * Loads a model from files and stores it into cache
     * @param id Id of model to load
     * @return Returns the loaded model
     */
    private Model loadModel(int id, boolean cacheIt) {
        //System.out.format("  -Model::Load: (%d)%n", id);
        validation= true;
        
        Map<ObjectState,List<Image>> data= new HashMap<>();
        
        //load model states
        List<ObjectState> modelStates= loadModelStates(Integer.toString(id));
        if(modelStates == null) {
            return loadSimpleModel(id);
        }
        
        //load each state's images in each direction
        modelStates.forEach((state) -> {
            data.put(state, loadStateImages(id, state));
        });
        
        Model m= new ModelComplex(data, validation);
        if(!cache.containsKey(id) && cacheIt)
            cache.put(id, m);
        //System.out.format("  -loaded: (%d)%n", id);
        return m;
    }
    /*
    private Model testLoadModel(int id) {
        Map<ObjectState,List<ModelImage>> data= new HashMap<>();
        
         //load model states
        List<ObjectState> modelStates= loadModelStates(Integer.toString(id));
        if(modelStates == null) {
            return loadSimpleModel(id);
        }
        
        //load each state's images in each direction
        modelStates.forEach((state) -> {
            data.put(state, testLoadStateImages(id, state));
        });
        
        Model m= null;//new ModelComplex1(data, validation);
        if(!cache.containsKey(id))
            cache.put(id, m);
        System.out.format("  -loaded: (%d)%n", id);
        return m;
    }
    
    private List<ModelImage> testLoadStateImages(int id, ObjectState state) {
        List<ModelImage> l= new ArrayList<>();
        
        String path= ResourceType.IMAGE.buildFolderPath(String.format("models/%d/%s/", id, state));
        
        List<Image> rl;
        for(int i= 0; i < Rotation.values().length; i++) {
            rl= new ArrayList<>();
            int n= 0;
            InputStream is;
            while((is= Resources.openStream(path + i + "/" + n + ".png")) != null) {
                Image m= new Image(is);
                rl.add(m);
            }
            l.add(new ModelImage(rl));
        }
        return l;
    }*/
    
    /**
     * Loads all images for given state of a model<br>
     * Loads 1 image for 1 rotation value
     * @param id ID of model you wish to load
     * @param state State that you want to load
     * @return Returns List of images (1 for each direction), if any image is 
     * missing, it's replaced by IMG_MISSING image
     */
    private List<Image> loadStateImages(int id, ObjectState state) {
        
        List<Image> l= new ArrayList<>(Rotation.values().length);
        String sID= ResourceType.MODEL.buildFolderPath(String.format("%d/%s/", id, state.name()));
        
        for(int i= 0; i < Rotation.values().length; i++) {
            InputStream in= Resources.openStream(String.format("%s%d.gif", sID, i));
            if(in == null) {
                l.add(Model.IMAGE_MISSING);
                validation= false;
                continue;
            }
            l.add(new Image(in));
        }
        
        return l;
    }
    
    /**
     * Loads model type and states from model.dat file in current models folder.<br>
     * File specifies model type and states which does this model have
     * @param id Model identifier (string)
     * @return Returns List of model's states or null if the model is simple
     */
    @SuppressWarnings("unchecked")
    private List<ObjectState> loadModelStates(String id) {
        InputStream in= Resources.openStream(ResourceType.MODEL.buildFolderPath(id + "/model.dat"));
        if(in == null)
            return null;
        //System.out.println(ResourceType.MODEL.buildFolderPath(id + "/model.dat"));
        
        try (ObjectInputStream read= new ObjectInputStream(in)) {
            boolean isSimple= read.readBoolean();
            if(isSimple)
                return null;
            
            return (List<ObjectState>) read.readObject();
            
            
        } catch (Exception e) {
            //System.err.format("Model states loading error: (%s)%n", id);
            //e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Returns halfed size of Image
     * @param img Image to process
     * @return Returns half of image's size as Point2D
     */
    static final Point2D setOffset(Image img) {
        return new Point2D(img.getWidth(), img.getHeight()).multiply(Const.IMG_OFFSET_SCALE);
    }
    
    /**
     * <b>TEMPORARY METHOD</b>
     * For model state saving- needed for identification of different states
     * @param id Id of model
     * @param states States to save
     */
    public static void SaveModelStates(String id, List<ObjectState> states) {
        
        //System.err.println("MODEL-SAVE");
        try (ObjectOutputStream out= new ObjectOutputStream(
                Resources.save(ResourceType.MODEL.buildFolderPath(id + "/model.dat")))) {
            
            out.writeBoolean(states == null);
            out.writeObject(states);
            
        } catch (IOException e) {
            //System.err.println("Error while saving data: " + e.getMessage());
        }
    }
}
