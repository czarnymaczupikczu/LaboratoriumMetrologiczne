package dataModels;

import dbModels.instrument.BaseModel;
import fxModels.CommonFxModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.database.CommonDao;

import java.util.List;

public class CommonDataModel {
    private ObservableList<CommonFxModel> dataObservableList= FXCollections.observableArrayList();
    private ObjectProperty<CommonFxModel> currentElement=new SimpleObjectProperty<>(new CommonFxModel());
    private Class cls;
    private String columnNameEdit;
    private String columnNameDelete;
    private String function;

    public void init(){
        dataObservableList.clear();
        CommonDao commonDao = new CommonDao();
        List<BaseModel> dataList = commonDao.queryForAll(cls);
        dataList.forEach(element ->{
            dataObservableList.add(new CommonFxModel(element.getId(),element.getName()));
        });
    }

    public ObservableList<CommonFxModel> getDataObservableList() {
        return dataObservableList;
    }
    public void setDataObservableList(ObservableList<CommonFxModel> dataObservableList) {
        this.dataObservableList = dataObservableList;
    }
    public CommonFxModel getCurrentElement() {
        return currentElement.get();
    }
    public ObjectProperty<CommonFxModel> currentElementProperty() {
        return currentElement;
    }
    public void setCurrentElement(CommonFxModel currentElement) {
        this.currentElement.set(currentElement);
    }
    public Class getCls() {
        return cls;
    }
    public void setCls(Class cls) {
        this.cls = cls;
    }
    public String getColumnNameEdit() {
        return columnNameEdit;
    }
    public void setColumnNameEdit(String columnNameEdit) {
        this.columnNameEdit = columnNameEdit;
    }
    public String getColumnNameDelete() {
        return columnNameDelete;
    }
    public void setColumnNameDelete(String columnNameDelete) {
        this.columnNameDelete = columnNameDelete;
    }
    public void setFunction(String function) {
        this.function = function;
    }
    public String getFunction() {
        return function;
    }
}
