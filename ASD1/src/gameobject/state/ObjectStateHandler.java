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
    private long expireTime;

    /**
     * Updates this object's state based on input
     * @param moveDir
     * @param stats
     */
    public void update(Point2D moveDir, Stats stats) {
        boolean canModify= true;
        
        if(state.ordinal() >= ObjectState.MELEE.ordinal()) {
            //System.out.println((expireTime) + "\n" + System.currentTimeMillis());
            
            if(stats.combat().attackTimer()) {
                canModify= false;
            }
        }
        
        if(canModify) {
            //apply movement
            state= isMoving(moveDir) ? ObjectState.RUN : ObjectState.IDLE;
        }
    }
    
    private boolean isMoving(Point2D moveDir) {
        return (moveDir.getX() != 0 || moveDir.getY() != 0);
    }
    
    public void setState(ObjectState state) {
        this.setState(state, 0);
    }
    
    public void setState(ObjectState state, long expireTime) {
        this.state= state;
        this.expireTime= System.currentTimeMillis() + expireTime;
    }
    
    public ObjectStateHandler() {
        state= ObjectState.IDLE;
    }

    public ObjectState getState() {
        return state;
    }
}
