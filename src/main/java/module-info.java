module fx.pressurefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens fx.pressurefx to javafx.fxml;
    exports fx.pressurefx;
    exports fx.pressurefx.dao;
    exports fx.pressurefx.controller;
    opens fx.pressurefx.controller to javafx.fxml;
}