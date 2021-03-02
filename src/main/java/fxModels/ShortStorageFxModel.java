package fxModels;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShortStorageFxModel {
    private IntegerProperty storageIndex=new SimpleIntegerProperty();
    private IntegerProperty idStorage=new SimpleIntegerProperty();
    private InstrumentFxModel instrument=new InstrumentFxModel();
    private StringProperty entryDate= new SimpleStringProperty();
    private StringProperty entryUser= new SimpleStringProperty();
    private StringProperty spendDate=new SimpleStringProperty();
    private StringProperty spendUser= new SimpleStringProperty();
    private StringProperty storageRemarks=new SimpleStringProperty();

    //Konstruktory
    public ShortStorageFxModel() {
        //Domyślne ustawienie zbindowanych pól
        this.entryDate=new SimpleStringProperty("");
        this.entryUser=new SimpleStringProperty("");
        this.spendDate = new SimpleStringProperty("");
        this.spendUser = new SimpleStringProperty("");
    }
    public ShortStorageFxModel(int idStorage, InstrumentFxModel instrument, String entryDate, String entryUser, String calibrationDates, String calibrationUsers, String cardNumbers,String spendDate, String spendUser, String storageRemarks) {
        this.idStorage = new SimpleIntegerProperty(idStorage);
        this.instrument = instrument;
        this.entryDate = new SimpleStringProperty(entryDate);
        this.entryUser = new SimpleStringProperty(entryUser);
        this.spendDate = new SimpleStringProperty(spendDate);
        this.spendUser = new SimpleStringProperty(spendUser);
        this.storageRemarks = new SimpleStringProperty(storageRemarks);
    }

    //Gettery i Settery
    public int getStorageIndex() {
        return storageIndex.get();
    }
    public IntegerProperty storageIndexProperty() {
        return storageIndex;
    }
    public void setStorageIndex(int storageIndex) {
        this.storageIndex.set(storageIndex);
    }
    public int getIdStorage() {
        return idStorage.get();
    }
    public IntegerProperty idStorageProperty() {
        return idStorage;
    }
    public void setIdStorage(int idStorage) {
        this.idStorage.set(idStorage);
    }
    public InstrumentFxModel getInstrument() {
        return instrument;
    }
    public void setInstrument(InstrumentFxModel instrument) {
        this.instrument = instrument;
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
    public String getStorageRemarks() {
        return storageRemarks.get();
    }
    public StringProperty storageRemarksProperty() {
        return storageRemarks;
    }
    public void setStorageRemarks(String storageRemarks) {
        this.storageRemarks.set(storageRemarks);
    }
}
