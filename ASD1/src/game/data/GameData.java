/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.data;

/**
 * Gathers data from above and servers it to GameLoop in ticks.
 * @author Radek
 */
public interface GameData {
    
    /**
     * Online 
     *   -stores syncing queues for objects
     *   -reads from networkAccess
     *   -transforms data to ticks, stores them
     *   -servers ticks to GameLoop
     * 
     * Offline
     *   -calls for GameUpdateGenerator
     *   -transforms to ticks and serves it to GameLoop (no cached ticks)
     */
}
