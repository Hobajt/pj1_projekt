/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.behaviour.ai.idle;

import gameobject.GameObject;
import java.io.Serializable;
import java.util.List;
import util.Point;

/**
 * Describes data for AI idle behaviour
 * @author Radek
 */
public class IdleAIData implements Serializable {
    
    //null data == static idle
    //probability == random movement idle
    //routes == routines idle
    
    private final boolean routines;
    private final List<Point> route;
    private float probability;
    private int radius;
    
    public IdleAIData(List<Point> route) {
        this.route= route;
        routines= true;
    }
    
    public IdleAIData(float prob, int radius) {
        this.probability= prob;
        this.radius= radius;
        route= null;
        routines= false;
    }
    
    public boolean hasRoutines() {
        return this.routines;
    }
    
    public IdleAI getIdleAI(GameObject owner) {
        return (routines ? new IdleAIRoutines(this, owner) : new IdleAIMovements(this, owner));
    }

    public float getProbability() {
        return probability;
    }

    public int getRadius() {
        return radius;
    }

    public List<Point> getRoutes() {
        return route;
    }
}
