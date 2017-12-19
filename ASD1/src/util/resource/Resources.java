/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Resource loading class
 * @author Radek
 */
public class Resources {
    
    /**
     * Attempts to open a stream at given location in resources
     * @param fullPath Full path to the resource (including folders within and file extension)
     * @return Returns InputStream or null if path is invalid
     */
    public static InputStream openStream(String fullPath) {
        return Resources.class.getClassLoader().getResourceAsStream(fullPath);
    }
    
    /**
     * Attempts to open a stream a given path
     * @param type Type of resource to open
     * @param id Name of the file
     * @return Returns InputStream or null if nothing is found
     */
    public static InputStream openStream(ResourceType type, String id) {
        return Resources.class.getClassLoader().getResourceAsStream(type.buildPath(id));
    }
    
    /**
     * Attempts to open a file stream, if this fails attempts to open 2nd stream (def)
     * @param type Type of resource for loading - defines path 
     * @param id Name of the stream
     * @param def Additional name, if the first one fails
     * @return Returns InputStream or null if both file names are invalid
     */
    public static InputStream openStreamOrDefault(ResourceType type, String id, String def) {
        
        String path= type.buildPath(id);
        InputStream s= Resources.class.getClassLoader().getResourceAsStream(path);
        
        if(s == null)
            s= Resources.class.getClassLoader().getResourceAsStream(type.buildPath(def));
        
        return s;
    }
    
    public static OutputStream save(String fullpath) throws IOException {
        //System.err.println("SAVING DATA");
        return Files.newOutputStream(Paths.get("res/" + fullpath));
    }
    
    public static OutputStream save(ResourceType type, String id) throws IOException {
        String path= type.buildPath(id);
        return save(path);
    }
}
