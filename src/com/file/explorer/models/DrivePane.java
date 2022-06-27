/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.explorer.models;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

/**
 *
 * @author The_Me
 */
public class DrivePane extends Node {

    private ImageView partitionIcon;
    private Label paritionName;
    private Label partitionSize;
    private ProgressBar paritionSizeProgress;

    public DrivePane(ImageView partitionIcon, Label paritionName, Label partitionSize, ProgressBar paritionSizeProgress) {
        this.partitionIcon = partitionIcon;
        this.paritionName = paritionName;
        this.partitionSize = partitionSize;
        this.paritionSizeProgress = paritionSizeProgress;
    }

    public ImageView getPartitionIcon() {
        return partitionIcon;
    }

    public void setPartitionIcon(ImageView partitionIcon) {
        this.partitionIcon = partitionIcon;
    }

    public Label getParitionName() {
        return paritionName;
    }

    public void setParitionName(Label paritionName) {
        this.paritionName = paritionName;
    }

    public Label getPartitionSize() {
        return partitionSize;
    }

    public void setPartitionSize(Label partitionSize) {
        this.partitionSize = partitionSize;
    }

    public ProgressBar getParitionSizeProgress() {
        return paritionSizeProgress;
    }

    public void setParitionSizeProgress(ProgressBar paritionSizeProgress) {
        this.paritionSizeProgress = paritionSizeProgress;
    }

    @Override
    protected NGNode impl_createPeer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BaseBounds impl_computeGeomBounds(BaseBounds bounds, BaseTransform tx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean impl_computeContains(double localX, double localY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object impl_processMXNode(MXNodeAlgorithm alg, MXNodeAlgorithmContext ctx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
