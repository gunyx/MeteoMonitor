package ro.mta.se.lab;


import ro.mta.se.lab.Controller.MeteoMonitorController;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * JavaFX App
 */
public class Main extends Application {


    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("View/UserViewService"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(new MeteoMonitorController("in.txt","out.txt"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}