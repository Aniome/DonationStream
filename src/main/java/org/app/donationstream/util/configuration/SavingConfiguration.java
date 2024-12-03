package org.app.donationstream.util.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.Stage;
import org.app.donationstream.RunApplication;
import org.app.donationstream.controllers.MainController;
import org.app.donationstream.util.Alerts;
import org.app.donationstream.util.HibernateUtil;

import java.io.File;
import java.io.IOException;

public class SavingConfiguration {
    public static Stage mainStage;
    public static Stage initStage;

    public static void observableMainStage(Stage stage, MainController mainController) {
        mainStage = stage;
        mainStage.setOnHiding(windowEvent -> {
            saveConfiguration(stage, mainController);

            if (initStage == null) {
                HibernateUtil.tearDown();
            }
        });
    }

    private static void saveConfiguration(Stage stage, MainController mainController) {
        SettingsData settingsData = generateSettingsData(stage, mainController);
        ObjectMapper objectMapper = new ObjectMapper();
        String settings = RunApplication.appPath + "/settings.json";
        try {
            objectMapper.writeValue(new File(settings), settingsData);
        } catch (IOException e) {
            Alerts.createAndShowError(e.getMessage());
        }
    }

    private static SettingsData generateSettingsData(Stage stage, MainController mainController) {
        SettingsData settingsData = new SettingsData();

        boolean isMaximized = stage.isMaximized();
        if (!isMaximized) {
            settingsData.setHeight(stage.getHeight());
            settingsData.setWidth(stage.getWidth());
        }

        settingsData.setMaximized(isMaximized);
        settingsData.setDividerPosition(mainController.splitPane.getDividerPositions()[0]);
        //settingsData.setTheme(ApplyConfiguration.theme);
        //settingsData.setLanguage(language);
        return settingsData;
    }

    private static void closeWindow(Stage stage) {
        if (stage != null)
            stage.close();
    }
}