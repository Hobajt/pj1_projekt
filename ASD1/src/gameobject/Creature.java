/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import gameobject.collider.CollisionEngine;
import gameobject.combat.Attack;
import gameobject.combat.AttackFactory;
import gameobject.combat.AttackType;
import util.Transform;
import gameobject.data.CreatureData;
import gameobject.state.CreatureStateHandler;
import javafx.geometry.Point2D;
import util.Rotation;

/**
 * Instance of Advanced GameObject within the game
 * @author Radek
 */
public class Creature extends GameObject {
    
    private final Stats stats;
    
    public void update(Point2D moveDir, Rotation rot) {
        if(!stats.combat().attInProgress()) {
            move(moveDir);
            rotate(rot);
        }
        else {
            attackMovement(moveDir, rot);
        }
        
        getStateHandler().update(moveDir, stats);
        CollisionEngine.inst().handleCollisions(this);
    }
    
    /**
     * For movement and rotation update controlled by ongoing attack
     */
    private void attackMovement(Point2D moveDir, Rotation rot) {
        //get objects direction and current attack data
        Point2D objDir= Rotation.getMove(getTransform().getRotation());
        Attack att= stats.combat().getCurrentAttack();
        
        //modifies the move and rotation
        int attProgress= stats.combat().getProgress();
        super.move(att.getMove(attProgress, moveDir, objDir));
        rotate(att.getRotation(attProgress, rot, getTransform().getRotation()));
    }
    
    Creature(int uID, CreatureData data, Transform transform) {
        super(uID, data, transform);
        stats= new Stats(getData().getStatsData());
    }

    public Stats getStats() {
        return stats;
    }
    
    /**
     * Shortcut- getter for attack permission check
     * @param att Attack that should be checked
     * @return Returns true if attack is good to go
     */
    public boolean canAttack(AttackType att) {
        return stats.combat().canUseAttack(att);
    }
    
    /**
     * Modifies Creature's position + <b> applies movementSpeed </b>
     * @param moveDir Direction of the move (no need to normalize)
     */
    @Override
    public void move(Point2D moveDir) {
        super.move(moveDir.normalize().multiply(getData().getStatsData().getMoveSpeed()));
    }
    
    /**
     * Overridden Object's data getter
     * @return Returns <b>CreatureData</b> instead of GameObjectData
     */
    @Override
    public final CreatureData getData() {
        return (CreatureData) super.getData();
    }

    /**
     * Overridden getter for StateHandler
     * @return 
     */
    @Override
    public CreatureStateHandler getStateHandler() {
        return (CreatureStateHandler)super.getStateHandler();
    }
    
    /**
     * Shortcut to retrieve attack object based on type
     * @param att
     * @return 
     */
    public Attack getAttack(AttackType att) {
        if(!canAttack(att))
            return null;
        return AttackFactory.inst().getAttack(att);
    }
    
    /**
     * Shortcut- triggered when attack is initialized. Updates state handler
     * @param att Attack that just started
     */
    public void attack(AttackType att) {
        getStateHandler().setAttack(getAttack(att));
        stats.combat().attack(att);
    }
}
