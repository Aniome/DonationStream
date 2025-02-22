package org.app.donationstream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.app.donationstream.controllers.MainController;
import org.app.donationstream.entity.Jwt;
import org.app.donationstream.util.configuration.ApplyConfiguration;
import org.app.donationstream.util.configuration.SavingConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class RunApplication extends Application {
    private static final int LOGIN_WIDTH = 600;
    private static final int LOGIN_HEIGHT = 400;
    private static final int MAIN_WIDTH = 1280;
    private static final int MAIN_HEIGHT = 720;
    public static ResourceBundle resourceBundle;
    public static Stage mainStage;
    public static String appPath;
    public static String separator;


    @Override
    public void start(Stage stage) throws IOException {
        //HibernateUtil.setUp();
        buildAppPathAndSeparator();
        ApplyConfiguration.loadAndApplySettings(stage);

        //add validation jwt tokens
        Jwt jwtTokens = ApplyConfiguration.getJwtTokens();
        boolean isAuthenticated = jwtTokens != null;

        mainStage = stage;
        if (isAuthenticated) {
            showMainPage(stage);
        } else {
            showLoginPage(stage);
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

    public static void showLoginPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("fxmls/login-view.fxml"),
                resourceBundle);
        Scene scene = new Scene(fxmlLoader.load(), LOGIN_WIDTH, LOGIN_HEIGHT);
        setIcon(stage);
        stage.setResizable(false);
        String loginTitle = resourceBundle.getString("loginTitle");
        prepareStage(LOGIN_HEIGHT, LOGIN_WIDTH, scene, loginTitle, stage);
        SavingConfiguration.observableLoginStage(stage);
    }

    public static void showMainPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("fxmls/main-view.fxml"),
                resourceBundle);
        Scene scene = new Scene(fxmlLoader.load(), MAIN_WIDTH, MAIN_HEIGHT);
        MainController mainController = fxmlLoader.getController();
        setIcon(stage);
        String mainTitle = resourceBundle.getString("mainTitle");
        stage.setTitle(mainTitle);
        stage.setScene(scene);
        stage.show();
        mainController.afterShowing();
        SavingConfiguration.observableMainStage(mainStage, mainController);
    }

    public static void setIcon(Stage stage) {
        stage.getIcons().add(new Image(String.valueOf(RunApplication.class.getResource("image/donate_icon.png"))));
    }

    public static void prepareStage(double height, double width, Scene scene, String title, Stage stage) {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setMinWidth(width);
        stage.setMinHeight(height);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}