package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import utils.CommonTools;

public class AboutProgramWindowController {

    @FXML private VBox mainVBox;

    @FXML
    void cancel(){
        CommonTools.closePaneWindow(mainVBox);
    }
}
