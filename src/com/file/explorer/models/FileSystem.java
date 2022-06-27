/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.file.explorer.models;

import com.file.explorer.enumerations.FileSystemType;


/**
 * 
 * @author The_Me
 */
public class FileSystem {
    
    private String name;
    private String path;
    private FileSystemType fileSystemType;

    public FileSystem() {
    }

    public FileSystem(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public FileSystem(String name, String path, FileSystemType fileSystemType) {
        this.name = name;
        this.path = path;
        this.fileSystemType = fileSystemType;
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

    public FileSystemType getFileSystemType() {
        return fileSystemType;
    }

    public void setFileSystemType(FileSystemType fileSystemType) {
        this.fileSystemType = fileSystemType;
    }

    @Override
    public String toString() {
        return "FileSystem{\n" + "name=" + name + ", path=" + path + "\n}";
    }

}
