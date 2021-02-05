package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class MainWindowController {
    public MainWindowController(){
        System.out.println("Konstruktor klasy MainWindowController");
    }

    private final String LOGO_EP_PATH=getClass().getResource("/images/logoEP.png").toExternalForm();

    @FXML
    private ImageView mainWindowImageView;
    @FXML
    private VBox mainWindowMainVBox;

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera MainWindowController ");
        mainWindowImageView.setImage(new Image(LOGO_EP_PATH));
    }
}
