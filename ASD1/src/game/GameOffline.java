/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.List;
import main.FXApp;

/**
 * Offline implementation. Contains level related data and 
 * functions as a connection between gameLoop and updateGenerator
 * @author Radek
 */
public class GameOffline extends Game {
    
    private final GameUpdateGenerator gameUpdate;

    private int uIDCounter;
    
    
    /**
     * Calls for update on GameUpdateGenerator and then
     * udates the GameView
     * @param deltaSec 
     */
    @Override
    public void nextTick(double deltaSec) {
        //generate new tick
        gameUpdate.updateTick(getObjManager(), deltaSec);
        
        //update gameLoop
        getGameLoop().getView().updateView(getObjManager());
    }
    
    /**
     * Returns this level into its initial state.
     * Initial state is loaded from local files.
     */
    @Override
    public final void resetLevel(boolean loadAgain) {
        if(loadAgain) {
            if(!loadLevel(null)) {
                FXApp.inst().quit();
            }
            getGameLoop().start();
        }
        
        getObjManager().reset();
        getGameLoop().getView().reset(loadAgain);
        //System.out.println("--Level Restarted--");
    }
    
    public GameOffline(String levelID) throws GameException {
        super(levelID);
        uIDCounter= 1;
        gameUpdate= new GameUpdateGenerator();
        resetLevel(false);
        getGameLoop().start();
    }
    
    @Override
    public int getUID() {
        return uIDCounter++;
    }

    @Override
    public List<Integer> generateUIDs(int amount) {
        List<Integer> l= new ArrayList<>();
        for(int i= 0; i < amount; i++)
            l.add(uIDCounter+i);
        uIDCounter += amount;
        return l;
    }
}
