/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data;

import gameobject.collider.Collider;
import gameobject.data.behaviour.CustomBehaviour;
import gameobject.data.flags.FlagsType;
import java.io.Serializable;

/**
 * Data class describing advanced GameObject that can do various advanced stuff...
 * @author Radek
 */
public class CreatureData extends GameObjectData implements Serializable {
    
    private final StatsData statsData;

    public CreatureData(int id, String name, FlagsType flags, int modelID, 
            Collider collider, CustomBehaviour behaviour, StatsData stats) {
        
        super(id, name, flags, modelID, collider, behaviour);
        this.statsData= stats;
    }

    public StatsData getStatsData() {
        return statsData;
    }
    
    
}
