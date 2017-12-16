/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.menu;

import util.Const;
import game.input.InputManager;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import main.Window;
import main.Window.GroupType;
import game.input.InputListenerCall;
import main.FXApp;

/**
 * Main menu - the initial menu that will popup on the start of the game
 * @author Radek
 */
public class MainMenu implements Menu {
    
    private final Group parent;                 //root element of this menu
    private final List<MenuButton> buttons;     //buttons present in this menu
    private final InputListenerCall l;              //listener for hotkeys implementation

    private MenuButton innerState;              //state for cursor position
    
    /**
     * Scales the menu to respond to current screen size
     */
    @Override
    public void onResize() {
        buttons.forEach((b) -> {
            b.onResize();
        });
    }

    /**
     * Called in InputManager when any key is pressed
     * @param c KeyCode of the key
     * @param pressed True- it has been pressed (false- released)
     */
    @Override
    public void listenerNotification(KeyCode c, boolean pressed) {
        
        //take action only on release (cuz press can send multiple signals)
        if(pressed)
            return;
        
        switch(c) {
            case W:
            case UP:
                if(buttons.indexOf(innerState) > 0) {
                    innerState= buttons.get(buttons.indexOf(innerState)-1);
                    updateStyles();
                }
                break;
            case S:
            case DOWN:
                if(buttons.indexOf(innerState) < buttons.size()-1) {
                    innerState= buttons.get(buttons.indexOf(innerState)+1);
                    updateStyles();
                }
                break;
            case ENTER:
                innerState.makeResponseCall();
                break;
        }
    }
    
    @Override
    public void show() {
        if(!Window.inst().getGroup(GroupType.MENU).getChildren().contains(parent)) {
            Window.inst().getGroup(GroupType.MENU).getChildren().add(parent);
            InputManager.inst().addListener(l);
        }
        FXApp.inst().resizeObserver().addListener(this);
    }

    @Override
    public void hide() {
        Window.inst().getCleanMenu();
        InputManager.inst().removeListener(l);
        FXApp.inst().resizeObserver().removeListener(this);
    }
    
    /**
     * Sets up this menu- generates two buttons: Play and quit
     * @param startGame response for Play button
     * @param quitApp response for Quit button
     */
    public MainMenu(MenuResponse startGame, MenuResponse quitApp) {
        parent= new Group();
        buttons= new ArrayList<>();
        Window.inst().getCleanMenu();
        
        appendButton(new MenuButton(   
                "Play", startGame, Const.BUTTON1_S1, 
                0.5, 0.3, 0.15, 0.05
        ));
        
        appendButton(new MenuButton(   
                "Quit", quitApp, Const.BUTTON1_S1, 
                0.5, 0.375, 0.15, 0.05
        ));
        
        innerState= buttons.get(0);
        updateStyles();
        l= this::listenerNotification;
    }
    
    /**
     * Updates all button styles- highlights currently selected button
     */
    private void updateStyles() {
        for(MenuButton b : buttons) {
            if(innerState.equals(b)) {
                b.updateStyle(Const.BUTTON1_S2);
            }
            else {
                b.updateStyle(Const.BUTTON1_S1);
            }
        }
    }
    
    /**
     * Private method that includes given button into this menu
     * @param b Button that is to be added
     */
    private void appendButton(MenuButton b) {
        buttons.add(b);
        parent.getChildren().add(b.getButton());
    }
}
