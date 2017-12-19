/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Window;

/**
 *
 * @author Radek
 */
class PrintMessage {
    
    private final long expire;
    private final Label lb;
    private Point2D pos;
    
    private static final int TIME_CONST= 600;
    private static List<Node> texts;
    
    public void remove() {
        texts.remove(lb);
    }
    
    public boolean update(Point2D center) {
        
        Point2D sPos= Window.inst().getScreenPoint(center, pos);
        pos= pos.add(-2,1);
        lb.setTranslateX(sPos.getX());
        lb.setTranslateY(sPos.getY());
        lb.setFont(Font.font("Verdana", FontWeight.BOLD, 9));
        
        return System.currentTimeMillis() >= expire;
    }
    
    PrintMessage(Point2D pos, String text) {
        if(texts == null)
            texts= Window.inst().getGroup(Window.GroupType.TEXT).getChildren();
        
        this.pos= pos.add(-20,20);
        expire= System.currentTimeMillis() + TIME_CONST;
        lb= new Label(text);
        lb.setTextFill(Color.BLACK);
        
        
        
        texts.add(lb);
    }
}
