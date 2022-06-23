/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.services;

import com.file.explorer.enumerations.FileType;
import com.file.explorer.models.Partition;
import com.file.explorer.models.SubFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author The_Me
 */
public class DeviceFilesLoader {

    public static List<Partition> rootPath() throws IOException {
        File[] roots;
        ArrayList<SubFile> subFiles = new ArrayList();
        ArrayList<Partition> partitions = new ArrayList();

        FileSystemView fsv = FileSystemView.getFileSystemView();

        roots = File.listRoots();

        for (File root : roots) {

            try {
                for (File file : root.listFiles()) {
                    subFiles.add(new SubFile(file.isFile() ? FileType.File : FileType.Folder,
                            Files.size(Paths.get(file.getPath())),
                            file.lastModified(), file.getName(),
                            file.getAbsolutePath()));
                }
            } catch (NullPointerException e) {
                System.out.println("The NullPointer message: " + e.getMessage());
            }

            partitions.add(new Partition(fsv.getSystemTypeDescription(root),
                    root.getTotalSpace(),
                    root.getFreeSpace(),
                    subFiles,
                    partitionName(fsv.getSystemDisplayName(root), root, fsv),
                    root.getAbsolutePath()));
            subFiles.clear();
        }

        return partitions;
    }

    public static List<SubFile> children(String path, Boolean isTreeSection) throws IOException {
        File file = new File(path);
        ArrayList<SubFile> children = new ArrayList();
        
        if (isTreeSection) {
            for (File child : file.listFiles()) {
                if (child.isDirectory()) {
                    children.add(new SubFile(FileType.Folder,
                            Files.size(Paths.get(child.getPath())),
                            child.lastModified(), child.getName(),
                            child.getAbsolutePath()));
                }
            }
        } else {
            for (File child : file.listFiles()) {
                children.add(new SubFile(FileType.Folder,
                        Files.size(Paths.get(child.getPath())),
                        child.lastModified(), child.getName(),
                        child.getAbsolutePath()));
            }
        }
        
        return children;
    }

    private static String partitionName(String name, File root, FileSystemView fsv) {
        if (name.isEmpty()) {
            name = fsv.getSystemTypeDescription(root) + " (" + root.toString().split(Pattern.quote("\\"))[0] + ") ";
        }

        return name;
    }

}
