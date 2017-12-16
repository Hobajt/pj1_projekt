/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;

/**
 *
 * @author Radek
 */
public class Const {
    
    public static final int BASE_WIDTH= 640;
    public static final int BASE_HEIGHT= 480;
    public static final Color BACKGROUND= Color.BLACK;
    
    public static final int SCREEN_FILTER_PERIOD= 20;
    
    //TODO: [Resources] Rework button styles - add some factory and load from file 
    public static final String BUTTON1_S1= ""
            + " -fx-background-color: yellow;"
            + " -fx-font: 16px \"Serif\";"
            + " -fx-font-weight: bold;";
    public static final String BUTTON1_S2= ""
            + " -fx-background-color: red;"
            + " -fx-font: 16px \"Serif\";"
            + " -fx-font-weight: bold;";
    
    /**
     * Generates new Logger for given class
     * @param cl Class that requires logger
     * @return Returns logger for given class
     */
    public static Logger setupLogger(Class cl) {
        Logger log = Logger.getLogger(cl.getName());
        for(Handler h : log.getHandlers())
            log.removeHandler(h);
        
        Formatter myFormatter= new LoggingFormatter();
        
        ConsoleHandler handler= new ConsoleHandler();
        handler.setFormatter(myFormatter);
        log.addHandler(handler);
        
        log.setLevel(Level.ALL);
        
        log.setUseParentHandlers(false);
        return log;
    }
}
