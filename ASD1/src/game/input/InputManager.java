/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.input;

import game.input.handler.InputHandler;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Window;

/**
 *
 * @author Radek
 * Singleton class designed for input management.<br>
 * Figures as a mediator between scene input events and outer classes.
 * Allows two access modes.<br>
 * 1- Direct access. Class registers a single listener, and receives notifications
 * on any input event.
 * 2- Handlers. Class can register handlers, that read from 1 or 2 keys only.
 */
public class InputManager {
    
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
        
        mapInputKeys();
    }
    //</editor-fold>
    
    
    /**
     * Adds new handler to the manager
     * @param handler Handler to manipulate with
     */
    public void addHandler(InputHandler handler) {
        if(!handlers.contains(handler))
            handlers.add(handler);
    }
    
    /**
     * Removes a handler from manager
     * @param handler Handler to manipulate with
     */
    public void removeHandler(InputHandler handler) {
        handlers.remove(handler);
    }
    
    /**
     * Adds listener to watch for possible inputs
     * @param l <b>InputListenerCall</b> to add
     */
    public void addListener(InputListenerCall l) {
        this.listeners.add(l);
    }
    
    /**
     * Removes listener from the list
     * @param l <b>InputListenerCall</b> to remove
     */
    public void removeListener(InputListenerCall l) {
        this.listeners.remove(l);
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
     * Removes all listeners
     */
    public void clearListeners() {
        this.listeners.clear();
    }
    
    /**
     * Maps desired keys to events.<br>
     * This is called on new window creation.
     */
    private void mapInputKeys() {
        Window.inst().getScene().setOnKeyPressed((e) -> inputEvent(e, true));
        Window.inst().getScene().setOnKeyReleased((e) -> inputEvent(e, false));
    }
}
