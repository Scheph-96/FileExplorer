/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.models;

import com.file.explorer.enumerations.FileType;

/**
 *
 * @author The_Me
 */
public class SubFile extends FileSystem {

    private FileType type;
    private Long size;
    private Long lastModified;

    public SubFile() {
        super();
    }

    public SubFile(FileType type, Long size, Long lastModified, String name, String path) {
        super(name, path);
        this.type = type;
        if (type == FileType.File) {
            this.size = size;
            this.lastModified = lastModified;
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
