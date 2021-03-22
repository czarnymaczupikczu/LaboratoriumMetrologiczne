package controllers;

import dbModels.RegisterModel;
import dbModels.StorageModel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.database.CommonDao;

import java.time.LocalDate;
import java.util.List;

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

    private String dateType="entryDate";
    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    private final String ENTRY_DATE_ERROR="Podałeś nieprawidłową datę przyjęcia";
    private final String CALIBRATION_DATE_ERROR="Podałeś nieprawidłową datę wzorcowania";
    private final String LEFT_DATE_ERROR="Podałeś nieprawidłową datę wydania";

    @FXML private VBox editDateWindowMainVBox;
    @FXML private Label editDateWindowLabel;
    @FXML private DatePicker editDateWindowDatePicker;
    @FXML private Label editDateErrorLabel;

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera EditDateWindowController ");
    }


    @FXML
    private void editDateWindowToday(){
        editDateWindowDatePicker.setValue(LocalDate.now());
    }
    @FXML
    void saveEditDate() {
        checkEditedDate();
    }

    @FXML
    private void editDateWindowCancel() {
        CommonTools.closePaneWindow(editDateWindowMainVBox);
    }

    private Boolean checkEditedDate(){
        String entryDate=this.storageWindowController.getStorageDataModel().getCurrentStorage().getEntryDate();
        String leftDate=this.storageWindowController.getStorageDataModel().getCurrentStorage().getSpendDate();
        CommonDao registerDao=new CommonDao();
        CommonDao storageDao=new CommonDao();
        List<StorageModel> storageList = storageDao.getListWithSimpleSelect(StorageModel.class, "idStorage", this.storageWindowController.getStorageDataModel().getCurrentStorage().getIdStorage());
        List<RegisterModel> registerList = registerDao.getListWithSimpleSelect(RegisterModel.class, "storage", this.storageWindowController.getStorageDataModel().getCurrentStorage().getIdStorage());
        StorageModel storageModel=storageList.get(0);
        if (dateType.equals("entryDate")) {
            if(registerList.isEmpty()) {
                if(leftDate.equals("")){
                    storageModel.setEntryDate(this.editDateWindowDatePicker.getValue().toString());
                    storageDao.createOrUpdate(storageModel);
                }
            }
        }

        return true;
    }

}
