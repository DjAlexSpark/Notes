module com.example.notes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.notes to javafx.fxml;
    exports com.example.notes;
}