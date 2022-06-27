/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.services;

import com.file.explorer.models.SubFile;
import com.file.explorer.models.TreeItemCustomObject;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author The_Me
 */
public class FileExplorerTreeItem extends TreeItem {

    public FileExplorerTreeItem() {
    }

    public FileExplorerTreeItem(TreeItemCustomObject value, Node graphic) {
        super(value, graphic);
        FileExplorerTreeItem placeholder = new FileExplorerTreeItem();
        this.getChildren().add(placeholder);

        FileExplorerTreeItem fileExplorerTreeItem = this;

        this.addEventHandler(TreeItem.branchExpandedEvent(), new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("The SubFile: " + value.getSubFile().getName());
                if (value.getSubFile() != null) {
                    fileExplorerTreeItem.setGraphic(new ImageView(new Image("/com/file/explorer/images/treeview/tree_folder_expanded.png")));
                }
                fileExplorerTreeItem.getChildren().clear();
                try {
                    List<SubFile> subFiles = DeviceFilesLoader.children(value.getPartition() != null ? value.getPartition().getPath() : value.getSubFile().getPath(), Boolean.TRUE);

                    subFiles.forEach((subFile) -> {
                        System.out.println("In for loop: " + subFile.getName());
                        fileExplorerTreeItem.getChildren().add(new FileExplorerTreeItem(new TreeItemCustomObject(subFile), new ImageView(new Image("/com/file/explorer/images/tree_folder_collapse.png"))));
                    });

                } catch (IOException ex) {
                    Logger.getLogger(FileExplorerTreeItem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        this.addEventHandler(TreeItem.branchCollapsedEvent(), new EventHandler() {
            @Override
            public void handle(Event event) {
                fileExplorerTreeItem.setGraphic(new ImageView(new Image("/com/file/explorer/images/treeview/tree_folder_collapse.png")));
            }

        });
    }

    public FileExplorerTreeItem(TreeItemCustomObject value) {
        super(value);
        FileExplorerTreeItem placeholder = new FileExplorerTreeItem();
                    this.setGraphic(new ImageView(new Image(DeviceFilesLoader.fileImage(value.getPartition(), Boolean.TRUE))));
//        if (value.getPartition() != null) {
//
//            switch (value.getPartition().getFileSystemType()) {
//                case SystemDrive:
//                    this.setGraphic(new ImageView(new Image("/com/file/explorer/images/treeview/drive_windows.png")));
//                    break;
//                case SeveralDrive:
//                    this.setGraphic(new ImageView(new Image("/com/file/explorer/images/treeview/drive_harddrive.png")));
//                    break;
//                case CD_ROM:
//                    this.setGraphic(new ImageView(new Image("/com/file/explorer/images/treeview/drive_dvdrom.png")));
//                    break;
//                case UsbDrive:
//                    this.setGraphic(new ImageView(new Image("/com/file/explorer/images/treeview/drive_usb_removable.png")));
//                    break;
//                default:
//                    break;
//            }
//        }

        this.getChildren().add(placeholder);
        FileExplorerTreeItem fileExplorerTreeItem = this;

        this.addEventHandler(TreeItem.branchExpandedEvent(), new EventHandler() {
            @Override
            public void handle(Event event) {
                if (value.getSubFile() != null) {
                    fileExplorerTreeItem.setGraphic(new ImageView(new Image("/com/file/explorer/images/treeview/tree_folder_expanded.png")));
                }
                fileExplorerTreeItem.getChildren().remove(placeholder);
                fileExplorerTreeItem.removeEventHandler(TreeItem.branchExpandedEvent(), this);
                try {
                    List<SubFile> subFiles = DeviceFilesLoader.children(value.getPartition() != null ? value.getPartition().getPath() : value.getSubFile().getPath(), Boolean.TRUE);

                    subFiles.forEach((subFile) -> {
                        System.out.println("In for loop: " + subFile.getName());
                        fileExplorerTreeItem.getChildren().add(new FileExplorerTreeItem(new TreeItemCustomObject(subFile), new ImageView(new Image("/com/file/explorer/images/tree_folder_collapse.png"))));
                    });

                } catch (IOException ex) {
                    Logger.getLogger(FileExplorerTreeItem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

}
