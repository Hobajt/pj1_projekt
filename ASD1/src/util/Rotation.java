/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * Serves as rotation description (double representation is not needed)
 * @author Radek
 */
public enum Rotation {
    NORTH(0,1),
    NORTH_EAST(-1,1),
    EAST(-1,0),
    SOUTH_EAST(-1,-1),
    SOUTH(0,-1),
    SOUTH_WEST(1,-1),
    WEST(1,0),
    NORTH_WEST(1,1);

    private final int x;
    private final int y;
    
    private Rotation(int x, int y) {
        this.x= x;
        this.y= y;
    }
    
    public static Rotation getDefault() {
        return SOUTH;
    }
    
    public static Rotation getByMoveDir(int x, int y) {
        for(Rotation r: Rotation.values()) {
            if(r.x == x && r.y == y)
                return r;
        }
        return getDefault();
    }
}
