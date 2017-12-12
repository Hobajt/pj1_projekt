/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.input;

import javafx.scene.input.KeyCode;

/**
 * Interface for input listening
 * @author Radek
 */
public interface InputListener {
    void keyAction(KeyCode c, boolean pressed);
}
