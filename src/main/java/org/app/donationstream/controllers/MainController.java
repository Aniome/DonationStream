package org.app.donationstream.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.app.donationstream.util.configuration.ApplyConfiguration;

public class MainController {
    @FXML
    public SplitPane splitPane;
    @FXML
    public TreeView<String> treeView;

    public void initialize() {
        TreeItem<String> root = new TreeItem<>("Root");
        treeView.setRoot(root);
        //String[] items = new String[] {}
        //root.getChildren().addAll()


    }

    public void afterShowing() {
        splitPane.setDividerPositions(ApplyConfiguration.getDividerPosition());
    }
}
