module module.view {
    requires transitive javafx.fxml;
    requires transitive javafx.controls;
    requires transitive javafx.media;
    requires transitive javafx.graphics;
    requires com.google.gson;

    exports view;
    opens view to javafx.fxml;
}
