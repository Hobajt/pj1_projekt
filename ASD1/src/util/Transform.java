/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.geometry.Point2D;

/**
 * Describes location within the level
 * @author Radek
 */
public class Transform {
    
    private Point2D position;
    private Rotation rotation;
    
    public Transform(Point2D pos, Rotation rot) {
        this.position= pos;
        this.rotation= rot;
    }
    
    public Transform(Point2D p) {
        this.position= p;
        this.rotation= Rotation.getDefault();
    }
    
    public Transform() {
        this.position= Point2D.ZERO;
        this.rotation= Rotation.getDefault();
    }
    
    public Transform(int x, int y) {
        this(new Point2D(x,y));
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
    
    public void move(Point2D pos) {
        position= position.add(pos);
    }
    
    public void rotate(Rotation rot) {
        this.rotation= rot;
    }

    public Point2D getPosition() {
        return position;
    }

    public Rotation getRotation() {
        return rotation;
    }
}
