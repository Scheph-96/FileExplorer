/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.controllers;

import com.file.explorer.enumerations.FileSystemType;
import com.file.explorer.models.Partition;
import com.file.explorer.services.Computer;
import com.file.explorer.services.DeviceFilesLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author The_Me
 */
public class ContentDisplayerController implements Initializable {

    @FXML
    private AnchorPane contentDisplayerAnchorPane;

    @FXML
    private TilePane contentDisplayer;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Iterate over partitions list
            for (Partition partition : DeviceFilesLoader.rootPath()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/file/explorer/view/DrivesPane.fxml"));
                Pane pane = loader.load();
                DrivesPaneController drivesPaneController = loader.getController();
                drivesPaneController.setFileSystem(partition);
                HBox hbox = (HBox) pane.getChildren().get(0);
                if (partition.getFileSystemType() == FileSystemType.CD_ROM) {
                    ImageView icon = (ImageView) hbox.getChildren().get(0);
                    icon.setImage(new Image(DeviceFilesLoader.fileImage(partition, false)));
                    // Set partition's Icon
                    contentDisplayer.getChildren().add(pane);
                    VBox vbox = (VBox) hbox.getChildren().get(1);
                    // Remove useless nodes
                    vbox.getChildren().remove(1);
                    vbox.getChildren().remove(1);
                    Label label = (Label) vbox.getChildren().get(0);
                    // Set partition's name;
                    if (label.getId().equals("partitionName")) {
                        label.setText(partition.getName());
                    }
                } else {
                    ImageView icon = (ImageView) hbox.getChildren().get(0);
                    icon.setImage(new Image(DeviceFilesLoader.fileImage(partition, false)));
                    // Set partition's Icon
                    contentDisplayer.getChildren().add(pane);
                    VBox vbox = (VBox) hbox.getChildren().get(1);
                    // Iterate over vbox children
                    for (int j = 0; j < vbox.getChildren().size(); j++) {
                        // Isolate label
                        if (vbox.getChildren().get(j) instanceof Label) {
                            Label label = (Label) vbox.getChildren().get(j);
                            // Set partition's name;
                            if (label.getId().equals("partitionName")) {
                                label.setText(partition.getName());
                                // Set partition's size;
                            } else {
                                String freeSpace = Computer.convertBytes(partition.getFreeSpace());
                                String totalSpace = Computer.convertBytes(partition.getTotalSpace());
                                label.setText(Computer.hddSize(freeSpace, totalSpace));
                                label.setStyle("-fx-font: normal normal 16px ''");
                            }
                        } else {
                            ProgressBar partitionSizeProgress = (ProgressBar) vbox.getChildren().get(j);
//                            System.out.println("The percent: "+Computer.hddPercentage(partition.getFreeSpace(), partition.getTotalSpace()));
                            partitionSizeProgress.setProgress(1 - Computer.hddPercentage(partition.getFreeSpace(), partition.getTotalSpace()));
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ContentDisplayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
