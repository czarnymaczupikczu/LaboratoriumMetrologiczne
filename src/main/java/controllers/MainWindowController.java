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
import utils.CommonTools;
import utils.FxmlTools;

import java.io.IOException;


public class MainWindowController {


    public MainWindowController(){
        System.out.println("Konstruktor klasy MainWindowController");
    }

    private final String LOGO_EP_PATH=getClass().getResource("/images/logoEP.png").toExternalForm();
    private static final String REJESTR_AP131="AP131";
    private static final String REJESTR_POZA="PozaAP";

    private final MainDataModel mainDataModel=new MainDataModel();
    public MainDataModel getMainDataModel() {
        return mainDataModel;
    }

    private RegisterWindowController registerAP131WindowController;
    private RegisterWindowController registerWindowController;
    private InstrumentsWindowController instrumentsWindowController;
    private ApplicantsWindowController applicantsWindowController;
    private AdminWindowController adminWindowController;
    //Poszczególne okna
    private VBox storageWindowVBox;
    private VBox registerAP131WindowVBox;
    private VBox registerWindowVBox;
    private VBox instrumentsWindowVBox;
    private VBox applicantsWindowVBox;
    private VBox adminWindowVBox;

    @FXML private ImageView mainWindowImageView;
    @FXML private BorderPane mainWindowBorderPane;
    @FXML private ToggleButton administrationToggleButton;
    @FXML private Label userLabel;

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
        mainWindowBorderPane.setCenter(storageWindowVBox);
    }
    @FXML
    void setRegister1Window() {
        mainWindowBorderPane.setCenter(registerAP131WindowVBox);
    }
    @FXML
    void setRegister2Window() {
        mainWindowBorderPane.setCenter(registerWindowVBox);
    }
    @FXML
    void setInstrumentsWindow(){
        mainWindowBorderPane.setCenter(instrumentsWindowVBox);
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
            this.storageWindowVBox=loader.load();
            //Kontrolery poszczególnych okien
            StorageWindowController storageWindowController = loader.getController();
            storageWindowController.setMainController(this);
            storageWindowController.init();
        } catch (IOException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }
    public void setRegisterAP131 (String fxmlPath){
        FXMLLoader loader= FxmlTools.getLoader(fxmlPath);
        try {
            this.registerAP131WindowVBox=loader.load();
            this.registerAP131WindowController=loader.getController();
            this.registerAP131WindowController.getRegisterDataModel().setRegisterType(REJESTR_AP131);
            this.registerAP131WindowController.setMainController(this);
            this.registerAP131WindowController.init();
        } catch (IOException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }
    public void setRegister (String fxmlPath){
        FXMLLoader loader= FxmlTools.getLoader(fxmlPath);
        try {
            this.registerWindowVBox=loader.load();
            this.registerWindowController=loader.getController();
            this.registerWindowController.getRegisterDataModel().setRegisterType(REJESTR_POZA);
            this.registerWindowController.setMainController(this);
            this.registerWindowController.init();
        } catch (IOException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }
    public void setInstruments (String fxmlPath){
        FXMLLoader loader= FxmlTools.getLoader(fxmlPath);
        try {
            this.instrumentsWindowVBox=loader.load();
            this.instrumentsWindowController=loader.getController();
            this.instrumentsWindowController.setMainController(this);
            this.instrumentsWindowController.init();
        } catch (IOException e) {
            CommonTools.displayAlert(e.getMessage());
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
            CommonTools.displayAlert(e.getMessage());
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
            CommonTools.displayAlert(e.getMessage());
        }
    }
}
