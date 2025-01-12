module org.app.donationstream {
    requires javafx.controls;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires atlantafx.base;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jakarta.persistence;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;

    exports org.app.donationstream;
    opens org.app.donationstream.controllers;
    exports org.app.donationstream.controllers;
    opens org.app.donationstream.image;
    exports org.app.donationstream.util;
    opens org.app.donationstream.util;
    exports org.app.donationstream.util.configuration;
    opens org.app.donationstream.util.configuration;
    exports org.app.donationstream.entity;
    opens org.app.donationstream.entity;
}