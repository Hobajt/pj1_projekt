/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.combat;

import gameobject.state.ObjectState;
import java.util.Map;

/**
 *
 * @author Radek
 */
public class Combat {
    
    private long attackEnd;
    private AttackType lastAttack;
    
    private final Map<AttackType, Long> cooldowns;
    
    private final CombatData data;
    
    public Combat(CombatData data) {
        this.data= data;
        attackEnd= 0;
        cooldowns= data.generateCooldowns();
    }
    
    /**
     * True if Creature can use speficied attack
     * @param att
     * @return 
     */
    public boolean canAttack(AttackType att) {
        try {
            return cooldowns.get(att) < System.currentTimeMillis();
        } catch (NullPointerException e) {
            return false;
        }
    }
    
    /**
     * <b>Signal for attack</b><br>
     * Updates attack state- sets attack timer and specified attack's cooldown
     * @param att
     * @return 
     */
    public ObjectState attack(AttackType att) {
        if(!canAttack(att))
            return ObjectState.IDLE;
        
        Attack a= data.getAttack(att);
        System.out.println(a.getCooldown());
        
        this.lastAttack= att;
        this.cooldowns.put(att, System.currentTimeMillis() + a.getCooldown());
        this.attackEnd= System.currentTimeMillis() + a.getDuration();
        return a.getAttackState();
    }
    
    public Attack getCurrentAttack() {
        return AttackFactory.inst().getAttack(lastAttack);
    }
    
    /**
     * True if attack timer is still running
     * @return 
     */
    public boolean attackTimer() {
        return attackEnd >= System.currentTimeMillis();
    }
    
    public AttackType getNextAttackType(AttackType current) {
        return data.getNextAttackType(current);
    }
}
