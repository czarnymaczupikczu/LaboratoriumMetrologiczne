package dataModels;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import dbModels.instrument.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import utils.DatabaseTools;
import utils.database.CommonDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstrumentDataModel {

    //Listy do ComboBoxów trochę przerost formy nad tręścią ale w sumie pożyteczna sprawa
    private ObservableList<String> nameObservableList = FXCollections.observableArrayList();
    private FilteredList<String> filteredNames = new FilteredList<String>(nameObservableList, p -> true);
    private ObservableList<String> typeObservableList = FXCollections.observableArrayList();
    private FilteredList<String> filteredTypes = new FilteredList<String>(typeObservableList, p -> true);
    private ObservableList<String> producerObservableList = FXCollections.observableArrayList();
    private FilteredList<String> filteredProducers = new FilteredList<String>(producerObservableList, p -> true);
    private ObservableList<String> rangeObservableList = FXCollections.observableArrayList();
    private FilteredList<String> filteredRange = new FilteredList<String>(rangeObservableList, p -> true);

    //Listy obiektów z tabel z bazy danych
    private List<NameModel> nameList;
    private List<TypeModel> typeList;
    private List<ProducerModel> producerList;
    private List<RangeModel> rangeList;

    public void init(){
        nameList= getDataToComboBox(NameModel.class,nameObservableList);
        typeList=getDataToComboBox(TypeModel.class,typeObservableList);
        producerList=getDataToComboBox(ProducerModel.class,producerObservableList);
        rangeList=getDataToComboBox(RangeModel.class,rangeObservableList);
    }

    //Gettery i Setter
    public FilteredList<String> getFilteredNames() {
        return filteredNames;
    }
    public void setFilteredNames(FilteredList<String> filteredNames) {
        this.filteredNames = filteredNames;
    }
    public FilteredList<String> getFilteredTypes() {
        return filteredTypes;
    }
    public void setFilteredTypes(FilteredList<String> filteredTypes) {
        this.filteredTypes = filteredTypes;
    }
    public FilteredList<String> getFilteredProducers() {
        return filteredProducers;
    }
    public void setFilteredProducers(FilteredList<String> filteredProducers) {
        this.filteredProducers = filteredProducers;
    }
    public FilteredList<String> getFilteredRange() {
        return filteredRange;
    }
    public void setFilteredRange(FilteredList<String> filteredRange) {
        this.filteredRange = filteredRange;
    }
    public List<NameModel> getNameList() {
        return nameList;
    }
    public void setNameList(List<NameModel> nameList) {
        this.nameList = nameList;
    }
    public List<TypeModel> getTypeList() {
        return typeList;
    }
    public void setTypeList(List<TypeModel> typeList) {
        this.typeList = typeList;
    }
    public List<ProducerModel> getProducerList() {
        return producerList;
    }
    public void setProducerList(List<ProducerModel> producerList) {
        this.producerList = producerList;
    }
    public List<RangeModel> getRangeList() {
        return rangeList;
    }
    public void setRangeList(List<RangeModel> rangeList) {
        this.rangeList = rangeList;
    }

    public <T extends BaseModel, I> List <T> getDataToComboBox (Class<T> cls, ObservableList<String> dataObservableList){
        dataObservableList.clear();
        //Dao<T, I> instrumentDao = DaoManager.createDao(DatabaseTools.getConnectionSource(), cls);
        //List<T> dataList = instrumentDao.queryForAll();
        CommonDao commonDao=new CommonDao();
        List<T> dataList=commonDao.queryForAll(cls);
        dataList.forEach(instrument -> {
            dataObservableList.add(instrument.getName());
        });
        DatabaseTools.closeConnection();
        return dataList;

    }
    public <T extends BaseModel> T getDataModel(String instrument, List<T> dataList){
        for(T instrumentData:dataList){
            if(instrumentData.getName().equals(instrument)){
                return instrumentData;
            }
        }
        return null;
    }
}
