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
    
    private int health;
    private float moveSpeed;
    //TODO: [Combat] Add CombatData here
}
