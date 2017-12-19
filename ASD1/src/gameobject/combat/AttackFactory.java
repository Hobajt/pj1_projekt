/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.combat;

import gameobject.collider.ColliderSpecial;
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
    
    /*MOVE INTO FILE AND CHANGE AttackType ENUM TO ORDINARY Integer Identifier IF 
    I AM TO ADD DOZENS OF ABILITIES- for small amount, the switch is K.
    */
    
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
        
        vals.put(AttackType.MELEE, new AttackSimple(false, 25, 600, 800, ObjectState.MELEE, new Point2D(1,1), true, 
        new ColliderSpecial(45,45,0,0)));
        
        //Co budu delat s colliderama?? -- potrebuju je podle utoku vzdy jen nahodit na jeden vypocet
        //Pridat do CollisionEngine metodu na rychle porovnani jednoho prvku (v ramci 3x3 bloku)
        
        vals.put(AttackType.RANGED, new AttackSimple(false, 10, 550, 550, ObjectState.RANGED, Point2D.ZERO, false,
        10));
        
        vals.put(AttackType.CHARGE, new AttackSimple(false, 0, 250, 8000, ObjectState.RANGED, new Point2D(8,8), true, null));
    }
    
    private void meleeAttack() {
        
    }
    
    private void rangedAttack() {
        
    }
}
