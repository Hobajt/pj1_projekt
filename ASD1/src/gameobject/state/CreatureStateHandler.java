/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.state;

import gameobject.Stats;
import gameobject.combat.Attack;
import javafx.geometry.Point2D;

/**
 * Extension to ObjectStateHandler for Creatures. Allows integration with attacks (that can
 * be performed only by Creatures.
 * @author Radek
 */
public class CreatureStateHandler extends ObjectStateHandler {

    /**
     * Updates this object's state based on input
     * @param moveDir
     * @param stats
     */
    @Override
    public void update(Point2D moveDir, Stats stats) {
        boolean canModify= true;
        
        //if attack i happening
        if(getState().ordinal() >= ObjectState.MELEE.ordinal()) {
            
            canModify= !stats.combat().attInProgress();
        }
        
        if(canModify) {
            //apply movement
            setState(isMoving(moveDir) ? ObjectState.RUN : ObjectState.IDLE);
        }
    }
    
    private boolean isMoving(Point2D moveDir) {
        return (moveDir.getX() != 0 || moveDir.getY() != 0);
    }
    
    public void setAttack(Attack att) {
        if(att != null)
            setState(att.getAttackState(0));
    }
}
