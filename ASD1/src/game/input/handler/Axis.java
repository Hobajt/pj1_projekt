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
 * Input handling that acts as an axis- signal returns the value of the axis
 */
class Axis extends InputHandler {
    
    private int state;
    
    private boolean positivePressed, negativePressed;
    
    /**
     * Method for value reading.
     * @return Returns <b>1</b>, <b>0</b> or <b>-1</b> based on what
     * combination of key/altKey s currently pressed.
     * AltKey represents the negative value of the axis
     */
    @Override
    public int read() {
        return state;
    }
    
    private void updateState() {
        state= 0 + (positivePressed ? 1 : 0) - (negativePressed ? 1 : 0);
    }
    
    @Override
    public void onPress(KeyCode c) {
        if(c.equals(super.getKey())) {
            positivePressed= true;
        }
        else {
            negativePressed= true;
        }
        updateState();
    }

    @Override
    public void onRelease(KeyCode c) {
        if(c.equals(super.getKey())) {
            positivePressed= false;
        }
        else {
            negativePressed= false;
        }
        updateState();
    }
    
    /**
     * Constructor for <b>Axi</b> inputHandler.
     * @param k Key for positive value in this handler.
     * @param altK Key for negative value in this handler.
     * @param name Name of this axis
     */
    public Axis(String name, KeyCode k, KeyCode altK) {
        super(name, k, altK);
        state= 0;
        positivePressed= negativePressed= false;
    }
}
