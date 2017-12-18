/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data;

import gameobject.data.flags.ObjectFlags;
import gameobject.collider.Collider;
import gameobject.collider.ColliderBuilder;
import gameobject.collider.ColliderType;
import gameobject.model.ModelFactory;
import gameobject.data.flags.FlagsFactory;
import gameobject.data.flags.FlagsType;
import gameobject.model.Model;
import java.io.Serializable;
import gameobject.data.behaviour.BehaviourData;

/**
 * Data class, that describes basic GameObject
 * @author Radek
 */
public class GameObjectData implements Serializable {
    
    private final int id;
    private final String name;
    
    private final FlagsType flags;
    private final int modelID;
    private final Collider collider;
    private final BehaviourData behaviour;
    
    public GameObjectData(int id, String name, FlagsType flags, int modelID, 
            Collider collider, BehaviourData behaviour) {
        
        this.id= id;
        this.name= name;
        this.flags= flags;
        this.modelID= modelID;
        this.collider= collider;
        this.behaviour= behaviour;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public ObjectFlags getFlags() {
        return FlagsFactory.inst().getFlags(flags);
    }
    
    public FlagsType getFlagsType() {
        return flags;
    }

    public int getModelID() {
        return modelID;
    }
    
    public Model getModel() {
        return ModelFactory.inst().getModel(modelID);
    }
    
    public Collider getCollider() {
        return this.collider;
    }

    public BehaviourData getBehaviour() {
        return behaviour;
    }
}
