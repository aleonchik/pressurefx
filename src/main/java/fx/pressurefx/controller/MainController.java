package fx.pressurefx.controller;

import fx.pressurefx.App;
import fx.pressurefx.dao.DAOPatient;
import fx.pressurefx.dao.DAOPatientImpl;
import fx.pressurefx.dao.DAOPressure;
import fx.pressurefx.dao.DAOPressureImpl;
import fx.pressurefx.entity.Patient;
import fx.pressurefx.entity.Pressure;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainController {

    private App mainApp;
    private Stage primaryStage;
    int userId; // user id
    /*ObservableList<Pressure> pressure = FXCollections.observableArrayList(
            new Pressure(1, 1, 130, 80, 65, LocalDateTime.now()),
            new Pressure(2, 1, 131, 81, 66, LocalDateTime.now()),
            new Pressure(3, 1, 132, 82, 67, LocalDateTime.now()),
            new Pressure(4, 1, 133, 83, 68, LocalDateTime.now()),
            new Pressure(5, 1, 134, 84, 69, LocalDateTime.now())
    );*/

//    ObservableList<Pressure> pressure = FXCollections.observableArrayList();
    ObservableList<Pressure> pressure;

    protected DAOPatient dao = new DAOPatientImpl();
    protected DAOPressure daoPressure = new DAOPressureImpl();

    @FXML private ComboBox<Patient> cmbUser;

    @FXML private Button btnUpdate;
    @FXML private Button btnAddP;
    @FXML private Button btnEditP;
    @FXML private Button btnDelP;

    @FXML private Button btnAdd;
    @FXML private Button btnEdit;
    @FXML private Button btnDel;
    @FXML private Button btnExit;

    @FXML private TableView<Pressure> tvPressure;

    @FXML private TableColumn<Pressure, Integer> colId;
    @FXML private TableColumn<Pressure, Integer> colSys;
    @FXML private TableColumn<Pressure, Integer> colDia;
    @FXML private TableColumn<Pressure, Integer> colPulse;
    @FXML private TableColumn<Pressure, LocalDateTime> colDt;

    @FXML
    void initialize() {
        // Table
        fillTable();
        tvPressure.setItems(pressure);

        colId.setCellValueFactory(new PropertyValueFactory<Pressure, Integer>("id"));

        colSys.setCellValueFactory(new PropertyValueFactory<Pressure, Integer>("sys"));

        colDia.setCellValueFactory(new PropertyValueFactory<Pressure, Integer>("dia"));
        colPulse.setCellValueFactory(new PropertyValueFactory<Pressure, Integer>("pulse"));

        colDt.setCellValueFactory(new PropertyValueFactory<Pressure, LocalDateTime>("dt"));
        colDt.setCellFactory(column -> {
            TableCell<Pressure, LocalDateTime> cell = new TableCell<Pressure, LocalDateTime>() {
                private DateTimeFormatter format = DateTimeFormatter.ofPattern("E d MMM yyyy H:m");
//                private DateTimeFormatter format = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);

                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        this.setText(format.format(item));
                    }
                }
            };

            return cell;
        });

        /**
         * Событие при выборе из комбинированного списка
         */
        cmbUser.setOnAction(actionEvent -> {
            Patient p = cmbUser.getValue();

            if (p != null) {
                userId = p.id();
            }
        });

        /**
         * Конвертер коминированного списка
         */
        cmbUser.setConverter(new StringConverter<Patient>() {
            @Override
            public String toString(Patient patient) {

                int age = 0;
                // для корректного подсчета полного количества лет
                if (patient != null) {
                    LocalDate now = LocalDate.now();
                    LocalDate dbirth = patient.birthday();
                    Period p = Period.between(dbirth, now);
                    age = p.getYears();
                }

                return patient != null ? patient.name() + " " + age + " лет"  : "Выберите пользователя" ;
            }

            @Override
            public Patient fromString(String s) {
                return null;
            }
        });

        fillCombo();
    }

    /**
     * Заполним список пользователей
     */
    public void fillCombo() {
        List<Patient> patients = dao.getAll();

        cmbUser.getSelectionModel().clearSelection();
        cmbUser.getItems().clear();

        for (Patient p : patients) {
            cmbUser.getItems().add(p);
        }
    }

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

    /**
     * Выход из программы
     */
    @FXML
    protected void btnExitClick() {
        Platform.exit();
    }

    /**
     * Добавить пациента (пользователя)
     */
    @FXML
    protected void btnAddClick() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fx/pressurefx/add-user.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        Parent root = loader.getRoot();
        stage.initOwner(primaryStage);
        stage.setTitle("Добавить");

        AddUserController controller = loader.getController();
        controller.setAddUserStage(stage);
        controller.setMainApp(mainApp);
        controller.setPrimaryStage(primaryStage);

        stage.showAndWait();

        fillCombo();
    }

    /**
     * Редактирование пациента (пользователя)
     * @throws IOException
     */
    @FXML
    protected void btnEditClick() throws IOException {

        if (userId != 0) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fx/pressurefx/edit-user.fxml"));

            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initModality(Modality.WINDOW_MODAL);
            Parent root = loader.getRoot();
            stage.initOwner(primaryStage);
            stage.setTitle("Редактирование");

            EditUserController controller = loader.getController();

            // Получим данные пользователя для редактирования
            Patient patient = dao.getOne(userId);
            // Передадим в контроллер
            controller.fillData(patient.id(), patient.name(), patient.birthday());

            controller.setAddUserStage(stage);
            controller.setMainApp(mainApp);
            controller.setPrimaryStage(primaryStage);

            stage.showAndWait();

            if (controller.isSaveClick()) {
                fillCombo();
            }

        }
    }

    /**
     * Удаляем пациента (пользователя)
     */
    @FXML
    protected void btnDelClick() {
        if (userId != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление");
            alert.setHeaderText("Удаление");
            alert.setContentText("Действительно хотите удалить запись?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                dao.delete(userId);
                fillCombo();
            }
        }
//        cmbUser.getSelectionModel().select(0);
    }

    /**
     * Наполним таблицу данными...
     */
    public void fillTable() {
        DAOPressure dao = new DAOPressureImpl();

        List<Pressure> lp = dao.getAll(1, 100);
        pressure = FXCollections.observableArrayList(lp);
    }

    /**
     * Добавим давление пользователя
     * @param actionEvent
     */
    public void btnAddPressure(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fx/pressurefx/add-pressure.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        Parent root = loader.getRoot();
        stage.initOwner(primaryStage);
        stage.setTitle("Редактирование");

        AddPressureController controller = loader.getController();

        // Получим данные пользователя для редактирования
        Patient patient = dao.getOne(userId);
        // Передадим в контроллер
//        controller.fillData(patient.id(), patient.name(), patient.birthday());

//        controller.setAddUserStage(stage);
//        controller.setMainApp(mainApp);
//        controller.setPrimaryStage(primaryStage);

        stage.showAndWait();

    }
}