package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class FxmlTools {
    public static Pane fxmlLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FxmlTools.class.getResource(fxmlPath));

        try {
            return loader.load();
        } catch (Exception e) {
            ShowAlert.display(e.getMessage());
        }
        return null;
    }
    public static FXMLLoader getLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FxmlTools.class.getResource(fxmlPath));
        return loader;
    }
    public static <T> T openVBoxWindow(String fxmlPath, String title){
        try {
            FXMLLoader loader = new FXMLLoader(FxmlTools.class.getResource(fxmlPath));
            VBox vBox = loader.load();
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            Scene scene = new Scene(vBox);
            window.initStyle(StageStyle.TRANSPARENT);
            window.setScene(scene);
            window.show();
            return loader.getController();
        } catch (IOException e) {
            CommonTools.displayAlert(e.getMessage());
            return null;
        }
    }
}
