package org.app.donationstream.util.configuration;

import atlantafx.base.theme.CupertinoDark;
import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Dracula;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.stage.Stage;
import org.app.donationstream.RunApplication;
import org.app.donationstream.entity.Jwt;
import org.app.donationstream.entity.SettingsData;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class ApplyConfiguration {
    private static final String DARK = "Dark";
    public static String theme;
    private static double dividerPosition;

    public static void loadAndApplySettings(Stage mainStage) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SettingsData settingsData = objectMapper.readValue(new File(RunApplication.appPath +
                    "/settings.json"), SettingsData.class);

            //language
            if (settingsData.getLanguage().equals("en")) {
                RunApplication.resourceBundle = ResourceBundle.getBundle("local/text", Locale.ENGLISH);
            } else {
                RunApplication.resourceBundle = ResourceBundle.getBundle("local/text",
                        Locale.of("ru"));
            }

            //theme
            if (settingsData.getTheme().equals(DARK)) {
                theme = DARK;
                Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
            } else {
                theme = "Light";
                Application.setUserAgentStylesheet(new CupertinoLight().getUserAgentStylesheet());
            }

            dividerPosition = settingsData.getDividerPosition();

            mainStage.setHeight(settingsData.getHeight());
            mainStage.setWidth(settingsData.getWidth());

            mainStage.setMaximized(settingsData.isMaximized());
        } catch (FileNotFoundException e) {
            applyDefaultSettings(mainStage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Jwt getJwtTokens() {
        try (FileInputStream inputStream = new FileInputStream(RunApplication.appPath + "/data.ser");
             ObjectInputStream objectInput = new ObjectInputStream(inputStream)) {
            return (Jwt) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    private static void applyDefaultSettings(Stage mainStage) {
        RunApplication.resourceBundle = ResourceBundle.getBundle("local/text", Locale.ENGLISH);
        theme = DARK;
        dividerPosition = 0.13;
        mainStage.setHeight(1280);
        mainStage.setWidth(720);
        mainStage.setMaximized(false);
        Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());
    }

    public static double getDividerPosition() {
        return dividerPosition;
    }
}
