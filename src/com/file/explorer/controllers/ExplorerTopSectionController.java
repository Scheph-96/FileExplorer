/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.controllers;

import com.file.explorer.services.DeviceFilesLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        leftArrow.setImage(new Image("/com/file/explorer/images/arrow-left.png"));
        rightArrow.setImage(new Image("/com/file/explorer/images/arrow-right.png"));

        EventHandler<MouseEvent> leftArrowClick = (MouseEvent event) -> {
            System.out.println("Left click");
            for (int i = 0; i < DeviceFilesLoader.pathHistory.size(); i++) {
                System.out.println(DeviceFilesLoader.pathHistory.size());
                System.out.println(DeviceFilesLoader.pathHistory.get(i));
                try {
                    if (pathDisplayer.getText().equals(DeviceFilesLoader.pathHistory.get(i))) {
                        pathDisplayer.setText(DeviceFilesLoader.pathHistory.get(i - 1));
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Out of Bound");
                }
            }
        };
        leftArrow.addEventFilter(MouseEvent.MOUSE_CLICKED, leftArrowClick);

        EventHandler<MouseEvent> rightArrowClick = (MouseEvent event) -> {
            System.out.println("Right click");
            for (int i = 0; i < DeviceFilesLoader.pathHistory.size(); i++) {
                System.out.println(DeviceFilesLoader.pathHistory.size());
                System.out.println(DeviceFilesLoader.pathHistory.get(i));
                try {
                    if (pathDisplayer.getText().equals(DeviceFilesLoader.pathHistory.get(i))) {
                        pathDisplayer.setText(DeviceFilesLoader.pathHistory.get(i + 1));
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Out of Bound");
                }
            }
        };
        rightArrow.addEventFilter(MouseEvent.MOUSE_CLICKED, rightArrowClick);

        DeviceFilesLoader.pathHistory.add("/");
        pathDisplayer.setText("/");
    }

}
