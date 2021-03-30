package fxModels;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShortRegisterFxModel {
    private IntegerProperty id= new SimpleIntegerProperty();
    private StringProperty cardNumber= new SimpleStringProperty();
    private StringProperty entryDate= new SimpleStringProperty();
    private StringProperty entryUser=new SimpleStringProperty();
    private StringProperty calibrationDate= new SimpleStringProperty();
    private StringProperty calibrateUser=new SimpleStringProperty();
    private StringProperty spendDate= new SimpleStringProperty();
    private StringProperty spendUser=new SimpleStringProperty();
    private StringProperty certificateNumber=new SimpleStringProperty();
    private StringProperty documentKind=new SimpleStringProperty();
    private StringProperty agreementNumber= new SimpleStringProperty();
    private StringProperty instrumentRemarks=new SimpleStringProperty();
    private StringProperty calibrationRemarks=new SimpleStringProperty();
    private ApplicantFxModel applicantFxModel=new ApplicantFxModel();

    public ShortRegisterFxModel() {
    }
    public ShortRegisterFxModel(int id, String cardNumber, String entryDate, String entryUser, String calibrationDate, String calibrateUser, String spendDate, String spendUser, String certificateNumber, String documentKind, String agreementNumber,String instrumentRemarks, String calibrationRemarks, ApplicantFxModel applicantFxModel) {
        this.id = new SimpleIntegerProperty(id);
        this.cardNumber = new SimpleStringProperty(cardNumber);
        this.entryDate = new SimpleStringProperty(entryDate);
        this.entryUser = new SimpleStringProperty(entryUser);
        this.calibrationDate = new SimpleStringProperty(calibrationDate);
        this.calibrateUser = new SimpleStringProperty(calibrateUser);
        this.spendDate = new SimpleStringProperty(spendDate);
        this.spendUser = new SimpleStringProperty(spendUser);
        this.certificateNumber = new SimpleStringProperty(certificateNumber);
        this.documentKind = new SimpleStringProperty(documentKind);
        this.agreementNumber = new SimpleStringProperty(agreementNumber);
        this.instrumentRemarks = new SimpleStringProperty(instrumentRemarks);
        this.calibrationRemarks=new SimpleStringProperty(calibrationRemarks);
        this.applicantFxModel = applicantFxModel;
    }

    public int getId() {
        return id.get();
    }
    public IntegerProperty idProperty() {
        return id;
    }
    public void setId(int id) {
        this.id.set(id);
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
    public String getEntryDate() {
        return entryDate.get();
    }
    public StringProperty entryDateProperty() {
        return entryDate;
    }
    public void setEntryDate(String entryDate) {
        this.entryDate.set(entryDate);
    }
    public String getEntryUser() {
        return entryUser.get();
    }
    public StringProperty entryUserProperty() {
        return entryUser;
    }
    public void setEntryUser(String entryUser) {
        this.entryUser.set(entryUser);
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
    public String getCalibrateUser() {
        return calibrateUser.get();
    }
    public StringProperty calibrateUserProperty() {
        return calibrateUser;
    }
    public void setCalibrateUser(String calibrateUser) {
        this.calibrateUser.set(calibrateUser);
    }
    public String getSpendDate() {
        return spendDate.get();
    }
    public StringProperty spendDateProperty() {
        return spendDate;
    }
    public void setSpendDate(String spendDate) {
        this.spendDate.set(spendDate);
    }
    public String getSpendUser() {
        return spendUser.get();
    }
    public StringProperty spendUserProperty() {
        return spendUser;
    }
    public void setSpendUser(String spendUser) {
        this.spendUser.set(spendUser);
    }
    public String getCertificateNumber() {
        return certificateNumber.get();
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
    public String getInstrumentRemarks() {
        return instrumentRemarks.get();
    }
    public StringProperty instrumentRemarksProperty() {
        return instrumentRemarks;
    }
    public void setInstrumentRemarks(String instrumentRemarks) {
        this.instrumentRemarks.set(instrumentRemarks);
    }
    public String getCalibrationRemarks() {
        return calibrationRemarks.get();
    }
    public StringProperty calibrationRemarksProperty() {
        return calibrationRemarks;
    }
    public void setCalibrationRemarks(String calibrationRemarks) {
        this.calibrationRemarks.set(calibrationRemarks);
    }
    public ApplicantFxModel getApplicantFxModel() {
        return applicantFxModel;
    }
    public void setApplicantFxModel(ApplicantFxModel applicantFxModel) {
        this.applicantFxModel = applicantFxModel;
    }
}
