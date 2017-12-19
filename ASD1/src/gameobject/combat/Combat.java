/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.combat;

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
    
    /**
     * True if Creature can use speficied attack
     * @param att
     * @return Returns true if specified attack can be used.
     */
    public boolean canUseAttack(AttackType att) {
        try {
            return cooldowns.get(att) < System.currentTimeMillis();
        } catch (NullPointerException e) {
            return false;
        }
    }
    
    /**
     * True if attack timer is still running
     * @return 
     */
    public boolean attInProgress() {
        return attackEnd >= System.currentTimeMillis();
    }
    
    /**
     * Getter for ongoing attack progress.
     * @return Returns cast time progress in millis (returns negative if no attack
     * is progressing)
     */
    public int getProgress() {
        return (int)(attackEnd - System.currentTimeMillis());
    }
    
    /**
     * Getter for ongoing attack progress in %.
     * @return Returns % of attack's progress.
     */
    public float getProgressPercentage() {
        return (float)((attackEnd - System.currentTimeMillis())/data.getAttack(lastAttack).getCooldown());
    }
    
    /**
     * <b>Attack signal</b><br>
     * Updates attack state- sets attack timer and specified attack's cooldown
     * @param att AttackType to trigger
     * @return Returns true on successfull attack trigger.
     */
    public boolean attack(AttackType att) {
        if(!canUseAttack(att))
            return false;
        
        Attack a= data.getAttack(att);
        this.cooldowns.put(att, System.currentTimeMillis() + a.getCooldown());
        this.attackEnd= System.currentTimeMillis() + a.getDuration();
        this.lastAttack= att;
        
        return true;
    }
    
    public Combat(CombatData data) {
        this.data= data;
        attackEnd= 0;
        cooldowns= data.generateCooldowns();
    }
    
    public Attack getCurrentAttack() {
        return AttackFactory.inst().getAttack(lastAttack);
    }
    
    /**
     * Generates next basic attack available (for player and his basic attack switching)
     * @param current Current basic attack
     * @return Returns the basic attack after the current
     */
    public AttackType getNextAttackType(AttackType current) {
        return data.getNextAttackType(current);
    }
}
