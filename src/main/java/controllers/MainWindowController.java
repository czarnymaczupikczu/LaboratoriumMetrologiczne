package controllers;

import controllers.admin.AdminWindowController;
import dataModels.MainDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class MainWindowController {
    public MainWindowController(){
        System.out.println("Konstruktor klasy MainWindowController");
    }

    private final String LOGO_EP_PATH=getClass().getResource("/images/logoEP.png").toExternalForm();
    private final String STORAGE_FXML="/fxml/StorageWindow.fxml";


    private MainDataModel mainDataModel=new MainDataModel();
    public MainDataModel getMainDataModel() {
        return mainDataModel;
    }

    private StorageWindowController storage;
    private RegisterWindowController register1;
    private RegisterWindowController register2;
    private ApplicantsWindowController applicants;
    private AdminWindowController admin;
    private VBox storageVbox;
    private VBox register1Vbox;
    private VBox register2Vbox;
    private VBox applicantsVBox;
    private VBox adminVBox;

    public StorageWindowController getStorage() {
        return storage;
    }
    public void setStorage(StorageWindowController storage) {
        this.storage = storage;
    }
    public RegisterWindowController getRegister1() {
        return register1;
    }
    public void setRegister1(RegisterWindowController register1) {
        this.register1 = register1;
    }
    public RegisterWindowController getRegister2() {
        return register2;
    }
    public void setRegister2(RegisterWindowController register2) {
        this.register2 = register2;
    }
    public ApplicantsWindowController getApplicants() {
        return applicants;
    }
    public void setApplicants(ApplicantsWindowController applicants) {
        this.applicants = applicants;
    }
    public AdminWindowController getAdmin() {
        return admin;
    }
    public void setAdmin(AdminWindowController admin) {
        this.admin = admin;
    }

    public void setStorageVbox1(VBox storageVbox) {
        this.storageVbox = storageVbox;
    }
    public void setRegister1Vbox(VBox register1Vbox) {
        this.register1Vbox = register1Vbox;
    }
    public void setRegister2Vbox(VBox register2Vbox) {
        this.register2Vbox = register2Vbox;
    }
    public void setApplicantsVBox(VBox applicantsVBox) {
        this.applicantsVBox = applicantsVBox;
    }
    public void setAdminVBox(VBox adminVBox) {
        this.adminVBox = adminVBox;
    }

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
        if (storageVbox==null) {
           // storageVbox1 = FxmlTools.fxmlLoader(STORAGE_FXML);
        }
        mainWindowBorderPane.setCenter(storageVbox);

    }
    @FXML
    void setRegister1Window() {
        if (register1Vbox==null) {
            //storageVbox2 = FxmlTools.fxmlLoader(STORAGE_FXML);
        }
        mainWindowBorderPane.setCenter(register1Vbox);
    }
    @FXML
    void setRegister2Window() {
        if (register2Vbox==null) {
           // storageVbox3 = FxmlTools.fxmlLoader(STORAGE_FXML);
        }
        mainWindowBorderPane.setCenter(register2Vbox);
    }
    @FXML
    void setApplicantsWindow(){
        if(applicantsVBox==null){

        }
        mainWindowBorderPane.setCenter(applicantsVBox);
    }
    @FXML
    void setAdminWindow(){
        mainWindowBorderPane.setCenter(adminVBox);
    }


    public void disableAdministrationToggleButton(){
        this.administrationToggleButton.setVisible(false);
    }



}
