/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.combat;

import gameobject.collider.ColliderData;
import gameobject.data.GameObjectData;
import gameobject.state.ObjectState;
import javafx.geometry.Point2D;
import util.Rotation;

/**
 *
 * @author Radek
 */
public class AttackSimple extends Attack {
    
    private final ObjectState attackState;  //defines, which animation state will be playing while active
    
    private final Point2D move;             //allows specific movement patterns during cast time
    private final boolean freezeRotation;   //false == Creature cannot turn during cast time
    
    /**
     * Specifies which animation to use. Returns the same value throughout the 
     * whole cast.
     * @param progress
     * @return 
     */
    @Override
    public ObjectState getAttackState(int progress) {
        return attackState;
    }
    
    /**
     * Specifies movement during the cast. Returns the same value throughout the
     * whole cast.
     * @param progress
     * @return 
     */
    @Override
    public Point2D getMove(int progress, Point2D inp, Point2D current) {
        return new Point2D(current.getX() * move.getX(), current.getY() * move.getY());
        
        //return current to keep entry direction and disable any change
        //return inp to completely allow change
        //return this.move to have always the same direction
    }

    /**
     * Specifies if Creature can turn during casting. Returns the same value throughout the
     * whole cast.
     * @param progress
     * @param current
     * @return 
     */
    @Override
    public Rotation getRotation(int progress, Rotation inp, Rotation current) {
        return freezeRotation ? current : inp;
    }
    
    public AttackSimple(boolean special, int baseValue, int duration, int cooldown, 
            ObjectState attackState, Point2D move, boolean freezeRotation) {
        
        super(special, baseValue, duration, cooldown);
        this.attackState = attackState;
        this.move = move;
        this.freezeRotation = freezeRotation;
    }
}
