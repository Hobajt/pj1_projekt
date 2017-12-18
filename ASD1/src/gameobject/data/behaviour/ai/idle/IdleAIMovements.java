/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour.ai.idle;

import gameobject.GameObject;
import gameobject.data.behaviour.ai.AIState;
import java.util.Random;
import javafx.geometry.Point2D;
import util.Const;

/**
 * Idle behaviour for random movements around central pivot
 * @author Radek
 */
public class IdleAIMovements extends IdleAI {
    
    private final int radius;   //max distance from pivot
    
    private long lastMove;      //time of last point generation
    
    private Point2D lastPos;    //position in last update
    private Point2D target;     //target of movement
    private Point2D pivot;      //central point
    
    /**
     * Periodic update for Idle movements
     * @param state AIState to update
     * @return Returns updated state
     */
    @Override
    public AIState update(AIState state) {
        
        //no movement if AI is not in idle state
        if(state != AIState.IDLE)
            return state;
        
        Random rd= new Random();
        Point2D currPos= getOwner().getTransform().getPosition();
        
        //calculate probability and make a move
        if(moveTrigger(rd)) {
            target= generatePoint(rd);
            return state.setMoveDir(getMoveDir(currPos));
        }
        
        //updates move direction
        return updateState(state, currPos);
    }
    
    /**
     * Updates the move direction on the state.<br>
     * Breaks the movement when Creature is at the same position as it was last
     * update (when stuck on collider for example)
     * @param state State that is to be updated
     * @param currPos Creature's current position
     * @return Returns updated AIState
     */
    private AIState updateState(AIState state, Point2D currPos) {
        
        //target distance check
        if(target.distance(currPos) <= Const.MOVE_BREAK_DISTANCE)
            return state.setMoveDir(Point2D.ZERO);
        
        if(currPos.distance(lastPos) < 0.5) {
            return state.setMoveDir(Point2D.ZERO);
        }
        
        lastPos= currPos;
        Point2D moveDir= getMoveDir(currPos);
        return state.setMoveDir(moveDir);
    }
    
    /**
     * Calculates move direction from current and target position
     * @param currPos Current position (calculated elsewhere)
     * @return Returns Point2D- direction of the move
     */
    private Point2D getMoveDir(Point2D currPos) {
        return target.subtract(currPos).normalize();
    }
    
    /**
     * Calculates the movement trigger based on initial probability and 
     * last move timer
     * @return Returns true if move is to trigger
     */
    private boolean moveTrigger(Random rd) {
        if(rd.nextDouble() < getData().getProbability() * timeInfluence()) {
            lastMove= System.currentTimeMillis();
            return true;
        }
        return false;
    }
    
    /**
     * Generates additional probability based on time difference from last trigger
     * @return Returns double value (1/100th)s of time difference
     */
    private double timeInfluence() {
        return (System.currentTimeMillis() - lastMove)*1e-5;
    }
    
    /**
     * Generates random new point within the bounds of Data parameters
     * @return Returns Point2D
     */
    private Point2D generatePoint(Random rd) {
        return new Point2D(getRandomVal(rd), getRandomVal(rd)).multiply(radius).add(pivot);
    }
    
    private double getRandomVal(Random rd) {
        return ((rd.nextDouble()*2)-1);
    }
    
    public IdleAIMovements(IdleAIData data, GameObject owner) {
        super(data, owner);
        radius= data.getRadius();
        changePivot(owner.getTransform().getPosition());
    }
    
    public final void changePivot(Point2D pivot) {
        this.pivot= pivot;
        lastPos= target= pivot;
    }
}
