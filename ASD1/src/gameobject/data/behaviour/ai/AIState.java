/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour.ai;

import javafx.geometry.Point2D;

/**
 * Enumeration for AI state- also holds its move direction
 * throughout the calculation process.
 * @author Radek
 */
public enum AIState {
    IDLE,
    COMBAT;
    
    private Point2D moveDir;

    public AIState setMoveDir(Point2D moveDir) {
        this.moveDir = moveDir;
        return this;
    }

    public Point2D getMoveDir() {
        return moveDir;
    }
}
