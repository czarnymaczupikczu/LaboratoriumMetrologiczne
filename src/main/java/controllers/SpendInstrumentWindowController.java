package controllers;

import fxModels.StorageFxModel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.Converter;
import utils.database.CommonDao;

import java.time.LocalDate;
import java.util.List;

public class SpendInstrumentWindowController {


    private MainWindowController mainWindowController;
    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
    private StorageWindowController storageWindowController;
    public void setStorageWindowController(StorageWindowController storageWindowController) {
        this.storageWindowController = storageWindowController;
    }

    private static final String TITLE_MESSAGE="Nieprawidłowa data wydania";
    private static final String EMPTY_DATE_MESSAGE="Nie wybrałeś prawidłowo daty wydania";

    @FXML private VBox mainVBox;
    @FXML private Label nameLabel;
    @FXML private Label typeLabel;
    @FXML private Label producerLabel;
    @FXML private Label serialNumberLabel;
    @FXML private Label identificationLabel;
    @FXML private Label rangeLabel;
    @FXML private Label applicantLabel;
    @FXML private Label lengthLabel;
    @FXML private Label diameterLabel;
    @FXML private DatePicker spendDatePicker;

    public void init(StorageFxModel storageFxModel){
        setInstrumentDataToForm(storageFxModel);
    }

    @FXML
    void save() {
        if(this.spendDatePicker.getValue()!=null) {
            List<LocalDate> calibrationDatesList = EditDateWindowController.decodeCalibrationDates(this.storageWindowController.getStorageDataModel().getCurrentStorage().getCalibrationDates());
            LocalDate newSpendDate = this.spendDatePicker.getValue();
            LocalDate entryDate = Converter.getConverter().fromString(this.storageWindowController.getStorageDataModel().getCurrentStorage().getEntryDate());
            if(checkSpendDate(newSpendDate,calibrationDatesList,entryDate)){
                this.storageWindowController.getStorageDataModel().getCurrentStorageModel().setSpendDate(Converter.getConverter().toString(newSpendDate));
                this.storageWindowController.getStorageDataModel().getCurrentStorageModel().setSpendUser(this.mainWindowController.getMainDataModel().getUser());
                CommonDao commonDao=new CommonDao();
                commonDao.createOrUpdate(this.storageWindowController.getStorageDataModel().getCurrentStorageModel());
                cancel();
            }
            else{
                CommonTools.displayMessage(TITLE_MESSAGE, EMPTY_DATE_MESSAGE);
            }
        }
        else{
            CommonTools.displayMessage(TITLE_MESSAGE, EMPTY_DATE_MESSAGE);
        }
    }
    @FXML
    void cancel() {
        CommonTools.closePaneWindow(this.mainVBox);
    }
    @FXML
    void todayOnAction() {
        this.spendDatePicker.setValue(LocalDate.now());
    }
    public void setInstrumentDataToForm(StorageFxModel storageFxModel){
        this.nameLabel.setText(storageFxModel.getInstrument().getName());
        this.typeLabel.setText(storageFxModel.getInstrument().getType());
        this.producerLabel.setText(storageFxModel.getInstrument().getProducer());
        this.serialNumberLabel.setText(storageFxModel.getInstrument().getSerialNumber());
        this.identificationLabel.setText(storageFxModel.getInstrument().getIdentificationNumber());
        this.lengthLabel.setText(storageFxModel.getInstrument().getLength());
        this.diameterLabel.setText(storageFxModel.getInstrument().getDiameter());
        this.rangeLabel.setText(storageFxModel.getInstrument().getRange());
        this.applicantLabel.setText(storageFxModel.getInstrument().getApplicant().getShortName());
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
}
