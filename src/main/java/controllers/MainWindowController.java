package controllers;

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

    private StorageWindowController storage1;
    private RegisterWindowController register1;
    private RegisterWindowController register2;
    private ApplicantsWindowController applicants;
    private VBox storageVbox1;
    private VBox register1Vbox;
    private VBox register2Vbox;
    private VBox applicantsVBox;

    public StorageWindowController getStorage1() {
        return storage1;
    }
    public void setStorage1(StorageWindowController storage1) {
        this.storage1 = storage1;
    }
    public RegisterWindowController getRegister1() {
        return register1;
    }
    public ApplicantsWindowController getApplicants() {
        return applicants;
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
    public void setApplicants(ApplicantsWindowController applicants) {
        this.applicants = applicants;
    }

    public void setStorageVbox1(VBox storageVbox1) {
        this.storageVbox1 = storageVbox1;
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
        if (storageVbox1==null) {
           // storageVbox1 = FxmlTools.fxmlLoader(STORAGE_FXML);
        }
        mainWindowBorderPane.setCenter(storageVbox1);

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


    public void disableAdministrationToggleButton(){
        this.administrationToggleButton.setVisible(false);
    }



}
