package org.app.donationstream.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.app.donationstream.util.Alerts;
import org.app.donationstream.RunApplication;

import java.io.IOException;

public class LoginController {
    @FXML
    TextField login;

    @FXML
    PasswordField password;

    @FXML
    private void login() throws IOException {
        boolean username = login.getText().equals("1");
        boolean userPassword = password.getText().equals("1");
        if (username && userPassword) {
            RunApplication.mainPage(RunApplication.stage);
            return;
        }
        String loginWrongMessage = RunApplication.resourceBundle.getString("loginWrongMessage");
        Alerts.createAndShowWarning(loginWrongMessage);
    }
}