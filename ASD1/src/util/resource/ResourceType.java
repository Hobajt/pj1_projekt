/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.resource;

/**
 * Specifies path and extension for given resource
 * @author Radek
 */
public enum ResourceType {
    OBJECT("data/objects/", ".dat"),
    MODEL("images/models/", ".gif"),
    IMAGE("images/", ".png"),
    TILE("images/tiles/", ".png");
    
    private final String path;
    private final String extension;

    private ResourceType(String path, String extension) {
        this.path= path;
        this.extension= extension;
    }

    public String getExtension() {
        return extension;
    }

    public String getPath() {
        return path;
    }
    
    /**
     * Builds a path to a resource
     * @param name Name of the file
     * @return Returns string representation of path
     */
    public String buildPath(String name) {
        return path + name + extension;
    }
    
    /**
     * Builds a path to a folder 
     * @param name Folder name
     * @return String representation of path
     */
    public String buildFolderPath(String name) {
        return path + name;
    }
}