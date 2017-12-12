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
     * When passed into PlayerInput, it listens to any key actions
     * Usable for custom hot-keys and arrow key controls
     * @param c KeyCode of the given key
     * @param pressed Pressed or released?
     */
    void listenerMethod(KeyCode c, boolean pressed);
    
    /**
     * Method that shows this menu
     */
    void show();
    
    /**
     * Method that hides this menu
     */
    void hide();
}
