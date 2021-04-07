package controllers;

import dataModels.InstrumentDataModel;
import dbModels.InstrumentModel;
import dbModels.RegisterModel;
import fxModels.StorageFxModel;
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

    private final String TITLE_MESSAGE="Nieprawidłowa data wzorcowania";
    private final String WINDOW_MESSAGE="Data wzorcowania jest wcześniejsza niż ostatnia w rejestrze";
    private final String EMPTY_DATE_MESSAGE="Nie wybrałeś prawidłowo daty wzorcowania";
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
        if(this.calibrationDatePicker.getValue()!=null) {
            this.storageWindowController.getStorageDataModel().initializeVeryShortRegisterFxModel(this.registerKind);
            if (this.storageWindowController.getStorageDataModel().getVeryShortRegisterFxModel().getCalibrationDate().isAfter(this.calibrationDatePicker.getValue())) {
                CommonTools.displayMessage(TITLE_MESSAGE, WINDOW_MESSAGE);
            } else {
                this.registerModel = setFormDataToRegister();
                this.registerModel.setIdRegisterByYear(this.storageWindowController.getStorageDataModel().getVeryShortRegisterFxModel().getIdRegisterByYear() + 1);
                if (this.storageWindowController.getStorageDataModel().getVeryShortRegisterFxModel().getCardNumber().contains(this.mainController.getMainDataModel().getYear().getYear())) {
                    //To ten sam rok
                    this.registerModel.setCardNumber(getCardNumber(this.registerModel.getIdRegisterByYear(), this.registerKind, this.mainController.getMainDataModel().getYear().getYear()));
                } else {
                    //Pierwszy wpis w nowym roku :)
                    this.registerModel.setCardNumber(getCardNumber(1, this.registerKind, this.mainController.getMainDataModel().getYear().getYear()));
                }
                this.registerModel.setCertificateNumber(getCertificateNumber(this.registerKind, this.registerModel.getCardNumber()));
                if (this.storageWindowController.getStorageDataModel().getCurrentStorage().getInstrument().getApplicant().getFullName().contains("ENERGOPOMIAR")) {
                    this.registerModel.setAgreementNumber("EP");
                }
                CommonDao commonDao = new CommonDao();
                commonDao.create(registerModel);
                cancel();
            }
        }
        else{
            CommonTools.displayMessage(TITLE_MESSAGE, EMPTY_DATE_MESSAGE);
        }
    }

    @FXML
    void cancel() {
        CommonTools.closePaneWindow(mainVBox);
    }

    @FXML
    void todayOnAction() {
        calibrationDatePicker.setValue(LocalDate.now());
    }
    public void setInstrumentDataToForm(InstrumentModel instrument, StorageFxModel storageFxModel){
        this.nameLabel.setText(instrument.getName().getInstrumentName());
        this.typeLabel.setText(instrument.getType().getTypeName());
        this.producerLabel.setText(instrument.getProducer().getProducerName());
        this.serialNumberLabel.setText(instrument.getSerialNumber());
        this.identificationLabel.setText(instrument.getIdentificationNumber());
        this.lengthLabel.setText(instrument.getLength());
        this.diameterLabel.setText(instrument.getDiameter());
        this.rangeLabel.setText(instrument.getRange().getRangeName());
        this.applicantLabel.setText(instrument.getApplicant().getShortName());
        this.instrumentRemarks.setText(storageFxModel.getInstrumentRemarks());
        this.instrumentRemarks.setEditable(false);
        this.calibrationRemarks.setText(storageFxModel.getCalibrationRemarks());
        this.calibrationRemarks.setEditable(false);

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

    private String getCardNumber(Integer idRegisterByYear,String registerKind,String year){
        if(registerKind.equals("AP131")){
            return idRegisterByYear+"-"+year;
        }
        else{
            if(idRegisterByYear <= 9){
                return "000"+String.valueOf(idRegisterByYear)+"-"+year;
            }else if(idRegisterByYear > 9 && idRegisterByYear <= 99){
                return "00"+ String.valueOf(idRegisterByYear)+"-"+year;
            }else if (idRegisterByYear > 99 && idRegisterByYear <=999){
                return "0"+String.valueOf(idRegisterByYear)+"-"+year;
            }else{
                return String.valueOf(idRegisterByYear)+"-"+year;
            }
        }

    }
    private String getCertificateNumber(String registerKind, String cardNumber) {
        Integer month=this.calibrationDatePicker.getValue().getMonthValue();
        if(registerKind.equals("AP131")){
            return cardNumber+"-P";
        }
        else{
            if (month <= 9) {
                return "0" + String.valueOf(month)+"-"+cardNumber;
            } else {
                return String.valueOf(month)+"-"+cardNumber;
            }
        }
    }


}
