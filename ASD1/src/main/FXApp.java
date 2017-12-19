/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import game.Game;
import game.GameOffline;
import game.GameException;
import game.input.InputManager;
import game.menu.GameMenu;
import game.menu.MainMenu;
import game.menu.Menu;
import gameobject.GameObject;
import gameobject.combat.AttackType;
import gameobject.data.ObjectDataFactory;
import gameobject.model.ModelFactory;
import gameobject.state.ObjectState;
import java.util.Arrays;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Transform;

/**
 * Class that manages FX Window startup (thus starting the whole game)
 * @author Radek
 */
public class FXApp extends Application {

    private static FXApp instance;
    
    private Menu activeMenu;
    private Game game;
    
    private ResizeObserver onResize;
    
    private Label currWeapon;
    
    private void TMP_saveAll() {
        //TileData.saveData();
        ObjectDataFactory.inst().SaveDataBulk();
        ModelFactory.SaveModelStates("0", Arrays.asList(ObjectState.IDLE, ObjectState.RUN, ObjectState.MELEE, ObjectState.RANGED));
        ModelFactory.SaveModelStates("1", Arrays.asList(ObjectState.IDLE, ObjectState.RUN));
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //test obj saving
        TMP_saveAll();
        
        //<editor-fold defaultstate="collapsed" desc="Window initialization">
        instance= this;
        onResize= new ResizeObserver(Window.inst().getScene());
        InputManager.inst();
        
        currWeapon= new Label("");
        currWeapon.setTextFill(Color.YELLOW);
        Window.inst().getGroup(Window.GroupType.TEXT).getChildren().add(currWeapon);
        
        primaryStage.setTitle("PJ1- Projekt");
        primaryStage.setScene(Window.inst().getScene());
        primaryStage.show();
        //</editor-fold>
        
        reset();
    }
    
    /**
     * Returns the game to its initial state.
     */
    public void reset() {
        
        //create and show main menu (Play, Quit- later mb even options)
        activeMenu= new MainMenu(this::startGameOffline, this::quit);
        activeMenu.show();
    }
    
    public void pause() {
        game.setPaused(true);
        activeMenu= new GameMenu(this::unpause, this::quit);
        activeMenu.show();
    }
    
    private void unpause() {
        game.setPaused(false);
        activeMenu.hide();
    }
    
    /**
     * Menu response call that starts the game
     */
    private void startGameOffline() {
        activeMenu.hide();
        try {
            if(game != null) {
                game.resetLevel(true);
            }
            else
                game= new GameOffline(null);
        } catch (GameException e) {
            //System.err.println("Game could not be started- " + e.getMessage());
            reset();
        }
    }
    
    /**
     * Terminates this application
     */
    public void quit() {
        //System.out.println("--Application terminated--");
        Platform.exit();
    }
    
    /**
     * Initiates the game window
     * @param args Console arguments
     */
    public static void init(String[] args) {
        launch(args);
    }

    public ResizeObserver resizeObserver() {
        return onResize;
    }
    
    public static FXApp inst() {
        return instance;
    }
    
    public void createPrefab(int id, Transform pos, GameObject owner) {
        game.getObjManager().instantiatePrefab(id, pos, owner);
    }
    
    /**
     * Destroys GameObject instance
     * @param g 
     */
    public void destroyObject(GameObject g) {
        game.getObjManager().destroy(g);
    }
    
    public void changeWeapon(AttackType at) {
        currWeapon.setText(at.name());
    }
}
