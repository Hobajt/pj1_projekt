/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data;

import gameobject.collider.Collider;
import gameobject.collider.ColliderBuilder;
import gameobject.collider.ColliderType;
import gameobject.data.behaviour.ai.combat.CombatAIType;
import gameobject.data.flags.FlagsType;
import gameobject.model.ModelFactory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Point2D;
import util.Const;
import util.resource.ResourceType;
import util.resource.Resources;
import gameobject.data.behaviour.BehaviourData;
import gameobject.data.behaviour.ai.BehaviourDataAI;
import gameobject.data.behaviour.ai.idle.IdleAIData;
import gameobject.data.behaviour.projectile.BehaviourDataProjectile;

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
        //System.out.format("  -ObjData::Load: (%d)%n", id);
        
        GameObjectData gd= null;
        try (ObjectInputStream load= new ObjectInputStream(
                Resources.openStreamOrDefault(ResourceType.OBJECT, Integer.toString(id), "default"))) {
            
            gd= (GameObjectData) load.readObject();
            cache.put(id, gd);
            
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        //System.out.format("  -loaded: (%d)%n", id);
        return gd;
    }
    
    /**
     * Get GameObjectData from cache or from file, based on given ID
     * @param id Identifier of gameObject
     * @return Returns GameObject with given ID
     */
    public GameObjectData getData(int id) {
        if(cache.containsKey(id))
            return cache.get(id);
        
        return loadData(id);
    }
    
    /**
     * <b>TEMPORARY METHOD</b>
     * Saves GameObjectData into .dat files
     */
    public void SaveDataBulk() {
        
        saveDataHelper(0, "PlayerObject", FlagsType.CREATURE, 0, 
                ColliderBuilder.inst().buildNew(ModelFactory.inst().getModel(0), 
                        ColliderType.CREATURE, new Point2D(0,
                            -ModelFactory.inst().getModel(0).getSizeOffset().getY()*(0.95-Const.IMG_CREATURE_SCALE)),
                        false), new StatsData());
        
        saveDataHelper(1, "Creature 1", FlagsType.CREATURE, 0, 
                ColliderBuilder.inst().buildNew(ModelFactory.inst().getModel(0),
                         ColliderType.CREATURE, new Point2D(0,
                            -ModelFactory.inst().getModel(0).getSizeOffset().getY()*(0.95-Const.IMG_CREATURE_SCALE)),
                        false), new StatsData(), 
                        new BehaviourDataAI(125, CombatAIType.AGGRESSIVE, new IdleAIData(0.1f, 45)));
        
        saveDataHelper(2, "Creature 2", FlagsType.CREATURE, 0, 
                ColliderBuilder.inst().buildNew(ModelFactory.inst().getModel(2),
                        ColliderType.NORMAL, Point2D.ZERO, false), new StatsData());
        
        //gameobjects
        saveDataHelper(4, "Tile4", FlagsType.STATIC, 4, 
                ColliderBuilder.inst().buildNew(ModelFactory.inst().getModel(4),
                        ColliderType.HALF_Y, new Point2D(0, -10), false), null);
        
        saveDataHelper(5, "Tile5", FlagsType.STATIC, 5, 
                ColliderBuilder.inst().buildNew(ModelFactory.inst().getModel(5),
                        ColliderType.NORMAL, Point2D.ZERO, false), null);
        
        saveDataHelper(6, "Tile6", FlagsType.STATIC, 6, 
                ColliderBuilder.inst().buildNew(ModelFactory.inst().getModel(6),
                        ColliderType.HALF_Y, Point2D.ZERO, false), null);
        
        saveDataHelper(7, "Tile7", FlagsType.STATIC, 7, 
                ColliderBuilder.inst().buildNew(ModelFactory.inst().getModel(8),
                        ColliderType.NORMAL, Point2D.ZERO, false), null);
        
        saveDataHelper(8, "Tile5", FlagsType.STATIC, 8, 
                ColliderBuilder.inst().buildNew(ModelFactory.inst().getModel(8),
                        ColliderType.NORMAL, Point2D.ZERO, false), null);
        
        saveDataHelper(9, "Tile5", FlagsType.STATIC, 9, 
                ColliderBuilder.inst().buildNew(ModelFactory.inst().getModel(9),
                        ColliderType.HALF_Y, Point2D.ZERO, false), null);
        
        saveDataHelper(10, "Projectile", FlagsType.PREFAB, 10, 
                ColliderBuilder.inst().buildNew(ModelFactory.inst().getModel(10),
                        ColliderType.MIDWAY, Point2D.ZERO, true), null,
                new BehaviourDataProjectile(5,15));
    }
    
    private void saveDataHelper(int id, String name, FlagsType t, int mID, Collider collider, StatsData st) {
        GameObjectData save;
        if(st == null)
            save= new GameObjectData(id, name, t, mID, collider, null);
        else
            save= new CreatureData(id, name, t, mID, collider, null, st);
        
        saveData(save);
    }
    
    private void saveDataHelper(int id, String name, FlagsType t, int mID, Collider collider, StatsData st, BehaviourData bh) {
        GameObjectData save;
        if(st == null)
            save= new GameObjectData(id, name, t, mID, collider, bh);
        else
            save= new CreatureData(id, name, t, mID, collider, bh, st);
        
        saveData(save);
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
            //System.err.println("Error while saving data: " + e.getMessage());
        }
    }
}
