/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.data;

import gameobject.GameObject;
import gameobject.collider.Collision;
import gameobject.player.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Point2D;
import util.Const;

/**
 * Handles all GameObject collisions
 * @author Radek
 */
public class CollisionEngine {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton- inst(), cons()">
    private static CollisionEngine instance;
    
    private CollisionEngine() {
        this.grid= new HashMap<>();
    }
    
    public static CollisionEngine inst() {
        if(instance == null)
            instance= new CollisionEngine();
        return instance;
    }
    //</editor-fold>
    
    private int tickCounter= 0;
    
    //devides level objects into grid of squares- each object is member of one square
    private final Map<Integer, Map<Integer, List<GameObject>>> grid;
    
    //TODO: Reduce size
    private final int GRID_X= 100;
    private final int GRID_Y= 100;
    
    /**
     * Periodically updates Collision grid, based on it's inner tickTimer
     * @param objs List of all GameObjects in the game
     */
    public void updateGrid(List<GameObject> objs) {
        if(++tickCounter > Const.T_COL_SORTING) {
            gridUpdate(objs);
            tickCounter= 0;
        }
    }

    /**
     * Checks for any collisions of given <b>dynamic</b> gameObject.<br>
     * Detection is only done within gameObject's square and all it's neighboring
     * ones. (3x3, total of 9 squares)
     * @param go GameObject for checkup
     */
    public void handleCollisions(GameObject go) {
        
        //filters static gameObjects (only for collison, they still can be collided with)
        if(!go.getData().getFlags().isDynamic())
            return;
        
        Index ind= getObjectsIndex(go);
        
        //cycles through 3x3 grid of squares around GameObject's square
        for(int x= ind.x-1; x <= ind.x+1; x++) {
            for(int y= ind.y-1; y <= ind.y+1; y++) {
                try {
                    squareCheck(go, x, y);
                } catch (NullPointerException e) {} //catches when mid square is 0 or last index
            }
        }
    }
    
    /**
     * Collision check within <b>one</b> square
     * @param go GameObject to check
     * @param x x index of the square
     * @param y y index of the square
     * @throws NullPointerException Thrown when given square is not initialized - thus is empty
     */
    private void squareCheck(GameObject go, int x, int y) throws NullPointerException {
        List<GameObject> inSquare= grid.get(y).get(x);
        
        for(GameObject o : inSquare) {
            collisionCheck(go, o);
        }
    }
    
    /**
     * Collision check between <b>two objects</b>.<br>
     * If objects are colliding, moves 1st object out of the 2nd
     * @param g1 1st object for comparison
     * @param g2 2nd object for comparison
     */
    private void collisionCheck(GameObject g1, GameObject g2) {
        if(g1 == g2)
            return;
        
        Collision col= new Collision(g1, g2);
        Point2D fix= g1.getData().getCollider().checkCollision(col);
        
        if(fix != null) {
            g1.getTransform().move(fix);
        }
    }
    
    /**
     * Updates GameObjects grid assignments
     * @param objs List of all ingame GameObjects
     */
    private void gridUpdate(List<GameObject> objs) {
        grid.clear();
        
        assignToGrid(Player.inst().getObject());
        for(GameObject o : objs) {
            assignToGrid(o);
        }
    }
    
    /**
     * Generates square index from GameObjects position
     * @param go GameObject
     * @return Returns Index of the square
     */
    private Index getObjectsIndex(GameObject go) {
        return new Index(
                (int)(go.getTransform().getPosition().getX()/GRID_X),
                (int)(go.getTransform().getPosition().getY()/GRID_Y)
        );
    }
    
    /**
     * Assigns a single GameObject to a grid
     * @param go GameObject to assign
     */
    private void assignToGrid(GameObject go) {
        
        Index ind= getObjectsIndex(go);

        //creates a row of squares (empty) if it doesnt exist yet
        if(!grid.containsKey(ind.y)) {
            grid.put(ind.y, new HashMap<>());
        }

        //creates square in destined row if it doesnt exist yet
        if(!grid.get(ind.y).containsKey(ind.x)) {
            grid.get(ind.y).put(ind.x, new ArrayList<>());
        }

        grid.get(ind.y).get(ind.x).add(go);
    }
    
    /**
     * Helper nested class to pass indexes
     */
    private class Index {
        public int x;
        public int y;

        public Index(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
