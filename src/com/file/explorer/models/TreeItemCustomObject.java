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
    private FileSystem fileSystem;
//    private Object object;
    private Partition partition;
    private SubFile subFile;

    public TreeItemCustomObject() {
    }

    public TreeItemCustomObject(Partition partition) {
        this.partition = partition;
        this.title = partition.getName();
    }

//
    public TreeItemCustomObject(SubFile subFile) {
        this.subFile = subFile;
        this.title = subFile.getName();
    }

    public SubFile getSubFile() {
        return subFile;
    }

    public void setSubFile(SubFile subFile) {
        this.subFile = subFile;
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
//
//    public Object getObject() {
//        if(this.object instanceof Partition){
//        return (Partition) object;
//    }else{
//        return (SubFile) object;
//        }
//    }
//
//    public void setObject(Object object) {
//        this.object = object;
//    }

    @Override
    public String toString() {
        return title;
    }

}
