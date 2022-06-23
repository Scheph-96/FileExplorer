/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.models;

import com.file.explorer.enumerations.FileType;
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

    private FileType type;
    private Long size;
    private Long lastModified;
    private ArrayList<SubFile> subFiles = new ArrayList();

    public SubFile() {
        super();
    }

    public SubFile(FileType type, Long size, Long lastModified, String name, String path) {
        super(name, path);
        this.type = type;
        this.lastModified = lastModified;
        if (type == FileType.File) {
            this.size = size;
        } else {
            File file = new File(path);
            for (File child : file.listFiles()) {
                if (child.isDirectory()) {
                    try {
                        subFiles.add(new SubFile(FileType.Folder,
                                Files.size(Paths.get(child.getPath())),
                                child.lastModified(), child.getName(),
                                child.getAbsolutePath()));
                    } catch (IOException ex) {
                        Logger.getLogger(SubFile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

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

}
