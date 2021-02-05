package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import utils.CommonTools;

import java.time.LocalDate;

public class EditDateWindowController {
    public EditDateWindowController(){
        System.out.println("Konstruktor klasy EditDateWindowController");
    }

    @FXML
    VBox editDateWindowMainVBox;
    @FXML
    DatePicker editDateWindowDatePicker;

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera EditDateWindowController ");
    }


    @FXML
    private void editDateWindowToday(){
        editDateWindowDatePicker.setValue(LocalDate.now());
    }
    @FXML
    private void editDateWindowCancel() {
        CommonTools.closePaneWindow(editDateWindowMainVBox);
    }
}
