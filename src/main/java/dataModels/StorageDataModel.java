package dataModels;

import fxModels.StorageFxModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StorageDataModel {
    private ObservableList<StorageFxModel> storehouseList= FXCollections.observableArrayList();
    private ObjectProperty<StorageFxModel> currentStorehouse1= new SimpleObjectProperty<>(new StorageFxModel());
    private ObservableList<StorageFxModel> storehouseSelectedItemsList=FXCollections.observableArrayList();
    private StorageFxModel currentStorehouse= new StorageFxModel();

    public void listInitialize(){
        storehouseList.clear();
        storehouseList.add(new StorageFxModel(new SimpleStringProperty("1111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CM"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));
        storehouseList.add(new StorageFxModel(new SimpleStringProperty("2111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CT"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));
        storehouseList.add(new StorageFxModel(new SimpleStringProperty("3111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CK"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01,2021-01-02"),new SimpleStringProperty("2021-01-01")));
        storehouseList.add(new StorageFxModel(new SimpleStringProperty("4111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CA"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));
        storehouseList.add(new StorageFxModel(new SimpleStringProperty("5111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CZ"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));
        storehouseList.add(new StorageFxModel(new SimpleStringProperty("6111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CW"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));
        storehouseList.add(new StorageFxModel(new SimpleStringProperty("7111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CD"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));

    }


    public ObservableList<StorageFxModel> getStorehouseList() {
        return storehouseList;
    }

    public void setStorehouseList(ObservableList<StorageFxModel> storehouseList) {
        this.storehouseList = storehouseList;
    }


    public ObservableList<StorageFxModel> getStorehouseSelectedItemsList() {
        return storehouseSelectedItemsList;
    }

    public void setStorehouseSelectedItemsList(ObservableList<StorageFxModel> storehouseSelectedItemsList) {
        this.storehouseSelectedItemsList = storehouseSelectedItemsList;
    }

    public StorageFxModel getCurrentStorehouse() {
        return currentStorehouse;
    }

    public void setCurrentStorehouse(StorageFxModel currentStorehouse) {
        this.currentStorehouse = currentStorehouse;
    }

    public StorageFxModel getCurrentStorehouse1() {
        return currentStorehouse1.get();
    }

    public ObjectProperty<StorageFxModel> currentStorehouse1Property() {
        return currentStorehouse1;
    }

    public void setCurrentStorehouse1(StorageFxModel currentStorehouse1) {
        this.currentStorehouse1.set(currentStorehouse1);
    }
}
