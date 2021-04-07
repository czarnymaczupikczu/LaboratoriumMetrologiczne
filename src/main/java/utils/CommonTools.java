package utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.hssf.util.HSSFColor;

import static javafx.scene.paint.Color.WHITE;

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
    static boolean answer;
    public static boolean display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label label = new Label();
        label.setText(message);
        Button yesButton = new Button("Tak");
        Button noButton = new Button("Nie");

        yesButton.setOnAction(e -> {
            answer=true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer=false;
            window.close();
        });
        VBox vBox = new VBox(10);
        HBox hBox = new HBox(10);

        hBox.getChildren().addAll(yesButton,noButton);
        hBox.setAlignment(Pos.CENTER);
        yesButton.setPrefWidth(80);
        noButton.setPrefWidth(80);
        vBox.getChildren().addAll(label,hBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefWidth(300);
        vBox.setPrefHeight(120);
        vBox.setBackground(new Background(new BackgroundFill(WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(vBox);
        scene.getStylesheets().add("css/main.css");
        //window.initStyle(StageStyle.TRANSPARENT);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
    public static void displayMessage(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label label = new Label();
        label.setText(message);
        Button yesButton = new Button("Ok");
        yesButton.setOnAction(e -> {
            window.close();
        });
        VBox vBox = new VBox(10);
        HBox hBox = new HBox(10);

        hBox.getChildren().addAll(yesButton);
        hBox.setAlignment(Pos.CENTER);
        yesButton.setPrefWidth(80);
        vBox.getChildren().addAll(label,hBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefWidth(400);
        vBox.setPrefHeight(80);
        vBox.setBackground(new Background(new BackgroundFill(WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(vBox);
        scene.getStylesheets().add("css/main.css");
        //window.initStyle(StageStyle.TRANSPARENT);
        window.setScene(scene);
        window.showAndWait();
    }
}
