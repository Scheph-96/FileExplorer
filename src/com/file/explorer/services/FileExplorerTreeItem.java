/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.file.explorer.services;

import com.file.explorer.controllers.FoldersTreeViewController;
import com.file.explorer.models.SubFile;
import com.file.explorer.models.TreeItemCustomObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;

/**
 * 
 * @author The_Me
 */
public class FileExplorerTreeItem extends TreeItem{

    public FileExplorerTreeItem() {
    }

    public FileExplorerTreeItem(Object value) {
        super(value);
    }

    public FileExplorerTreeItem(Object value, Node graphic) {
        super(value, graphic);
    }

    @Override
    public void addEventHandler(EventType eventType, EventHandler eventHandler) {
        super.addEventHandler(eventType, eventHandler); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public ObservableList getChildren() {
        return super.getChildren(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void customExpandedProperty(){
        this.expandedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
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
    }
    
    

}
