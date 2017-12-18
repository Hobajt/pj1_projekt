/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import gameobject.combat.Attack;
import gameobject.combat.AttackType;
import util.Transform;
import gameobject.data.CreatureData;
import javafx.geometry.Point2D;
import util.Rotation;

/**
 * Instance of Advanced GameObject within the game
 * @author Radek
 */
public class Creature extends GameObject {
    
    private final Stats stats;
    
    Creature(int uID, CreatureData data, Transform transform) {
        super(uID, data, transform);
        
        stats= new Stats(getData().getStatsData());
    }

    public Stats getStats() {
        return stats;
    }
    
    /**
     * Returns true if attack timer is not running
     * @param att
     * @return 
     */
    public boolean canAttack(AttackType att) {
        return stats.combat().canAttack(att);
    }
    
    /**
     * In addition to modifying position, applies this objects movementSpeed to it
     * @param moveDir Direction of the move
     */
    @Override
    public void move(Point2D moveDir) {
        super.move(moveDir.normalize().multiply(getData().getStatsData().getMoveSpeed()));
    }
    
    @Override
    public final CreatureData getData() {
        return (CreatureData) super.getData();
    }
    
    public void update(Point2D moveDir, Rotation rot) {
        if(!stats.combat().attackTimer()) {
            move(moveDir);
            rotate(rot);
        }
        else {
            //attack specific moves
            Point2D moveVal= Rotation.getMove(getTransform().getRotation());
            Attack att= stats.combat().getCurrentAttack();
            moveVal= new Point2D(moveVal.getX() * att.getMove().getX(), moveVal.getY() * att.getMove().getY());
            
            super.move(moveVal);
            if(!att.isFreezeRotation())
                rotate(rot);
        }
        
        getStateHandler().update(moveDir, stats);
    }
    
    public void attack(AttackType att) {
        getStateHandler().setState(stats.combat().attack(att));
    }
}
