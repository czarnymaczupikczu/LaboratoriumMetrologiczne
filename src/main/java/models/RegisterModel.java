package models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli REGISTER
 */

@DatabaseTable(tableName = "REGISTER")
public class RegisterModel {

    public static final String ID_REGISTER="idRegister";
    public static final String REGISTER_KIND="registerKind";
    public static final String ID_REGISTER_BY_YEAR="idRegisterByYear";
    public static final String STORAGE="storage";
    public static final String CARD_NUMBER="cardNumber";
    public static final String CALIBRATION_DATE="calibrationDate";
    public static final String CALIBRATION_USER="calibrationUser";
    public static final String CERTIFICATE_NUMBER="certificateNumber";
    public static final String DOCUMENT_KIND="documentKind";
    public static final String AGREEMENT_NUMBER="agreementNumber";
    public static final String STATE="state";
    public static final String REGISTER_REMARKS="registerRemarks";

    @DatabaseField(generatedId = true,columnName = ID_REGISTER)
    private Integer idRegister;
    @DatabaseField(columnName = REGISTER_KIND)
    private Integer registerKind;  //W zakresie akredytacji lub poza zakresem
    @DatabaseField(columnName = ID_REGISTER_BY_YEAR)
    private Integer idRegisterByYear;
    @DatabaseField(foreign = true,foreignAutoRefresh = true,columnName = STORAGE)
    private StorageModel storage;
    @DatabaseField(columnName = CARD_NUMBER)
    private String cardNumber;
    @DatabaseField(columnName = CALIBRATION_DATE)
    private String calibrationDate;
    @DatabaseField(foreign = true, foreignAutoRefresh = true,columnName = CALIBRATION_USER)
    private UserModel userWhoCalibrate;
    @DatabaseField(columnName = CERTIFICATE_NUMBER)
    private String certificateNumber;
    @DatabaseField(columnName = DOCUMENT_KIND)
    private String documentKind;
    @DatabaseField(columnName = AGREEMENT_NUMBER)
    private String agreementNumber;
    @DatabaseField(columnName = STATE)
    private String state;
    @DatabaseField(dataType = DataType.LONG_STRING,columnName = REGISTER_REMARKS)
    private String registerRemarks;

    //Konstruktory
    public RegisterModel() {
    }
    public RegisterModel(Integer idRegister, Integer registerKind, Integer idRegisterByYear, StorageModel storage, String cardNumber, String calibrationDate, UserModel userWhoCalibrate, String certificateNumber, String documentKind, String agreementNumber, String state, String registerRemarks) {
        this.idRegister = idRegister;
        this.registerKind = registerKind;
        this.idRegisterByYear = idRegisterByYear;
        this.storage = storage;
        this.cardNumber = cardNumber;
        this.calibrationDate = calibrationDate;
        this.userWhoCalibrate = userWhoCalibrate;
        this.certificateNumber = certificateNumber;
        this.documentKind = documentKind;
        this.agreementNumber = agreementNumber;
        this.state = state;
        this.registerRemarks = registerRemarks;
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
    public StorageModel getStorage() {
        return storage;
    }
    public void setStorage(StorageModel storage) {
        this.storage = storage;
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
    public String getRegisterRemarks() {
        return registerRemarks;
    }
    public void setRegisterRemarks(String registerRemarks) {
        this.registerRemarks = registerRemarks;
    }
}
