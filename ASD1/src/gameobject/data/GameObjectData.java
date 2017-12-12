/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data;

import gameobject.collider.Collider;
import gameobject.Model;
import gameobject.ObjectFlags;

/**
 * Data class, that describes basic GameObject
 * @author Radek
 */
public class GameObjectData {
    
    private int id;
    private String name;
    private ObjectFlags flags;
    
    private Collider collider;
    private Model model;
    
}
