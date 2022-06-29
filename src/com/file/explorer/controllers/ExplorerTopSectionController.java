/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.controllers;

import com.file.explorer.enumerations.FileSystemType;
import com.file.explorer.models.Partition;
import com.file.explorer.models.SubFile;
import com.file.explorer.services.Computer;
import com.file.explorer.services.DeviceFilesLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author The_Me
 */
public class ExplorerTopSectionController implements Initializable {

    @FXML
    AnchorPane anchPane;

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
                System.out.println("The list element: " + DeviceFilesLoader.pathHistory.get(i));
                try {
                    if (pathDisplayer.getText().equals(DeviceFilesLoader.pathHistory.get(i))) {
                        pathDisplayer.setText(DeviceFilesLoader.pathHistory.get(i - 1));

                        if (DeviceFilesLoader.pathHistory.size() == 5) {
                            DeviceFilesLoader.pathHistory.remove(0);
                        }
                        DeviceFilesLoader.pathHistory.add(pathDisplayer.getText());

                        BorderPane bPane = (BorderPane) anchPane.getParent();
                        AnchorPane bPaneAnchPane = (AnchorPane) bPane.getCenter();
                        ScrollPane scroller = (ScrollPane) bPaneAnchPane.getChildren().get(0);
                        TilePane contentDisplayer = (TilePane) scroller.getContent();

                        System.out.println("The parent: " + anchPane.getParent());
                        contentDisplayer.getChildren().clear();

                        if (pathDisplayer.getText().equals("/")) {

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
                                        }
                                    }
                                }
                            }
                            break;
                        }

                        for (SubFile subFile : DeviceFilesLoader.children(pathDisplayer.getText(), Boolean.FALSE)) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/file/explorer/view/FilesPane.fxml"));
                            Pane filesPane = loader.load();
                            FilesPaneController filesPaneController = loader.getController();
                            filesPaneController.setFileSystem(subFile);
                            VBox vbox = (VBox) filesPane.getChildren().get(0);

                            if (subFile.getFileSystemType() == FileSystemType.Folder) {
                                ImageView fileicon = (ImageView) vbox.getChildren().get(0);
                                fileicon.setImage(new Image(DeviceFilesLoader.fileImage(subFile, Boolean.FALSE)));
                            } else if (subFile.getFileSystemType() == FileSystemType.File) {
//                                System.out.println("In DevicePaneController: Is File");
                                ImageView fileicon = (ImageView) vbox.getChildren().get(0);
                                fileicon.setImage(Computer.loadNativeIcon(subFile.getPath()));
                            }
                            Label filename = (Label) vbox.getChildren().get(1);
                            filename.setText(subFile.getName());
                            contentDisplayer.getChildren().add(filesPane);

                        }
                        break;
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Out of Bound");
                    break;
                } catch (IOException ex) {
                    Logger.getLogger(ExplorerTopSectionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        leftArrow.addEventFilter(MouseEvent.MOUSE_CLICKED, leftArrowClick);

        EventHandler<MouseEvent> rightArrowClick = (MouseEvent event) -> {
            System.out.println("Right click");
            for (int i = 0; i < DeviceFilesLoader.pathHistory.size(); i++) {
                try {
                    if (pathDisplayer.getText().equals(DeviceFilesLoader.pathHistory.get(i))) {
                        pathDisplayer.setText(DeviceFilesLoader.pathHistory.get(i + 1));

                        if (DeviceFilesLoader.pathHistory.size() == 5) {
                            DeviceFilesLoader.pathHistory.remove(0);
                        }
                        DeviceFilesLoader.pathHistory.add(pathDisplayer.getText());

                        BorderPane bPane = (BorderPane) anchPane.getParent();
                        AnchorPane bPaneAnchPane = (AnchorPane) bPane.getCenter();
                        ScrollPane scroller = (ScrollPane) bPaneAnchPane.getChildren().get(0);
                        TilePane contentDisplayer = (TilePane) scroller.getContent();

                        System.out.println("The parent: " + anchPane.getParent());
                        contentDisplayer.getChildren().clear();

                        for (SubFile subFile : DeviceFilesLoader.children(pathDisplayer.getText(), Boolean.FALSE)) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/file/explorer/view/FilesPane.fxml"));
                            Pane filesPane = loader.load();
                            FilesPaneController filesPaneController = loader.getController();
                            filesPaneController.setFileSystem(subFile);
                            VBox vbox = (VBox) filesPane.getChildren().get(0);

                            if (subFile.getFileSystemType() == FileSystemType.Folder) {
                                ImageView fileicon = (ImageView) vbox.getChildren().get(0);
                                fileicon.setImage(new Image(DeviceFilesLoader.fileImage(subFile, Boolean.FALSE)));
                            } else if (subFile.getFileSystemType() == FileSystemType.File) {
//                                System.out.println("In DevicePaneController: Is File");
                                ImageView fileicon = (ImageView) vbox.getChildren().get(0);
                                fileicon.setImage(Computer.loadNativeIcon(subFile.getPath()));
                            }
                            Label filename = (Label) vbox.getChildren().get(1);
                            filename.setText(subFile.getName());
                            contentDisplayer.getChildren().add(filesPane);

                        }
                        break;

                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Out of Bound");
                } catch (IOException ex) {
                    Logger.getLogger(ExplorerTopSectionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        rightArrow.addEventFilter(MouseEvent.MOUSE_CLICKED, rightArrowClick);

        DeviceFilesLoader.pathHistory.add("/");
        pathDisplayer.setText("/");
    }

}
