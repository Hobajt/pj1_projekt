/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.data;

import gameobject.data.GameObjectData;
import gameobject.data.ObjectDataFactory;
import util.Transform;
import gameobject.spawner.Spawner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.geometry.Point2D;
import util.Const;

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
        
        
        //horizontal walls
        drawObjectLine(ObjectDataFactory.inst().getData(4), new Point2D(0,350), 180, 19, true);
        drawObjectLine(ObjectDataFactory.inst().getData(4), new Point2D(0,-350), 180, 19, true);
        
        //vertical walls
        GameObjectData gd= ObjectDataFactory.inst().getData(8);
        drawObjectLine(gd, new Point2D(560,0), -90, 13, false);
        drawObjectLine(gd, new Point2D(-560,0), -90, 13, false);
        
        /*
        drawObjectLine(ObjectDataFactory.inst().getData(4), new Point2D(-20,150), 180, 4);
        
        drawObjectLine(ObjectDataFactory.inst().getData(4), new Point2D(-20,150), 180, 4);
        GameObjectData gd= ObjectDataFactory.inst().getData(8);
        GameObjectData gdd= ObjectDataFactory.inst().getData(9);
        Point2D end= drawObjectLine(gd, new Point2D(100,70), -90, 4);
        end= new Point2D(end.getX(), end.getY() + (gd.getModel().getSizeOffset().getY() - gdd.getModel().getSizeOffset().getY()));
        
        lst= new ArrayList<>();
        lst.add(new Transform(end));
        initialObjects.put(9, lst);*/
        
        lst= new ArrayList<>();
        lst.add(new Transform(-90,-150));
        initialObjects.put(6, lst);
        
        lst= new ArrayList<>();
        lst.add(new Transform(-50,60));
        lst.add(new Transform(-60,0));
        //lst.add(new Transform(-100,0));
        initialObjects.put(1, lst);
        
        /*
        lst= new ArrayList<>();
        lst.add(new Transform(0,150));
        initialObjects.put(5, lst);
        
        lst= new ArrayList<>();
        lst.add(new Transform(90,150));
        initialObjects.put(6, lst);
        
        lst= new ArrayList<>();
        lst.add(new Transform(180,150));
        initialObjects.put(7, lst);
        
        lst= new ArrayList<>();
        lst.add(new Transform(-90,150));
        initialObjects.put(8, lst);*/
        
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
            return new Transform();
        
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
    
    /**
     * Helper method that generates line of gameobjects
     * @param gd
     * @param startPos
     * @param angle 
     * @return Returns last position after last one
     */
    private Point2D drawObjectLine(GameObjectData gd, Point2D startPos, double angle, int amount, boolean centerX) {
        Point2D centerOff= gd.getModel().getSizeOffset().multiply(1/Const.IMG_OFFSET_SCALE).multiply(amount/2);
        
        Point2D sOff= gd.getModel().getSizeOffset().multiply(1/Const.IMG_OFFSET_SCALE);
        angle= Math.toRadians(angle);
        
        List<Transform> l= new ArrayList<>();
        for(int i= 0; i < amount; i++) {
            Point2D pos= new Point2D(sOff.multiply(i).getX() * Math.cos(angle), sOff.multiply(i).getY() * Math.sin(angle));
            pos= pos.add(startPos);
            if(centerX)
                pos= pos.add(centerOff.getX(), 0);
            else
                pos= pos.add(0, centerOff.getY());
            l.add(new Transform(pos));
        }
        if(initialObjects.containsKey(gd.getId()))
            initialObjects.get(gd.getId()).addAll(l);
        else
            initialObjects.put(gd.getId(), l);
        
        return new Point2D(sOff.multiply(amount).getX() * Math.cos(angle),
                sOff.multiply(amount).getY() * Math.sin(angle))
                .add(startPos);
    }
}
