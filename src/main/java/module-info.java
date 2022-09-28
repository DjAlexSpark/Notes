module com.example.notes {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    exports com.example.notes;
    exports com.example.notes.Remote;

    opens com.example.notes to
            javafx.fxml;
    opens com.example.notes.Remote to
            javafx.fxml;
}