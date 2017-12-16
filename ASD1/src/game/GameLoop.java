/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.data.ObjectManager;
import javafx.animation.AnimationTimer;

/**
 * Most "physical" class. Generates periodic ticks that run the game
 * and handles screen updates.
 * @author Radek
 */
public class GameLoop extends AnimationTimer {
    
    private boolean initialized;
    
    private final Game data;
    
    private long lastTick;
    private double delta;
    private int frameRate;
    
    @Override
    public void start() {
        
        super.start();
        lastTick= System.nanoTime();
        System.out.println("--Game started--");
    }

    @Override
    public void stop() {
        super.stop();
        System.out.println("--Game stopped--");
    }
    
    @Override
    public void handle(long now) {
        delta= ((now - lastTick)*1e-9);
        frameRate= (int) (1/delta);
        
        data.nextTick(delta);
        
        lastTick= now;
    }
    
    /**
     * Updates the game screen
     * Called from higher level classes in response to nextTick() call
     * @param objManager 
     */
    public void updateView(ObjectManager objManager) {
        //System.out.println("view update");
    }
    
    public GameLoop(Game data) {
        this.data= data;
    }
}
