/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.view;

import gameobject.GameObject;
import gameobject.player.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import main.Window;
import util.Const;

/**
 * Manages ScreenObjects updates and recylcing
 * @author Radek
 */
class ViewObjectManager {
    
    private final GameView view;
    
    private ScreenObject player;
    
    private final Map<Integer, ScreenObject> imgs;
    
    private final Group front;
    private final Group stat;
    private final Group back;
    
    private int filterTimer;
    private int orderTimer;
    
    public ViewObjectManager(GameView view) {
        this.view= view;
        player= null;
        imgs= new HashMap<>();
        front= Window.inst().getGroup(Window.GroupType.IN_FRONT);
        stat= Window.inst().getGroup(Window.GroupType.STATIC);
        back= Window.inst().getGroup(Window.GroupType.BEHIND);
        filterTimer= 0;
    }
    
    /**
     * Creates screen representation for all objects passed by the list
     * @param o List of GameObjects that are supposed to be drawn
     * @return Returns current central point in the view (player pos)
     */
    public Point2D update(List<GameObject> objs) {
        
        updatePlayer();
        
        //every Nth frame, filter out old ScreenObjects that are no longer relevant
        if(++filterTimer > Const.T_SCREEN_FILTER) {
            dropRedundantObjects();
            filterTimer= 0;
        }
        
        for(GameObject g : objs) {
            
            ScreenObject s= imgs.get(g.getUniqueID());
            
            //add ScreenObjects for new GameObjects
            if(s == null) {
                imgs.put(g.getUniqueID(), new ScreenObject(g, g.getData().getFlags().isDynamic() ? front : stat));
                System.out.println("-ViewManager::Adding " + g.getUniqueID());
                s= imgs.get(g.getUniqueID());
            }
            
            //update position
            s.updatePosition(
                    player.getGameObject().getTransform().getPosition(), 
                    g.getTransform().getPosition()
            );
            
            //update animation
            s.setImage(g.getState(), g.getTransform().getRotation());
        }
        
        //every Nth frame, find closest object for each dynamic GO and determine the front one
        if(++orderTimer > Const.T_SCREEN_ORDER) {
            updateObjectOrder(objs);
            orderTimer= 0;
        } 
        
        return player.getGameObject().getTransform().getPosition();
    }
    
    /**
     * Updates screen order for every object on screen (which on is in front)
     * @param objs 
     */
    private void updateObjectOrder(List<GameObject> objs) {
        
        //go through all objects
        for(GameObject o : objs) {
            innerOrderUpdate(objs, o, imgs.get(o.getUniqueID()));
        }
        innerOrderUpdate(objs, Player.inst().getObject(), player);
    }
    
    /**
     * Changes order for 1 GameObject
     * @param objs list of all objects on screen
     * @param g GameObject to update
     * @param s His screen representation
     */
    private void innerOrderUpdate(List<GameObject> objs, GameObject g, ScreenObject s) {
        if(!g.getData().getFlags().isDynamic())
                return;
        
            //for each, find closest object
            GameObject closest= objs.isEmpty() ? null : objs.get(0);
            double closestDist= g.distance(closest);
            for(GameObject o : objs) {
                if(g == o)
                    continue;
                
                double dist= g.distance(o);
                if(dist < closestDist) {
                    closest= o;
                    closestDist= dist;
                }
            }
            
            //compare distance from top left corner, move 1 into front
            s.changeParent(g.closerToCorner(closest) ? back : front);
    }
    
    /**
     * Iterates through all ScreenObjects and filters out objects, that are
     * way too far away from players current position. 
     */
    private void dropRedundantObjects() {
        
        List<Integer> toRemove= new ArrayList<>();
        
        for(Entry<Integer, ScreenObject> entry : imgs.entrySet()) {
            if(entry.getValue().getGameObject().distance(player.getGameObject()) > view.getRenderRadius() * 1.3) {
                toRemove.add(entry.getKey());
            }
        }
        
        for(Integer id : toRemove) {
            imgs.remove(id);
            System.out.println("-ViewManager::Removing " + id);
        }
    }
    
    /**
     * Updates player screen representation
     */
    public void updatePlayer() {
        Player p= Player.inst();
        
        if(player == null) {
            player= new ScreenObject(p.getObject(), Window.inst().getGroup(Window.GroupType.IN_FRONT));
        }
        
        player.updatePosition(Point2D.ZERO, Point2D.ZERO);
        player.setImage(p.getObject().getState(), p.getObject().getTransform().getRotation());
    }
}
