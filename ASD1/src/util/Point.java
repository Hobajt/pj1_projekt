/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import javafx.geometry.Point2D;

/**
 * Class for Point serialization
 * @author Radek
 */
public class Point implements Serializable {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point2D getPoint2D() {
        return new Point2D(x, y);
    }
}