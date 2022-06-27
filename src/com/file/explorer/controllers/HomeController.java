/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.controllers;

import com.file.explorer.services.FxmlLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author The_Me
 */
public class HomeController implements Initializable {
    
    @FXML
    private BorderPane borderPane;
    
    private AnchorPane pane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            pane = (AnchorPane) FxmlLoader.page("ExplorerTopSection");
            borderPane.setTop(pane);
            
            pane = (AnchorPane) FxmlLoader.page("FoldersTreeView");
            borderPane.setLeft(pane);
            
            pane = (AnchorPane) FxmlLoader.page("ContentDisplayer");
            borderPane.setCenter(pane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
