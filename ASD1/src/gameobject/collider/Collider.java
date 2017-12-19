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

    public boolean detectCollision(Point2D pos, ColliderSpecial cd, GameObject me) {
        double xCol, yCol;
        Point2D dif= me.getTransform().getPosition().subtract(cd.getPos(pos));
        
        //check xAxis
        xCol= Math.abs(dif.getX()) - (x + cd.getX());
        if(xCol > 0)
            return false;
        
        //check yAxis
        yCol= Math.abs(dif.getY()) - (y + cd.getY());
        if(yCol > 0)
            return false;
        
        return true;
    }
    
    /**
     * Checks for collision between two GameObjects
     * @param col
     * @return Returns the smallest fix vector out of the collision or null
     * if no collision is happening
     */
    public Point2D checkCollision(Collision col) {
        
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
            
        
        //check for triggers
        GameObject trg= validateTrigger(col);
        if(trg != null) {
            triggerTrigger(trg, col);
            return null;
        }
        
        //returns the closest vector out of the collision
        if(xCol > yCol)
            return new Point2D(col.isOtherBehindXAxis() ? -xCol : xCol, 0);
        else
            return new Point2D(0, col.isOtherBehindYAxis() ? -yCol : yCol);
    }
    
    private void triggerTrigger(GameObject trg, Collision col) {
        trg.triggerTriggered((trg == col.getThis()) ? col.getOther() : col.getThis());
    }
    
    /**
     * Checks whether one (only 1, not 2) of these colliders is a trigger
     * @param col Collision data
     * @return Returns false, if none of them is trigger
     */
    private GameObject validateTrigger(Collision col) {
        GameObject r= null;
        if(col.getThis().getData().getCollider().isTrigger())
            r= col.getThis();
        
        if(col.getOther().getData().getCollider().isTrigger()) {
            if(r == null)
                r= col.getOther();
            else
                r= null;
        }
        
        return r;
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
