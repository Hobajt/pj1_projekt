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
 * Input handling that acts as a trigger- signal is sent only on initial press.
 */
public class Trigger extends InputHandler {

    private boolean value, alreadySent;
    
    /**
     * Method for value reading.
     * @return Returns <b>1</b> if button has been pressed and read() is 
     * called for the first time (during that certain press).
     * In other cases, returns <b>0</b>.
     */
    @Override
    public int read() {
        if(alreadySent) {
            return 0;
        }
        alreadySent= value;
        return (value ? 1 : 0);
    }

    @Override
    public void onPress(KeyCode c) {
        value= true;
    }

    @Override
    public void onRelease(KeyCode c) {
        value= false;
        alreadySent= false;
    }
    
    /**
     * Constructor for <b>Trigger</b> inputHandler.
     * @param k Key that corresponds with this handler.
     * @param altK Alternative key value.
     * @param name Name of this trigger
     */
    public Trigger(String name, KeyCode k, KeyCode altK) {
        super(name, k, altK);
        value= alreadySent= false;
    }
}
