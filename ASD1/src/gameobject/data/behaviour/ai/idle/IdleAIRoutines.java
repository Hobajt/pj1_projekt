/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour.ai.idle;

import gameobject.GameObject;
import gameobject.data.behaviour.ai.AIState;
import java.util.List;
import util.Point;

/**
 * Idle behaviour for movement around predefined path
 * @author Radek
 */
public class IdleAIRoutines extends IdleAI {
    
    private List<Point> route;
    
    private transient int nextStop;

    @Override
    public AIState update(AIState state) {
        return AIState.IDLE;
    }
    
    public IdleAIRoutines(IdleAIData data, GameObject owner) {
        super(data, owner);
    }
}
