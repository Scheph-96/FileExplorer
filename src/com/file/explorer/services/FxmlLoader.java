/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.file.explorer.services;

import com.file.explorer.FileExplorer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author The_Me
 */
public class FxmlLoader {
    
    private static AnchorPane pane;
    
    
    public static AnchorPane page(String filename) throws FileNotFoundException, IOException{
        URL filePath = FileExplorer.class.getResource("/com/file/explorer/view/"+filename+".fxml");
        
        if(filePath == null){
            throw new java.io.FileNotFoundException("Le fichier "+filename+" n'existe pas");
        }
        
        pane = new FXMLLoader().load(filePath);
        
        return pane;
    }

}
