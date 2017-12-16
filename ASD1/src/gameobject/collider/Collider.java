/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.collider;

import java.io.Serializable;

/**
 * Description of "physical" represeentation of GameObject in game
 * @author Radek
 */
public interface Collider extends Serializable {
    
    //TODO: [Collisions] Impement some colliders
    
    public boolean isTrigger();
}
