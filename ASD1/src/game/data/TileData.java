/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import util.resource.Resources;

/**
 * Contains all data related to background and tiles of one level
 * @author Radek
 */
public class TileData {
    
    private static final String PATH= "data/tileset/default.dat";
    
    private List<Integer> tiles;
    private int xSize, ySize;
    
    private void load(String levelID) throws LevelLoadingException {
        
        tiles= new ArrayList<>();
        try (DataInputStream load= new DataInputStream(Resources.openStream(PATH))) {
            
            xSize= load.readInt();
            ySize= load.readInt();
            
            for(int i= 0; i < xSize*ySize; i++)
                tiles.add(load.read());
            
        } catch (IOException e) {
            
        }
    }
    
    TileData(String levelID) throws LevelLoadingException {
        System.out.println("--Tile data loading--");
        if(levelID == null)
            levelID= "default";
        
        load(levelID);
    }
    
    public static void saveData() {
        int k= 0;
        try (DataOutputStream out = new DataOutputStream(Resources.save(PATH))) {
            System.out.println(PATH);
            
            List<Integer> map= Arrays.asList(
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,2,2,2,0,0,0,0,0,
                    0,0,0,0,0,2,2,2,2,2,0,0,0,0,
                    0,0,0,0,0,2,2,2,2,2,0,0,0,0,
                    0,0,0,0,0,0,2,2,2,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0
            );
            
            int xs= 14;
            int ys= 11;
            out.writeInt(xs);
            out.writeInt(ys);
            
            for(Integer n : map) {
                out.writeInt(n);
                if(n == 2)
                    k++;
            }
            /*
            for(int i= 0; i < xs*ys; i++) {
                out.writeInt(0);
            }*/
            /*
            for(Integer n : map) {
                out.writeInt(n);
            }*/
        } catch (IOException e) {
            System.err.println("Error during tileset saving");
        }
        System.out.println("done " + k);
    }

    public List<Integer> getTiles() {
        return tiles;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }
    
    
}
