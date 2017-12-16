/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.state;

import gameobject.player.PlayerInput;

/**
 * Class that manages objects state- applies priorities to certain states.
 * For example, any immediate state (like attacks) will have priority over 
 * movement states.
 * @author Radek
 */
public class ObjectStateHandler {
    
    private ObjectState state;

    /**
     * Updates this object state based on player input
     * @param input Players input class
     */
    public void update(PlayerInput input) {
        
        state= input.isMoving() ? ObjectState.RUN : ObjectState.IDLE;
    }
    
    public ObjectStateHandler() {
        state= ObjectState.IDLE;
    }

    public ObjectState getState() {
        return state;
    }

    public void setState(ObjectState state) {
        this.state = state;
    }
}
