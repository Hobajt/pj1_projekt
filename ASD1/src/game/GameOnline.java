/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.List;

/**
 * Both server and client implementation. In addition to offline 
 * implementation features, also stores few ticks in history (for corrections) 
 * and PROBABLY few future ticks (to keep things synced and smooth-running
 * in case of packet LOSS).
 * @author Radek
 */
public class GameOnline extends Game {

    //private NetworkAccess netManager;
    //private List<GameObject> viewState;
    
    /*
    Online 
     *   -stores syncing queues for objects
     *   -stores serverCommands (create,remove object,...)
     *   -reads from networkAccess
     *   -transforms data to ticks, stores them
     *   -servers ticks to GameLoop
    */
    
    /**
     * Managed by NetworkAccess
     */
    @Override
    public void nextTick(double deltaSec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        /*
        //if there is new updated tick
        if(netManager.newUpdate(getObjManager(), deltaSec)) {
            //on client, deltaSec is not needed, but server needs it
            
        }
        */
        //lerp viewState into objectManager state (real state)
    }

    /**
     * Returns this level into its initial state.
     * State loading is managed by NetworkAccess- either loads
     * from local files (server) or makes a call to server (client).
     */
    @Override
    public void resetLevel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public GameOnline() throws GameException {
        super(null);
        
        //establish networkAccess
        //if server -> load level and reset as usual
        //if client -> read levelID from server and restart from server
    }

    @Override
    public int getUID() {
        //forward to NetworkAccess - if this is a client, throw some exception probably
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> generateUIDs(int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
