package controllers;

import dbModels.StorageModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.database.CommonDao;

public class EditRemarksWindowController {
    public EditRemarksWindowController(){
        System.out.println("Konstruktor klasy EditRemarksWindowController");
    }

    private StorageWindowController storageWindowController;
    public void setStorageWindowController(StorageWindowController storageWindowController) {
        this.storageWindowController = storageWindowController;
    }

    private String dateType;
    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    @FXML private VBox editRemarksWindowMainVBox;
    @FXML private Label editRemarksWindowLabel;
    @FXML private TextArea editRemarksTextArea;

    @FXML
    void save() {
        CommonDao commonDao = new CommonDao();
        StorageModel tempStorageModel = commonDao.queryForFirst(StorageModel.class, "idStorage", this.storageWindowController.getStorageDataModel().getCurrentStorage().getIdStorage());
        if(this.dateType.equals("Uwagi dotyczące przyrządu")){
            tempStorageModel.setInstrumentRemarks(this.editRemarksTextArea.getText());
            commonDao.createOrUpdate(tempStorageModel);
        }
        else{
            tempStorageModel.setCalibrationRemarks(this.editRemarksTextArea.getText());
            commonDao.createOrUpdate(tempStorageModel);
        }
        cancel();
    }

    @FXML
    void cancel() {
        CommonTools.closePaneWindow(editRemarksWindowMainVBox);
    }

    public void setEditRemarksTextArea(String remarks){
        this.editRemarksTextArea.setWrapText(true);
        this.editRemarksTextArea.setText(remarks);
    }
    public void setEditRemarksWindowLabel(String label){
        this.editRemarksWindowLabel.setText(label);
    }
}