/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author The_Me
 */
public class DrivesPaneController implements Initializable {
    
    @FXML
    private Pane drivePane;
    
    @FXML
    private ImageView partitionIcon;
    
    @FXML
    private Label partitionName;
    
    @FXML
    private ProgressBar paritionSizeProgress;
    
    @FXML
    private Label partitionSize;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
