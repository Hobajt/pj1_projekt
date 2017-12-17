/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.collider;

import gameobject.GameObject;
import javafx.geometry.Point2D;

/**
 * Container for collision data
 * @author Radek
 */
public class Collision {
    
    private final GameObject thisObj;
    private final GameObject otherObj;
    
    private final Point2D gp1;
    private final Point2D gp2;

    public Collision(GameObject thisObj, GameObject otherObj) {
        this.thisObj = thisObj;
        this.otherObj = otherObj;
        this.gp1= thisObj.getTransform().getPosition();
        this.gp2= otherObj.getTransform().getPosition();
    }

    public GameObject getThis() {
        return thisObj;
    }
    
    public GameObject getOther() {
        return otherObj;
    }
    
    /**
     * Checks if otherObj's collider is of Collider type
     * @return Returns true if it is
     */
    public boolean isOtherObjRectangle() {
        return otherObj.getData().getCollider() instanceof Collider;
    }
    
    /**
     * Get collider attached to otherObject
     * @return 
     */
    public Collider getOtherCollider() {
        return otherObj.getData().getCollider();
    }
    
    /**
     * Calulcautes distance between colliding objects
     * @return Returns distance between both objects
     */
    public Point2D getObjectDistance() {
        return thisObj.getTransform().getPosition().subtract(otherObj.getTransform().getPosition());
    }
    
    /**
     * Calcalates difference between both colliders (applies offsets to them)
     * @return Returns difference as Point2D
     */
    public Point2D getColDifference() {
        return thisObj.getData().getCollider().getColPosition(thisObj)
                .subtract(otherObj.getData().getCollider().getColPosition(otherObj));
    }
    
    /**
     * Determines whether otherObject is closer to top border of the map
     * @return Returns true if otherObj is closer to the top border
     */
    public boolean isOtherBehindYAxis() {
        return thisObj.getTransform().getPosition().getY() >= otherObj.getTransform().getPosition().getY();
    }
    
    /**
     * Determines whether otherObject is closer to left border of the map
     * @return Returns true if otherObj is closer to the left border
     */
    public boolean isOtherBehindXAxis() {
        return thisObj.getTransform().getPosition().getX() >= otherObj.getTransform().getPosition().getX();
    }
}
