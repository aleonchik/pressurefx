package fx.pressurefx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Project: pressurefx
 * Package: fx.pressurefx.controller
 * <p>
 * User: alexey
 * Date: вс 22 февр. 2026
 */
public class AddPressureController {

    @FXML  private TextField txtName;
    @FXML  private TextField txtSys;
    @FXML  private TextField txtDia;
    @FXML  private TextField txtPulse;
    @FXML  private TextField txtDate;
    @FXML  private TextField txtTime;

    @FXML  private Button btnSave;
    @FXML  private Button btnCancel;

    @FXML
    protected void btnSaveClick(ActionEvent actionEvent) {
    }

    @FXML
    protected void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
