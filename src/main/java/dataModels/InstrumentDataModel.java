package dataModels;

import dbModels.ApplicantModel;
import dbModels.InstrumentModel;
import dbModels.instrument.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import utils.DatabaseTools;
import utils.database.CommonDao;

import java.util.List;

import static dbModels.ApplicantModel.SHORT_NAME;
import static dbModels.InstrumentModel.ID_INSTRUMENT;

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
    private ObservableList<String> unitObservableList=FXCollections.observableArrayList();

    //Listy obiektów z tabel z bazy danych
    private List<NameModel> nameList;
    private List<TypeModel> typeList;
    private List<ProducerModel> producerList;
    private List<RangeModel> rangeList;
    private List<UnitModel> unitList;
    private List<InstrumentModel> instrumentList;
    //Obiekty InstrumentModel z formularza lub wyszukane
    private InstrumentModel findInstrument=new InstrumentModel();
    private InstrumentModel formInstrument=new InstrumentModel();

    public void init() {
        nameListInit();
        typeListInit();
        producerListInit();
        rangeListInit();
        unitListInit();
    }
    public void nameListInit(){
        nameList= getDataToComboBox(NameModel.class,nameObservableList);
    }
    public void typeListInit(){
        typeList=getDataToComboBox(TypeModel.class,typeObservableList);
    }
    public void producerListInit(){
        producerList=getDataToComboBox(ProducerModel.class,producerObservableList);
    }
    public void rangeListInit(){
        rangeList=getDataToComboBox(RangeModel.class,rangeObservableList);
    }
    public void unitListInit(){
        unitList=getDataToComboBox(UnitModel.class,unitObservableList);
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
    public ObservableList<String> getUnitObservableList() {
        return unitObservableList;
    }
    public List<InstrumentModel> getInstrumentList() {
        return instrumentList;
    }
    public void setInstrumentList(List<InstrumentModel> instrumentList) {
        this.instrumentList = instrumentList;
    }

    public InstrumentModel getFindInstrument() {
        return findInstrument;
    }
    public void setFindInstrument(InstrumentModel findInstrument) {
        this.findInstrument = findInstrument;
    }
    public InstrumentModel getFormInstrument() {
        return formInstrument;
    }
    public void setFormInstrument(InstrumentModel formInstrument) {
        this.formInstrument = formInstrument;
    }

    /* Takie sprytne rozwiązanie, że uzupełnia listę observable a zwraca zwykłą listę, dzięki temu aktualizuje 2 jednocześnie*/
    public <T extends BaseModel, I> List <T> getDataToComboBox (Class<T> cls, ObservableList<String> dataObservableList){
        dataObservableList.clear();
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
    public InstrumentModel searchForInstrument(String numberKind, String value){
        CommonDao commonDao=new CommonDao();
        this.instrumentList=commonDao.selectAndWithLikeStatement(InstrumentModel.class, numberKind, value);
        if(this.instrumentList.size()>0){
            return this.instrumentList.get(this.instrumentList.size()-1);
        }else{
            return null;
        }
    }
    public ApplicantModel searchForApplicant(String shortName){
        CommonDao commonDao= new CommonDao();
        return commonDao.queryForFirst(ApplicantModel.class, SHORT_NAME, shortName);
    }
    public void searchForInstrument(Integer value){
        CommonDao commonDao=new CommonDao();
        this.findInstrument=commonDao.queryForFirst(InstrumentModel.class, ID_INSTRUMENT, value);
    }
}
