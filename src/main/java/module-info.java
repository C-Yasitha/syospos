module com.brylix.derp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.brylix.derp.controller to javafx.fxml;

    exports com.brylix.derp;
    exports com.brylix.derp.controller;
    exports com.brylix.derp.model;
    exports com.brylix.derp.database;
    exports com.brylix.derp.migration;
}