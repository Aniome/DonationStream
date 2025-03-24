package org.app.donationstream.util.configuration;

import atlantafx.base.theme.CupertinoDark;
import atlantafx.base.theme.CupertinoLight;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.app.donationstream.RunApplication;
import org.app.donationstream.entity.Jwt;
import org.app.donationstream.entity.SettingsData;

import java.io.*;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class ApplyConfiguration {
    private static final String DARK = "Dark";
    public static String theme;
    private static double dividerPosition;
    private static Scene mainScene;

    public static void loadAndApplySettings(Stage mainStage) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SettingsData settingsData = objectMapper.readValue(new File(RunApplication.appPath +
                    RunApplication.separator + "settings.json"), SettingsData.class);

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

    public static void changeThemeCssOnMainScene() {
        ObservableList<String> mainSceneStylesheets = mainScene.getStylesheets();
        String darkTheme = Objects.requireNonNull(RunApplication.class.getResource("css/themes/dark.css"))
                .toExternalForm();
        String lightTheme = Objects.requireNonNull(RunApplication.class.getResource("css/themes/light.css"))
                .toExternalForm();
        if (theme.equals(DARK)) {
            mainSceneStylesheets.add(darkTheme);
            mainSceneStylesheets.remove(lightTheme);
        } else {
            mainSceneStylesheets.add(lightTheme);
            mainSceneStylesheets.remove(darkTheme);
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
        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
    }

    public static void setMainScene(Scene mainScene) {
        ApplyConfiguration.mainScene = mainScene;
    }

    public static double getDividerPosition() {
        return dividerPosition;
    }
}
