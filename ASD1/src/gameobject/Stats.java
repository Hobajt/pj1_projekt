/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import gameobject.combat.Combat;
import gameobject.data.StatsData;
import javafx.geometry.Point2D;

/**
 * Stats representation on instance of Creature
 * @author Radek
 */
public class Stats {
    
    private int currHealth;
    private final Combat cmb;
    
    private final StatsData data;
    
    public Stats(StatsData data) {
        this.data= data;
        this.cmb= new Combat(data.getCombat());
        currHealth= data.getHealth();
    }

    public int getCurrHealth() {
        return currHealth;
    }
    
    
    public Combat combat() {
        return cmb;
    }
    
    public boolean dealDamage(int dmg) {
        this.currHealth -= dmg;
        return (currHealth <= 0);
    }
    
    public boolean dealDamage(int dmg, Point2D pos) {
        this.currHealth -= dmg;
        PrintController.inst().printMessage(dmg + "", pos);
        return (currHealth <= 0);
    }
}
