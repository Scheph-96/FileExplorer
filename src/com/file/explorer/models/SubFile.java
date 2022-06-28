/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.models;

import com.file.explorer.enumerations.FileSystemType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author The_Me
 */
public class SubFile extends FileSystem {

    private Long size;
    private Long lastModified;
    private List<SubFile> subFiles;

    public SubFile() {
        super();
    }

//    public SubFile(FileSystemType type, Long size, Long lastModified, String name, String path) {
//        super(name, path);
//        this.type = type;
//        this.size = size;
//        this.lastModified = lastModified;
//    }

    public SubFile(Long size, Long lastModified, String name, String path, FileSystemType fileSystemType) {
        super(name, path, fileSystemType);
        this.size = size;
        this.lastModified = lastModified;
    }

    public SubFile(Long lastModified, String name, String path, FileSystemType fileSystemType) {
        super(name, path, fileSystemType);
        this.lastModified = lastModified;
    }
    
    

//    public SubFile(FileSystemType type, Long size, Long lastModified, String name, String path) {
//        super(name, path);
//        this.type = type;
//        this.lastModified = lastModified;
//        if (type == FileSystemType.File) {
//            this.size = size;
//        } else {
//            System.out.println("The paths: "+path);
//            File file = new File(path);
//            for (File child : file.listFiles()) {
//                if (child.isDirectory()) {
//                    try {
//                        subFiles.add(new SubFile(FileSystemType.Folder,
//                                Files.size(Paths.get(child.getPath())),
//                                child.lastModified(), child.getName(),
//                                child.getAbsolutePath()));
//                    } catch (IOException ex) {
//                        Logger.getLogger(SubFile.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        }
//    }

    
//
//    public SubFile(FileSystemType type, Long size, Long lastModified, List<SubFile> subFiles, String name, String path) {
//        super(name, path);
//        this.type = type;
//        this.size = size;
//        this.lastModified = lastModified;
//        this.subFiles = subFiles;
//    }
   
    

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    public List<SubFile> getSubFiles() {
        return subFiles;
    }

    public void setSubFiles(ArrayList<SubFile> subFiles) {
        this.subFiles = subFiles;
    }

}
