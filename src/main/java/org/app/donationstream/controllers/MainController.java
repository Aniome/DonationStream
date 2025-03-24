package org.app.donationstream.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.app.donationstream.RunApplication;
import org.app.donationstream.util.configuration.ApplyConfiguration;
import org.app.donationstream.util.icons.IconConfigurer;

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
        ObservableList<TreeItem<String>> treeItems = root.getChildren();
        ResourceBundle resourceBundle = RunApplication.resourceBundle;

        TreeItem<String> dataReports = new TreeItem<>(resourceBundle.getString("mainSettingsDataReports"),
                IconConfigurer.getDataReportsIcon());
        String[] dataReportsSettings = {resourceBundle.getString("mainSettingsDashboard"),
                resourceBundle.getString("mainSettingsActivityFeed"),
                resourceBundle.getString("mainSettingsRecentDonations")};
        loadDataInTreeItem(dataReportsSettings, dataReports.getChildren());
        dataReports.setExpanded(true);
        treeItems.add(dataReports);

        TreeItem<String> donationPage = new TreeItem<>(resourceBundle.getString("mainSettingsDonationPage"),
                IconConfigurer.getSettingsIcon());
        String[] donationPageSettings = {resourceBundle.getString("mainSettingsDonationPageSettings"),
                resourceBundle.getString("mainSettingsDonationPageSettings"),
                resourceBundle.getString("mainSettingsSettingsAlerts"),
                resourceBundle.getString("mainSettingsStreamStatistics"),
                resourceBundle.getString("mainSettingsDonationGoals")};
        loadDataInTreeItem(donationPageSettings, donationPage.getChildren());
        treeItems.add(donationPage);

        TreeItem<String> revenue = new TreeItem<>(resourceBundle.getString("mainSettingsRevenue"));
        String[] revenueSettings = {resourceBundle.getString("mainSettingsRevenue"),
                resourceBundle.getString("mainSettingsPayments"),
                resourceBundle.getString("mainSettingsPaymentHistory")};
        loadDataInTreeItem(revenueSettings, revenue.getChildren());
        treeItems.add(revenue);

        treeItems.add(new TreeItem<>(resourceBundle.getString("mainSettingsSupport")));

        //treeView.getStyleClass().add(Tweaks.ALT_ICON);
    }

    private void loadDataInTreeItem(String[] settings, ObservableList<TreeItem<String>> treeItemsChildren) {
        for (String setting : settings) {
            TreeItem<String> child = new TreeItem<>(setting);
            treeItemsChildren.add(child);
        }
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
