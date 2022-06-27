/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.controllers;

import com.file.explorer.models.Partition;
import com.file.explorer.models.TreeItemCustomObject;
import com.file.explorer.services.DeviceFilesLoader;
import com.file.explorer.services.FileExplorerTreeItem;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author The_Me
 */
public class FoldersTreeViewController implements Initializable {

    @FXML
    private AnchorPane foldersTreeViewAnchorPane;

    @FXML
    private TreeView<TreeItemCustomObject> foldersTreeView;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TreeItem rootItem = new TreeItem("/");

        try {
            for (Partition partition : DeviceFilesLoader.rootPath()) {
                FileExplorerTreeItem fileSystemItem = new FileExplorerTreeItem(new TreeItemCustomObject(partition));

                rootItem.getChildren().add(fileSystemItem);
            }
        } catch (IOException ex) {
            Logger.getLogger(FoldersTreeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        foldersTreeView.setShowRoot(false);
        foldersTreeView.setRoot(rootItem);
    }

    public void mouseClick(MouseEvent event) {
        TreeItem<Partition> item = (TreeItem<Partition>) foldersTreeView.getSelectionModel().getSelectedItems();
//        if
    }

}
