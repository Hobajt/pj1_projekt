/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import gameobject.data.CreatureData;

/**
 * Instance of Advanced GameObject within the game
 * @author Radek
 */
public class Creature extends GameObject {
    
    private final Stats stats;
    
    Creature(int uID, CreatureData data, Transform transform) {
        super(uID, data, transform);
        
        stats= new Stats(getData().getStatsData());
    }
    
    @Override
    public final CreatureData getData() {
        return (CreatureData) super.getData();
    }
}
