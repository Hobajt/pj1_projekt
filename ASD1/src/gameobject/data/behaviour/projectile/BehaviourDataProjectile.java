/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour.projectile;

import gameobject.GameObject;
import gameobject.data.behaviour.Behaviour;
import gameobject.data.behaviour.BehaviourData;
import util.Rotation;

/**
 *
 * @author Radek
 */
public class BehaviourDataProjectile implements BehaviourData {

    private final int speed;
    private final int damage;

    @Override
    public Behaviour createInstance(GameObject g) {
        return createInstance(g, null, Rotation.EAST);
    }
    
    public Behaviour createInstance(GameObject g, GameObject owner, Rotation rot) {
        return new BehaviourProjectile(this,rot, owner, g);
    }

    public BehaviourDataProjectile(int speed, int damage) {
        this.speed = speed;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }
    
    
}
