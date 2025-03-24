package org.app.donationstream.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.app.donationstream.util.ResizeHelper;

public class DonationController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label title;
    @FXML
    private Label message;

    private Stage donationStage;

    public void initialize(Stage stage) {
        donationStage = stage;
        anchorPane.setBackground(Background.EMPTY);
    }

    public void afterShowing() {
        setBorders();
    }

    public void showDonation() {
        donationStage.show();
    }

    public void hideDonation() {
        donationStage.hide();
    }

    public void testAlert() {
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        donationStage.show();
        pause.setOnFinished(event -> {
            donationStage.hide();
        });
        pause.play();
    }

    private void setDrag() {
        final Delta dragDelta = new Delta();
        anchorPane.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = donationStage.getX() - mouseEvent.getScreenX();
            dragDelta.y = donationStage.getY() - mouseEvent.getScreenY();
        });
        anchorPane.setOnMouseDragged(mouseEvent -> {
            donationStage.setX(mouseEvent.getScreenX() + dragDelta.x);
            donationStage.setY(mouseEvent.getScreenY() + dragDelta.y);
        });
    }

    private void setBorders() {
        ResizeHelper.addResizeListener(donationStage);
    }


    // records relative x and y co-ordinates.
    static class Delta {
        double x, y;
    }
}
