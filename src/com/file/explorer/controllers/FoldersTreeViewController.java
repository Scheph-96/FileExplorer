/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.controllers;

import com.file.explorer.models.Partition;
import com.file.explorer.models.SubFile;
import com.file.explorer.models.TreeItemCustomObject;
import com.file.explorer.services.DeviceFilesLoader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        File[] routes;
//
//          FileSystemView fsv = FileSystemView.getFileSystemView();
//          
//          routes = fsv.getRoots();
//          
        TreeItem rootItem = new TreeItem("/");
//        
//        JFileChooser fileChooser = new JFileChooser();
//        
//        for(File file:routes[0].listFiles()){
//            TreeItem someItem = new TreeItem(file.getName());
//            someItem.setGraphic();
//            rootItem.getChildren().add(someItem);
//        }

        try {
            for (Partition partition : DeviceFilesLoader.rootPath()) {
                TreeItem<Partition> fileSystemItem = new TreeItem(new TreeItemCustomObject(partition));
                fileSystemItem.setGraphic(new ImageView(new Image("/com/file/explorer/images/folder.png")));
//                fileSystemItem.setExpanded(true);
                fileSystemItem.getChildren().add(new TreeItem("Mouais"));
                fileSystemItem.expandedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    BooleanProperty bb = (BooleanProperty) observable;
                    System.out.println("bb.getBeans() = " + bb.getBean());
                    TreeItem<TreeItemCustomObject> item = (TreeItem) bb.getBean();
                    
                    try {
                        for(SubFile subFile : DeviceFilesLoader.children(item.getValue().getPartition().getPath(), true)){
                            
                        }
//                        System.out.println(item.getValue().getPartition().getName());
                    } catch (IOException ex) {
                        Logger.getLogger(FoldersTreeViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
//                foldersTreeView.setCellFactory(param -> new TreeCell<Partition>() {
//                    @Override
//                    protected void updateItem(Partition partition, boolean empty) {
//                    super.updateItem(partition, empty);
//                        this.setText(partition.getName());
//                    }
//                });
                rootItem.getChildren().add(fileSystemItem);
            }
        } catch (IOException ex) {
            Logger.getLogger(FoldersTreeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        TreeItem webItem = new TreeItem("Web Tutorials");        
//        webItem.setGraphic(new ImageView(new Image("/com/file/explorer/images/folder.png")));
//        webItem.getChildren().add(new TreeItem("HTML Tutorial"));
//        webItem.getChildren().add(new TreeItem("HTML5 Tutorial"));
//        webItem.getChildren().add(new TreeItem("CSS Tutorial"));
//        webItem.getChildren().add(new TreeItem("SVG Tutorial"));
//        rootItem.getChildren().add(webItem);
//        
//        TreeItem javaItem = new TreeItem("Java Tutorials");
//        javaItem.getChildren().add(new TreeItem("Java Language"));
//        javaItem.getChildren().add(new TreeItem("Java Collections"));
//        javaItem.getChildren().add(new TreeItem("Java Concurrency"));
//        rootItem.getChildren().add(javaItem);
        foldersTreeView.setShowRoot(false);
        foldersTreeView.setRoot(rootItem);
    }

    public void mouseClick(MouseEvent event) {
        TreeItem<Partition> item = (TreeItem<Partition>) foldersTreeView.getSelectionModel().getSelectedItems();
//        if
    }

}
