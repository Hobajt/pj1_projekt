/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data;

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
    
    public void drop() {
        cache.clear();
    }
    
    /**
     * Loads GameObject from data and stores it into cache
     * @param id Id of the gameObject
     * @return Returns the loaded gameObjectData
     */
    private GameObjectData loadData(int id) {
        System.out.format("  -ObjData::Load: (%d)%n", id);
        
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
        System.out.format("  -loaded: (%d)%n", id);
        return gd;
    }
    
    /**
     * Get GameObjectData from cache or from file, based on given ID
     * @param id Identifier of gameObject
     * @return Returns GameObject with given ID
     */
    public GameObjectData getData(int id) {
        System.out.format("-ObjData::Get: (%d)%n", id);
        if(cache.containsKey(id))
            return cache.get(id);
        
        return loadData(id);
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
    
    /**
     * <b>TEMPORARY METHOD</b>
     * Saves GameObjectData into .dat files
     */
    public void SaveObjectData() {
        
        CreatureData pl= new CreatureData(0, "PlayerObject", FlagsType.CREATURE, 0, null, null, new StatsData());
        CreatureData cr1= new CreatureData(1, "Creature", FlagsType.CREATURE, 0, null, null, new StatsData());
        CreatureData cr2= new CreatureData(2, "Creature2", FlagsType.CREATURE, 1, null, null, new StatsData());
        GameObjectData go1= new GameObjectData(3, "Tile1", FlagsType.STATIC, 2, null, null);
        GameObjectData go2= new GameObjectData(4, "Tile2", FlagsType.STATIC, 3, null, null);
        GameObjectData go3= new GameObjectData(5, "Tile2", FlagsType.STATIC, 4, null, null);
        GameObjectData go4= new GameObjectData(6, "Tile2", FlagsType.STATIC, 5, null, null);
        GameObjectData go5= new GameObjectData(7, "Tile2", FlagsType.STATIC, 6, null, null);
        GameObjectData go6= new GameObjectData(8, "Tile2", FlagsType.STATIC, 7, null, null);
        
        saveData(pl);
        saveData(cr1);
        saveData(cr2);
        saveData(go1);
        saveData(go2);
        saveData(go3);
        saveData(go4);
        saveData(go5);
        saveData(go6);
        
        System.out.println("saved");
    }
}
