package fxModels;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Obiekty klasy storehouseFxModel są wyświetlane w kontrolerze TableView w zakładce Storehouse
 */
public class StorageFxModel {
    private StringProperty indexOfStorehouseModelList=new SimpleStringProperty();
    private StringProperty idInstrument=new SimpleStringProperty(); //To trzeba zmienić na idStorehouse ale poki co musi byc
    private StringProperty instrumentName=new SimpleStringProperty();
    private StringProperty instrumentType=new SimpleStringProperty();
    private StringProperty instrumentProducer=new SimpleStringProperty();
    private StringProperty serialNumber=new SimpleStringProperty();
    private StringProperty identificationNumber=new SimpleStringProperty();
    private StringProperty instrumentRange=new SimpleStringProperty();
    private StringProperty instrumentLength=new SimpleStringProperty();
    private StringProperty instrumentDiameter=new SimpleStringProperty();
    private StringProperty client= new SimpleStringProperty();
    private StringProperty addDate=new SimpleStringProperty();
    private StringProperty calibrationDate=new SimpleStringProperty();
    private StringProperty leftDate=new SimpleStringProperty();

    public StorageFxModel() {
    }

    public StorageFxModel(StringProperty indexOfStorehouseModelList, StringProperty idInstrument, StringProperty instrumentName, StringProperty instrumentType, StringProperty instrumentProducer, StringProperty serialNumber, StringProperty identificationNumber, StringProperty instrumentRange, StringProperty instrumentLength, StringProperty instrumentDiameter, StringProperty client, StringProperty addDate, StringProperty calibrationDate, StringProperty leftDate) {
        this.indexOfStorehouseModelList = indexOfStorehouseModelList;
        this.idInstrument = idInstrument;
        this.instrumentName = instrumentName;
        this.instrumentType = instrumentType;
        this.instrumentProducer = instrumentProducer;
        this.serialNumber = serialNumber;
        this.identificationNumber = identificationNumber;
        this.instrumentRange = instrumentRange;
        this.instrumentLength = instrumentLength;
        this.instrumentDiameter = instrumentDiameter;
        this.client = client;
        this.addDate = addDate;
        this.calibrationDate = calibrationDate;
        this.leftDate = leftDate;
    }

    public String getIndexOfStorehouseModelList() {
        return indexOfStorehouseModelList.get();
    }

    public StringProperty indexOfStorehouseModelListProperty() {
        return indexOfStorehouseModelList;
    }

    public void setIndexOfStorehouseModelList(String indexOfStorehouseModelList) {
        this.indexOfStorehouseModelList.set(indexOfStorehouseModelList);
    }

    public String getIdInstrument() {
        return idInstrument.get();
    }

    public StringProperty idInstrumentProperty() {
        return idInstrument;
    }

    public void setIdInstrument(String idInstrument) {
        this.idInstrument.set(idInstrument);
    }

    public String getInstrumentName() {
        return instrumentName.get();
    }

    public StringProperty instrumentNameProperty() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName.set(instrumentName);
    }

    public String getInstrumentType() {
        return instrumentType.get();
    }

    public StringProperty instrumentTypeProperty() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType.set(instrumentType);
    }

    public String getInstrumentProducer() {
        return instrumentProducer.get();
    }

    public StringProperty instrumentProducerProperty() {
        return instrumentProducer;
    }

    public void setInstrumentProducer(String instrumentProducer) {
        this.instrumentProducer.set(instrumentProducer);
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

    public String getInstrumentRange() {
        return instrumentRange.get();
    }

    public StringProperty instrumentRangeProperty() {
        return instrumentRange;
    }

    public void setInstrumentRange(String instrumentRange) {
        this.instrumentRange.set(instrumentRange);
    }

    public String getInstrumentLength() {
        return instrumentLength.get();
    }

    public StringProperty instrumentLengthProperty() {
        return instrumentLength;
    }

    public void setInstrumentLength(String instrumentLength) {
        this.instrumentLength.set(instrumentLength);
    }

    public String getInstrumentDiameter() {
        return instrumentDiameter.get();
    }

    public StringProperty instrumentDiameterProperty() {
        return instrumentDiameter;
    }

    public void setInstrumentDiameter(String instrumentDiameter) {
        this.instrumentDiameter.set(instrumentDiameter);
    }

    public String getClient() {
        return client.get();
    }

    public StringProperty clientProperty() {
        return client;
    }

    public void setClient(String client) {
        this.client.set(client);
    }

    public String getAddDate() {
        return addDate.get();
    }

    public StringProperty addDateProperty() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate.set(addDate);
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

    public String getLeftDate() {
        return leftDate.get();
    }

    public StringProperty leftDateProperty() {
        return leftDate;
    }

    public void setLeftDate(String leftDate) {
        this.leftDate.set(leftDate);
    }
}
