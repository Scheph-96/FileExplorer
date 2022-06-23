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
public class TreeItemCustomObject {
    
    private String title;
    private Partition partition;

    public TreeItemCustomObject() {
    }

    public TreeItemCustomObject(Partition partition) {
        this.partition = partition;
        this.title = partition.getName();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Partition getPartition() {
        return partition;
    }

    public void setPartition(Partition partition) {
        this.partition = partition;
    }

    @Override
    public String toString() {
        return title;
    }
    
    

}
