/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.state;

import gameobject.Stats;
import javafx.geometry.Point2D;

/**
 * Class that manages objects state- applies priorities to certain states.
 * For example, any immediate state (like attacks) will have priority over 
 * movement states.
 * @author Radek
 */
public class ObjectStateHandler {
    
    private ObjectState state;

    /**
     * Updates this object's state based on input
     * @param moveDir
     * @param stats
     */
    public void update(Point2D moveDir, Stats stats) {
        state= (moveDir.getX() != 0 || moveDir.getY() != 0) ? ObjectState.RUN : ObjectState.IDLE;
    }
    
    public void setState(ObjectState state) {
        this.state= state;
    }
    
    ObjectStateHandler() {
        state= ObjectState.IDLE;
    }

    public ObjectState getState() {
        return state;
    }
}
