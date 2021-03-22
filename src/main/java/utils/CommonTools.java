package utils;

import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Klasa zawierające metody do zamykania okien.
 */
public class CommonTools {
    public static void closePaneWindow(Pane pane){
        Stage window = (Stage)pane.getScene().getWindow();
        window.close();
    }
    public static void displayAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("****** Błąd ******");
        alert.setHeaderText("Komunikat błędu");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static String deleteWhiteSpaces(String s){
        return s.replaceAll("\\s+","");
    }
}
