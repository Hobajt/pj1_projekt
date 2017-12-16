/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import util.Transform;
import gameobject.data.CreatureData;
import javafx.geometry.Point2D;

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

    /**
     * In addition to modifying position, applies this objects movementSpeed to it
     * @param moveDir Direction of the move
     */
    @Override
    public void move(Point2D moveDir) {
        super.move(moveDir.normalize().multiply(getData().getStatsData().getMoveSpeed()));
    }
    
    @Override
    public final CreatureData getData() {
        return (CreatureData) super.getData();
    }
}
