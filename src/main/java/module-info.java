module com.example.notes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.notes to javafx.fxml;
    exports com.example.notes;
}