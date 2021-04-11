package controllers;

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

import static dbModels.StorageModel.*;

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
    private static final String ENTRY_DATE_ERROR="Nieprawidłowa data przyjęcia";
    private static final String CALIBRATION_DATE_ERROR="Nieprawidłowa data wzorcowania";
    private static final String SPEND_DATE_ERROR="Nieprawidłowa data wydania";
    private static final String EMPTY_DATE_MESSAGE="Nie wybrałeś prawidłowo daty wzorcowania";

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
        switch (this.dateType) {
            case "Data przyjęcia" -> saveEntryDate();
            case "Data wzorcowania" -> saveCalibrationDate();
            case "Data wydania" -> saveSpendDate();
        }
    }

    @FXML
    private void editDateWindowCancel() {
        CommonTools.closePaneWindow(editDateWindowMainVBox);
    }

    private void saveEntryDate(){
        if(this.editDateWindowDatePicker.getValue()!=null) {
            List<StorageModel> storageModelList = new ArrayList<>();
            for (StorageFxModel storageFxModel : this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList()) {
                CommonDao storageDao = new CommonDao();
                List<LocalDate> calibrationDatesList = decodeCalibrationDates(storageFxModel.getCalibrationDates());
                LocalDate newEntryDate = this.editDateWindowDatePicker.getValue();
                LocalDate spendDate = Converter.getConverter().fromString(storageFxModel.getSpendDate());
                if (checkEntryDate(newEntryDate, calibrationDatesList, spendDate)) {
                    storageModelList.add(storageDao.queryForFirst(StorageModel.class, ID_STORAGE, storageFxModel.getIdStorage()));
                } else {
                    this.editDateErrorLabel.setText(ENTRY_DATE_ERROR);
                    break;
                }
                if (this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().size() == storageModelList.size()) {
                    String newEntryDateString = newEntryDate.toString();
                    for (StorageModel storageModel : storageModelList) {
                        storageModel.setEntryDate(newEntryDateString);
                        storageDao.createOrUpdate(storageModel);
                    }
                    editDateWindowCancel();
                } else {
                    this.editDateErrorLabel.setText(ENTRY_DATE_ERROR);
                }
            }
        }
        else {
            this.editDateErrorLabel.setText(ENTRY_DATE_ERROR);
        }
    }
    private void saveSpendDate(){
        if(this.editDateWindowDatePicker.getValue()!=null) {
            List<StorageModel> storageModelList = new ArrayList<>();
            for (StorageFxModel storageFxModel : this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList()) {
                CommonDao storageDao = new CommonDao();
                List<LocalDate> calibrationDatesList = decodeCalibrationDates(storageFxModel.getCalibrationDates());
                LocalDate newSpendDate = this.editDateWindowDatePicker.getValue();
                LocalDate entryDate = Converter.getConverter().fromString(storageFxModel.getEntryDate());
                String oldSpendDate = storageFxModel.getSpendDate();
                if (!oldSpendDate.isEmpty() && checkSpendDate(newSpendDate, calibrationDatesList, entryDate)) {
                    storageModelList.add(storageDao.queryForFirst(StorageModel.class, ID_STORAGE, storageFxModel.getIdStorage()));
                } else {
                    this.editDateErrorLabel.setText(SPEND_DATE_ERROR);
                    break;
                }
                if (this.storageWindowController.getStorageDataModel().getStorageSelectedItemsList().size() == storageModelList.size()) {
                    String newSpendDateString = newSpendDate.toString();
                    for (StorageModel storageModel : storageModelList) {
                        storageModel.setSpendDate(newSpendDateString);
                        storageDao.createOrUpdate(storageModel);
                    }
                    editDateWindowCancel();
                } else {
                    this.editDateErrorLabel.setText(SPEND_DATE_ERROR);
                }
            }
        }
        else {
            this.editDateErrorLabel.setText(SPEND_DATE_ERROR);
        }
    }
    private void saveCalibrationDate(){
        if(this.editDateWindowDatePicker.getValue()!=null) {
            LocalDate newCalibrationDate =this.editDateWindowDatePicker.getValue();
            if(checkCalibrationDate(newCalibrationDate)){
                this.registerWindowController.getRegisterDataModel().getCurrentRegisterModel().setCalibrationDate(Converter.getConverter().toString(newCalibrationDate));
                CommonDao commonDao=new CommonDao();
                commonDao.createOrUpdate(this.registerWindowController.getRegisterDataModel().getCurrentRegisterModel());
            }
            else{
                this.editDateErrorLabel.setText(CALIBRATION_DATE_ERROR);
            }
        }
        else{
            this.editDateErrorLabel.setText(EMPTY_DATE_MESSAGE);
        }
    }
    public Boolean checkCalibrationDate(LocalDate newCalibrationDate){
        //entryDate zawsze jest
        LocalDate entryDate=Converter.getConverter().fromString(this.registerWindowController.getRegisterDataModel().getCurrentRegisterElement().getStorage().getEntryDate());
        LocalDate spendDate;
        LocalDate beforeCalibrationDate;
        LocalDate afterCalibrationDate;
        //spendDate
        if(!this.registerWindowController.getRegisterDataModel().getCurrentRegisterElement().getStorage().getSpendDate().isEmpty()){
            spendDate=Converter.getConverter().fromString(this.registerWindowController.getRegisterDataModel().getCurrentRegisterElement().getStorage().getSpendDate());
        }
        else{
            spendDate=newCalibrationDate;
        }
        //Szukamy poprzedniego i następnego przyrządu w rejestrze
        int index=this.registerWindowController.getRegisterDataModel().getRegisterList().indexOf(this.registerWindowController.getRegisterDataModel().getCurrentRegisterElement());
        if(index==0){
            beforeCalibrationDate=newCalibrationDate;
        }
        else{
            beforeCalibrationDate=Converter.getConverter().fromString(this.registerWindowController.getRegisterDataModel().getRegisterList().get(index-1).getCalibrationDate());
        }
        if(index==this.registerWindowController.getRegisterDataModel().getRegisterList().size()-1) {
            afterCalibrationDate=newCalibrationDate;
        }
        else{
            afterCalibrationDate = Converter.getConverter().fromString(this.registerWindowController.getRegisterDataModel().getRegisterList().get(index + 1).getCalibrationDate());
        }
        return !newCalibrationDate.isBefore(entryDate) && !newCalibrationDate.isBefore(beforeCalibrationDate) && !newCalibrationDate.isAfter(spendDate) && !newCalibrationDate.isAfter(afterCalibrationDate);
    }
    public Boolean checkEntryDate(LocalDate newEntryDate, List<LocalDate> calibrationDatesList, LocalDate spendDate){
        if (newEntryDate != null) {
            if(calibrationDatesList.isEmpty()){ //nie był wzorcowany
                if(spendDate!=null){//był wydany
                    if(newEntryDate.isAfter(spendDate)){
                        return false;
                    }
                }
            }
            else{
                for (LocalDate localDate : calibrationDatesList) {
                    if (newEntryDate.isAfter(localDate)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public Boolean checkSpendDate(LocalDate newSpendDate, List<LocalDate> calibrationDatesList, LocalDate entryDate){
        if (newSpendDate != null) {
            if(calibrationDatesList.isEmpty()){ //nie był wzorcowany
                if(newSpendDate.isBefore(entryDate)){
                    return false;
                }
            }
            else{
                for (LocalDate localDate : calibrationDatesList) {
                    if (newSpendDate.isBefore(localDate)) {
                        return false;
                    }
                }
            }
        }
        else
        {
             return false;
        }
        return true;
    }
    public void setEditDateWindowLabel(String label) {
        this.editDateWindowLabel.setText(label);
    }
    public static List<LocalDate> decodeCalibrationDates(String calibrationDates){
        List<LocalDate> calibrationDatesList=new ArrayList<>();
        if(calibrationDates.length()>0) {
            int start = 0;
            int end = 10;
            while (end <= calibrationDates.length()) {
                calibrationDatesList.add(Converter.getConverter().fromString(calibrationDates.substring(start, end)));
                System.out.println(calibrationDates.substring(start, end));
                start = end + 2;
                end = start + 10;
            }
        }
        return calibrationDatesList;
    }
}