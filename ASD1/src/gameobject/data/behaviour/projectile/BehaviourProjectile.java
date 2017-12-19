/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour.projectile;

import gameobject.Creature;
import gameobject.GameObject;
import gameobject.collider.CollisionEngine;
import gameobject.data.behaviour.Behaviour;
import main.FXApp;
import util.Rotation;

/**
 *
 * @author Radek
 */
public class BehaviourProjectile implements Behaviour {
    
    private final BehaviourDataProjectile data;
    private final GameObject owner, g;
    private final Rotation rot;

    private final long endTime;
    
    @Override
    public void update() {
        g.move(Rotation.getMove(rot).normalize().multiply(data.getSpeed()));
        CollisionEngine.inst().handleCollisions(g);
        
        if(System.currentTimeMillis() >= endTime)
            FXApp.inst().destroyObject(g);
    }
    
    @Override
    public void trigger(GameObject col) {
        if(col == owner)
            return;
        
        if(col instanceof Creature) {
            if(((Creature)col).getStats().dealDamage(data.getDamage(), col.getTransform().getPosition()))
                FXApp.inst().destroyObject(col);
        }
        
        
        FXApp.inst().destroyObject(g);
    }
    
    public BehaviourProjectile(BehaviourDataProjectile data, Rotation rot, GameObject owner, GameObject g) {
        this.data = data;
        this.owner= owner;
        this.g= g;
        this.rot= rot;
        this.endTime= System.currentTimeMillis() + 1250;
    }
}
