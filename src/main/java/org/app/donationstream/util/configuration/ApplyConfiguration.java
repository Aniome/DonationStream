package org.app.donationstream.util.configuration;

import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Dracula;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.stage.Stage;
import org.app.donationstream.RunApplication;
import org.app.donationstream.entity.SettingsData;
import org.app.donationstream.entity.jwtStorage;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class ApplyConfiguration {
    public static String theme;
    private static final String THEME = "Dark";
    private static SettingsData settingsData;

    public static void build() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            settingsData = objectMapper.readValue(new File(RunApplication.appPath +
                    "/settings.json"), SettingsData.class);

            if (settingsData.getLanguage().equals("en")) {
                RunApplication.resourceBundle = ResourceBundle.getBundle("local/text", Locale.ENGLISH);
            } else {
                RunApplication.resourceBundle = ResourceBundle.getBundle("local/text",
                        Locale.of("ru"));
            }

            if (settingsData.getTheme().equals(THEME)) {
                theme = THEME;
                Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());
            } else {
                theme = "Light";
                Application.setUserAgentStylesheet(new CupertinoLight().getUserAgentStylesheet());
            }
        } catch (FileNotFoundException e) {
            applyDefaultSettings();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void applySettingsOnMainPage(Stage mainStage) {
        if (settingsData == null) {
            return;
        }
        mainStage.setHeight(settingsData.getHeight());
        mainStage.setWidth(settingsData.getWidth());

        mainStage.setMaximized(settingsData.isMaximized());
    }

    public static jwtStorage getJwtTokens() {
        try (FileInputStream inputStream = new FileInputStream(RunApplication.appPath + "/data.ser");
             ObjectInputStream objectInput = new ObjectInputStream(inputStream)) {
            return (jwtStorage) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    private static void applyDefaultSettings() {
        RunApplication.resourceBundle = ResourceBundle.getBundle("local/text", Locale.ENGLISH);
        settingsData = new SettingsData("en", THEME, 1280, 720, false, 0.13);
        Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());
    }

    public static double getDividerPosition() {
        return settingsData.getDividerPosition();
    }

    public static String getLanguage() {
        return settingsData.getLanguage();
    }
}
