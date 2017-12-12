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
    
    private Stats stats;
    
    
    
    @Override
    public CreatureData getData() {
        return (CreatureData) super.getData();
    }
}
