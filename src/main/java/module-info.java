module jun.thesismanagementsystem{
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive core.fx;

    requires transitive core.db;
    requires core.util;
    requires javafx.graphics;
    requires java.base;
    requires atlantafx.base;
    requires java.management;

    opens dev.jun to javafx.fxml;
    opens dev.jun.app to javafx.fxml;

    exports dev.jun;
    exports dev.jun.app;
}
