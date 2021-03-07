package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import dataModels.MainDataModel;
import dataModels.StorageDataModel;
import dbModels.YearModel;
import fxModels.InstrumentFxModel;
import fxModels.StorageFxModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.CommonTools;
import utils.DatabaseTools;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class StorageWindowController {
    public StorageWindowController(){
        System.out.println("Konstruktor klasy StorehouseWindowController");
    }

    private final String INSTRUMENT_WINDOW="/fxml/InstrumentWindow.fxml";

    //Pola prywatne
    private StorageDataModel storageDataModel=new StorageDataModel();
    //Główny kontroler powiązany z kontrolerami poszczególnych okien
    private MainWindowController mainController;
    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }
    private InstrumentWindowController instrumentWindowController;


    //Deklaracje związane z widokiem fxml
    @FXML private VBox storageMainVBox;
    //ComboBox
    @FXML private ComboBox<String> storageStateComboBox;
    @FXML private ComboBox<String> storageYearComboBox;
    //TableView
    @FXML private TableView<StorageFxModel> storageTableView;
    @FXML private TableColumn<StorageFxModel, Integer> idStorageColumn;
    @FXML private TableColumn<StorageFxModel, String> nameColumn;
    @FXML private TableColumn<StorageFxModel, String> typeColumn;
    @FXML private TableColumn<StorageFxModel, String> producerColumn;
    @FXML private TableColumn<StorageFxModel, String> serialNumberColumn;
    @FXML private TableColumn<StorageFxModel, String> identificationNumberColumn;
    @FXML private TableColumn<StorageFxModel, String> rangeColumn;
    @FXML private TableColumn<StorageFxModel,String> lengthColumn;
    @FXML private TableColumn<StorageFxModel,String> diameterColumn;
    @FXML private TableColumn<StorageFxModel, String> applicantColumn;
    @FXML private TableColumn<StorageFxModel, String> entryDateColumn;
    @FXML private TableColumn<StorageFxModel, String> calibrationDateColumn;
    @FXML private TableColumn<StorageFxModel, String> spendDateColumn;
    //Labels
    @FXML private Label shortNameLabel;
    @FXML private Label fullNameLabel;
    @FXML private Label cityLabel;
    @FXML private Label streetLabel;
    @FXML private Label entryLabel;
    @FXML private Label calibrationLabel;
    @FXML private Label spendLabel;
    @FXML private Label cardNumberLabel;
    @FXML private TextArea storageRemarksTextArea;
    @FXML private TextField searchTextField;


    @FXML
    public void initialize() throws SQLException {
        System.out.println("Metoda initialize kontrolera StorehouseWindowController ");
    }
    public void init(){
        initializeComboBoxes();
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
        bindingSizeProperty();
    }
    private void initializeComboBoxes(){
        this.storageStateComboBox.getItems().addAll(mainController.getMainDataModel().getStorageStateComboBoxList());
        this.storageStateComboBox.setValue(mainController.getMainDataModel().getStorageStateComboBoxList().get(0));
        this.storageYearComboBox.getItems().addAll(mainController.getMainDataModel().getYearComboBoxList());
        this.storageYearComboBox.setValue(mainController.getMainDataModel().getYearComboBoxList().get(mainController.getMainDataModel().getYearComboBoxList().size()-1));
    }
    private void bindingSizeProperty(){
        this.storageTableView.prefHeightProperty().bind(storageMainVBox.heightProperty().multiply(0.7));

    }
    private void bindingLabels(){
        this.shortNameLabel.textProperty().bind(this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().shortNameProperty());
        this.fullNameLabel.textProperty().bind(this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().fullNameProperty());
        this.cityLabel.textProperty().bind(Bindings.concat(this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().postCodeProperty()," ",this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().cityProperty()));
        this.streetLabel.textProperty().bind(Bindings.concat(this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().streetProperty()," ",this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().numberProperty()));
        this.entryLabel.textProperty().bind(Bindings.concat(this.storageDataModel.getCurrentStorage().entryUserProperty()," ",this.storageDataModel.getCurrentStorage().entryDateProperty()));
        this.calibrationLabel.textProperty().bind(Bindings.concat(this.storageDataModel.getCurrentStorage().calibrationUsersProperty()," ",this.storageDataModel.getCurrentStorage().calibrationDatesProperty()));
        this.spendLabel.textProperty().bind(Bindings.concat(this.storageDataModel.getCurrentStorage().spendUserProperty()," ",this.storageDataModel.getCurrentStorage().spendDateProperty()));
        this.cardNumberLabel.textProperty().bind(this.storageDataModel.getCurrentStorage().cardNumbersProperty());
        this.storageRemarksTextArea.textProperty().bind(this.storageDataModel.getCurrentStorage().storageRemarksProperty());
    }
    private void updateBindings(StorageFxModel storageElement){
        this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().setShortName(storageElement.getInstrument().getApplicant().getShortName());
        this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().setFullName(storageElement.getInstrument().getApplicant().getFullName());
        this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().setPostCode(storageElement.getInstrument().getApplicant().getPostCode());
        this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().setCity(storageElement.getInstrument().getApplicant().getCity());
        this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().setStreet(storageElement.getInstrument().getApplicant().getStreet());
        this.storageDataModel.getCurrentStorage().getInstrument().getApplicant().setNumber(storageElement.getInstrument().getApplicant().getNumber());
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
        this.storageDataModel.getCurrentStorage().setCardNumbers(storageElement.getCardNumbers());
        this.storageDataModel.getCurrentStorage().setStorageRemarks(storageElement.getStorageRemarks());
        System.out.println(this.storageDataModel.getCurrentStorage().spendUserProperty().getValue());
    }
    private void addFilter(){
        searchTextField.textProperty().addListener((value,oldValue, newValue) ->{
            storageDataModel.addFilterToObservableList(newValue);
        } );
    }

    //Przyciski
    @FXML
    void loadStorageList() {
        storageDataModel.listInitialize(storageStateComboBox.getValue(),storageYearComboBox.getValue());
        //System.out.println(storageDataModel.createSQLStatement(storageStateComboBox.getValue(),storageYearComboBox.getValue()));
    }
    @FXML
    void addInstrument() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(INSTRUMENT_WINDOW));
            VBox vBox = loader.load();
            instrumentWindowController = loader.getController();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Dodaj przyrząd do magazynu");
            Scene scene = new Scene(vBox);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }
}
