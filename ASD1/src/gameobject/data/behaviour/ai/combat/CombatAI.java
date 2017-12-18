/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour.ai.combat;

import gameobject.Creature;
import gameobject.GameObject;
import gameobject.data.behaviour.ai.AIState;

/**
 * Describes controller for AI behaviour during combat
 * @author Radek
 */
public abstract class CombatAI {
    
    private final Creature owner;
    
    public abstract boolean inCombat();
    
    public abstract AIState update(AIState state);

    public CombatAI(GameObject owner) {
        this.owner= (Creature) owner;
    }
    
    /**
     * Generates new CombatAI controller 
     * @param type Type of CombatAI to generate
     * @param owner
     * @return Returns new CombatAI
     */
    public static CombatAI createNew(CombatAIType type, GameObject owner) {
        switch(type) {
            case NEUTRAL:
                return new CombatAINeutral(owner);
            case FRIGHTENED:
                return new CombatAIFrightened(owner);
            case AGGRESSIVE:
                return new CombatAIAggressive(owner);
        }
        return null;
    }
}
