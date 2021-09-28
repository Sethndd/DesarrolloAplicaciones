module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;
    requires postgresql;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports DataBaseConnection;
    opens DataBaseConnection to javafx.fxml;
    exports POJO;
    opens POJO to javafx.fxml;
}