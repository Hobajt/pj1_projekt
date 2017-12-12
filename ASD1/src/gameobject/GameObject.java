/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import gameobject.data.GameObjectData;
import javafx.geometry.Point2D;

/**
 * Instance of GameObject within the game
 * @author Radek
 */
public class GameObject {
    
    private int uniqueID;
    
    private Point2D position;
    private Direction rotation;
    //TODO: [] Possibly replace int state with class of State
    private int state;
    
    private GameObjectData data;
    
    public GameObjectData getData() {
        return data;
    }
}
