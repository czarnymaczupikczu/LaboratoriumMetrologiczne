package utils;

import javafx.scene.control.Alert;

/**
 * Klasa zawierająca metodę display, która służy do wyświetlania alertów. Wykorzystywana przy przechwytywaniu wyjątków.
 */
public class ShowAlert {
    public static void display(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("****** Błąd ******");
        alert.setHeaderText("Komunikat błędu");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void commonAlert(String headerText, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("****** Błąd ******");
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
