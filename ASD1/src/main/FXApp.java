/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import game.Game;
import game.GameOffline;
import game.GameException;
import game.menu.MainMenu;
import game.menu.Menu;
import gameobject.data.ObjectDataFactory;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.stage.Stage;
import util.Const;

/**
 * Class that manages FX Window startup (thus starting the whole game)
 * @author Radek
 */
public class FXApp extends Application {

    private static FXApp instance;
    
    private static final Logger LOGGER= Const.setupLogger(FXApp.class);
    
    private Window window;
    private Menu activeMenu;
    private Game game;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //test obj saving
        ObjectDataFactory.inst().testSave();
        
        //<editor-fold defaultstate="collapsed" desc="Window initialization">
        instance= this;
        window= Window.inst();
        
        addResizeListener(window);
        
        primaryStage.setTitle("kek");
        primaryStage.setScene(window.getScene());
        primaryStage.show();
        //</editor-fold>
        
        reset();
    }
    
    /**
     * Returns the game to its initial state.
     */
    public void reset() {
        //cleanup variables and old references (if there are any)
        game= null;
        
        //create and show main menu (Play, Quit- later mb even options)
        activeMenu= new MainMenu(this::startGameOffline, this::quit);
        activeMenu.show();
    }
    
    /**
     * Menu response call that starts the game
     */
    private void startGameOffline() {
        activeMenu.hide();
        try {
            game= new GameOffline(null);
        } catch (GameException e) {
            System.err.println("Game could not be started- " + e.getMessage());
            reset();
        }
    }
    
    /**
     * Terminates this application
     */
    public void quit() {
        System.out.println("--Application terminated--");
        Platform.exit();
    }
    
    /**
     * Initiates the game window
     * @param args Console arguments
     */
    public static void init(String[] args) {
        launch(args);
    }
    
    /**
     * Creates new listener for scene size properties. 
     * Called during setup
     * @param w Window controller that contains the scene object
     */
    private void addResizeListener(Window w) {
        ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> {
            if(activeMenu != null)
                activeMenu.onResize();
            //LOG.info("Resizing");
            //additional resize calls
        };
        w.getScene().heightProperty().addListener(resizeListener);
        w.getScene().widthProperty().addListener(resizeListener);
    }
    
    public static FXApp inst() {
        return instance;
    }
}
