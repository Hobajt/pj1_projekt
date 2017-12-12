/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.input.handler;

import javafx.scene.input.KeyCode;

/**
 *
 * @author Radek
 * Abstract class for various types of player input handling.
 */
public abstract class InputHandler {
    
    private String name;
    private KeyCode k, altK;
    
    /**
     * Method that determines, whether the given key is this methods key as well
     * @param c Key to be compared
     * @return Returns <b>true</b> if key belongs under this object
     */
    public boolean isKeyRelevant(KeyCode c) {
        return (c.equals(k) || c.equals(altK));
    }
    
    /**
     * Based on inner state, determines output value (implementation specific)
     * @return Value of the object
     */
    public abstract int read();
    
    /**
     * Method that is called by controller, when this objects key is pressed.
     * @param c Key that has been pressed.
     */
    public abstract void onPress(KeyCode c);
    
    /**
     * Method that is called by controller, when this objects key is released.
     * @param c Key that has been released.
     */
    public abstract void onRelease(KeyCode c);
    
    public void setKey(KeyCode k) {
        this.k = k;
    }
    public void setAltKey(KeyCode k) {
        altK= k;
    }
    
    public KeyCode getKey() {
        return k;
    }
    
    public KeyCode getAltKey() {
        return altK;
    }
    
    public String getName() {
        return name;
    }
    
    public InputHandler(String name, KeyCode k, KeyCode altK) {
        this.name= name;
        this.k = k;
        this.altK= altK;
    }
}
