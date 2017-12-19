/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour.ai;

import gameobject.Creature;
import gameobject.GameObject;
import gameobject.data.behaviour.Behaviour;
import gameobject.data.behaviour.ai.combat.CombatAI;
import gameobject.data.behaviour.ai.idle.IdleAI;
import util.Rotation;

/**
 * Basic class for AI implementation
 * @author Radek
 */
public class BehaviourAI implements Behaviour {
    
    private final BehaviourDataAI data;
    private final Creature owner;
    
    private final IdleAI idle;
    private final CombatAI combat;
    private AIState state;
    
    /**
     * Updates the NPC GameObject
     */
    @Override
    public void update() {
        
        //update for combat (prior to idle)
        state= combat.update(state);
        
        //update for idle state
        if(data.getIdle() != null)
            state= idle.update(state);
        
        //update state and movement
        owner.update(state.getMoveDir(), Rotation.getByMoveDir(state.getMoveDir()));
    }
    
    @Override
    public void trigger(GameObject col) {
        return;
    }

    BehaviourAI(BehaviourDataAI data, GameObject owner) {
        this.data= data;
        this.owner= (Creature)owner;
        
        this.idle= IdleAI.createNew(data.getIdle(), owner);
        this.combat= CombatAI.createNew(data.getAggresivity(), data.getDetectDistance(), owner);
        state= AIState.IDLE;
    }
}
