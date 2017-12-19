/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.view;

import game.data.TileData;
import game.data.TileFactory;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Window;

/**
 *
 * @author Radek
 */
public class TileManager {
    
    private final Group tileset;
    private final List<ImageView> tiles;
    
    private final TileData data;
    private boolean init;
    
    public TileManager(TileData data) {
        this.data= data;
        tileset= new Group();
        tiles= new ArrayList<>();
        init= false;
        
    }
    
    public void init() {
        if(init)
            return;
        init= true;
        System.out.println("--Tile Manager Initialized--");
        
        int n= 0;
        int xOff= data.getxSize()/2;
        int yOff= data.getySize()/2;
        
        Image tmp= TileFactory.inst().getImage(data.getTiles().get(0));
        
        for(int y= 0; y < data.getySize(); y++) {
            for(int x= 0; x < data.getxSize(); x++) {
                ImageView iv= new ImageView(TileFactory.inst().getImage(data.getTiles().get(y*data.getxSize()+x)));
                iv.setTranslateX(tmp.getWidth() * (x-xOff));
                iv.setTranslateY(tmp.getHeight() * (y-yOff));
                tileset.getChildren().add(iv);
                n++;
            }
        }
        
        System.out.println("CYCLES: " +n );
        Window.inst().getGroup(Window.GroupType.BACK).getChildren().add(tileset);
        /*
        for(Integer n : data.getTiles()) {
            ImageView iv= new ImageView(TileFactory.inst().getImage(n));
            
            iv.setX(xPos);
            iv.setX(yPos);
            tileset.getChildren().add(iv);
            
            
            
            xPos += iv.getImage().getWidth();
            yPos += iv.getImage().getHeight();
            if(++xCounter >= data.getxSize()) {
                System.out.println("BREAKBREAKBREAKBREAKBREAKBREAKBREAKBREAKBREAK: " + yPos);
                xCounter= 0;
                xPos= 0;
                yPos += iv.getImage().getHeight();
            }
        }
        Window.inst().getGroup(Window.GroupType.BACK).getChildren().add(tileset);
        */
    }

    public Group getTileset() {
        return tileset;
    }
}
