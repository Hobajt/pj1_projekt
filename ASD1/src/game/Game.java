/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.data.ObjectManager;
import game.data.LevelData;
import game.data.LevelLoadingException;
import game.data.TileData;
import java.util.List;
import main.FXApp;

/**
 * Stores and manages all game and level related data.
 * @author Radek
 */
public abstract class Game {
    
    private final GameLoop gameLoop;
    
    private int enemyCount;
    private LevelData data;
    private ObjectManager objManager;
    
    private boolean paused;
    
    /**
     * Generates unique Identifier for an object instance
     * @return Returns uID
     */
    public abstract int getUID();
    
    /**
     * Generates multiple uIDs
     * @param amount Amount of uIDs to create
     * @return Returns List of Unique IDs
     */
    public abstract List<Integer> generateUIDs(int amount);
    
    /**
     * Periodic frame update call- will be called from GameLoop
     * @param deltaSec Time difference from last update
     */
    public abstract void nextTick(double deltaSec);
    
    /**
     * Returns current level into its initial state (initial GameObject states)
     */
    public abstract void resetLevel(boolean loadAgain);
    
    /**
     * Loads level data from local files
     * @param id Level identification
     * @return 
     */
    public final boolean loadLevel(String id) {
        try {
            data= new LevelData(id, this);
            return true;
        } catch (LevelLoadingException e) {
            //System.err.println("Error during level loading: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Constructor- initiates GameLoop and loads the level
     * @param levelID
     * @throws GameException When level fails to load or server 
     * cannot be reached.
     */
    public Game(String levelID) throws GameException {
        if(!loadLevel(levelID)) {
            throw new GameException("Level loading error");
        }
        
        objManager= new ObjectManager(this, data);
        this.gameLoop= new GameLoop(this);
    }
    
    public final GameLoop getGameLoop() {
        return gameLoop;
    }

    public final ObjectManager getObjManager() {
        return objManager;
    }
    
    public TileData getTileData() {
        return this.data.getTileData();
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
        if(paused)
            gameLoop.stop();
        else
            gameLoop.start();
    }

    
    public void enemyKilled() {
        --this.enemyCount;
        if(enemyCount <= 0) {
            setPaused(true);
            FXApp.inst().reset();
        }
    }
    
    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }
}
