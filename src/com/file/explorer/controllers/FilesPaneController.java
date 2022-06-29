/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.controllers;

import com.file.explorer.enumerations.FileSystemType;
import com.file.explorer.models.FileSystem;
import com.file.explorer.models.SubFile;
import com.file.explorer.services.Computer;
import com.file.explorer.services.DeviceFilesLoader;
import java.awt.Desktop;
import java.io.File;
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
public class FilesPaneController implements Initializable {

    @FXML
    ImageView fileIcon;

    @FXML
    Label fileName;

    @FXML
    Pane filePane;

    private FileSystem fileSystem;
    private String currentPath;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventHandler<MouseEvent> mouseEventHandler = (MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                try {
                    if (fileSystem.getFileSystemType() == FileSystemType.Folder) {
                        BorderPane bPane = (BorderPane) filePane.getParent().getParent().getParent().getParent().getParent().getParent();
                        AnchorPane anchPane = (AnchorPane) bPane.getTop();
                        HBox hbox = (HBox) anchPane.getChildren().get(0);
                        TextField pathDisplayer = (TextField) hbox.getChildren().get(1);
                        pathDisplayer.setText(fileSystem.getPath());
                    if (DeviceFilesLoader.pathHistory.size() == 5) {
                        DeviceFilesLoader.pathHistory.remove(0);
                    }
                        DeviceFilesLoader.pathHistory.add(pathDisplayer.getText());
                        TilePane contentDisplayer = (TilePane) filePane.getParent();

                        contentDisplayer.getChildren().clear();

                        for (SubFile subFile : DeviceFilesLoader.children(fileSystem.getPath(), Boolean.FALSE)) {
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
                    } else {
                        Desktop.getDesktop().open(new File(fileSystem.getPath()));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(DrivesPaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        filePane.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventHandler);
    }
    
    public void reloadPath(String path){
        try {
                    if (fileSystem.getFileSystemType() == FileSystemType.Folder) {
                        BorderPane bPane = (BorderPane) filePane.getParent().getParent().getParent().getParent().getParent().getParent();
                        AnchorPane anchPane = (AnchorPane) bPane.getTop();
                        HBox hbox = (HBox) anchPane.getChildren().get(0);
                        TextField pathDisplayer = (TextField) hbox.getChildren().get(1);
                        pathDisplayer.setText(fileSystem.getPath());
                    if (DeviceFilesLoader.pathHistory.size() == 5) {
                        DeviceFilesLoader.pathHistory.remove(0);
                    }
                        DeviceFilesLoader.pathHistory.add(pathDisplayer.getText());
                        TilePane contentDisplayer = (TilePane) filePane.getParent();

                        contentDisplayer.getChildren().clear();

                        for (SubFile subFile : DeviceFilesLoader.children(currentPath, Boolean.FALSE)) {
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
                    } else {
                        Desktop.getDesktop().open(new File(fileSystem.getPath()));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(DrivesPaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }

    public void setFileSystem(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }

}
