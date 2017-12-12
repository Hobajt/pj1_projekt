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
 * Input handling that acts as a simple button- true on pressed.
 */
public class Button extends InputHandler {
    
    private boolean value;
    
    /**
     * Method for value reading.
     * @return Returns <b>1</b> while the button is pressed.
     */
    @Override
    public int read() {
        return (value ? 1 : 0);
    }

    @Override
    public void onPress(KeyCode c) {
        value= true;
    }

    @Override
    public void onRelease(KeyCode c) {
        value= false;
    }
    
    /**
     * Constructor for <b>Button</b> inputHandler.
     * @param k Key that corresponds with this handler.
     * @param altK Alternative key value.
     * @param name Name of this button
     */
    public Button(String name, KeyCode k, KeyCode altK) {
        super(name, k, altK);
        value= false;
    }
}
