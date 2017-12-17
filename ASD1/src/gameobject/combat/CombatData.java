/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.combat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains attacks, that can specific Creature do
 * @author Radek
 */
public class CombatData implements Serializable {
    
    private final List<AttackType> attacks;
    
    public CombatData() {
        this.attacks= new ArrayList<>();
        attacks.add(AttackType.MELEE);
        attacks.add(AttackType.RANGED);
        attacks.add(AttackType.CHARGE);
    }
    
    public CombatData(List<AttackType> attacks) {
        this.attacks= attacks;
    }

    public Attack getAttack(AttackType att) {
        if(!attacks.contains(att))
            return null;
        
        return AttackFactory.inst().getAttack(att);
    }
    
    /**
     * Generates cooldown structure for Combat
     * @return 
     */
    Map<AttackType, Long> generateCooldowns() {
        Map<AttackType, Long> m = new HashMap<>();
        
        attacks.forEach((t) -> {
            m.put(t, (long)0);
        });
        
        return m;
    }
    
    /**
     * Returns next attack after the current attack
     * @param current
     * @return 
     */
    AttackType getNextAttackType(AttackType current) {
        int ind= attacks.indexOf(current);
        return getFirstNonSpecialAttack(ind+1);
    }
    
    private AttackType getFirstNonSpecialAttack(int ind) {
        int check;
        for(int i= 0; i < attacks.size(); i++) {
            if(ind + i < attacks.size())
                check= ind+i;
            else
                check= ind+i -attacks.size();
            
            Attack a= AttackFactory.inst().getAttack(attacks.get(check));
            if(!a.isSpecial())
                return attacks.get(check);
        }
        return null;
    }
}
