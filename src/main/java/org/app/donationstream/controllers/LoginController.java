package org.app.donationstream.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.app.donationstream.RunApplication;
import org.app.donationstream.entity.Jwt;
import org.app.donationstream.util.Alerts;
import org.app.donationstream.util.configuration.SavingConfiguration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoginController {
    @FXML
    TextField login;
    @FXML
    PasswordField password;

    @FXML
    private void login() {
        String username = login.getText();
        String userPassword = password.getText();
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://localhost:8080/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{" +
                            "\"username\":\"" + username + "\"," +
                            "\"password\":\"" + userPassword + "\"" +
                            "}"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                Jwt jwt = objectMapper.readValue(response.body(), Jwt.class);
                SavingConfiguration.saveJwt(jwt);
                RunApplication.showMainPage();
                return;
            }
        } catch (InterruptedException | URISyntaxException | IOException e) {
            Alerts.createAndShowError(e.getMessage());
            return;
        }
        String loginWrongMessage = RunApplication.resourceBundle.getString("loginWrongMessage");
        Alerts.createAndShowWarning(loginWrongMessage);
    }
}