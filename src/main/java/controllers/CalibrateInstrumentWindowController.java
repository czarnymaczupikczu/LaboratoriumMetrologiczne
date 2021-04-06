package controllers;

import dataModels.InstrumentDataModel;
import dbModels.InstrumentModel;
import dbModels.RegisterModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.database.CommonDao;

import java.time.LocalDate;

public class CalibrateInstrumentWindowController {
    public CalibrateInstrumentWindowController(){System.out.println("Konstruktor klasy CalibrateInstrumentsWindowController");}

    //Główny kontroler powiązany z kontrolerami poszczególnych okien
    private MainWindowController mainController;
    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }
    private StorageWindowController storageWindowController;
    public void setStorageWindowController(StorageWindowController storageWindowController) {
        this.storageWindowController = storageWindowController;
    }

    private String registerKind="";
    public void setRegisterKind(String registerKind) {
        this.registerKind = registerKind;
    }
    private RegisterModel registerModel=new RegisterModel();

    @FXML private VBox mainVBox;
    @FXML private Label mainLabel;
    @FXML private Label nameLabel;
    @FXML private Label typeLabel;
    @FXML private Label producerLabel;
    @FXML private Label serialNumberLabel;
    @FXML private Label identificationLabel;
    @FXML private Label rangeLabel;
    @FXML private Label applicantLabel;
    @FXML private Label lengthLabel;
    @FXML private Label diameterLabel;
    @FXML private DatePicker calibrationDatePicker;
    @FXML private TextArea instrumentRemarks;
    @FXML private TextArea calibrationRemarks;
    @FXML private Button saveButton;


    @FXML
    public void initialize(){

    }
    @FXML
    void save() {
        registerModel=setFormDataToRegister();
        CommonDao commonDao=new CommonDao();
        commonDao.create(registerModel);
        System.out.println(registerModel.getIdRegister());
    }

    @FXML
    void cancel() {
        CommonTools.closePaneWindow(mainVBox);
    }

    @FXML
    void todayOnAction() {
        calibrationDatePicker.setValue(LocalDate.now());
    }
    public void setInstrumentDataToForm(InstrumentModel instrument){
        this.nameLabel.setText(instrument.getName().getInstrumentName());
        this.typeLabel.setText(instrument.getType().getTypeName());
        this.producerLabel.setText(instrument.getProducer().getProducerName());
        this.serialNumberLabel.setText(instrument.getSerialNumber());
        this.identificationLabel.setText(instrument.getIdentificationNumber());
        this.lengthLabel.setText(instrument.getLength());
        this.diameterLabel.setText(instrument.getDiameter());
        this.rangeLabel.setText(instrument.getRange().getRangeName());
        this.applicantLabel.setText(instrument.getApplicant().getShortName());
    }
    public void setMainLabel(String label){
        this.mainLabel.setText(label);
    }
    public RegisterModel setFormDataToRegister(){
        RegisterModel temp=new RegisterModel();
        temp.setRegisterKind(this.registerKind);
        temp.setStorage(this.storageWindowController.getStorageDataModel().getCurrentStorageModel());
        temp.setCalibrationDate(this.calibrationDatePicker.getValue().toString());
        temp.setCalibrationUser(this.mainController.getMainDataModel().getUser());
        temp.setState("ON");
        return temp;
    }
}
