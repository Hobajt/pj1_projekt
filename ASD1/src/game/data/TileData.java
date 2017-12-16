/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.data;

/**
 * Contains all data related to background and tiles of one level
 * @author Radek
 */
class TileData {
    
    
    private void load(String levelID) throws LevelLoadingException {
        
    }
    
    public TileData(String levelID) throws LevelLoadingException {
        if(levelID == null)
            levelID= "default";
        
        load(levelID);
    }
}
