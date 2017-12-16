/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.data;

import gameobject.Transform;
import gameobject.spawner.Spawner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Contains all data related to objects on current level
 * @author Radek
 */
class ObjectData {

    private Map<Integer, List<Transform>> initialObjects;
    private List<Spawner> spawners;
    private List<Transform> playerSpawn;
    
    /**
     * Loads object data for this level or throws exception on failure
     * @param levelID
     * @throws LevelLoadingException Thrown when file access errors out
     */
    private void load(String levelID) throws LevelLoadingException {
        
        initialObjects= new HashMap<>();
        spawners= new ArrayList<>();
        playerSpawn= new ArrayList<>();
        
        //TODO: rework ObjectData loading- to load from files
        
        List<Transform> lst;
        
        //initialObjects
        lst= new ArrayList<>();
        lst.add(Transform.Origin());
        initialObjects.put(0, lst);
        lst= new ArrayList<>();
        lst.add(Transform.Origin());
        lst.add(Transform.Origin());
        initialObjects.put(1, lst);
        
        lst= new ArrayList<>();
        lst.add(Transform.Origin());
        lst.add(Transform.Origin());
        lst.add(Transform.Origin());
        initialObjects.put(0, lst);
        
        //playerSpawn
        //lst= Arrays.asList(new Transform())
        
        //throw new LevelLoadingException("Object data loading error");
    }
    
    /**
     * Constructor, loads ObjectData
     * @param levelID Identification of the level
     * @throws LevelLoadingException Thrown when File reading fails
     */
    public ObjectData(String levelID) throws LevelLoadingException {
        if(levelID == null)
            levelID= "default";
        
        load(levelID);
        System.out.println("--Object data loading--");
    }

    /**
     * Randomizes player spawn point selection
     * @return Returns random Spawn position for player
     */
    public Transform getRandomPlayerSpawn() {
        if(playerSpawn.size() < 1)
            return Transform.Origin();
        
        Random rd= new Random();
        return playerSpawn.get(rd.nextInt(playerSpawn.size()));
    }
    
    
    /**
     * Filters initial Object pool based on GameObject type
     * @param predicate Predicate used for filtering
     * @return Returns new filtered map of <gObjData, transforms>
     */
    public Map<Integer, List<Transform>> filterObjects(Predicate<Integer> predicate) {
        return initialObjects.entrySet()
                .stream()
                .filter(entry -> predicate.test(entry.getKey()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }
    
    /**
     * @return Returns amount of initial objects present on this level
     */
    public int getObjectCount() {
        int counter= 0;
        for(List<Transform> t : initialObjects.values()) {
            counter += t.size();
        }
        return counter;
    }
    
    public Map<Integer, List<Transform>> getInitialObjects() {
        return initialObjects;
    }
    
    public List<Spawner> getSpawners() {
        return spawners;
    }

    public List<Transform> getPlayerSpawn() {
        return playerSpawn;
    }
}
