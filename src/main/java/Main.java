import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.DatabaseTools;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private final String LOGIN_WINDOW="/fxml/LoginWindow.fxml";

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
       FXMLLoader loader= new FXMLLoader(Main.class.getResource(LOGIN_WINDOW));
         BorderPane kontener = loader.load();
        //VBox kontener = loader.load();
        Scene scene = new Scene(kontener);
        scene.getStylesheets().add("css/main.css");
        primaryStage.setScene(scene);
        //DatabaseTools.initDatabase();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

}
