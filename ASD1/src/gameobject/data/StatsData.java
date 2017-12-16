/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data;

import java.io.Serializable;

/**
 * Data class that describes creatures stat values
 * @author Radek
 */
public class StatsData implements Serializable {
    
    //Stats might be alterable (dunno yet tho)
    
    private int health;
    private float moveSpeed;
    //TODO: [Combat] Add CombatData here

    public StatsData() {
        this.health= 50;
        this.moveSpeed= 2;
    }
    
    public int getHealth() {
        return health;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }
}
