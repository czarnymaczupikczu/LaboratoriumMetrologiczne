package dataModels;

import dbModels.ApplicantModel;
import fxModels.ApplicantFxModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import utils.Converter;
import utils.database.CommonDao;

import java.util.List;

public class ApplicantsDataModel {

    private ObservableList<ApplicantFxModel> applicantList= FXCollections.observableArrayList();
    private FilteredList<ApplicantFxModel> filteredApplicantList=new FilteredList<>(applicantList,p->true);
    private ObjectProperty<ApplicantFxModel> currentApplicantElement=new SimpleObjectProperty<>(new ApplicantFxModel());

    public void listInitialize(){
        applicantList.clear();
        CommonDao commonDao=new CommonDao();
        List<ApplicantModel> tempList = commonDao.queryForAll(ApplicantModel.class);
        for(ApplicantModel applicantModel:tempList){
            applicantList.add(Converter.convertApplicantModelToApplicantFxModel(applicantModel));
        }
    }
    public void listInitializeApplicantsActive(){
        applicantList.clear();
        CommonDao commonDao=new CommonDao();
        List<ApplicantModel> tempList = commonDao.selectAndStatement(ApplicantModel.class, "status","aktywny");
        for(ApplicantModel applicantModel:tempList){
            applicantList.add(Converter.convertApplicantModelToApplicantFxModel(applicantModel));
        }
    }
    public void addFilterToObservableList(String newValue){
        filteredApplicantList.setPredicate(item -> {
            if (item.getShortName().toUpperCase().contains(newValue.toUpperCase())||item.getFullName().toUpperCase().contains(newValue.toUpperCase())||
                    item.getPostCode().toUpperCase().contains(newValue.toUpperCase())||item.getCity().toUpperCase().contains(newValue.toUpperCase())||
                    item.getStreet().toUpperCase().contains(newValue.toUpperCase())||item.getNumber().toUpperCase().contains(newValue.toUpperCase())) {
                return true;
            } else {
                return false;
            }
        });
    }

    public ObservableList<ApplicantFxModel> getApplicantList() {
        return applicantList;
    }
    public FilteredList<ApplicantFxModel> getFilteredApplicantList() {
        return filteredApplicantList;
    }
    public ApplicantFxModel getCurrentApplicantElement() {
        return currentApplicantElement.get();
    }
    public ObjectProperty<ApplicantFxModel> currentApplicantElementProperty() {
        return currentApplicantElement;
    }
    public void setCurrentApplicantElement(ApplicantFxModel currentApplicantElement) {
        this.currentApplicantElement.set(currentApplicantElement);
    }
}
