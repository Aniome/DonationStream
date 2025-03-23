package org.app.donationstream.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.app.donationstream.RunApplication;
import org.app.donationstream.util.configuration.ApplyConfiguration;

import java.util.ResourceBundle;

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
        ResourceBundle resourceBundle = RunApplication.resourceBundle;

        TreeItem<String> dataReports = new TreeItem<>(resourceBundle.getString("mainSettingsDataReports"));
        String[] dataReportsSettings = {resourceBundle.getString("mainSettingsDashboard"),
                resourceBundle.getString("mainSettingsActivityFeed"),
                resourceBundle.getString("mainSettingsRecentDonations")};
        for (String setting : dataReportsSettings) {
            TreeItem<String> item = new TreeItem<>(setting);
            dataReports.getChildren().add(item);
        }
        dataReports.setExpanded(true);

        resourceBundle.getString("mainSettingsDonationPage");
        resourceBundle.getString("mainSettingsDonationPageSettings");
        resourceBundle.getString("mainSettingsSettingsAlerts");
        resourceBundle.getString("mainSettingsStreamStatistics");
        resourceBundle.getString("mainSettingsDonationGoals");

        resourceBundle.getString("mainSettingsRevenue");
        resourceBundle.getString("mainSettingsPayments");
        resourceBundle.getString("mainSettingsPaymentHistory");

        resourceBundle.getString("mainSettingsSupport");


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
