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

public class EditUserController {
    protected DAOPatient dao = new DAOPatientImpl();

    private int id = 0; // Patient ID
    private App mainApp;
    private Stage primaryStage;
    private Stage editUserStage;
    private boolean saveClick = false; // нажали кнопку созранить

    @FXML
    private TextField txtName;

    @FXML
    private DatePicker dtDate;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    /**
     * Кликнули по кнопке сохранить?
     * @return
     */
    public boolean isSaveClick() {
        return  saveClick;
    }

    /**
     * Устанавливается ссылка на основную программу
     *
     * @param mainApp - ссылка на основной модуль программы
     */
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Ссылка на главную сцену
     *
     * @param primaryStage - ссылка на основную сцену
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setAddUserStage(Stage addUserStage) {
        this.editUserStage = addUserStage;
    }

    /**
     * Клик по кнопке Сохранить
     */
    @FXML
    protected void btnSaveClick(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        LocalDate birth = dtDate.getValue();
        Patient p = new Patient(id, name, birth);

        dao.update(p);

        saveClick = true;
        btnCancelClick();
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

    /**
     * Устанавливает новые данные в поля для персоны
     * @param id   - идентификатор
     * @param name - имя персоны
     * @param dt   - дата рождения персоны
     */
    public void fillData(int id, String name, LocalDate dt) {
        this.id = id;
        txtName.setText(name);
        dtDate.setValue(dt);
    }
}
