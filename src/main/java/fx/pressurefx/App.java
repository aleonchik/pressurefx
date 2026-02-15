package fx.pressurefx;

import fx.pressurefx.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public Stage primarySTage;
    public MainController controller;

    @Override
    public void start(Stage stage) throws IOException {
        this.primarySTage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        scene.getStylesheets().add(getClass().getResource("/combo.css").toExternalForm());
        controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage);
        controller.setMainApp(this);
        stage.setTitle("Давление");
        stage.setScene(scene);
        stage.show();
    }

//    public static void main(String[] args) {
//        launch();
//    }

    public void run(String[] args) {
//        logger.info("Starting UI");
        launch(args);
    }

}