/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

/**
 * Gameobject representing player + some additional player setup
 * @author Radek
 */
public class Player {

    private static Player instance;
    
    private final Creature playerObject;
    
    public Player(Creature c) {
        this.playerObject= c;
        overridePlayer();
    }
    
    /**
     * Moves any dependencies from old playerObject to new one (listeners,...)
     */
    private void overridePlayer() {
        if(instance != null) {
            //override dependencies
        }
        instance= this;
    }
    
    public static Player getPlayer() {
        return instance;
    }

    public Creature getPlayerObject() {
        return playerObject;
    }
}
