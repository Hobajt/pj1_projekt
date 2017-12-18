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
public class CombatAINeutral extends CombatAI {
    
    @Override
    public AIState update(AIState state) {
        return AIState.IDLE;
    }

    @Override
    public boolean inCombat() {
        return false;
    }

    public CombatAINeutral(GameObject owner) {
        super(owner);
    }
}
