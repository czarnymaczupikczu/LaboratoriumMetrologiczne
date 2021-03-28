package controllers;

import controllers.admin.AdminWindowController;
import dataModels.MainDataModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import utils.FxmlTools;

import java.io.IOException;


public class MainWindowController {


    public MainWindowController(){
        System.out.println("Konstruktor klasy MainWindowController");
    }

    private final String LOGO_EP_PATH=getClass().getResource("/images/logoEP.png").toExternalForm();
    private final String REJESTR_AP131="AP131";
    private final String REJESTR_POZA="PozaAP";

    private MainDataModel mainDataModel=new MainDataModel();
    public MainDataModel getMainDataModel() {
        return mainDataModel;
    }
    //Kontrolery poszczególnych okien
    private StorageWindowController storageWindowController;
    private RegisterWindowController registerAP131WindowController;
    private RegisterWindowController registerWindowController;
    private ApplicantsWindowController applicantsWindowController;
    private AdminWindowController adminWindowController;
    //Poszczególne okna
    private VBox storageWindowVbox;
    private VBox registerAP131WindowVbox;
    private VBox registerWindowVbox;
    private VBox applicantsWindowVBox;
    private VBox adminWindowVBox;

    @FXML private ImageView mainWindowImageView;
    @FXML private VBox mainVBox;
    @FXML private BorderPane mainWindowBorderPane;
    @FXML private ToggleButton administrationToggleButton;
    @FXML private Label userLabel;

    public BorderPane getMainWindowBorderPane() {
        return mainWindowBorderPane;
    }

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera MainWindowController ");
        mainWindowImageView.setImage(new Image(LOGO_EP_PATH));
    }
    public void init(){
        this.userLabel.setText("użytkownik: "+mainDataModel.getUser().getLogin());
        setStorageWindow();
    }
    @FXML
    void setStorageWindow() {
        mainWindowBorderPane.setCenter(storageWindowVbox);
    }
    @FXML
    void setRegister1Window() {
        mainWindowBorderPane.setCenter(registerAP131WindowVbox);
    }
    @FXML
    void setRegister2Window() {
        mainWindowBorderPane.setCenter(registerWindowVbox);
    }
    @FXML
    void setApplicantsWindow(){
        mainWindowBorderPane.setCenter(applicantsWindowVBox);
    }
    @FXML
    void setAdminWindow(){
        mainWindowBorderPane.setCenter(adminWindowVBox);
    }

    public void disableAdministrationToggleButton(){
        this.administrationToggleButton.setVisible(false);
    }
    public void setStorage (String fxmlPath){
        FXMLLoader loader= FxmlTools.getLoader(fxmlPath);
        try {
            this.storageWindowVbox=loader.load();
            this.storageWindowController=loader.getController();
            this.storageWindowController.setMainController(this);
            this.storageWindowController.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setRegisterAP131 (String fxmlPath){
        FXMLLoader loader= FxmlTools.getLoader(fxmlPath);
        try {
            this.registerAP131WindowVbox=loader.load();
            this.registerAP131WindowController=loader.getController();
            this.registerAP131WindowController.getRegisterDataModel().setRegisterType(REJESTR_AP131);
            this.registerAP131WindowController.setMainController(this);
            this.registerAP131WindowController.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setRegister (String fxmlPath){
        FXMLLoader loader= FxmlTools.getLoader(fxmlPath);
        try {
            this.registerWindowVbox=loader.load();
            this.registerWindowController=loader.getController();
            this.registerWindowController.getRegisterDataModel().setRegisterType(REJESTR_POZA);
            this.registerWindowController.setMainController(this);
            this.registerWindowController.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setApplicants (String fxmlPath){
        FXMLLoader loader= FxmlTools.getLoader(fxmlPath);
        try {
            this.applicantsWindowVBox=loader.load();
            this.applicantsWindowController=loader.getController();
            this.applicantsWindowController.setMainController(this);
            //this.applicantsWindowController.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAdmin (String fxmlPath){
        FXMLLoader loader= FxmlTools.getLoader(fxmlPath);
        try {
            this.adminWindowVBox=loader.load();
            this.adminWindowController=loader.getController();
            this.adminWindowController.setMainController(this);
            //this.applicantsWindowController.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
