/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.models;

import java.io.File;
import java.util.List;
/**
 *
 * @author The_Me
 */
public class Partition extends FileSystem{
    
    private String description;
    private long totalSpace;
    private long freeSpace;
    private List<SubFile> subFiles;

    public Partition() {
        super();
    }

    public Partition(String description, long totalSpace, long freeSpace, List<SubFile> subFiles, String name, String path) {
        super(name, path);
        this.description = description;
        this.totalSpace = totalSpace;
        this.freeSpace = freeSpace;
        this.subFiles = subFiles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(long totalSpace) {
        this.totalSpace = totalSpace;
    }

    public long getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(long freeSpace) {
        this.freeSpace = freeSpace;
    }

    public List<SubFile> getSubFiles() {
        return subFiles;
    }

    public void setSubFiles(List<SubFile> subFiles) {
        this.subFiles = subFiles;
    }

    @Override
    public String toString() {
        return "Partitions{\n" + "description=" + description + ", totalSpace=" + totalSpace + ", freeSpace=" + freeSpace + ", subFiles=" + subFiles + "\n}";
    }
    
}
