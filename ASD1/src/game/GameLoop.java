/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.view.GameView;
import javafx.animation.AnimationTimer;
import main.FXApp;

/**
 * Most "physical" class. Generates periodic ticks that run the game
 * and handles screen updates.
 * @author Radek
 */
public class GameLoop extends AnimationTimer {
    
    private boolean initialized;
    
    private final Game data;
    private final GameView view;
    
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
        
        //System.out.println(frameRate);
        
        lastTick= now;
    }

    public GameView getView() {
        return view;
    }
    
    public GameLoop(Game data) {
        this.data= data;
        this.view= new GameView();
        FXApp.inst().resizeObserver().addListener(view);   //dont have to remove, will be receiving till the end
    }
}
