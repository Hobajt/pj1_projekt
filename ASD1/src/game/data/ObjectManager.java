/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.data;

import game.Game;
import gameobject.Creature;
import util.Rotation;
import gameobject.GameObject;
import gameobject.ObjectFactory;
import gameobject.player.Player;
import util.Transform;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.geometry.Point2D;

/**
 * Manages instances of GameObjects present on the current level
 * @author Radek
 */
public class ObjectManager {
    
    private final Game game;
    private final ObjectData data;
    
    private final List<GameObject> objStatic;
    private final List<GameObject> objDynamic;
    
    private Player player;
    
    /**
     * Creates new dynamic GameObject at the selected location on the level
     * @param gdID Identifier of GameObjectData to load
     * @param trans Location to instantiate on
     * @return Returns the reference to created GameObject
     */
    public GameObject instantiate(int gdID, Transform trans) {
        
        //create it
        GameObject gob= ObjectFactory.inst().createNew(game.getUID(), 0, trans);
        
        //check if is dynamic
        if(!gob.getData().getFlags().isDynamic())
            return null;
        
        //add to list
        objDynamic.add(gob);
        return gob;
    }
    
    /**
     * Creates new dynamic GameObject at the selected location on the level
     * @param gdID Identifier of GameObjectData to load
     * @param pos Position
     * @param rot Rotation
     * @return Returns the reference to created GameObject
     */
    public GameObject instantiate(int gdID, Point2D pos, Rotation rot) {
        return instantiate(gdID, new Transform(pos, rot));
    }
    
    /**
     * Returns to initial state
     */
    public void reset() {
        serverReset(game.generateUIDs(data.getObjectCount()));
    }
    
    /**
     * Reset from the server
     * @param uIDs Unique IDs received from server (or generated in Game controller)
     */
    public void serverReset(List<Integer> uIDs) {
        int n= 0;
        objDynamic.clear();
        
        //local players uID will always be 0, Creature ID might be set in some const value
        
        //reload player object
        player= new Player( (Creature)ObjectFactory.inst().createNew(0, 0, data.getRandomPlayerSpawn()));
        
        //reload initial objects
        for(Integer id : data.getInitialObjects().keySet()) {
            for(Transform t : data.getInitialObjects().get(id)) {
                GameObject g= ObjectFactory.inst().createNew(uIDs.get(n++), id, t);
                
                if(g.getData().getFlags().isDynamic())
                    objDynamic.add(g);
                else
                    objStatic.add(g);
            }
        }
        
        System.out.format("--ObjManager reloaded- Dynamic: (%d), Static: (%d)--%n", objDynamic.size(), objStatic.size());
    }
    
    /**
     * Returns list of objects that are within certain distance from player
     * @param radius Radius in pixels that limit the selection
     * @return Returns list of gameobjects
     */
    public List<GameObject> getObjectsInRadius(int radius) {
        
        //filters and joins together both lists
        return Stream.concat(
                objStatic.stream().filter(go -> withinRadius(go, radius)),
                objDynamic.stream().filter(go -> withinRadius(go, radius)))
                .collect(Collectors.toList());
    }
    
    /**
     * Checks if given gameObject is in specified distance from player
     * @param g Object to compare
     * @param radius Maximum distance
     * @return Returns true if Object is within the range
     */
    private boolean withinRadius(GameObject g, int radius) {
        return player.getObject().distance(g) <= radius;
    }
    
    /**
     * Constructor- Requires references
     * @param game Game controller reference (for uniqueID generation)
     * @param data Object Data reference
     */
    public ObjectManager(Game game, LevelData data) {
        this.game= game;
        this.data= data.getObjectData();
        objStatic= new ArrayList<>();
        objDynamic= new ArrayList<>();
        System.out.println("--ObjManager initialized--");
    }

    public List<GameObject> getObjDynamic() {
        return objDynamic;
    }

    public List<GameObject> getObjStatic() {
        return objStatic;
    }

    public Player getPlayer() {
        return player;
    }
}
