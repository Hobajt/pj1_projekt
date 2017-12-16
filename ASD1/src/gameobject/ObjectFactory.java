/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import util.Transform;
import gameobject.data.CreatureData;
import gameobject.data.GameObjectData;
import gameobject.data.ObjectDataFactory;

/**
 * Handles GameObject instance creation
 * @author Radek
 */
public class ObjectFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton- inst(), cons()">
    private static ObjectFactory instance;

    private ObjectFactory() {
    }
    
    public static ObjectFactory inst() {
        if(instance == null)
            instance= new ObjectFactory();
        return instance;
    }
    //</editor-fold>
    
    /**
     * Creates new GameObject instance
     * @param uID Unique Identifier for this exact GameObject instance
     * @param id ID of the GameObjectData to load
     * @param tr spawn transform
     * @return Return GameObject instance
     */
    public GameObject createNew(int uID, int id, Transform tr) {
        
        GameObjectData gd= ObjectDataFactory.inst().getData(id);
        GameObject g;
        
        if(gd instanceof CreatureData)
            g= new Creature(uID, (CreatureData)gd, tr);
        else
            g= new GameObject(uID, gd, tr);
        
        System.out.format("-Obj::Create: (%d:%d)%n", id, uID);
        return g;
    }
}
