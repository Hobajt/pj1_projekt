/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data;

import gameobject.combat.CombatData;
import java.io.Serializable;

/**
 * Data class that describes creatures stat values
 * @author Radek
 */
public class StatsData implements Serializable {
    
    //Stats might be alterable (dunno yet tho)
    
    private final int health;
    private final float moveSpeed;
    private final CombatData combat;

    private static final int BASE_HEALTH= 50;
    private static final float BASE_MS= 2;
    
    public StatsData() {
        this(new CombatData());
    }
    
    public StatsData(CombatData cmb) {
        this(BASE_HEALTH, BASE_MS, cmb);
    }
    
    public StatsData(int health, float ms) {
        this(health, ms, new CombatData());
    }
    
    public StatsData(int health, float ms, CombatData cmb) {
        this.health= health;
        this.moveSpeed= ms;
        this.combat= cmb;
    }
    
    public int getHealth() {
        return health;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public CombatData getCombat() {
        return combat;
    }
}
