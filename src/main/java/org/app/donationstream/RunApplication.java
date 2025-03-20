package org.app.donationstream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.app.donationstream.controllers.DonationController;
import org.app.donationstream.controllers.MainController;
import org.app.donationstream.entity.Jwt;
import org.app.donationstream.util.Alerts;
import org.app.donationstream.util.configuration.ApplyConfiguration;
import org.app.donationstream.util.configuration.SavingConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class RunApplication extends Application {
    private static final int LOGIN_WIDTH = 600;
    private static final int LOGIN_HEIGHT = 400;
    private static final int MAIN_WIDTH = 800;
    private static final int MAIN_HEIGHT = 600;
    public static ResourceBundle resourceBundle;
    public static Stage mainStage;
    public static String appPath;
    public static String separator;


    @Override
    public void start(Stage stage) {
        buildAppPathAndSeparator();
        ApplyConfiguration.loadAndApplySettings(stage);

        //add validation jwt tokens
        Jwt jwtTokens = ApplyConfiguration.getJwtTokens();
        boolean isAuthenticated = jwtTokens != null;

        mainStage = stage;
        if (isAuthenticated) {
            showMainPage();
        } else {
            showLoginPage();
        }
    }

    public static void buildAppPathAndSeparator() {
        String path = new File("").getAbsolutePath();
        if (path.contains("/")) {
            separator = "/";
        } else {
            separator = "\\";
        }
        appPath = path;
    }

    public static void showLoginPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("fxmls/login-view.fxml"),
                    resourceBundle);
            Scene scene = new Scene(fxmlLoader.load(), LOGIN_WIDTH, LOGIN_HEIGHT);
            Stage initialStage = new Stage();
            SavingConfiguration.setLoginStage(initialStage);
            setIcon(initialStage);
            initialStage.setResizable(false);
            String loginTitle = resourceBundle.getString("loginTitle");
            prepareStage(LOGIN_HEIGHT, LOGIN_WIDTH, scene, loginTitle, initialStage);
        } catch (IOException e) {
            Alerts.createAndShowError(String.valueOf(e));
        }
    }

    public static void showMainPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("fxmls/main-view.fxml"),
                    resourceBundle);
            Scene scene = new Scene(fxmlLoader.load(), MAIN_WIDTH, MAIN_HEIGHT);
            MainController mainController = fxmlLoader.getController();
            setIcon(mainStage);
            prepareStage(MAIN_HEIGHT, MAIN_WIDTH, scene, resourceBundle.getString("mainTitle"), mainStage);
            mainController.afterShowing();
            SavingConfiguration.observableMainStage(mainStage, mainController);
        } catch (IOException e) {
            Alerts.createAndShowError(String.valueOf(e));
        }
    }

    public static DonationController showDonationAlert() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("fxmls/donate-view.fxml"),
                    resourceBundle);
            Scene scene = new Scene(fxmlLoader.load());
            Stage donationStage = new Stage();
            donationStage.initStyle(StageStyle.UNDECORATED);
            DonationController donationController = fxmlLoader.getController();
            donationController.initialize(donationStage);
            SavingConfiguration.donationStage = donationStage;
            prepareStage(200, 200, scene, "Donate", donationStage);
            donationController.afterShowing();
            return donationController;
        } catch (IOException e) {
            Alerts.createAndShowError(String.valueOf(e));
            return null;
        }
    }

    public static void setIcon(Stage stage) {
        stage.getIcons().add(new Image(String.valueOf(RunApplication.class.getResource("image/donate_icon.png"))));
    }

    public static void prepareStage(double minHeight, double minWidth, Scene scene, String title, Stage stage) {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}