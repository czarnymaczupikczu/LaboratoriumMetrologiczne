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
    private final String LEFT_DATE_ERROR="Podałeś nieprawidłową datę wydania";

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
        if(this.dateType.equals(ENTRY_DATE)) {

        }
        saveEntryDate();
    }

    @FXML
    private void editDateWindowCancel() {
        CommonTools.closePaneWindow(editDateWindowMainVBox);
    }

    private void saveEntryDate(){
        for(int i=0; i<this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().size();i++){
            StorageFxModel tempStorageFx=this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().get(i);
            CommonDao storageDao=new CommonDao();
            StorageModel tempStorageModel=storageDao.queryForFirst(StorageModel.class,"idStorage",tempStorageFx.getIdStorage());
            List<LocalDate> calibrationDatesList =decodeCalibrationDates(tempStorageFx.getCalibrationDates());
            LocalDate oldEntryDate= Converter.getConverter().fromString(tempStorageFx.getEntryDate());
            LocalDate newEntryDate=this.editDateWindowDatePicker.getValue();
            LocalDate spendDate=Converter.getConverter().fromString(tempStorageFx.getSpendDate());
            if (newEntryDate != null) {
                if(calibrationDatesList.isEmpty()){ //nie był wzorcowany
                    if(spendDate==null){//nie był wydany
                        tempStorageModel.setEntryDate(Converter.getConverter().toString(newEntryDate));
                        storageDao.createOrUpdate(tempStorageModel);
                    }
                    else{
                        if(newEntryDate.isEqual(spendDate)||newEntryDate.isBefore(spendDate)){
                            tempStorageModel.setEntryDate(Converter.getConverter().toString(newEntryDate));
                            storageDao.createOrUpdate(tempStorageModel);
                        }
                    }
                }
                else{
                    for(int j=0;j<calibrationDatesList.size();j++){
                        if(newEntryDate.isEqual(calibrationDatesList.get(j))||newEntryDate.isBefore(calibrationDatesList.get(j))){
                            tempStorageModel.setEntryDate(Converter.getConverter().toString(newEntryDate));
                            storageDao.createOrUpdate(tempStorageModel);
                        }
                    }
                }
            }
        }
    }
    private void saveSpendData(){
        for(int i=0; i<this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().size();i++){
            StorageFxModel tempStorageFx=this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().get(i);
            CommonDao storageDao=new CommonDao();
            StorageModel tempStorageModel=storageDao.queryForFirst(StorageModel.class,"idStorage",tempStorageFx.getIdStorage());
            List<LocalDate> calibrationDatesList =decodeCalibrationDates(tempStorageFx.getCalibrationDates());
            LocalDate oldSpendDate= Converter.getConverter().fromString(tempStorageFx.getSpendDate());
            LocalDate newSpendDate=this.editDateWindowDatePicker.getValue();
            LocalDate spendDate=Converter.getConverter().fromString(tempStorageFx.getSpendDate());
            if (newSpendDate != null) {
                if(calibrationDatesList.isEmpty()){ //nie był wzorcowany
                    if(spendDate==null){//nie był wydany
                        tempStorageModel.setEntryDate(Converter.getConverter().toString(newSpendDate));
                        storageDao.createOrUpdate(tempStorageModel);
                    }
                    else{
                        if(newSpendDate.isEqual(spendDate)||newSpendDate.isBefore(spendDate)){
                            tempStorageModel.setEntryDate(Converter.getConverter().toString(newSpendDate));
                            storageDao.createOrUpdate(tempStorageModel);
                        }
                    }
                }
                else{
                    for(int j=0;j<calibrationDatesList.size();j++){
                        if(newSpendDate.isEqual(calibrationDatesList.get(j))||newSpendDate.isBefore(calibrationDatesList.get(j))){
                            tempStorageModel.setEntryDate(Converter.getConverter().toString(newSpendDate));
                            storageDao.createOrUpdate(tempStorageModel);
                        }
                    }
                }
            }

        }
    }
    private void saveCalibrationDate(){

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
