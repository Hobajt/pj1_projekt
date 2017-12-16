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
    IMAGE("images/", ".png");
    
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
    
    public String buildPath(String name) {
        return path + name + extension;
    }
}