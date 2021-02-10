package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli REGISTER
 */

@DatabaseTable(tableName = "REGISTER")
public class RegisterModel {
    @DatabaseField(generatedId = true)
    private Integer idRegister;
    @DatabaseField
    private Integer registerKind;  //W zakresie akredytacji lub poza zakresem
    @DatabaseField
    private Integer idRegisterByYear;
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private StorehouseModel storehouse;
    @DatabaseField
    private String cardNumber;
    @DatabaseField
    private String calibrationDate;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private UserModel userWhoCalibrate;
    @DatabaseField
    private String certificateNumber;
    @DatabaseField
    private String documentKind;
    @DatabaseField
    private String agreementNumber;
    @DatabaseField
    private String state;

    //Konstruktory
    public RegisterModel() {
    }
    public RegisterModel(Integer idRegister, Integer registerKind, Integer idRegisterByYear, StorehouseModel storehouse, String cardNumber, String calibrationDate, UserModel userWhoCalibrate, String certificateNumber, String documentKind, String agreementNumber, String state) {
        this.idRegister = idRegister;
        this.registerKind = registerKind;
        this.idRegisterByYear = idRegisterByYear;
        this.storehouse = storehouse;
        this.cardNumber = cardNumber;
        this.calibrationDate = calibrationDate;
        this.userWhoCalibrate = userWhoCalibrate;
        this.certificateNumber = certificateNumber;
        this.documentKind = documentKind;
        this.agreementNumber = agreementNumber;
        this.state = state;
    }

    //Gettery i Settery
    public Integer getIdRegister() {
        return idRegister;
    }
    public void setIdRegister(Integer idRegister) {
        this.idRegister = idRegister;
    }
    public Integer getRegisterKind() {
        return registerKind;
    }
    public void setRegisterKind(Integer registerKind) {
        this.registerKind = registerKind;
    }
    public Integer getIdRegisterByYear() {
        return idRegisterByYear;
    }
    public void setIdRegisterByYear(Integer idRegisterByYear) {
        this.idRegisterByYear = idRegisterByYear;
    }
    public StorehouseModel getStorehouse() {
        return storehouse;
    }
    public void setStorehouse(StorehouseModel storehouse) {
        this.storehouse = storehouse;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getCalibrationDate() {
        return calibrationDate;
    }
    public void setCalibrationDate(String calibrationDate) {
        this.calibrationDate = calibrationDate;
    }
    public UserModel getUserWhoCalibrate() {
        return userWhoCalibrate;
    }
    public void setUserWhoCalibrate(UserModel userWhoCalibrate) {
        this.userWhoCalibrate = userWhoCalibrate;
    }
    public String getCertificateNumber() {
        return certificateNumber;
    }
    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
    public String getDocumentKind() {
        return documentKind;
    }
    public void setDocumentKind(String documentKind) {
        this.documentKind = documentKind;
    }
    public String getAgreementNumber() {
        return agreementNumber;
    }
    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
