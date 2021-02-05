import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader= new FXMLLoader(Main.class.getResource("/fxml/StorehouseWindow.fxml"));
         //BorderPane kontener = loader.load();
        VBox kontener = loader.load();
        Scene scene = new Scene(kontener);
        scene.getStylesheets().add("css/main.css");
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
