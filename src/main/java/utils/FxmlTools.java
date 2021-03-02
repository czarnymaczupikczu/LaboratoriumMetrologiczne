package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

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
}
