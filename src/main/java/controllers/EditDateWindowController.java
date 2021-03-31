package controllers;

import dbModels.RegisterModel;
import dbModels.StorageModel;
import fxModels.StorageFxModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.Converter;
import utils.database.CommonDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static dbModels.StorageModel.ENTRY_DATE;
import static dbModels.StorageModel.SPEND_DATE;

public class EditDateWindowController {
    public EditDateWindowController(){
        System.out.println("Konstruktor klasy EditDateWindowController");
    }


    private StorageWindowController storageWindowController;
    public void setStorageWindowController(StorageWindowController storageWindowController) {
        this.storageWindowController = storageWindowController;
    }
    private RegisterWindowController registerWindowController;
    public void setRegisterWindowController(RegisterWindowController registerWindowController) {
        this.registerWindowController = registerWindowController;
    }

    private String dateType;
    public void setDateType(String dateType) {
        this.dateType = dateType;
    }
    //Stałe tekstowe
    private final String ENTRY_DATE_ERROR="Podałeś nieprawidłową datę przyjęcia";
    private final String CALIBRATION_DATE_ERROR="Podałeś nieprawidłową datę wzorcowania";
    private final String SPEND_DATE_ERROR="Podałeś nieprawidłową datę wydania";

    @FXML private VBox editDateWindowMainVBox;
    @FXML private Label editDateWindowLabel;
    @FXML private DatePicker editDateWindowDatePicker;
    @FXML private Button saveButton;
    @FXML private Label editDateErrorLabel;
    @FXML
    public void initialize(){
        this.saveButton.disableProperty().bind(this.editDateWindowDatePicker.valueProperty().isNull());
    }
    @FXML
    private void editDateWindowToday(){
        editDateWindowDatePicker.setValue(LocalDate.now());
    }
    @FXML
    void saveEditDate() {
        if(this.dateType.equals("Data przyjęcia")) {
            saveEntryDate();
        }
        else if(this.dateType.equals("Data wydania")){
            saveSpendData();
        }

    }

    @FXML
    private void editDateWindowCancel() {
        CommonTools.closePaneWindow(editDateWindowMainVBox);
    }

    private void saveEntryDate(){
        List<StorageModel> storageModelList=new ArrayList<>();
        for(int i=0; i<this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().size();i++){
            StorageFxModel tempStorageFx=this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().get(i);
            CommonDao storageDao=new CommonDao();
            List<LocalDate> calibrationDatesList =decodeCalibrationDates(tempStorageFx.getCalibrationDates());
            LocalDate oldEntryDate= Converter.getConverter().fromString(tempStorageFx.getEntryDate());
            LocalDate newEntryDate=this.editDateWindowDatePicker.getValue();
            LocalDate spendDate=Converter.getConverter().fromString(tempStorageFx.getSpendDate());
            if(checkEntryDate(newEntryDate,calibrationDatesList,spendDate)){
                storageModelList.add(storageDao.queryForFirst(StorageModel.class,"idStorage",tempStorageFx.getIdStorage()));
            }
            else{
                this.editDateErrorLabel.setText(ENTRY_DATE_ERROR);
                break;
            }

            if(this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().size()==storageModelList.size()){
                String newEntryDateString=newEntryDate.toString();
                for(int k=0;k<storageModelList.size();k++){
                    storageModelList.get(k).setEntryDate(newEntryDateString);
                    storageDao.createOrUpdate(storageModelList.get(k));
                }
            }
            else {
                this.editDateErrorLabel.setText(ENTRY_DATE_ERROR);
            }
        }
        System.out.println("List1 "+this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().size());
        System.out.println("List2 "+storageModelList.size());
    }
    private void saveSpendData(){
        List<StorageModel> storageModelList=new ArrayList<>();
        for(int i=0; i<this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().size();i++){
            StorageFxModel tempStorageFx=this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().get(i);
            CommonDao storageDao=new CommonDao();
            List<LocalDate> calibrationDatesList =decodeCalibrationDates(tempStorageFx.getCalibrationDates());
            LocalDate newSpendDate=this.editDateWindowDatePicker.getValue();
            LocalDate entryDate=Converter.getConverter().fromString(tempStorageFx.getEntryDate());
            if(checkSpendDate(newSpendDate,calibrationDatesList,entryDate)){
                storageModelList.add(storageDao.queryForFirst(StorageModel.class,"idStorage",tempStorageFx.getIdStorage()));
            }
            else{
                this.editDateErrorLabel.setText(SPEND_DATE_ERROR);
                break;
            }

            if(this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().size()==storageModelList.size()){
                String newSpendDateString=newSpendDate.toString();
                for(int k=0;k<storageModelList.size();k++){
                    storageModelList.get(k).setSpendDate(newSpendDateString);
                    storageDao.createOrUpdate(storageModelList.get(k));
                }
            }
            else {
                this.editDateErrorLabel.setText(SPEND_DATE_ERROR);
            }
        }
        System.out.println("List1 "+this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().size());
        System.out.println("List2 "+storageModelList.size());
    }
    private void saveCalibrationDate(){

    }
    public Boolean checkEntryDate(LocalDate newEntryDate, List<LocalDate> calibrationDatesList, LocalDate spendDate){
        Boolean temp=true;
        if (newEntryDate != null) {
            if(calibrationDatesList.isEmpty()){ //nie był wzorcowany
                if(spendDate!=null){//był wydany
                    if(newEntryDate.isAfter(spendDate)){
                        temp =false;
                    }
                }
            }
            else{
                for(int j=0;j<calibrationDatesList.size();j++){
                    if(newEntryDate.isAfter(calibrationDatesList.get(j))){
                        temp=false;
                    }
                }
            }
        }
        return temp;
    }
    public Boolean checkSpendDate(LocalDate newSpendDate, List<LocalDate> calibrationDatesList, LocalDate entryDate){
        Boolean temp=true;
        if (newSpendDate != null) {
            if(calibrationDatesList.isEmpty()){ //nie był wzorcowany
                    if(newSpendDate.isBefore(entryDate)){
                        temp =false;
                    }
            }
            else{
                for(int j=0;j<calibrationDatesList.size();j++){
                    if(newSpendDate.isBefore(calibrationDatesList.get(j))){
                        temp=false;
                    }
                }
            }
        }
        else
        {
            temp=false;
        }
        return temp;
    }
    public void setEditDateWindowLabel(String label) {
        this.editDateWindowLabel.setText(label);
    }
    public List<LocalDate> decodeCalibrationDates(String calibrationDates){
        List<LocalDate> calibrationDatesList=new ArrayList<>();
        int start=0;
        int end=10;
        while(end<=calibrationDates.length()){
            calibrationDatesList.add(Converter.getConverter().fromString(calibrationDates.substring(start,end)));
            System.out.println(calibrationDates.substring(start,end));
            start=end+2;
            end=start+10;
        };
        return calibrationDatesList;
    }
}
