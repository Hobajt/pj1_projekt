/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.state;

import gameobject.Creature;
import gameobject.GameObject;

/**
 * Specifies a state of GameObject - needed for animation
 * @author Radek
 */
public enum ObjectState {
    IDLE,
    RUN,
    MELEE,
    RANGED;
    
    /**
     * Generates correct state handler based on type of GameObject (Creature/GameObject)
     * @param g Owner of the state handler
     * @return Returns matching handler
     */
    public static ObjectStateHandler createHandler(GameObject g) {
        return (g instanceof Creature) ? new CreatureStateHandler() : new ObjectStateHandler();
    }
}
