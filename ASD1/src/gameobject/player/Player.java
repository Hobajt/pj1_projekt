/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.player;

import gameobject.Creature;

/**
 * Gameobject representing player + some additional player setup
 * @author Radek
 */
public class Player {

    //<editor-fold defaultstate="collapsed" desc="Singleton- inst(), cons()">
    private static Player instance;
    
    public Player(Creature c) {
        this.playerObject= c;
        this.input= PlayerInput.inst();
        
        overridePlayer();
    }
    
    public static Player inst() {
        return instance;
    }
    //</editor-fold>
    
    private final Creature playerObject;
    private final PlayerInput input;
    
    /**
     * Moves any dependencies from old playerObject to new one (listeners,...)
     */
    private void overridePlayer() {
        if(instance != null) {
            //override dependencies
        }
        instance= this;
    }

    public Creature getObject() {
        return playerObject;
    }

    public PlayerInput getInput() {
        return input;
    }
}
