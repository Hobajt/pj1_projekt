/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.view;

import game.data.ObjectManager;
import javafx.geometry.Point2D;
import main.Resizable;
import main.Window;

/**
 * Manages view orientation in world space and camera movement
 * @author Radek
 */
public class GameView implements Resizable {
    
    private Point2D center;
    private final ViewObjectManager objs;
    
    private int renderRadius;

    /**
     * Changes the rendering radius to half of the screen larger dimension
     */
    @Override
    public final void onResize() {
        Point2D tmp= Window.inst().getScreenSize().multiply(0.6);
        renderRadius= (int)(tmp.getX() > tmp.getY() ? tmp.getX() : tmp.getY());
    }
    
     /**
     * Updates the game screen
     * Called from higher level classes in response to nextTick() call
     * @param objManager 
     */
    public void updateView(ObjectManager objManager) {
        
        //get objects that are in some distance from player, draw em
        center= objs.update(objManager.getObjectsInRadius(renderRadius));
        
        
        //center on player
        
        //update objects group dependencies (only ddo for dynamic objects)
    }
    
    public GameView() {
        objs= new ViewObjectManager(this);
        onResize();
        System.out.println("--GameView Initialized--");
    }

    public int getRenderRadius() {
        return renderRadius;
    }
}
