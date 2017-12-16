/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import game.input.InputManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import util.Const;

/**
 * Class that handles FX hierarchy.
 * @author Radek
 */
public class Window {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton- inst(), cons()">
    private static Window instance;
    
    private Window() {
        groups= new HashMap<>();
        
        groups.put(GroupType.ROOT, new Group());
        groups.put(GroupType.BACK, new Group());
        groups.put(GroupType.MID, new Group());
        groups.put(GroupType.FRONT, new Group());
        groups.put(GroupType.MENU, new Group());
        
        groups.get(GroupType.ROOT).getChildren().addAll(
            groups.get(GroupType.BACK),
            groups.get(GroupType.MID),
            groups.get(GroupType.FRONT),
            groups.get(GroupType.MENU)
        );
        
        scene= new Scene(groups.get(GroupType.ROOT), Const.BASE_WIDTH, Const.BASE_HEIGHT, Const.BACKGROUND);
        InputManager.inst().mapInputKeys(scene);
    }
    
    public static Window inst() {
        if(instance == null) 
            instance= new Window();
        return instance;
    }
    //</editor-fold>
    
    private final Map<GroupType, Group> groups;
    private final Scene scene;
    
    public Scene getScene() {
        return scene;
    }
    
    /**
     * Translates viewport point into screen point
     * @param x Viewport X <0,1>
     * @param y Viewport Y <0,1>
     * @return Returns the given point translated to screen position (pixels)
     * as a <b>Point2D</b>
     */
    public Point2D getScreenPoint(double x, double y) {
        return new Point2D(scene.getWidth()*x, scene.getHeight()*y);
    }
    
    /**
     * Getter for every possible group (hierarchy parent) there is
     * @param g Group identifier
     * @return Returns the group with given ID
     */
    public Group getGroup(GroupType g) {
        return groups.get(g);
    }
    
    /**
     * Cleans menu parent from all children.
     * @return Returns the menu
     */
    public Group getCleanMenu() {
        Group g= getGroup(GroupType.MENU);
        g.getChildren().clear();
        return g;
    }
    
    public enum GroupType {
        ROOT(0),
        BACK(1),
        MID(2),
        FRONT(3),
        MENU(4);
        
        int val;
        
        public int getValue() {
            return val;
        }
        
        GroupType(int n) {
            val= n;
        }
    }
}
