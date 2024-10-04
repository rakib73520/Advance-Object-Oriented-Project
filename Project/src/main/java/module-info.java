module com.example.coffeefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;

    requires java.sql;
    requires java.desktop;
    requires jdk.xml.dom;

    opens com.example.coffeefx to javafx.fxml;
    exports com.example.coffeefx;
}