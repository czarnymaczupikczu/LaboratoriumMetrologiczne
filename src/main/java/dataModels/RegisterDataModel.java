package dataModels;

import dbModels.RegisterModel;
import fxModels.RegisterFxModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import utils.Converter;
import utils.database.CommonDao;

import java.util.List;

import static dbModels.RegisterModel.*;

public class RegisterDataModel {
    private ObservableList<RegisterFxModel> registerList= FXCollections.observableArrayList();
    private ObjectProperty<RegisterFxModel> currentRegisterElement=new SimpleObjectProperty<>(new RegisterFxModel());
    private ObservableList<RegisterFxModel> registerSelectedItemsList=FXCollections.observableArrayList();
    private FilteredList<RegisterFxModel>   filteredRegisterList=new FilteredList<>(registerList,p->true);
    private String registerType;

    public void listInitialize(String registerState, String registerYear){
        registerList.clear();
        CommonDao commonDao=new CommonDao();
       // List<RegisterModel> tempList=commonDao.getListWithSimpleSelect(RegisterModel.class,"registerKind","AP131");;
        List<RegisterModel> tempList=commonDao.queryForAll(RegisterModel.class);


        /*if(registerState.equals(registerYear)){   //Wszystkie i wszystkie
            System.out.println("typ rejestru"+this.registerType);
            tempList=commonDao.getListWithSimpleLikeSelect(RegisterModel.class,"registerKind","AP131");
        }else if(registerState.equals("Wszystkie") && !registerYear.equals("Wszystkie")){
            tempList= commonDao.selectWithThreeConditions(RegisterModel.class, REGISTER_KIND, this.registerType, REGISTER_KIND, this.registerType, CALIBRATION_DATE, registerYear);
        }else if(!registerState.equals("Wszystkie") && registerYear.equals("Wszystkie")) {
            tempList= commonDao.selectWithThreeConditions(RegisterModel.class, REGISTER_KIND, this.registerType, STATE, registerState, REGISTER_KIND, this.registerType);
        }*/
        for (RegisterModel registerModel: tempList) {
            registerList.add(Converter.convertRegisterModelToRegisterFxModel(registerModel));
        }

    }

    public void addFilterToObservableList(String newValue){
        filteredRegisterList.setPredicate(item -> {
            if (item.getStorage().getInstrument().getName().toUpperCase().contains(newValue.toUpperCase())||item.getStorage().getInstrument().getType().toUpperCase().contains(newValue.toUpperCase())||
                    item.getStorage().getInstrument().getProducer().toUpperCase().contains(newValue.toUpperCase())||item.getStorage().getInstrument().getSerialNumber().toUpperCase().contains(newValue.toUpperCase())||
                    item.getStorage().getInstrument().getIdentificationNumber().toUpperCase().contains(newValue.toUpperCase())||item.getStorage().getInstrument().getRange().toUpperCase().contains(newValue.toUpperCase())||
                    item.getStorage().getInstrument().getApplicant().getShortName().toUpperCase().contains(newValue.toUpperCase())) {
                return true;
            } else {
                return false;
            }
        });
    }

    public ObservableList<RegisterFxModel> getRegisterList() {
        return registerList;
    }
    public void setRegisterList(ObservableList<RegisterFxModel> registerList) {
        this.registerList = registerList;
    }
    public RegisterFxModel getCurrentRegisterElement() {
        return currentRegisterElement.get();
    }
    public ObjectProperty<RegisterFxModel> currentRegisterElementProperty() {
        return currentRegisterElement;
    }
    public void setCurrentRegisterElement(RegisterFxModel currentRegisterElement) {
        this.currentRegisterElement.set(currentRegisterElement);
    }
    public ObservableList<RegisterFxModel> getRegisterSelectedItemsList() {
        return registerSelectedItemsList;
    }
    public void setRegisterSelectedItemsList(ObservableList<RegisterFxModel> registerSelectedItemsList) {
        this.registerSelectedItemsList = registerSelectedItemsList;
    }
    public FilteredList<RegisterFxModel> getFilteredRegisterList() {
        return filteredRegisterList;
    }
    public void setFilteredRegisterList(FilteredList<RegisterFxModel> filteredRegisterList) {
        this.filteredRegisterList = filteredRegisterList;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

}
