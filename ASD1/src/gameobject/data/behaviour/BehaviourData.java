/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour;

import gameobject.GameObject;
import java.io.Serializable;

/**
 * Custom behaviour data, that is stored with GameObject data
 * @author Radek
 */
public interface BehaviourData extends Serializable {
    
    /**
     * Creates instance of this behaviour for specified object
     * @param g Gameobject that will own this behaviour
     * @return Returns an instance of behaviour
     */
    public Behaviour createInstance(GameObject g);
}
