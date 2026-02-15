package fx.pressurefx.controller;

import fx.pressurefx.App;
import fx.pressurefx.dao.DAOPatient;
import fx.pressurefx.dao.DAOPatientImpl;
import fx.pressurefx.entity.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddUserController {

    protected DAOPatient dao = new DAOPatientImpl();

    private int id = 0; // Patient ID
    private App mainApp;
    private Stage primaryStage;
    private Stage addUserStage;

    @FXML
    private TextField txtName;

    @FXML
    private DatePicker tdDate;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    /**
     * Устанавливается ссылка на основную программу
     * @param mainApp - ссылка на основной модуль программы
     */
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Ссылка на главную сцену
     * @param primaryStage - ссылка на основную сцену
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setAddUserStage(Stage addUserStage) {
        this.addUserStage = addUserStage;
    }

    @FXML
    protected void btnSaveClick(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        LocalDate birth = tdDate.getValue();

        Patient p = null;

        if (id != 0) {
            p = new Patient(id, name, birth);
        } else {
            p = new Patient(0, name, birth);
        }

        int numRows = dao.insert(p);
        // Если добавление прошло без ошибок - просто закроем окно
        if (numRows > 0) {
            btnCancelClick();
        }
    }

    /**
     * Закроем окно
     */
    @FXML
    protected void btnCancelClick() {
//        addUserStage.close();
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

    }

    public void fillData(int id, String name, LocalDate dt) {
        this.id = id;
        txtName.setText(name);
        tdDate.setValue(dt);
    }

}
