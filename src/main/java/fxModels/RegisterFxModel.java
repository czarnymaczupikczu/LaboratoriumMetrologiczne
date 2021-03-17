package fxModels;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegisterFxModel {
    private IntegerProperty idRegister=new SimpleIntegerProperty();
    private IntegerProperty idRegisterByYear=new SimpleIntegerProperty();
    private ShortStorageFxModel storage=new ShortStorageFxModel();
    private StringProperty cardNumber= new SimpleStringProperty();
    private StringProperty calibrationDate=new SimpleStringProperty();
    private StringProperty calibrationUser=new SimpleStringProperty();
    private StringProperty certificateNumber = new SimpleStringProperty();
    private StringProperty documentKind=new SimpleStringProperty();
    private StringProperty agreementNumber= new SimpleStringProperty();
    private StringProperty state=new SimpleStringProperty();
    private StringProperty registerRemarks=new SimpleStringProperty();

    //Konstruktory
    public RegisterFxModel() {
        this.calibrationDate = new SimpleStringProperty("");
        this.calibrationUser = new SimpleStringProperty("");
    }
    public RegisterFxModel(int idRegister, int idRegisterByYear, ShortStorageFxModel storage, String cardNumber, String calibrationDate, String calibrationUser, String certificateNumber, String documentKind, String agreementNumber, String state, String registerRemarks) {
        this.idRegister = new SimpleIntegerProperty(idRegister);
        this.idRegisterByYear = new SimpleIntegerProperty(idRegisterByYear);
        this.storage = storage;
        this.cardNumber = new SimpleStringProperty(cardNumber);
        this.calibrationDate = new SimpleStringProperty(calibrationDate);
        this.calibrationUser = new SimpleStringProperty(calibrationUser);
        this.certificateNumber = new SimpleStringProperty(certificateNumber);
        this.documentKind = new SimpleStringProperty(documentKind);
        this.agreementNumber = new SimpleStringProperty(agreementNumber);
        this.state = new SimpleStringProperty(state);
        this.registerRemarks = new SimpleStringProperty(registerRemarks);
    }
    //Gettery i Settery
    public int getIdRegister() {
        return idRegister.get();
    }
    public IntegerProperty idRegisterProperty() {
        return idRegister;
    }
    public void setIdRegister(int idRegister) {
        this.idRegister.set(idRegister);
    }
    public int getIdRegisterByYear() {
        return idRegisterByYear.get();
    }
    public IntegerProperty idRegisterByYearProperty() {
        return idRegisterByYear;
    }
    public void setIdRegisterByYear(int idRegisterByYear) {
        this.idRegisterByYear.set(idRegisterByYear);
    }
    public ShortStorageFxModel getStorage() {
        return storage;
    }
    public void setStorage(ShortStorageFxModel storage) {
        this.storage = storage;
    }
    public String getCardNumber() {
        return cardNumber.get();
    }
    public StringProperty cardNumberProperty() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber.set(cardNumber);
    }
    public String getCalibrationDate() {
        return calibrationDate.get();
    }
    public StringProperty calibrationDateProperty() {
        return calibrationDate;
    }
    public void setCalibrationDate(String calibrationDate) {
        this.calibrationDate.set(calibrationDate);
    }
    public String getCalibrationUser() {
        return calibrationUser.get();
    }
    public StringProperty calibrationUserProperty() {
        return calibrationUser;
    }
    public void setCalibrationUser(String calibrationUser) {
        this.calibrationUser.set(calibrationUser);
    }
    public StringProperty certificateNumberProperty() {
        return certificateNumber;
    }
    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber.set(certificateNumber);
    }
    public String getDocumentKind() {
        return documentKind.get();
    }
    public StringProperty documentKindProperty() {
        return documentKind;
    }
    public void setDocumentKind(String documentKind) {
        this.documentKind.set(documentKind);
    }
    public String getAgreementNumber() {
        return agreementNumber.get();
    }
    public StringProperty agreementNumberProperty() {
        return agreementNumber;
    }
    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber.set(agreementNumber);
    }
    public String getState() {
        return state.get();
    }
    public StringProperty stateProperty() {
        return state;
    }
    public void setState(String state) {
        this.state.set(state);
    }
    public String getRegisterRemarks() {
        return registerRemarks.get();
    }
    public StringProperty registerRemarksProperty() {
        return registerRemarks;
    }
    public void setRegisterRemarks(String registerRemarks) {
        this.registerRemarks.set(registerRemarks);
    }
}
