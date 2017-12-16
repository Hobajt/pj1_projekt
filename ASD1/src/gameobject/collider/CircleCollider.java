/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.collider;

/**
 *
 * @author Radek
 */
public class CircleCollider implements Collider {
    
    private boolean trigger;
    private int radius;

    @Override
    public boolean isTrigger() {
        return trigger;
    }
    
    
}
