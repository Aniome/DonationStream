module org.app.donationstream {
    requires javafx.controls;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires atlantafx.base;
    requires javafx.fxml;

    exports org.app.donationstream;
    opens org.app.donationstream.controllers;
    exports org.app.donationstream.controllers;
    opens org.app.donationstream.image;
}