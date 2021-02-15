package controllers;

import dataModels.StorageDataModel;
import fxModels.InstrumentFxModel;
import fxModels.StorageFxModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.sql.SQLException;


public class StorageWindowController {
    public StorageWindowController(){
        System.out.println("Konstruktor klasy StorehouseWindowController");
    }

    //ComboBox
    @FXML
    private ComboBox<String> storageStateComboBox;
    @FXML
    private ComboBox<String> storageYearComboBox;

    @FXML
    private VBox storageMainVBox;
    //TableView
    @FXML
    private TableView<StorageFxModel> storageTableView;
    @FXML
    private TableColumn<StorageFxModel, Integer> idStorageColumn;
    @FXML
    private TableColumn<StorageFxModel, String> nameColumn;
    @FXML
    private TableColumn<StorageFxModel, String> typeColumn;
    @FXML
    private TableColumn<StorageFxModel, String> producerColumn;
    @FXML
    private TableColumn<StorageFxModel, String> serialNumberColumn;
    @FXML
    private TableColumn<StorageFxModel, String> identificationNumberColumn;
    @FXML
    private TableColumn<StorageFxModel, String> rangeColumn;
    @FXML
    private TableColumn<StorageFxModel,String> lengthColumn;
    @FXML
    private TableColumn<StorageFxModel,String> diameterColumn;
    @FXML
    private TableColumn<StorageFxModel, String> applicantColumn;
    @FXML
    private TableColumn<StorageFxModel, String> entryDateColumn;
    @FXML
    private TableColumn<StorageFxModel, String> calibrationDateColumn;
    @FXML
    private TableColumn<StorageFxModel, String> spendDateColumn;
    //Labels
    @FXML
    private Label shortNameLabel;
    @FXML
    private Label fullNameLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label entryLabel;
    @FXML
    private Label calibrationLabel;
    @FXML
    private Label spendLabel;
    @FXML
    private Label cardNumberLabel;
    @FXML
    private TextArea storageRemarksTextArea;
    @FXML
    private TextField searchTextField;

    private StorageDataModel storageDataModel=new StorageDataModel();

    @FXML
    public void initialize() throws SQLException {
        System.out.println("Metoda initialize kontrolera StorehouseWindowController ");
        this.storageStateComboBox.getItems().addAll("Wszystkie","W magazynie");
        this.storageStateComboBox.setValue("Wszystkie");
        this.storageYearComboBox.getItems().addAll("2021","2020","2019","2018");
        this.storageYearComboBox.setValue("2021");
        storageDataModel.listInitialize();
        initializeTableView();
        bindingLabels();
        addFilter();

    }

    private void initializeTableView(){
        this.storageTableView.setItems(this.storageDataModel.getFilteredStorageList());
        this.idStorageColumn.setCellValueFactory(cellData->cellData.getValue().storageIndexProperty().asObject());
        this.nameColumn.setCellValueFactory(cellData->cellData.getValue().getInstrument().nameProperty());
        this.typeColumn.setCellValueFactory(cellData->cellData.getValue().getInstrument().typeProperty());
        this.producerColumn.setCellValueFactory(cellData->cellData.getValue().getInstrument().producerProperty());
        this.serialNumberColumn.setCellValueFactory(cellData->cellData.getValue().getInstrument().serialNumberProperty());
        this.identificationNumberColumn.setCellValueFactory(cellData->cellData.getValue().getInstrument().identificationNumberProperty());
        this.rangeColumn.setCellValueFactory(cellData->cellData.getValue().getInstrument().rangeProperty());
        this.lengthColumn.setCellValueFactory(cellData->cellData.getValue().getInstrument().lengthProperty());
        this.diameterColumn.setCellValueFactory(cellData->cellData.getValue().getInstrument().diameterProperty());
        this.applicantColumn.setCellValueFactory(cellData->cellData.getValue().getInstrument().getApplicant().shortNameProperty());
        this.entryDateColumn.setCellValueFactory(cellData->cellData.getValue().entryDateProperty());
        this.calibrationDateColumn.setCellValueFactory(cellData->cellData.getValue().calibrationDatesProperty());
        this.spendDateColumn.setCellValueFactory(cellData->cellData.getValue().spendDateProperty());
        this.storageTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.storageTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
                if (this.storageTableView.getSelectionModel().getSelectedItems().size() < 2){ //Gdy jest multiple selection to zostaje ciągle ten sam obiekt
                    updateBindings(newValue);
                }
                this.storageDataModel.getStorageSelectedItemsList().clear();
                this.storageDataModel.getStorageSelectedItemsList().addAll(this.storageTableView.getSelectionModel().getSelectedItems());
            }
        });
        this.storageTableView.prefHeightProperty().bind(storageMainVBox.heightProperty().multiply(0.7));
    }
    private void bindingLabels(){
        this.shortNameLabel.textProperty().bind(this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().shortNameProperty());
        this.fullNameLabel.textProperty().bind(this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().fullNameProperty());
        this.cityLabel.textProperty().bind(Bindings.concat(this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().postCodeProperty()," ",this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().cityProperty()));
        //streetLabel.textProperty().bind(Bindings.concat(this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().streetProperty(),));
        this.entryLabel.textProperty().bind(Bindings.concat(this.storageDataModel.getCurrentStorage().entryUserProperty()," ",this.storageDataModel.getCurrentStorage().entryDateProperty()));
        this.calibrationLabel.textProperty().bind(Bindings.concat(this.storageDataModel.getCurrentStorage().calibrationUsersProperty()," ",this.storageDataModel.getCurrentStorage().calibrationDatesProperty()));
        this.spendLabel.textProperty().bind(Bindings.concat(this.storageDataModel.getCurrentStorage().spendUserProperty()," ",this.storageDataModel.getCurrentStorage().spendDateProperty()));
        this.storageRemarksTextArea.textProperty().bind(this.storageDataModel.getCurrentStorage().storageRemarksProperty());
    }
    private void updateBindings(StorageFxModel storageElement){
        this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().setShortName(storageElement.getInstrument().getApplicant().getShortName());
        this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().setFullName(storageElement.getInstrument().getApplicant().getFullName());
        this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().setPostCode(storageElement.getInstrument().getApplicant().getPostCode());
        this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().setCity(storageElement.getInstrument().getApplicant().getCity());
        this.storageDataModel.getCurrentStorage().setEntryDate(storageElement.getEntryDate());
        this.storageDataModel.getCurrentStorage().setEntryUser(storageElement.getEntryUser());
        if (storageElement.calibrationUsersProperty().isNull().getValue()){
            this.storageDataModel.getCurrentStorage().setCalibrationDates("");
            this.storageDataModel.getCurrentStorage().setCalibrationUsers("");
            this.storageDataModel.getCurrentStorage().setCardNumbers("");
        }
        else{
            this.storageDataModel.getCurrentStorage().setCalibrationDates(storageElement.getCalibrationDates());
            this.storageDataModel.getCurrentStorage().setCalibrationUsers(storageElement.getCalibrationUsers());
            this.storageDataModel.getCurrentStorage().setCardNumbers(storageElement.getCardNumbers());
        }
        if(storageElement.spendUserProperty().isNull().getValue()){
            this.storageDataModel.getCurrentStorage().setSpendDate("");
            this.storageDataModel.getCurrentStorage().setSpendUser("");
        }

        else{
            this.storageDataModel.getCurrentStorage().setSpendDate(storageElement.getSpendDate());
            this.storageDataModel.getCurrentStorage().setSpendUser(storageElement.getSpendUser());
        }
        this.storageDataModel.getCurrentStorage().setStorageRemarks(storageElement.getStorageRemarks());
        System.out.println(this.storageDataModel.getCurrentStorage().spendUserProperty().getValue());
    }
    private void addFilter(){
        searchTextField.textProperty().addListener((value,oldValue, newValue) ->{
            storageDataModel.addFilterToObservableList(newValue);
        } );
    }
    @FXML
    void showSelectedItems() {

    }
}
