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
    private final int dDistance;
    private final CombatAIType type;
    
    public abstract boolean inCombat();
    
    public abstract AIState update(AIState state);

    public CombatAI(CombatAIType type, int dDistance, GameObject owner) {
        this.owner= (Creature) owner;
        this.dDistance= dDistance;
        this.type= type;
    }
    
    /**
     * Generates new CombatAI controller 
     * @param type Type of CombatAI to generate
     * @param dDistance
     * @param owner
     * @return Returns new CombatAI
     */
    public static CombatAI createNew(CombatAIType type, int dDistance, GameObject owner) {
        switch(type) {
            case NEUTRAL:
                return new CombatAINeutral(type, dDistance, owner);
            case FRIGHTENED:
                return new CombatAIFrightened(type, dDistance, owner);
            case AGGRESSIVE:
                return new CombatAIAggressive(type, dDistance, owner);
        }
        return null;
    }

    public Creature getOwner() {
        return owner;
    }

    public CombatAIType getType() {
        return type;
    }

    public int getdDistance() {
        return dDistance;
    }
}
