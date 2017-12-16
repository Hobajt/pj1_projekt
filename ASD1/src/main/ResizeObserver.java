/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;

/**
 * Listens on scene width and height properties. Notifies all registered 
 * Resizable listeners on any screen scaling.
 * @author Radek
 */
public class ResizeObserver {
    
    private final List<Resizable> listeners;
    private final Scene s;
    
    private ChangeListener<Number> resizeListener;
    
    /**
     * Adds new listener to size properties
     * @param r Resizable that wants to register
     */
    public final void addListener(Resizable r) {
        if(!listeners.contains(r))
            this.listeners.add(r);
        updateListener();
    }
    
    /**
     * Removes a listener from this class
     * @param r Resizable to remove
     * @return Returns true if listener was present
     */
    public final boolean removeListener(Resizable r) {
        return this.listeners.remove(r);
    }
    
    public ResizeObserver(Scene s) {
        this.s= s;
        listeners= new ArrayList<>();
        addListener(Window.inst());        //no need to remove it
    }
    
    /**
     * Adds new listeners and removes old from size properties on the scene
     */
    private void updateListener() {
        
        if(resizeListener != null) {
            s.heightProperty().removeListener(resizeListener);
            s.widthProperty().removeListener(resizeListener);
        }
        
        resizeListener = (observable, oldValue, newValue) -> {
            listeners.forEach((r) -> r.onResize());
        };
        
        s.heightProperty().addListener(resizeListener);
        s.widthProperty().addListener(resizeListener);
    }
}
