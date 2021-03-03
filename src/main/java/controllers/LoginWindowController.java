package controllers;

import dbModels.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
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

    private final String MAIN_WINDOW="/fxml/MainWindow.fxml";
    private final String STORAGE_WINDOW="/fxml/StorageWindow.fxml";
    private final String REGISTER_WINDOW="/fxml/RegisterWindow.fxml";
    private final String REJESTR_AP131="AP131";
    private final String REJEST_POZA="PozaAP";
    private final String LOGO_EP_PATH=getClass().getResource("/images/logoEP.png").toExternalForm();
    static final String LOGIN_ERROR = "Nieprawidłowy użytkownik i/lub hasło";

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
        List<UserModel> userList = commonDao.selectWithTwoConditions(UserModel.class, "login", loginTextField.getText(), "password", passwordTextField.getText());
        if (userList.isEmpty()) {
            loginErrorLabel.setText(LOGIN_ERROR);
        }else{
            CommonTools.closePaneWindow(loginBorderPane);
            FXMLLoader loader= new FXMLLoader(LoginWindowController.class.getResource(MAIN_WINDOW));
            try {
                //Ladowanie głównego okna
                BorderPane mainBorderPane=loader.load();
                //Główny kontroler
                mainController=loader.getController();
                //Okno Storage
                FXMLLoader loader1= FxmlTools.getLoader(STORAGE_WINDOW);
                mainController.setStorageVbox1(loader1.load());
                mainController.setStorage1(loader1.getController());
                mainController.getStorage1().setMainController(this.mainController);
                mainController.getStorage1().init();
                //Okno Rejestr AP
                FXMLLoader loader2= new FXMLLoader(LoginWindowController.class.getResource(REGISTER_WINDOW));
                mainController.setRegister1Vbox(loader2.load());
                mainController.setRegister1(loader2.getController());
                mainController.getRegister1().setMainController(this.mainController);
                mainController.getRegister1().getRegisterDataModel().setRegisterType(REJESTR_AP131);
                mainController.getRegister1().init();
                //Okno Rejest poza AP
                FXMLLoader loader3= new FXMLLoader(LoginWindowController.class.getResource(REGISTER_WINDOW));
                mainController.setRegister2Vbox(loader3.load());
                mainController.setRegister2(loader3.getController());
                mainController.getRegister2().setMainController(this.mainController);
                mainController.getRegister2().getRegisterDataModel().setRegisterType(REJEST_POZA);
                mainController.getRegister2().init();
                //Ustawiamy usera globalnego
                mainController.getMainDataModel().setUser(userList.get(0));
                mainController.init();
                if(mainController.getMainDataModel().getUser().getPermissionLevel().equals("user")){
                    mainController.disableAdministrationToggleButton();
                }
                showWindow(mainBorderPane);
            } catch (IOException e) {
                CommonTools.displayAlert(e.getMessage());
            }
        }
    }
    @FXML
    private void loginCancel() {
        CommonTools.closePaneWindow(loginBorderPane);
    }
    private void showWindow(BorderPane mainBorderPane){
        Stage window = new Stage();
        window.setTitle("LABORATORIUM METROLOGICZNE");
        Scene scene = new Scene(mainBorderPane);
        scene.getStylesheets().add("css/main.css");
        window.setScene(scene);
        window.show();
    }

}
