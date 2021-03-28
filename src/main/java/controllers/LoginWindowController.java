package controllers;

import dbModels.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.CommonTools;
import utils.FxmlTools;
import utils.database.CommonDao;

import java.io.IOException;
import java.util.List;


public class LoginWindowController {
    public LoginWindowController(){
        System.out.println("Konstruktor klasy LoginWindowController");
    }

    //Deklaracje stałych tekstowych
    private final String MAIN_WINDOW="/fxml/MainWindow.fxml";
    private final String STORAGE_WINDOW="/fxml/StorageWindow.fxml";
    private final String REGISTER_WINDOW="/fxml/RegisterWindow.fxml";
    private final String APPLICANTS_WINDOW="/fxml/ApplicantsWindow.fxml";
    private final String INSTRUMENTS_WINDOW="/fxml/InstrumentsWindow.fxml";
    private final String ADMIN_WINDOW="/fxml/admin/AdminWindow.fxml";

    private final String LOGO_EP_PATH=getClass().getResource("/images/logoEP.png").toExternalForm();
    public static final String LOGIN_ERROR = "Nieprawidłowy użytkownik i/lub hasło";

    //Elementy okna LoginWindow
    @FXML private BorderPane loginBorderPane;
    @FXML private ImageView loginImageView;
    @FXML private Label loginErrorLabel;
    @FXML private Button loginButton;
    @FXML private TextField loginTextField;
    @FXML private TextField passwordTextField;

    private MainWindowController mainController;

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera LoginWindowController ");
        this.loginImageView.setImage(new Image(LOGO_EP_PATH));
        this.loginButton.disableProperty().bind(this.loginTextField.textProperty().isEmpty().or(this.passwordTextField.textProperty().isEmpty()));
        this.loginTextField.setText("gszymczyk");
        this.passwordTextField.setText("gs");
    }
    @FXML
    private void login(){
        CommonDao commonDao=new CommonDao();
        List<UserModel> userList = commonDao.selectAndStatement(UserModel.class, "login", loginTextField.getText(), "password", passwordTextField.getText());
        if (userList.isEmpty()) {
            loginErrorLabel.setText(LOGIN_ERROR);
        }else{
            CommonTools.closePaneWindow(loginBorderPane);
            FXMLLoader loader= FxmlTools.getLoader(MAIN_WINDOW);
            try {
                //Ladowanie głównego okna
                BorderPane mainBorderPane=loader.load();
                //Główny kontroler
                mainController=loader.getController();
                //Ustawiamy usera globalnego
                mainController.getMainDataModel().setUser(userList.get(0));
                mainController.init();
                //Ladujemy poszczególne karty
                mainController.setStorage(STORAGE_WINDOW);
                mainController.setRegisterAP131(REGISTER_WINDOW);
                mainController.setRegister(REGISTER_WINDOW);
                mainController.setApplicants(APPLICANTS_WINDOW);
                if(mainController.getMainDataModel().getUser().getPermissionLevel().equals("user")){
                    mainController.disableAdministrationToggleButton();
                }else{
                    mainController.setAdmin(ADMIN_WINDOW);
                }
                mainController.init();
                showWindow(mainBorderPane);
            } catch (IOException e) {
                CommonTools.displayAlert(e.getMessage());
            }
        }
    }
    @FXML
    private void cancel() {
        CommonTools.closePaneWindow(loginBorderPane);
    }
    private void showWindow(BorderPane mainBorderPane){
        Stage window = new Stage();
        window.setTitle("LABORATORIUM METROLOGICZNE");
        Scene scene = new Scene(mainBorderPane);
        //scene.getStylesheets().add("css/main.css");
        //Application.setUserAgentStylesheet("css/main.css");

        window.setScene(scene);
        window.show();
    }

}
