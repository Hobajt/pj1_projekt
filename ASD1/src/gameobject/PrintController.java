/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author Radek
 */
public class PrintController {
    
    private final List<PrintMessage> messages;
    
    public void update(Point2D center) {
        for(int i= messages.size()-1; i >= 0; i--) {
            if(messages.get(i).update(center)) {
                messages.get(i).remove();
                messages.remove(i);
            }
        }
    }
    
    public void printMessage(String text, Point2D pos) {
        messages.add(new PrintMessage(pos, text));
    }
    
    private static PrintController instance;
    
    private PrintController() {
        this.messages= new ArrayList<>();
    }
    
    public static PrintController inst() {
        if(instance == null)
            instance= new PrintController();
        return instance;
    }
}
