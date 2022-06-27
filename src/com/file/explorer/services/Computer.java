/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.services;

import java.text.DecimalFormat;

/**
 *
 * @author The_Me
 */
public class Computer {

    public static String convertBytes(Long size_bytes) {
        String convertSize;
        final DecimalFormat df = new DecimalFormat("0.00");

        double size_kb = size_bytes / 1024;
        double size_mb = size_kb / 1024;
        double size_gb = size_mb / 1024;

        if (size_gb > 0) {
            convertSize = df.format(size_gb) + " GB";
        } else if (size_mb > 0) {
            convertSize = df.format(size_mb) + " MB";
        } else {
            convertSize = df.format(size_kb) + " KB";
        }
        
        return convertSize;

    }
    
    public static String hddSize(String freeSpace, String totalSpace){
        return freeSpace+" available on "+totalSpace;
    }

}