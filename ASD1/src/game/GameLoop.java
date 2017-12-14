/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javafx.animation.AnimationTimer;
import network.client.ClientController;
import network.NetID;

/**
 * The lowest level of game updates. Hadles screen and view updates based
 * on served updates from GameData
 * @author Radek
 */
public class GameLoop extends AnimationTimer {
    
    @Override
    public void start() {
        
        //if(!initialized)
        try {
            new Thread(new ClientController(new NetID(InetAddress.getLocalHost(), 11111))).start();
        } catch (UnknownHostException e) {
            System.err.println("UnknownHostException");
            e.printStackTrace();
        }
        
        super.start();
        System.out.println("Loop started");
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void handle(long now) {
        //System.out.println("Looping");
    }
    
    public GameLoop() {
        
    }
}
