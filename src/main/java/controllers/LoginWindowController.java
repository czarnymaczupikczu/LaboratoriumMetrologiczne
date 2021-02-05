package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import utils.CommonTools;


public class LoginWindowController {
    public LoginWindowController(){
        System.out.println("Konstruktor klasy LoginWindowController");
    }


    private final String LOGO_EP_PATH=getClass().getResource("/images/logoEP.png").toExternalForm();
    static final String LOGIN_ERROR = "Nieprawidłowy użytkownik i/lub hasło";

    @FXML
    private BorderPane loginWindowMainBorderPane;
    @FXML
    private ImageView loginWindowImageView;
    @FXML
    private Label loginWindowErrorLabel;

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera LoginWindowController ");
        loginWindowImageView.setImage(new Image(LOGO_EP_PATH));
    }
    @FXML
    private void loginWindowLogIn(){
        loginWindowErrorLabel.setText(LOGIN_ERROR);
    }
    @FXML
    private void loginWindowCancel() {
        CommonTools.closePaneWindow(loginWindowMainBorderPane);
    }


}
