/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data;

import gameobject.data.flags.FlagsFactory;
import gameobject.data.flags.FlagsType;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import util.resource.ResourceType;
import util.resource.Resources;

/**
 * Loads GameObjectData objects from local files and caches them
 * @author Radek
 */
public class ObjectDataFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton- inst(), cons()">
    private static ObjectDataFactory instance;

    private ObjectDataFactory() {
        cache= new HashMap<>();
    }
    
    public static ObjectDataFactory inst() {
        if(instance == null)
            instance= new ObjectDataFactory();
        return instance;
    }
    //</editor-fold>
    
    private final Map<Integer, GameObjectData> cache;
    
    /**
     * Loads GameObject from data and stores it into cache
     * @param id Id of the gameObject
     * @return Returns the loaded gameObjectData
     */
    private GameObjectData loadData(int id) {
        
        GameObjectData gd= null;
        try (ObjectInputStream load= new ObjectInputStream(
                Resources.openStreamOrDefault(ResourceType.OBJECT, Integer.toString(id), "default"))) {
            
            gd= (GameObjectData) load.readObject();
            cache.put(id, gd);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gd;
    }
    
    /**
     * Get GameObjectData from cache or from file, based on given ID
     * @param ID Identifier of gameObject
     * @return Returns GameObject with given ID
     */
    public GameObjectData getData(int ID) {
        if(cache.containsKey(ID))
            return cache.get(ID);
        
        return loadData(ID);
    }
    
    
    /**
     * <b>TEMPORARY METHOD</b><br>
     * Saves data into file ID corresponding with GameObject's ID
     * @param data 
     */
    public void saveData(GameObjectData data) {
        
        try (ObjectOutputStream out= new ObjectOutputStream(
                Resources.save(ResourceType.OBJECT, Integer.toString(data.getId())))) {
            
            out.writeObject(data);
            
        } catch (IOException e) {
            System.err.println("Error while saving data: " + e.getMessage());
        }
    }
    
    public void testSave() {
        
        CreatureData cd= new CreatureData(420, "testCr", FlagsType.CREATURE, 3, null, null, null);
        GameObjectData gd= new GameObjectData(69, "testGo", FlagsType.STATIC, 0, null, null);
        
        saveData(cd);
        saveData(gd);
        System.out.println("saved");
    }
}
