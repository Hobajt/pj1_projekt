/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.collider;

import gameobject.GameObject;
import java.io.Serializable;
import javafx.geometry.Point2D;

/**
 *
 * @author Radek
 */
public class Collider implements Serializable {
    
    private final boolean trigger;
    private final int offX, offY;
    private final int x,y;

    public Point2D checkCollision(Collision col) {
        
        if(validateTrigger(col)) {
            //do trigger stuff
            return null;
        }
        
        Collider other= col.getOtherCollider();
        double xCol, yCol;
        Point2D dif= col.getColDifference();
        
        //check xAxis
        xCol= Math.abs(dif.getX()) - (x + other.x);
        if(xCol > 0)
            return null;
        
        //check yAxis
        yCol= Math.abs(dif.getY()) - (y + other.y);
        if(yCol > 0)
            return null;
            
        if(xCol > yCol)
            return new Point2D(col.isOtherBehindXAxis() ? -xCol : xCol, 0);
        else
            return new Point2D(0, col.isOtherBehindYAxis() ? -yCol : yCol);
    }
    /**
     * Checks whether any of these colliders is a trigger
     * @param col Collision data
     * @return Returns false, if none of them is trigger
     */
    private boolean validateTrigger(Collision col) {
        return false;
    }
    
    public Point2D getColPosition(GameObject g) {
        return g.getTransform().getPosition().add(getOffset());
    }
    
    public Point2D getOffset() {
        return new Point2D(offX, offY);
    }

    Collider(boolean trigger, Point2D offset, int x, int y) {
        this.trigger = trigger;
        this.x = x;
        this.y = y;
        this.offX= (int)offset.getX();
        this.offY= (int)offset.getY();
        
    }
        
    public boolean isTrigger() {
        return trigger;
    }
}
