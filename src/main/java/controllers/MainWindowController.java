package controllers;

import dataModels.MainDataModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.FxmlTools;

import java.io.IOException;


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
    private VBox storageVbox1;
    private VBox register1Vbox;
    private VBox register2Vbox;

    public StorageWindowController getStorage1() {
        return storage1;
    }
    public void setStorage1(StorageWindowController storage1) {
        this.storage1 = storage1;
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

    public void setStorageVbox1(VBox storageVbox1) {
        this.storageVbox1 = storageVbox1;
    }
    public void setRegister1Vbox(VBox register1Vbox) {
        this.register1Vbox = register1Vbox;
    }
    public void setRegister2Vbox(VBox register2Vbox) {
        this.register2Vbox = register2Vbox;
    }

    @FXML private ImageView mainWindowImageView;
    @FXML private VBox mainWindowMainVBox;
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
        setStorage1();
    }
    @FXML
    void setStorage1() {
        if (storageVbox1==null) {
           // storageVbox1 = FxmlTools.fxmlLoader(STORAGE_FXML);
        }
        mainWindowBorderPane.setCenter(storageVbox1);
    }
    @FXML
    void setStorage2() {
        if (register1Vbox==null) {
            //storageVbox2 = FxmlTools.fxmlLoader(STORAGE_FXML);
        }
        mainWindowBorderPane.setCenter(register1Vbox);
    }
    @FXML
    void setStorage3() {
        if (register2Vbox==null) {
           // storageVbox3 = FxmlTools.fxmlLoader(STORAGE_FXML);
        }
        mainWindowBorderPane.setCenter(register2Vbox);
    }
    public void disableAdministrationToggleButton(){
        this.administrationToggleButton.setVisible(false);
    }



}
