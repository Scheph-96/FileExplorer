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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author The_Me
 */
public class ExplorerTopSectionController implements Initializable {
    
    @FXML
    TextField pathDisplayer;
    
    @FXML
    ImageView leftArrow;
    
    @FXML
    ImageView rightArrow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        leftArrow.setImage(new Image("/com/file/explorer/images/arrow-left.png"));
        rightArrow.setImage(new Image("/com/file/explorer/images/arrow-right.png"));
    }    
    
}
