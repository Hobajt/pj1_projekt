/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import gameobject.collider.CollisionEngine;
import game.data.ObjectManager;
import gameobject.player.Player;

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
        
        //--Collision grid update--
        CollisionEngine.inst().updateGrid(objManager.getObjs());
        
        //--Update all movement--
        Player.inst().updatePlayer();
        //updateCLientPlayers();
        objManager.updateBehaviours();
    }
}
