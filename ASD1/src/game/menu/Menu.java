/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.menu;

import javafx.scene.input.KeyCode;

/**
 * Basic menu interface.
 * @author Radek
 */
public interface Menu {
    
    /**
     * Defines how should menu behave during screen scaling
     */
    void onResize();
    
    /**
     * Listener method that gets called after key press. Used to
     * implement key-controlled menu
     * @param c KeyCode of the given key
     * @param pressed Pressed or released?
     */
    void listenerNotification(KeyCode c, boolean pressed);
    
    /**
     * Shows menu and enables its control
     */
    void show();
    
    /**
     * Hides menu graphics and disables it
     */
    void hide();
}
