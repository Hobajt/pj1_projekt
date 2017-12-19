/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.collider;

import java.io.Serializable;
import javafx.geometry.Point2D;

/**
 *
 * @author Radek
 */
public class ColliderData implements Serializable {
    
    private final int x,y;
    private final int xOff, yOff;
    //private final double angle;
    
    public double getWidth() {
        return 0;
    }
    
    public ColliderData(Point2D size, Point2D offset, double angle) {
        this((int)size.getX(), (int)size.getY(), (int)offset.getX(), (int)offset.getY(), angle);
    }
    
    public ColliderData(int x, int y, int xOff, int yOff, double angle) {
        this.x= x;
        this.y= y;
        
        this.xOff= xOff;
        this.yOff= yOff;
        
        //this.angle= angle;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxOff() {
        return xOff;
    }

    public int getyOff() {
        return yOff;
    }
    
    public Point2D getPos(Point2D basePos) {
        return basePos.add(new Point2D(xOff, yOff));
    }
/*
    public double getAngle() {
        return angle;
    }*/
}
