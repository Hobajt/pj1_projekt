/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import gameobject.data.GameObjectData;

/**
 * Instance of GameObject within the game
 * @author Radek
 */
public class GameObject {
    
    private final int uniqueID;
    
    private final Transform transform;
    private final GameObjectData data;
    
    //TODO: [] Possibly replace int state with class of State
    private int state;
    
    GameObject(int uID, GameObjectData data, Transform transform) {
        this.uniqueID= uID;
        this.data= data;
        this.transform= transform;
    }
    
    public GameObjectData getData() {
        return data;
    }
}
