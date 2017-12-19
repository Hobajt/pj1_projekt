/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.data;

import game.Game;

/**
 * Contains all data related to this level. Class is managed as 
 * a factory- loadLevel() is method for creation calls.
 * @author Radek
 */
public class LevelData {
    
    private final ObjectData objectData;
    private final TileData tileData;
    
    /**
     * Loads new level
     * @param id LevelID to load- if <b>null</b> is passed, load the default level
     * @param game
     * @throws LevelLoadingException Thrown when error occurs during loading
     */
    public LevelData(String id, Game game) throws LevelLoadingException {
        //System.out.println("--Level data loading--");
        objectData= new ObjectData(id, game);
        tileData= new TileData(id);
        //System.out.println("--Level data loaded--");
    }

    ObjectData getObjectData() {
        return objectData;
    }

    public TileData getTileData() {
        return tileData;
    }
    
    
}
