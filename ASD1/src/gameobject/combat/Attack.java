/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.combat;

import gameobject.collider.Collider;
import gameobject.state.ObjectState;
import javafx.geometry.Point2D;
import util.Rotation;

/**
 * Describes a specific attack.
 * @author Radek
 */
public abstract class Attack {
    
    private final boolean special;          //for player- false == can be rotated on basicAttacks
    
    private final int baseValue;               //base value for any interaction with this attack
    private final int duration;             //for how long will this attack's animation be up
    private final int cooldown;             //cooldown

    /**
     * Determines the movement during cast time (implementation specific)
     * @param progress Time progress of the cast (in millis)
     * @param inp Input from outside
     * @param current Current Creature's moveDirection
     * @return Returns customly modified vector, that will be applied to 
     * the Creature's movement
     */
    public abstract Point2D getMove(int progress, Point2D inp, Point2D current);

    /**
     * Determines whether to freeze rotation (implementation specific)
     * @param progress Time progress of the cast (in millis)
     * @param current Current Creature's rotation
     * @param inp Input from outside
     * @return Returns new rotation for this Creature during attack
     */
    public abstract Rotation getRotation(int progress, Rotation inp, Rotation current);
    
    /**
     * Returns object state, that will be shown, when this attack happens
     * @param progress Time progress of the cast (in millis)
     * @return Returns ObjectState
     */
    public abstract ObjectState getAttackState(int progress);
    
    public Attack(boolean special, int baseValue, int duration, int cooldown) {
        this.special = special;
        this.baseValue= baseValue;
        this.duration = duration;
        this.cooldown = cooldown;
    }

    /**
     * Special attacks are not usable as basic attacks (melee, ranged)
     * @return 
     */
    public boolean isSpecial() {
        return special;
    }

    public int getDuration() {
        return duration;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getBaseValue() {
        return baseValue;
    }
}
