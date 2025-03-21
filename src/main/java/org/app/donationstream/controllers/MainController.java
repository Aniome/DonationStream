package org.app.donationstream.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.app.donationstream.RunApplication;
import org.app.donationstream.util.configuration.ApplyConfiguration;

public class MainController {
    @FXML
    public SplitPane splitPane;
    @FXML
    public TreeView<String> treeView;
    private DonationController donationController;

    public void initialize() {
        loadDataInTreeView();
    }

    private void loadDataInTreeView() {
        TreeItem<String> root = new TreeItem<>("");
        treeView.setRoot(root);
        String[] items = new String[]{

        };
        //root.getChildren().addAll();
    }

    @FXML
    private void showWindowDonate() {
        if (donationController == null) {
            donationController = RunApplication.showDonationAlert();
        } else {
            donationController.showDonation();
        }
    }

    @FXML
    private void hideWindowDonate() {
        donationController.hideDonation();
    }

    @FXML
    private void testDonateAlert() {
        donationController.testAlert();
    }

    public void afterShowing() {
        splitPane.setDividerPositions(ApplyConfiguration.getDividerPosition());
    }
}
