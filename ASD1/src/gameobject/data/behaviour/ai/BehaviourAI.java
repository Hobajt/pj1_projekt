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
import javafx.geometry.Point2D;
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
        
        state= combat.update(state);
        
        if(data.getIdle() != null)
            state= idle.update(state);
        
        //generate move direction
        //owner.move(state.getMoveDir());
        
        //owner.update(state.getMoveDir(), Rotation.getByMoveDir(state.getMoveDir()));
        owner.update(new Point2D(1,0), Rotation.getByMoveDir(new Point2D(1,0)));
        //update state and movement
    }

    BehaviourAI(BehaviourDataAI data, GameObject owner) {
        this.data= data;
        this.owner= (Creature)owner;
        
        this.idle= IdleAI.createNew(data.getIdle(), owner);
        this.combat= CombatAI.createNew(data.getAggresivity(), owner);
        state= AIState.IDLE;
    }
}
