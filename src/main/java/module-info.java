module com.lms.librarymanagementsystem {
    // JavaFX modules
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    // Additional libraries
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires eu.hansolo.tilesfx;

    // Standard Java modules
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;

    // Export and open directives
    exports com.lms.librarymanagementsystem; // Export your main package if needed

    opens com.lms.librarymanagementsystem to javafx.fxml; // Open main package to JavaFX FXML
    opens controllers to javafx.fxml;
    opens models to javafx.base;
    exports controllers; // Open models package to javafx.base if needed
}
