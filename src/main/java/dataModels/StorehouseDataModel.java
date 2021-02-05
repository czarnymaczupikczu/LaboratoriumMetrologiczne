package dataModels;

import fxModels.StorehouseFxModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StorehouseDataModel {
    private ObservableList<StorehouseFxModel> storehouseList= FXCollections.observableArrayList();
    private ObjectProperty<StorehouseFxModel> currentStorehouse1= new SimpleObjectProperty<>(new StorehouseFxModel());
    private ObservableList<StorehouseFxModel> storehouseSelectedItemsList=FXCollections.observableArrayList();
    private StorehouseFxModel currentStorehouse= new StorehouseFxModel();

    public void listInitialize(){
        storehouseList.clear();
        storehouseList.add(new StorehouseFxModel(new SimpleStringProperty("1111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CM"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));
        storehouseList.add(new StorehouseFxModel(new SimpleStringProperty("2111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CT"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));
        storehouseList.add(new StorehouseFxModel(new SimpleStringProperty("3111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CK"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));
        storehouseList.add(new StorehouseFxModel(new SimpleStringProperty("4111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CA"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));
        storehouseList.add(new StorehouseFxModel(new SimpleStringProperty("5111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CZ"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));
        storehouseList.add(new StorehouseFxModel(new SimpleStringProperty("6111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CW"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));
        storehouseList.add(new StorehouseFxModel(new SimpleStringProperty("7111"),new SimpleStringProperty("1"),new SimpleStringProperty("Ciśnieniomierz"),new SimpleStringProperty("EXT600"),new SimpleStringProperty("Beamex"),new SimpleStringProperty("1232"),new SimpleStringProperty("1/008/1976"),new SimpleStringProperty("(0 do 200)kPa"),new SimpleStringProperty("50000"),new SimpleStringProperty("3"),new SimpleStringProperty("ZC/CD"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01"),new SimpleStringProperty("2021-01-01")));

    }


    public ObservableList<StorehouseFxModel> getStorehouseList() {
        return storehouseList;
    }

    public void setStorehouseList(ObservableList<StorehouseFxModel> storehouseList) {
        this.storehouseList = storehouseList;
    }


    public ObservableList<StorehouseFxModel> getStorehouseSelectedItemsList() {
        return storehouseSelectedItemsList;
    }

    public void setStorehouseSelectedItemsList(ObservableList<StorehouseFxModel> storehouseSelectedItemsList) {
        this.storehouseSelectedItemsList = storehouseSelectedItemsList;
    }

    public StorehouseFxModel getCurrentStorehouse() {
        return currentStorehouse;
    }

    public void setCurrentStorehouse(StorehouseFxModel currentStorehouse) {
        this.currentStorehouse = currentStorehouse;
    }

    public StorehouseFxModel getCurrentStorehouse1() {
        return currentStorehouse1.get();
    }

    public ObjectProperty<StorehouseFxModel> currentStorehouse1Property() {
        return currentStorehouse1;
    }

    public void setCurrentStorehouse1(StorehouseFxModel currentStorehouse1) {
        this.currentStorehouse1.set(currentStorehouse1);
    }
}
