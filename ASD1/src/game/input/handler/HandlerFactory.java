/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.input.handler;

import game.input.InputManager;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Radek
 */
public class HandlerFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton- inst(), cons()">
    private static HandlerFactory instance;
    
    private HandlerFactory() {
        
    }
    
    public static HandlerFactory inst() {
        if (instance == null) {
            instance= new HandlerFactory();
        }
        return instance;
    }
    //</editor-fold>
    
    /**
     * Creates new <b>InputHandler</b> and registers it to InputManager
     * @param type Specific type of input handler
     * @param name Name for this handler
     * @param key Key which will trigger this handler
     * @param altKey Alternative key (or negative pole)
     * @return Returns already registered handler, as defined by input parameters
     */
    public InputHandler createNew(HandlerType type, String name, KeyCode key, KeyCode altKey) {
        InputHandler handler= null;
        switch(type) {
            case AXIS:
                handler= new Axis(name, key, altKey);
                break;
            case BUTTON:
                handler= new Button(name, key, altKey);
                break;
            case TRIGGER:
                handler= new Trigger(name, key, altKey);
                break;
        }
        
        InputManager.inst().addHandler(handler);
        return handler;
    }
}
