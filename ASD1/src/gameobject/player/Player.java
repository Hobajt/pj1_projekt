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
        this.plObj= c;
        this.input= PlayerInput.inst();
        
        overridePlayer();
    }
    
    public static Player inst() {
        return instance;
    }
    //</editor-fold>
    
    private final Creature plObj;
    private final PlayerInput input;
    
    /**
     * Updates local players state and position
     */
    public void updatePlayer() {
        
        input.gameMenu();
        
        if(input.attackTrigger() && plObj.canAttack(input.getAttackType())) {
            //checks if player can attack with currently active attack
            plObj.attack(input.getAttackType());
        }
        else if(input.switchAttackTrigger()) {
            //cycles to another available attack
            input.switchAttackType(plObj.getStats().combat().getNextAttackType(input.getAttackType()));
        }
        
        //move- consider freezing movement when attacking
        plObj.update(input.getMoveVector(), input.getRotation());
        
        //update state and rotation
        //playerObject.getStateHandler().update(input.getMoveVector(), playerObject.getStats());
    }
    
    /**
     * Moves any dependencies from old playerObject to new one (listeners,...)
     */
    private void overridePlayer() {
        if(instance != null) {
        }
        else {
            input.switchAttackType(plObj.getStats().combat().getNextAttackType(input.getAttackType()));
        }
        instance= this;
    }

    public Creature getObject() {
        return plObj;
    }

    public PlayerInput getInput() {
        return input;
    }
}
