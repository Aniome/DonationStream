module org.app.donationstream {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens org.app.donationstream to javafx.fxml;
    exports org.app.donationstream;
}