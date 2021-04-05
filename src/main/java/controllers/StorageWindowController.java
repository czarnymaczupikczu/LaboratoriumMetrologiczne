package controllers;

import dataModels.StorageDataModel;
import fxModels.StorageFxModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.Converter;
import utils.FxmlTools;



public class StorageWindowController {
    public StorageWindowController(){
        System.out.println("Konstruktor klasy StorehouseWindowController");
    }

    //Główny kontroler powiązany z kontrolerami poszczególnych okien
    private MainWindowController mainController;
    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }

    //Pola prywatne
    private StorageDataModel storageDataModel=new StorageDataModel();
    public StorageDataModel getStorageDataModel() {
        return storageDataModel;
    }

    private NewInstrumentWindowController newInstrumentWindowController;
    private EditInstrumentWindowController editInstrumentWindowController;
    private EditDateWindowController editDateWindowController;
    private EditRemarksWindowController editRemarksWindowController;

    //Stałe tekstowe
    private final String NEW_INSTRUMENT_WINDOW = "/fxml/NewInstrumentWindow.fxml";
    private final String EDIT_INSTRUMENT_WINDOW="/fxml/EditInstrumentWindow.fxml";
    private final String EDIT_DATE_WINDOW="/fxml/EditDateWindow.fxml";
    private final String EDIT_REMARKS_WINDOW="/fxml/EditRemarksWindow.fxml";
    private final String ENTRY_DATE="Data przyjęcia";
    private final String CALIBRATION_DATE="Data wzorcowania";
    private final String SPEND_DATE="Data wydania";
    private final String INSTRUMENT_REMARKS="Uwagi dotyczące przyrządu";
    private final String CALIBRATION_REMARKS="Uwagi dotyczące wzorcowania";

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
    @FXML private TableColumn<StorageFxModel, String> lengthColumn;
    @FXML private TableColumn<StorageFxModel, String> diameterColumn;
    @FXML private TableColumn<StorageFxModel, String> applicantColumn;
    @FXML private TableColumn<StorageFxModel, String> entryDateColumn;
    @FXML private TableColumn<StorageFxModel, String> calibrationDateColumn;
    @FXML private TableColumn<StorageFxModel, String> spendDateColumn;
    //MenuItem
    @FXML private MenuItem spendDateItem;
    @FXML private MenuItem calibrationRemarksItem;
    //Labels
    @FXML private Label shortNameLabel;
    @FXML private Label fullNameLabel;
    @FXML private Label cityLabel;
    @FXML private Label streetLabel;
    @FXML private Label entryLabel;
    @FXML private Label calibrationLabel;
    @FXML private Label spendLabel;
    @FXML private Label cardNumberLabel;
    @FXML private TextArea instrumentRemarksTextArea;
    @FXML private TextArea calibrationRemarksTextArea;
    @FXML private TextField searchTextField;

    public void init(){
        initializeComboBoxes();
        initializeTableView();
        bindingLabels();
        addFilter();
        if(this.mainController.getMainDataModel().getUser().getPermissionLevel().equals("worker")){
            this.calibrationRemarksItem.setDisable(true);
        }
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
                    this.storageDataModel.setCurrentStorage(newValue);
                    bindingLabels();
                }
                this.storageDataModel.getStorageSelectedItemsList().clear();
                this.storageDataModel.getStorageSelectedItemsList().addAll(this.storageTableView.getSelectionModel().getSelectedItems());
            }
        });
        this.instrumentRemarksTextArea.setWrapText(true);
        this.calibrationRemarksTextArea.setWrapText(true);
    }
    private void initializeComboBoxes(){
        this.storageStateComboBox.getItems().addAll(mainController.getMainDataModel().getStorageStateComboBoxList());
        this.storageStateComboBox.setValue(mainController.getMainDataModel().getStorageStateComboBoxList().get(0));
        this.storageYearComboBox.getItems().addAll(mainController.getMainDataModel().getYearComboBoxList());
        this.storageYearComboBox.setValue(mainController.getMainDataModel().getYearComboBoxList().get(mainController.getMainDataModel().getYearComboBoxList().size()-1));
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
        this.instrumentRemarksTextArea.textProperty().bind(this.storageDataModel.getCurrentStorage().instrumentRemarksProperty());
        this.calibrationRemarksTextArea.textProperty().bind(this.storageDataModel.getCurrentStorage().calibrationRemarksProperty());
        this.spendDateItem.disableProperty().bind(this.storageDataModel.getCurrentStorage().spendDateProperty().isEmpty());
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
    }
    @FXML
    void addInstrument() {
        this.newInstrumentWindowController =FxmlTools.openVBoxWindow(NEW_INSTRUMENT_WINDOW);
        this.newInstrumentWindowController.setMainController(this.mainController);
        if(this.mainController.getMainDataModel().getUser().getPermissionLevel().equals("worker")){
            this.newInstrumentWindowController.disabelTextArea();
        }
        this.newInstrumentWindowController.setStorageWindowController(this);
    }
    @FXML
    void spendInstrument(){

    }
    @FXML
    void editEntryDate() {
        loadEditDateWindow(ENTRY_DATE);
    }
    @FXML
    void editInstrument() {
        this.editInstrumentWindowController=FxmlTools.openVBoxWindow(EDIT_INSTRUMENT_WINDOW);
        this.editInstrumentWindowController.setMainController(this.mainController);
        this.editInstrumentWindowController.setStorageWindowController(this);
        this.editInstrumentWindowController.getInstrumentDataModel().searchForInstrument(this.getStorageDataModel().getCurrentStorage().getInstrument().getIdInstrument());
        this.editInstrumentWindowController.getInstrumentDataModel().getFormInstrument().setApplicant(Converter.convertApplicantFxModelToApplicantModel(this.storageDataModel.getCurrentStorage().getInstrument().getApplicant()));
        this.editInstrumentWindowController.setInstrumentDataToForm(this.editInstrumentWindowController.getInstrumentDataModel().getFindInstrument());
    }
    @FXML
    void editInstrumentRemarks(){
        this.editRemarksWindowController=FxmlTools.openVBoxWindow(EDIT_REMARKS_WINDOW);
        this.editRemarksWindowController.setStorageWindowController(this);
        this.editRemarksWindowController.setDateType(INSTRUMENT_REMARKS);
        this.editRemarksWindowController.setEditRemarksTextArea(this.getStorageDataModel().getCurrentStorage().getInstrumentRemarks());
        this.editRemarksWindowController.setEditRemarksWindowLabel(INSTRUMENT_REMARKS);
    }
    @FXML
    void editCalibrationRemarks(){
        this.editRemarksWindowController=FxmlTools.openVBoxWindow(EDIT_REMARKS_WINDOW);
        this.editRemarksWindowController.setStorageWindowController(this);
        this.editRemarksWindowController.setDateType(CALIBRATION_REMARKS);
        this.editRemarksWindowController.setEditRemarksTextArea(this.getStorageDataModel().getCurrentStorage().getCalibrationRemarks());
        this.editRemarksWindowController.setEditRemarksWindowLabel(CALIBRATION_REMARKS);
    }

    @FXML
    void editSpendDate() {
        loadEditDateWindow(SPEND_DATE);
    }
    private void loadEditDateWindow(String dateType){
        this.editDateWindowController=FxmlTools.openVBoxWindow(EDIT_DATE_WINDOW);
        if(this.editDateWindowController!=null){
            this.editDateWindowController.setStorageWindowController(this);
            this.editDateWindowController.setDateType(dateType);
            this.editDateWindowController.setEditDateWindowLabel(dateType);
        }
    }
}