package fxModels;

import javafx.beans.property.*;

public class InstrumentFxModel {

    private IntegerProperty idInstrument=new SimpleIntegerProperty();
    private StringProperty name=new SimpleStringProperty();
    private StringProperty type=new SimpleStringProperty();
    private StringProperty producer=new SimpleStringProperty();
    private StringProperty serialNumber=new SimpleStringProperty();
    private StringProperty identificationNumber=new SimpleStringProperty();
    private StringProperty length=new SimpleStringProperty();;
    private StringProperty diameter=new SimpleStringProperty();;
    private StringProperty range=new SimpleStringProperty();
    private ApplicantFxModel applicant=new ApplicantFxModel();

    //Konstruktory
    public InstrumentFxModel() {
    }
    public InstrumentFxModel(int idInstrument, String name, String type, String producer,
                             String serialNumber, String identificationNumber, String range, String length, String diameter, ApplicantFxModel applicant) {
        this.idInstrument = new SimpleIntegerProperty(idInstrument);
        this.name = new SimpleStringProperty(name);
        this.type =  new SimpleStringProperty(type);
        this.producer =  new SimpleStringProperty(producer);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.identificationNumber = new SimpleStringProperty(identificationNumber);
        this.length = new SimpleStringProperty(length);
        this.diameter = new SimpleStringProperty(diameter);
        this.range =  new SimpleStringProperty(range);
        this.applicant = applicant;
    }

    //Gettery i Settery
    public int getIdInstrument() {
        return idInstrument.get();
    }
    public IntegerProperty idInstrumentProperty() {
        return idInstrument;
    }
    public void setIdInstrument(int idInstrument) {
        this.idInstrument.set(idInstrument);
    }
    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public String getType() {
        return type.get();
    }
    public StringProperty typeProperty() {
        return type;
    }
    public void setType(String type) {
        this.type.set(type);
    }
    public String getProducer() {
        return producer.get();
    }
    public StringProperty producerProperty() {
        return producer;
    }
    public void setProducer(String producer) {
        this.producer.set(producer);
    }
    public void setRange(String range) {
        this.range.set(range);
    }
    public String getSerialNumber() {
        return serialNumber.get();
    }
    public StringProperty serialNumberProperty() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(serialNumber);
    }
    public String getIdentificationNumber() {
        return identificationNumber.get();
    }
    public StringProperty identificationNumberProperty() {
        return identificationNumber;
    }
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber.set(identificationNumber);
    }
    public String getLength() {
        return length.get();
    }
    public StringProperty lengthProperty() {
        return length;
    }
    public void setLength(String length) {
        this.length.set(length);
    }
    public String getDiameter() {
        return diameter.get();
    }
    public StringProperty diameterProperty() {
        return diameter;
    }
    public void setDiameter(String diameter) {
        this.diameter.set(diameter);
    }
    public String getRange() {
        return range.get();
    }
    public StringProperty rangeProperty() {
        return range;
    }
    public ApplicantFxModel getApplicant() {
        return applicant;
    }
    public void setApplicant(ApplicantFxModel applicant) {
        this.applicant = applicant;
    }
}
