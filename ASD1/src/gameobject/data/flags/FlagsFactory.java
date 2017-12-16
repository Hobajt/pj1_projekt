/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.data.flags;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages ObjectFlags creation
 * @author Radek
 */
public class FlagsFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton- inst(), cons()">
    private static FlagsFactory instance;

    private FlagsFactory() {
        flags= initFlags();
    }
    
    public static FlagsFactory inst() {
        if(instance == null)
            instance= new FlagsFactory();
        return instance;
    }
    //</editor-fold>
    
    private final Map<FlagsType, ObjectFlags> flags;
    
    /**
     * Returns ObjectFlags based on selected type
     * @param type Type that you want to retrieve
     * @return Returns ObjectFlag object
     */
    public ObjectFlags getFlags(FlagsType type) {
        return flags.get(type);
    }
    
    /**
     * Private method used for initialization in constructor
     * @return Returns distinct ObjectFlags() mapped to FlagsType enum
     */
    private Map<FlagsType, ObjectFlags> initFlags() {
        Map<FlagsType, ObjectFlags> m= new HashMap<>();
        
        m.put(FlagsType.STATIC, new ObjectFlags(false, false, false, false));
        m.put(FlagsType.CREATURE, new ObjectFlags(true, true, false, false));
        
        return m;
    }
}
