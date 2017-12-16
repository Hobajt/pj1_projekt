/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.data.ObjectManager;
import gameobject.Creature;
import gameobject.player.Player;
import gameobject.player.PlayerInput;

/**
 * Core computation- generates new positions and applies
 * logic to all GameObjects
 * @author Radek
 */
public class GameUpdateGenerator {
    
    /**
     * Updates the game
     * @param objManager 
     * @param delta Delta time- time difference from last update
     */
    public void updateTick(ObjectManager objManager, double delta) {
        //System.out.println("tick");
        
        updatePlayer();
        
        //System.out.println(Player.inst().getxAxis());
        //System.out.println(Player.inst().getObject().getTransform().getPosition());
    }
    
    
    /**
     * Updates local player
     */
    private void updatePlayer() {
        Creature player= Player.inst().getObject();
        PlayerInput pInput= Player.inst().getInput();
        
        player.move(pInput.getMoveVector());
        player.getStateHandler().update(pInput);
        player.rotate(pInput.getRotation());
    }
}
