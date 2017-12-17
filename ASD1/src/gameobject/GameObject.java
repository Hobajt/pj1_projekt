/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import util.Transform;
import gameobject.data.GameObjectData;
import gameobject.state.ObjectStateHandler;
import javafx.geometry.Point2D;
import gameobject.state.ObjectState;
import util.Rotation;

/**
 * Instance of GameObject within the game
 * @author Radek
 */
public class GameObject {
    
    private final int uniqueID;
    
    private final Transform transform;
    private final GameObjectData data;
    private final ObjectStateHandler state;
    
    /**
     * Calculates the distance between player and given GameObject (World space)
     * @param other GameObject that is to be compared
     * @return Returns distance as a double
     */
    public double distance(GameObject other) {
        return this.transform.getPosition().distance(other.transform.getPosition());
    }
    
    /**
     * Shortcut to modifying current transform position
     * @param moveDir Move direction
     */
    public void move(Point2D moveDir) {
        transform.move(moveDir);
    }
    
    public void rotate(Rotation rot) {
        this.transform.rotate(rot);
    }
    
    GameObject(int uID, GameObjectData data, Transform transform) {
        this.uniqueID= uID;
        this.data= data;
        this.transform= transform;
        
        this.state= new ObjectStateHandler();
    }

    public int getUniqueID() {
        return uniqueID;
    }
    
    public GameObjectData getData() {
        return data;
    }

    public Transform getTransform() {
        return transform;
    }

    public ObjectStateHandler getStateHandler() {
        return this.state;
    }
    
    public void setState(ObjectState state) {
        this.state.setState(state);
    }
    
    public ObjectState getState() {
        return state.getState();
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", getData().getName(), uniqueID);
    }
}
