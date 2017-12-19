/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour.ai.combat;

import gameobject.GameObject;
import gameobject.data.behaviour.ai.AIState;

/**
 *
 * @author Radek
 */
public class CombatAIAggressive extends CombatAI {
    
    @Override
    public AIState update(AIState state) {
        return AIState.IDLE;
        
        //determine the distance
        
        //generally- melee will be stronger than ranged
        
        //if has any ranged attack
            //attack ranged until youre in melee range
            //attack melee otherwise
        
        //else if melee
            //move closer if not close enough
            //spam attacks (cycle em)
    }

    @Override
    public boolean inCombat() {
        return false;
    }

    public CombatAIAggressive(GameObject owner) {
        super(owner);
    }
}
