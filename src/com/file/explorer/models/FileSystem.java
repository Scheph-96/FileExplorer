/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.file.explorer.models;


/**
 * 
 * @author The_Me
 */
public class FileSystem {
    
    private String name;
    private String path;

    public FileSystem() {
    }

    public FileSystem(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "FileSystem{\n" + "name=" + name + ", path=" + path + "\n}";
    }

}
