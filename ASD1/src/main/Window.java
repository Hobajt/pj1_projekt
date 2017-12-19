/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import util.Const;

/**
 * Class that handles FX hierarchy.
 * @author Radek
 */
public class Window implements Resizable {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton- inst(), cons()">
    private static Window instance;
    
    private Window() {
        groups= new HashMap<>();
        
        groups.put(GroupType.ROOT, new Group());
        groups.put(GroupType.BACK, new Group());
        groups.put(GroupType.BEHIND, new Group());
        groups.put(GroupType.STATIC, new Group());
        groups.put(GroupType.IN_FRONT, new Group());
        groups.put(GroupType.MENU, new Group());
        groups.put(GroupType.TEXT, new Group());
        
        groups.get(GroupType.ROOT).getChildren().addAll(
            groups.get(GroupType.BACK),
            groups.get(GroupType.BEHIND),
            groups.get(GroupType.STATIC),
            groups.get(GroupType.IN_FRONT),
            groups.get(GroupType.MENU),
            groups.get(GroupType.TEXT)
        );
        
        scene= new Scene(groups.get(GroupType.ROOT), Const.BASE_WIDTH, Const.BASE_HEIGHT, Const.BACKGROUND);
    }
    
    public static Window inst() {
        if(instance == null) {
            instance= new Window();
            instance.onResize();
        }
        return instance;
    }
    //</editor-fold>
    
    private Point2D screenSize;
    private Point2D screenHalf;
    
    private final Map<GroupType, Group> groups;
    private final Scene scene;
    
    /**
     * Calculates new size variables
     */
    @Override
    public void onResize() {
        screenSize= new Point2D(scene.getWidth(), scene.getHeight());
        screenHalf= screenSize.multiply(0.5);
    }
    
    /**
     * Method that translates viewport point into screen point
     * @param x Viewport X <0,1>
     * @param y Viewport Y <0,1>
     * @return Returns the given point translated to screen position (pixels)
     * as a <b>Point2D</b>
     */
    public Point2D getScreenPoint(double x, double y) {
        return new Point2D(screenSize.getX()*x, screenSize.getY()*y);
    }
    
    /**
     * Converts real position into screen position
     * @param center <b>World space</b> point, which is in the center of screen (typically player)
     * @param realPos <b>World space</b> of the point to convert
     * @return Returns <b>Point2D</b> representation of point in screen space
     */
    public Point2D getScreenPoint(Point2D center, Point2D realPos) {
        return center.subtract(realPos).add(screenHalf);
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
    
    public Scene getScene() {
        return scene;
    }
    
    public Point2D getScreenSize() {
        return screenSize;
    }

    public Point2D getScreenHalf() {
        return screenHalf;
    }
    
    public enum GroupType {
        ROOT(0),        //Parent of all these groups
        BACK(1),        //Tiles and background images
        BEHIND(2),      //Group for dynamic gameObjects to draw behind static
        STATIC(3),      //Static objects layer
        IN_FRONT(4),    //For dynamic to draw above static
        MENU(5),        //Menu layer, above all else
        TEXT(6);        //labels and stuff
        
        int val;
        
        public int getValue() {
            return val;
        }
        
        GroupType(int n) {
            val= n;
        }
    }
}
