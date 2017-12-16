/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import gameobject.data.StatsData;

/**
 * Stats representation on instance of Creature
 * @author Radek
 */
public class Stats {
    
    private int currHealth;
    //TODO: [Combat] Add Combat stats here (aka combat timers, cooldowns,...)
    
    private final StatsData data;
    
    public Stats(StatsData data) {
        this.data= data;
    }
}
