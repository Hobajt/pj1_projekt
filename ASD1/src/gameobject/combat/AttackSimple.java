/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.combat;

import gameobject.Creature;
import gameobject.GameObject;
import gameobject.collider.ColliderSpecial;
import gameobject.collider.CollisionEngine;
import gameobject.state.ObjectState;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import main.FXApp;
import util.Rotation;
import util.Transform;

/**
 *
 * @author Radek
 */
public class AttackSimple extends Attack {
    
    private final ObjectState attackState;  //defines, which animation state will be playing while active
    
    private final Point2D move;             //allows specific movement patterns during cast time
    private final boolean freezeRotation;   //false == Creature cannot turn during cast time
    
    private final Object ref;
    
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
    
    /**
     * Attack functionality
     * @param owner 
     */
    @Override
    public void execute(GameObject owner) {
        
        Point2D attPos= owner.getTransform().getPosition();
        
        if(ref instanceof ColliderSpecial) {
            melee(owner, attPos.add(Rotation.getMove(owner.getTransform().getRotation()).multiply(10)));
        }
        else if(ref instanceof Integer) {
            ranged(owner, attPos.add(Rotation.getMove(owner.getTransform().getRotation()).multiply(30)));
        }
    }
    
    private void ranged(GameObject owner, Point2D attPos) {
        Point2D offset= Rotation.getMove(owner.getTransform().getRotation()).multiply(30);
        Transform tr= new Transform(attPos, owner.getTransform().getRotation());
        FXApp.inst().createPrefab((int)ref, tr, owner);
    }
    
    private void melee(GameObject owner, Point2D attPos) {
        List<GameObject> hit= CollisionEngine.inst().detectCollisions(attPos, (ColliderSpecial)ref);
        if(hit.size() > 0) {
            List<GameObject> dead= new ArrayList<>();
            for(GameObject h : hit) {
                if(h == owner)
                    continue;
                
                if(h instanceof Creature) {
                    if(((Creature)h).getStats().dealDamage(getBaseValue(), h.getTransform().getPosition()))
                        dead.add(h);
                }
            }
            for(GameObject g : dead) {
                FXApp.inst().destroyObject(g);
            }
        }
    }
    
    public AttackSimple(boolean special, int baseValue, int duration, int cooldown, 
            ObjectState attackState, Point2D move, boolean freezeRotation, Object ref) {
        
        super(special, baseValue, duration, cooldown);
        this.attackState = attackState;
        this.move = move;
        this.freezeRotation = freezeRotation;
        this.ref= ref;
        
    }
}
