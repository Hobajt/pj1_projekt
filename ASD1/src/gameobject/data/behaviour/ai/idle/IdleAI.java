/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour.ai.idle;

import gameobject.Creature;
import gameobject.GameObject;
import gameobject.data.behaviour.ai.AIState;

/**
 *
 * @author Radek
 */
public abstract class IdleAI {
    
    private final Creature owner;
    private final IdleAIData data;
    
    /**
     * Movement generation is happening here (implementation specific)
     * @param state Current state of AI
     * @return Returns updated state
     */
    public abstract AIState update(AIState state);
    
    IdleAI(IdleAIData data, GameObject owner) {
        this.data= data;
        this.owner= (Creature)owner;
    }
    
    /**
     * Generates new CombatAI controller 
     * @param data Type of IdleAI to generate
     * @param owner
     * @return Returns new IdleAI
     */
    public static IdleAI createNew(IdleAIData data, GameObject owner) {
        if(data == null)
            return null;
        
        if(data.hasRoutines())
            return new IdleAIRoutines(data, owner);
        else
            return new IdleAIMovements(data, owner);
    }

    public Creature getOwner() {
        return owner;
    }

    public IdleAIData getData() {
        return data;
    }
}
