/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.combat;

import gameobject.state.ObjectState;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Point2D;

/**
 *
 * @author Radek
 */
public class AttackFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton- inst()">
    private static AttackFactory instance;
    
    public static AttackFactory inst() {
        if(instance == null) 
            instance= new AttackFactory();
        return instance;
    }
    //</editor-fold>
    
    private final Map<AttackType, Attack> vals;
    
    /**
     * Returns attack by attackType
     * @param type
     * @return 
     */
    public Attack getAttack(AttackType type) {
        return vals.get(type);
    }
    
    private AttackFactory() {
        vals= new HashMap<>();
        
        vals.put(AttackType.MELEE, new Attack(false, 600, 800, ObjectState.MELEE, new Point2D(1,1), true));
        vals.put(AttackType.RANGED, new Attack(false, 550, 550, ObjectState.RANGED, Point2D.ZERO, true));
        vals.put(AttackType.CHARGE, new Attack(false, 250, 8000, ObjectState.RANGED, new Point2D(8,8), true));
    }
}
