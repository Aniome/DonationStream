package org.app.donationstream.util.configuration;

import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Dracula;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.app.donationstream.RunApplication;
import org.app.donationstream.util.Alerts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ApplyConfiguration {
    private static double dividerPosition;
    public static String theme;
    private static final String dark = "Dark";
    private static String language;
    private static int fontSize;

    public static void build(Stage mainStage) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SettingsData settingsData = objectMapper.readValue(new File(RunApplication.appPath +
                    "/settings.json"), SettingsData.class);

            if (settingsData.language.equals("en")) {
                RunApplication.resourceBundle = ResourceBundle.getBundle("local/text", Locale.ENGLISH);
                language = "en";
            } else {
                RunApplication.resourceBundle = ResourceBundle.getBundle("local/text",
                        Locale.of("ru"));
                language = "ru";
            }

            if (settingsData.theme.equals(dark)) {
                theme = dark;
                Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());
            } else {
                theme = "Light";
                Application.setUserAgentStylesheet(new CupertinoLight().getUserAgentStylesheet());
            }

            mainStage.setHeight(settingsData.height);
            mainStage.setWidth(settingsData.width);

            mainStage.setMaximized(settingsData.maximized);

            fontSize = settingsData.fontSize;

            dividerPosition = settingsData.dividerPosition;
        } catch (FileNotFoundException e) {
            applyDefaultSettings();
        } catch (IOException e) {
            Alerts.createAndShowWarning(e.getMessage());
        }
    }

    private static void applyDefaultSettings() {
        RunApplication.resourceBundle = ResourceBundle.getBundle("local/text", Locale.ENGLISH);
        language = "en";

        theme = dark;
        Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());
        fontSize = 20;

        dividerPosition = 0.13;
    }

    public static double getDividerPosition() {
        return dividerPosition;
    }

    public static String getLanguage() {
        return language;
    }

    public static int getFontSize() {
        return fontSize;
    }
}
