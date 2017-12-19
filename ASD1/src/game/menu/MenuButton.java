/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.menu;


import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import main.Window;

/**
 * Package class that describes my custom GUI button.
 * @author Radek
 */
class MenuButton {
    
    private final Button b;
    private String baseStyle;
    private String sizeStyle;
    private final MenuResponse response;
    private final double xPos, yPos, xSize, ySize;
    
    /**
     * Scales and moves the button so that it stands at its viewport coords
     */
    public final void onResize() {
        Point2D sSize= Window.inst().getScreenPoint(xSize, ySize);
        Point2D sPos= Window.inst().getScreenPoint(xPos, yPos);
        
        b.setTranslateX(sPos.getX()-sSize.getX()/2);
        b.setTranslateY(sPos.getY()-sSize.getY()/2);
        
        sizeStyle= sizeToStyle(sSize);
        b.setStyle(baseStyle + sizeStyle);
    }
    
    /**
     * Updates style of this button
     * @param style CSS style
     */
    public void updateStyle(String style) {
        this.baseStyle= style;
        b.setStyle(baseStyle + sizeStyle);
    }
    
    public MenuButton(String text, MenuResponse response, String style,
                                    double xPos, double yPos, double xSize, double ySize) {
        
        baseStyle= style;
        b= new Button(text);
        this.response= response;
        b.setOnAction((evt) -> response.responseCall());
        
        //viewport coords- constant
        this.xSize= xSize;
        this.ySize= ySize;
        this.xPos= xPos;
        this.yPos= yPos;
        
        
        
        onResize();
    }
    
    /**
     * Method that parses button size into the CSS
     * @param size Size of button already in pixels
     * @return String that is to be added to base CSS style string
     */
    private String sizeToStyle(Point2D size) {
        return String.format(" -fx-pref-width: %d; -fx-pref-height: %d;", (int)size.getX(), (int)size.getY());
    }
    
    /**
     * Triggers this buttons response (to allow calling out of events)
     */
    public void makeResponseCall() {
        response.responseCall();
    }
    
    public Button getButton() {
        return b;
    }
}
