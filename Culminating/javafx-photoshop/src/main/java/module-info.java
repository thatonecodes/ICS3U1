module com.photoshop {
    requires transitive javafx.graphics; //fix javafx transitive warnings
    requires transitive javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.photoshop to javafx.fxml;
    exports com.photoshop;
}
