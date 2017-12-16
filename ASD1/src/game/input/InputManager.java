/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.input;

import game.input.handler.Button;
import game.input.handler.Axis;
import game.input.handler.Trigger;
import game.input.handler.InputHandler;
import util.Const;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Radek
 * Singleton class designed for input management
 */
public class InputManager {
    
    private final static Logger LOGGER = Const.setupLogger(InputManager.class);
    private final List<InputHandler> handlers;
    private final List<InputListenerCall> listeners;
    
    // <editor-fold defaultstate="collapsed" desc="Singleton- inst(), cons()">
    private static InputManager instance;
    
    public static InputManager inst() {
        if(instance == null) {
            instance= new InputManager();
        }
        return instance;
    }
    
    private InputManager() {
        handlers= new ArrayList<>();
        listeners= new ArrayList<>();
        
        handlers.add(new Trigger("xTrigger", KeyCode.D, KeyCode.A));
        handlers.add(new Button("xButton", KeyCode.D, KeyCode.A));
        handlers.add(new Axis("xAxis", KeyCode.D, KeyCode.A));
    }
    //</editor-fold>
    
    /**
     * Auxiliary method, that logs current values of all InputHandlers
     */
    public void logHandlerStates() {
        String out= String.format("%n___%n");
        out = handlers.stream().map((h) -> String.format("%s: %d%n", h.getName(), h.read())).reduce(out, String::concat);
        LOGGER.log(Level.FINE, out);
    }
    
    
    /**
     * Maps desired keys to events.
     * This is called on new window creation.
     * @param scene Scene object
     */
    public void mapInputKeys(Scene scene) {
        scene.setOnKeyPressed((e) -> inputEvent(e, true));
        scene.setOnKeyReleased((e) -> inputEvent(e, false));
    }
    
    /**
     * Method called by events from keyboard
     * @param k Key, that has been changed
     * @param pressed True if it was pressed
     */
    public void inputEvent(KeyEvent k, boolean pressed) {
        
        updateHandlers(k.getCode(), pressed);
        
        notifyListeners(k.getCode(), pressed);
        
        //logHandlerStates();
    }
    
    /**
     * Goes through every InputHandler, if they match the given keyCode, calls
     * their input method
     * @param c KeyCode of the button pressed
     * @param pressed Whether it has been pressed or released
     */
    private void updateHandlers(KeyCode c, boolean pressed) {
        handlers.stream().filter((h) -> (h.isKeyRelevant(c))).forEachOrdered((h) -> {
            if(pressed) {
                h.onPress(c);
            }
            else {
                h.onRelease(c);
            }
        });
    }
    
    /**
     * Updates all listeners that input has been made
     * @param c KeyCode of the button pressed
     * @param pressed Whether it's press or release signal
     */
    private void notifyListeners(KeyCode c, boolean pressed) {
        //foreach was throwing concurrentExc (when I removed a listener)
        for(int i= listeners.size()-1; i>= 0; i--) {
            listeners.get(i).keyAction(c, pressed);
        }
    }
    
    /**
     * Removes listener from the list
     * @param l <b>InputListenerCall</b> to remove
     */
    public void removeListener(InputListenerCall l) {
        this.listeners.remove(l);
    }
    
    /**
     * Adds listener to watch for possible inputs
     * @param l <b>InputListenerCall</b> to add
     */
    public void addListener(InputListenerCall l) {
        this.listeners.add(l);
    }
    
    /**
     * Removes all listeners
     */
    public void clearListeners() {
        this.listeners.clear();
    }
}
