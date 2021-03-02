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
    private StorageWindowController storage2;
    private StorageWindowController storage3;
    private VBox storageVbox1;
    private VBox storageVbox2;
    private VBox storageVbox3;

    public void setStorage1(StorageWindowController storage1) {
        this.storage1 = storage1;
    }
    public void setStorage2(StorageWindowController storage2) {
        this.storage2 = storage2;
    }
    public void setStorage3(StorageWindowController storage3) {
        this.storage3 = storage3;
    }

    public StorageWindowController getStorage1() {
        return storage1;
    }
    public StorageWindowController getStorage2() {
        return storage2;
    }
    public StorageWindowController getStorage3() {
        return storage3;
    }

    public void setStorageVbox1(VBox storageVbox1) {
        this.storageVbox1 = storageVbox1;
    }
    public void setStorageVbox2(VBox storageVbox2) {
        this.storageVbox2 = storageVbox2;
    }
    public void setStorageVbox3(VBox storageVbox3) {
        this.storageVbox3 = storageVbox3;
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
        if (storageVbox2==null) {
            //storageVbox2 = FxmlTools.fxmlLoader(STORAGE_FXML);
        }
        mainWindowBorderPane.setCenter(storageVbox2);
    }
    @FXML
    void setStorage3() {
        if (storageVbox3==null) {
           // storageVbox3 = FxmlTools.fxmlLoader(STORAGE_FXML);
        }
        mainWindowBorderPane.setCenter(storageVbox3);
    }
    public void disableAdministrationToggleButton(){
        this.administrationToggleButton.setVisible(false);
    }



}
