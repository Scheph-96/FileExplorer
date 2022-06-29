/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.services;

import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import me.marnic.jiconextract2.JIconExtract;

/**
 *
 * @author The_Me
 */
public class Computer {

    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public static String convertBytes(Long size_bytes) {
        String convertSize;

        double size_kb = size_bytes / 1024;
        double size_mb = size_kb / 1024;
        double size_gb = size_mb / 1024;

        if (size_gb > 0) {
            convertSize = DF.format(size_gb) + " GB";
        } else if (size_mb > 0) {
            convertSize = DF.format(size_mb) + " MB";
        } else {
            convertSize = DF.format(size_kb) + " KB";
        }

        return convertSize;

    }

    public static String hddSize(String freeSpace, String totalSpace) {
        return freeSpace + " available on " + totalSpace;
    }

    public static Image loadNativeIcon(String path) {

        BufferedImage bufferedImage = JIconExtract.getIconForFile(96, 96, path);

        Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);

        return fxImage;
    }

    public static double hddPercentage(Long freeSpace, Long totalSpace) {
//        System.out.println("TotalSpace: "+totalSpace);
//        System.out.println("FreeSpace: "+freeSpace);
//        System.out.println("Brut percentage: "+((totalSpace - (totalSpace - freeSpace)) / totalSpace) * 100);
        double occupied= totalSpace - freeSpace;
        double percentage = ((totalSpace - occupied) / totalSpace) ;
//        System.out.println("The percentage: "+percentage);
        return percentage;
    }

}
