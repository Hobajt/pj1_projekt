/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.player;

import game.input.handler.HandlerFactory;
import game.input.handler.HandlerType;
import game.input.handler.InputHandler;
import gameobject.combat.AttackType;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import util.Rotation;

/**
 * All players inputs grouped in one class.
 * In case I manage to get networking going, this will
 * be transmitted over to the server.
 * @author Radek
 */
public class PlayerInput {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton- inst(), cons()">
    private static PlayerInput instance;
    
    static PlayerInput inst() {
        if(instance == null)
            instance= new PlayerInput();
        return instance;
    }
    //</editor-fold>
    
    private final InputHandler xAxis;
    private final InputHandler yAxis;
    private final InputHandler attack;
    private final InputHandler switchAttack;
    
    private AttackType currentAttack;
    
    public Point2D getMoveVector() {
        return new Point2D(xAxis.read(), yAxis.read());
    }
    
    public boolean isMoving() {
        return (xAxis.read() != 0 || yAxis.read() != 0);
    }
    
    public Rotation getRotation() {
        if(!isMoving())
            return Player.inst().getObject().getTransform().getRotation();
        return Rotation.getByMoveDir(xAxis.read(), yAxis.read());
    }

    public boolean switchAttackTrigger() {
        return switchAttack.read() == 1;
    }
    
    public boolean attackTrigger() {
        return attack.read() == 1;
    }
    
    public void switchAttackType(AttackType att) {
        currentAttack= att;
    }
    
    public AttackType getAttackType() {
        return currentAttack;
    }
    
    private PlayerInput() {
        xAxis= HandlerFactory.inst().createNew(HandlerType.AXIS, "xAxis", KeyCode.A, KeyCode.D);
        yAxis= HandlerFactory.inst().createNew(HandlerType.AXIS, "yAxis", KeyCode.W, KeyCode.S);
        attack= HandlerFactory.inst().createNew(HandlerType.BUTTON, "Attack", KeyCode.SPACE, KeyCode.ENTER);
        switchAttack= HandlerFactory.inst().createNew(HandlerType.TRIGGER, "Switch Attack", KeyCode.R, KeyCode.R);
    }
}
