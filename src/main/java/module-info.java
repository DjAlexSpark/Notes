module com.example.notes  {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires javafx.graphics;

    opens com.example.notes to javafx.fxml;
    exports com.example.notes;
}