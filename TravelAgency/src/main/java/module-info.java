module com.example.travelagency {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens com.example.travelagency to javafx.fxml;
    opens com.example.travelagency.Controller to javafx.fxml;
    exports com.example.travelagency;
    exports com.example.travelagency.Controller;
    exports com.example.travelagency.Model;
    exports com.example.travelagency.DAO;
    opens com.example.travelagency.DAO to javafx.fxml;
}