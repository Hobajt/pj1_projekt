/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 * Exception that occurs, when Game fails to start
 * @author Radek
 */
public class GameException extends Exception {

    /**
     * Creates a new instance of <code>GameInitException</code> without detail
     * message.
     */
    public GameException() {
    }

    /**
     * Constructs an instance of <code>GameInitException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public GameException(String msg) {
        super(msg);
    }
}
