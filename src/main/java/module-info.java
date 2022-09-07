module com.example.untitled6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.untitled6 to javafx.fxml;
    exports com.example.untitled6;
}