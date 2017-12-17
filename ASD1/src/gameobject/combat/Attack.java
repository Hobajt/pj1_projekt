/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.combat;

import gameobject.state.ObjectState;
import javafx.geometry.Point2D;

/**
 *
 * @author Radek
 */
public class Attack {
    
    private final boolean special;
    
    private final int duration;
    private final int cooldown;
    private final ObjectState attackState;
    
    private final Point2D move;
    private final boolean freezeRotation;

    public Attack(boolean special, int duration, int cooldown, ObjectState attackState, Point2D move, boolean freezeRotation) {
        this.special = special;
        this.duration = duration;
        this.cooldown = cooldown;
        this.attackState = attackState;
        this.move = move;
        this.freezeRotation = freezeRotation;
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

    public Point2D getMove() {
        return move;
    }

    public boolean isFreezeRotation() {
        return freezeRotation;
    }
    
    /**
     * Returns object state, that will be shown, when this attack happens
     * @return 
     */
    public ObjectState getAttackState() {
        return attackState;
    }
}
