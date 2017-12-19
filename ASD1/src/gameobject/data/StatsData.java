/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data;

import gameobject.combat.CombatData;
import java.io.Serializable;
import util.Const;

/**
 * Data class that describes creatures stat values
 * @author Radek
 */
public class StatsData implements Serializable {
    
    //Stats might be alterable (dunno yet tho)
    
    private final int health;
    private final float moveSpeed;
    private final CombatData combat;
    
    public StatsData() {
        this(new CombatData());
    }
    
    public StatsData(CombatData cmb) {
        this(Const.BASE_HEALTH, Const.BASE_MS, cmb);
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
