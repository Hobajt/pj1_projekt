/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour.ai.combat;

import gameobject.GameObject;
import gameobject.combat.AttackType;
import gameobject.data.behaviour.ai.AIState;
import gameobject.player.Player;
import javafx.geometry.Point2D;

/**
 *
 * @author Radek
 */
public class CombatAIAggressive extends CombatAI {
    
    @Override
    public AIState update(AIState state) {
        
        //determine the distance
        double dist= Player.inst().getObject().distance(getOwner());
        if(dist > getdDistance()) {
            return AIState.IDLE;
        }
        state= AIState.COMBAT;
        
        if(dist > 40) {
            state.setMoveDir(moveTowardsPlayer());
        }
        else if (getOwner().canAttack(AttackType.MELEE)) {
            getOwner().attack(AttackType.MELEE);
        }
        return state;
    }

    @Override
    public boolean inCombat() {
        return false;
    }

    public CombatAIAggressive(CombatAIType type, int dDistance, GameObject owner) {
        super(type, dDistance, owner);
    }
    
    private Point2D moveTowardsPlayer() {
        return Player.inst().getObject().getTransform().getPosition().subtract(getOwner().getTransform().getPosition()).normalize();
    }
}
