package org.app.donationstream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.app.donationstream.controllers.LoginController;
import org.app.donationstream.controllers.MainController;
import org.app.donationstream.util.HibernateUtil;
import org.app.donationstream.util.configuration.ApplyConfiguration;
import org.app.donationstream.util.configuration.SavingConfiguration;

import java.io.IOException;
import java.util.ResourceBundle;

public class RunApplication extends Application {
    private static final int loginWidth = 600;
    private static final int loginHeight = 400;
    private static final int mainWidth = 1280;
    private static final int mainHeight = 720;
    public static ResourceBundle resourceBundle;
    public static Stage mainStage;
    public static String appPath;

    @Override
    public void start(Stage stage) throws IOException {
        ApplyConfiguration.build(stage);
        HibernateUtil.setUp();

        mainStage = stage;
        boolean isAuthenticated = true;

        if (isAuthenticated) {
            showMainPage(stage);
        } else {
            showLoginPage(stage);
        }

    }

    public static void showLoginPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("fxmls/login-view.fxml"),
                resourceBundle);
        Scene scene = new Scene(fxmlLoader.load(), loginWidth, loginHeight);
        setIcon(stage);
        stage.setResizable(false);
        String loginTitle = resourceBundle.getString("loginTitle");
        prepareStage(loginHeight, loginWidth, scene, loginTitle, stage);
        SavingConfiguration.observableLoginStage(stage);
    }

    public static void showMainPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("fxmls/main-view.fxml"),
                resourceBundle);
        Scene scene = new Scene(fxmlLoader.load(), mainWidth, mainHeight);
        MainController mainController = fxmlLoader.getController();
        setIcon(stage);
        String mainTitle = resourceBundle.getString("mainTitle");
        prepareStage(loginHeight, loginWidth, scene, mainTitle, stage);
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