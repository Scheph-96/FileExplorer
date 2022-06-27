/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.services;

import com.file.explorer.enumerations.FileSystemType;
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
            partitions.add(new Partition(fsv.getSystemTypeDescription(root),
                    root.getTotalSpace(),
                    root.getFreeSpace(),
                    obtainType(partitionName(fsv.getSystemDisplayName(root), root, fsv)),
                    children(root.getAbsolutePath(), true),
                    partitionName(fsv.getSystemDisplayName(root), root, fsv),
                    root.getAbsolutePath()));
            subFiles.clear();
        }

        return partitions;
    }

    public static List<SubFile> children(String path, Boolean isTreeSection) throws IOException {
        File file = new File(path);
        ArrayList<SubFile> children = new ArrayList();

        try {
            if (isTreeSection) {
                children.clear();
                for (File child : file.listFiles()) {
                    if (child.isDirectory() && !child.isHidden()) {
                        children.add(new SubFile(FileSystemType.Folder,
                                Files.size(Paths.get(child.getPath())),
                                child.lastModified(), child.getName(),
                                child.getAbsolutePath()));
                    }
                }
            } else {
                children.clear();
                for (File child : file.listFiles()) {
                    children.add(new SubFile(FileSystemType.Folder,
                            Files.size(Paths.get(child.getPath())),
                            child.lastModified(), child.getName(),
                            child.getAbsolutePath()));
                }
            }
        } catch (NullPointerException e) {

        }
        return children;
    }
    
    public static String fileImage(Partition partition, Boolean isTree){
        String image = null;
        if(isTree){
        switch (partition.getFileSystemType()) {
                case SystemDrive:
                    image = "/com/file/explorer/images/treeview/drive_windows.png";
                    break;
                case SeveralDrive:
                    image = "/com/file/explorer/images/treeview/drive_harddrive.png";
                    break;
                case CD_ROM:
                    image = "/com/file/explorer/images/treeview/drive_dvdrom.png";
                    break;
                case UsbDrive:
                    image = "/com/file/explorer/images/treeview/drive_usb_removable.png";
                    break;
                default:
                    break;
            }
        }else{
        switch (partition.getFileSystemType()) {
                case SystemDrive:
                    image = "/com/file/explorer/images/files/drive_windows.png";
                    break;
                case SeveralDrive:
                    image = "/com/file/explorer/images/files/drive_harddrive.png";
                    break;
                case CD_ROM:
                    image = "/com/file/explorer/images/files/drive_dvdrom.png";
                    break;
                case UsbDrive:
                    image = "/com/file/explorer/images/files/drive_usb_removable.png";
                    break;
                default:
                    break;
            }
        }
        return image;
    }

    private static String partitionName(String name, File root, FileSystemView fsv) {
        if (name.isEmpty()) {
            name = fsv.getSystemTypeDescription(root) + " (" + root.toString().split(Pattern.quote("\\"))[0] + ") ";
        }

        return name;
    }

    private static FileSystemType obtainType(String driveName) {

        if (driveName.contains(System.getenv("SystemDrive"))) {
            return FileSystemType.SystemDrive;
        } else if (driveName.contains("CD") || driveName.contains("DVD")) {
            return FileSystemType.CD_ROM;
        } else {
            return FileSystemType.SeveralDrive;
        }

    }
}
