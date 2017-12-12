/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

/**
 * Server Networking implementation.
 * @author Radek
 */
public class ServerController implements NetworkAccess {
    
    /**
     * NETWORK STUFF:
     * Receive client registrations - 1 thread
     * Receive specific client playerData - n threads for n clients
     * Send specific player data          - working on same threads as receiving
     * 
     * UPDATE STUFF:
     * calls for updates on GameUpdateGenerator (offline, its done straight from 
     * GameData).
     * 
     * FKIN THREADS WILL BE NECESSARY
     */
}
