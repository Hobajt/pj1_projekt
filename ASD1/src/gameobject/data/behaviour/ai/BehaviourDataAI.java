/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour.ai;

import gameobject.GameObject;
import gameobject.data.behaviour.Behaviour;
import gameobject.data.behaviour.BehaviourData;
import gameobject.data.behaviour.ai.combat.CombatAIType;
import gameobject.data.behaviour.ai.idle.IdleAIData;
import util.Const;

/**
 * Data for AI behaviour
 * @author Radek
 */
public class BehaviourDataAI implements BehaviourData {

    private final int detectDistance;
    private final CombatAIType aggresivity;
    private final IdleAIData idle;
    
    @Override
    public Behaviour createInstance(GameObject g) {
        return new BehaviourAI(this, g);
    }

    public BehaviourDataAI() {
        this(Const.BASE_WIDTH, CombatAIType.NEUTRAL, null);
    }
    
    public BehaviourDataAI(CombatAIType aggresivity, IdleAIData idle) {
        this(Const.BASE_WIDTH, aggresivity, idle);
    }
    
    public BehaviourDataAI(int detectDistance, CombatAIType aggresivity, IdleAIData idle) {
        this.detectDistance = detectDistance;
        this.aggresivity = aggresivity;
        this.idle = idle;
    }

    public IdleAIData getIdle() {
        return idle;
    }

    public CombatAIType getAggresivity() {
        return aggresivity;
    }

    public int getDetectDistance() {
        return detectDistance;
    }
}
