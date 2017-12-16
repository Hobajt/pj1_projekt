/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import javafx.geometry.Point2D;

/**
 * Describes location within the level
 * @author Radek
 */
public class Transform {
    private Point2D position;
    private Direction rotation;
    
    public Transform(Point2D pos, Direction rot) {
        this.position= pos;
        this.rotation= rot;
    }
    
    public static Transform Origin() {
        return new Transform(Point2D.ZERO, Direction.EAST);
    }
    
    public void move(Point2D pos) {
        this.position= pos;
    }
    
    public void rotate(Direction rot) {
        this.rotation= rot;
    }
}
