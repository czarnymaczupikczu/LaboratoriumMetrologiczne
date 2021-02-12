package fxModels;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ApplicantFxModel {

    private IntegerProperty idApplicant= new SimpleIntegerProperty();
    private StringProperty shortName=new SimpleStringProperty();
    private StringProperty fullName=new SimpleStringProperty();
    private StringProperty postCode=new SimpleStringProperty();
    private StringProperty city=new SimpleStringProperty();
    private StringProperty street=new SimpleStringProperty();
    private StringProperty houseNumber=new SimpleStringProperty();
    private StringProperty flatNumber=new SimpleStringProperty();
    private StringProperty status=new SimpleStringProperty();

    //Konstruktory
    public ApplicantFxModel() {
    }
    public ApplicantFxModel(int idApplicant, String shortName, String fullName, String postCode, String city, String street, String houseNumber, String flatNumber, String status) {
        this.idApplicant = new SimpleIntegerProperty(idApplicant);
        this.shortName = new SimpleStringProperty(shortName);
        this.fullName = new SimpleStringProperty(fullName);
        this.postCode = new SimpleStringProperty(postCode);
        this.city = new SimpleStringProperty(city);
        this.street = new SimpleStringProperty(street);
        this.houseNumber = new SimpleStringProperty(houseNumber);
        this.flatNumber = new SimpleStringProperty(flatNumber);
        this.status = new SimpleStringProperty(status);
    }

    //Gettery i Settery
    public int getIdApplicant() {
        return idApplicant.get();
    }
    public IntegerProperty idApplicantProperty() {
        return idApplicant;
    }
    public void setIdApplicant(int idApplicant) {
        this.idApplicant.set(idApplicant);
    }
    public String getShortName() {
        return shortName.get();
    }
    public StringProperty shortNameProperty() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName.set(shortName);
    }
    public String getFullName() {
        return fullName.get();
    }
    public StringProperty fullNameProperty() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }
    public String getPostCode() {
        return postCode.get();
    }
    public StringProperty postCodeProperty() {
        return postCode;
    }
    public void setPostCode(String postCode) {
        this.postCode.set(postCode);
    }
    public String getCity() {
        return city.get();
    }
    public StringProperty cityProperty() {
        return city;
    }
    public void setCity(String city) {
        this.city.set(city);
    }
    public String getStreet() {
        return street.get();
    }
    public StringProperty streetProperty() {
        return street;
    }
    public void setStreet(String street) {
        this.street.set(street);
    }
    public String getHouseNumber() {
        return houseNumber.get();
    }
    public StringProperty houseNumberProperty() {
        return houseNumber;
    }
    public void setHouseNumber(String houseNumber) {
        this.houseNumber.set(houseNumber);
    }
    public String getFlatNumber() {
        return flatNumber.get();
    }
    public StringProperty flatNumberProperty() {
        return flatNumber;
    }
    public void setFlatNumber(String flatNumber) {
        this.flatNumber.set(flatNumber);
    }
    public String getStatus() {
        return status.get();
    }
    public StringProperty statusProperty() {
        return status;
    }
    public void setStatus(String status) {
        this.status.set(status);
    }
}
