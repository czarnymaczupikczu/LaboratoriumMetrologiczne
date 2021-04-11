package controllers;

import dataModels.StorageDataModel;
import dbModels.InstrumentModel;
import dbModels.StorageModel;
import fxModels.InstrumentFxModel;
import fxModels.StorageFxModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.CommonTools;
import utils.Converter;
import utils.FxmlTools;
import utils.database.CommonDao;

import java.io.FileOutputStream;
import java.io.IOException;

import static dbModels.InstrumentModel.ID_INSTRUMENT;
import static dbModels.StorageModel.ID_STORAGE;


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
    private final StorageDataModel storageDataModel=new StorageDataModel();
    public StorageDataModel getStorageDataModel() {
        return storageDataModel;
    }

    private NewInstrumentWindowController newInstrumentWindowController;
    private EditInstrumentWindowController editInstrumentWindowController;
    private EditDateWindowController editDateWindowController;
    private EditRemarksWindowController editRemarksWindowController;
    private CalibrateInstrumentWindowController calibrateInstrumentWindowController;
    private SpendInstrumentWindowController spendInstrumentWindowController;

    //Stałe tekstowe
    private static final String NEW_INSTRUMENT_WINDOW = "/fxml/NewInstrumentWindow.fxml";
    private static final String EDIT_INSTRUMENT_WINDOW="/fxml/EditInstrumentWindow.fxml";
    private static final String CALIBRATE_INSTRUMENT_WINDOW="/fxml/CalibrateInstrumentWindow.fxml";
    private static final String SPEND_INSTRUMENT_WINDOW="/fxml/SpendInstrumentWindow.fxml";
    private static final String EDIT_DATE_WINDOW="/fxml/EditDateWindow.fxml";
    private static final String EDIT_REMARKS_WINDOW="/fxml/EditRemarksWindow.fxml";
    private static final String ENTRY_DATE="Data przyjęcia";
    private static final String SPEND_DATE="Data wydania";
    private static final String INSTRUMENT_REMARKS="Uwagi dotyczące przyrządu";
    private static final String CALIBRATION_REMARKS="Uwagi dotyczące wzorcowania";
    private static final String ACCREDITED_REGISTER="AP131";
    private static final String NON_ACCREDITED_REGISTER="PozaAP";
    private final String TITLE_MESSAGE="Ponowne wzorcowanie!";
    private final String WINDOW_MESSAGE="Czy chcesz wzorcować ten przyrząd ponownie ?";
    private static final String SPEND_TITLE_MESSAGE="Przyrząd nie był wzorcowany";
    private static final String SPEND_WINDOW_MESSAGE="Czy chcesz wydać przyrząd bez wzorcowania ?";
    private static final String ACCREDITED_MAIN_LABEL="Wzorcowanie przyrządu w zakresie akredytacji AP131";
    private static final String NON_ACCREDITED_MAIN_LABEL="Wzorcowanie przyrządu poza zakresem akredytacji";
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
    @FXML private MenuItem cancelSpendInstrumentItem;
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
            this.cancelSpendInstrumentItem.setVisible(false);
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
        this.instrumentRemarksTextArea.setDisable(true);
        this.instrumentRemarksTextArea.setStyle("-fx-opacity: 1.0;");
        this.calibrationRemarksTextArea.setWrapText(true);
        this.calibrationRemarksTextArea.setDisable(true);
        this.calibrationRemarksTextArea.setStyle("-fx-opacity: 1.0;");
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
        searchTextField.textProperty().addListener((value,oldValue, newValue) -> storageDataModel.addFilterToObservableList(newValue));
    }
    //Przyciski
    @FXML
    void loadStorageList() {
        storageDataModel.listInitialize(storageStateComboBox.getValue(),storageYearComboBox.getValue());
    }
    @FXML
    void addInstrument() {
        this.newInstrumentWindowController =FxmlTools.openVBoxWindow(NEW_INSTRUMENT_WINDOW);
        if(this.newInstrumentWindowController!=null) {
            this.newInstrumentWindowController.setMainController(this.mainController);
            if (this.mainController.getMainDataModel().getUser().getPermissionLevel().equals("worker")) {
                this.newInstrumentWindowController.disabelTextArea();
            }
            this.newInstrumentWindowController.setStorageWindowController(this);
        }
    }
    @FXML
    void spendInstrument(){
        if(this.storageDataModel.getStorageSelectedItemsList().size()>0) {
            for(StorageFxModel storageFxModel:this.storageDataModel.getStorageSelectedItemsList()) {
                if (storageFxModel.getSpendDate().equals("")) {
                    if (storageFxModel.getCalibrationDates().equals("")) {
                        if (CommonTools.display(SPEND_TITLE_MESSAGE, SPEND_WINDOW_MESSAGE)) {
                            spendInstrument(storageFxModel);
                        } else {
                            this.spendInstrumentWindowController.cancel(); //Zamykamy okienko
                        }
                    } else {
                        spendInstrument(storageFxModel);
                    }
                }
            }
        }
    }
    @FXML
    private void cancelSpendDate(){
       for(StorageFxModel storageFxModel: this.storageDataModel.getStorageSelectedItemsList()){
           System.out.println("Numer czujnika: "+storageFxModel.getInstrument().getIdentificationNumber());
           decodeInstrumentRemarks(storageFxModel);
       }

        /*for (StorageFxModel storageFxModel : this.storageDataModel.getStorageSelectedItemsList()) {
            if (!storageFxModel.getSpendDate().equals("")) {
                CommonDao storageDao = new CommonDao();
                StorageModel storageModel=storageDao.queryForFirst(StorageModel.class, ID_STORAGE, storageFxModel.getIdStorage());
                storageModel.setSpendDate("");
                storageModel.setSpendUser(null);
                storageDao.createOrUpdate(storageModel);
            }
        }*/
    }
    @FXML
    void accreditedCalibration(){
        if(this.storageDataModel.getCurrentStorage().getIdStorage()>0 && this.storageDataModel.getCurrentStorage().getSpendUser().equals("")) {
            if(!this.storageDataModel.getCurrentStorage().getCalibrationUsers().equals("")) {
                if (CommonTools.display(TITLE_MESSAGE, WINDOW_MESSAGE)) {
                    calibrateInstrument(ACCREDITED_REGISTER,ACCREDITED_MAIN_LABEL);
                }
            }
            else{
                calibrateInstrument(ACCREDITED_REGISTER,ACCREDITED_MAIN_LABEL);
            }
        }
    }
    @FXML
    void nonAccreditedCalibration(){
        if(this.storageDataModel.getCurrentStorage().getIdStorage()>0 && this.storageDataModel.getCurrentStorage().getSpendUser().equals("")) {
            if(!this.storageDataModel.getCurrentStorage().getCalibrationUsers().equals("")) {
                if (CommonTools.display(TITLE_MESSAGE, WINDOW_MESSAGE)) {
                    calibrateInstrument(NON_ACCREDITED_REGISTER,NON_ACCREDITED_MAIN_LABEL);
                }
            }
            else{
                calibrateInstrument(NON_ACCREDITED_REGISTER,NON_ACCREDITED_MAIN_LABEL);
            }
        }
    }
    @FXML
    void editEntryDate() {
        loadEditDateWindow(ENTRY_DATE);
    }
    @FXML
    void editInstrument() {
        this.editInstrumentWindowController=FxmlTools.openVBoxWindow(EDIT_INSTRUMENT_WINDOW);
        if(this.editInstrumentWindowController!=null) {
            this.editInstrumentWindowController.setMainController(this.mainController);
            this.editInstrumentWindowController.setStorageWindowController(this);
            this.editInstrumentWindowController.getInstrumentDataModel().searchForInstrument(this.storageDataModel.getCurrentStorage().getInstrument().getIdInstrument());
            this.editInstrumentWindowController.getInstrumentDataModel().getFormInstrument().setApplicant(Converter.convertApplicantFxModelToApplicantModel(this.storageDataModel.getCurrentStorage().getInstrument().getApplicant()));
            this.editInstrumentWindowController.setInstrumentDataToForm(this.editInstrumentWindowController.getInstrumentDataModel().getFindInstrument());
            this.storageDataModel.initializeCurrentStorageModel();
        }
    }
    @FXML
    void editInstrumentRemarks(){
        this.editRemarksWindowController=FxmlTools.openVBoxWindow(EDIT_REMARKS_WINDOW);
        if(this.editRemarksWindowController!=null) {
            this.editRemarksWindowController.setStorageWindowController(this);
            this.editRemarksWindowController.setDateType(INSTRUMENT_REMARKS);
            this.editRemarksWindowController.setEditRemarksTextArea(this.getStorageDataModel().getCurrentStorage().getInstrumentRemarks());
            this.editRemarksWindowController.setEditRemarksWindowLabel(INSTRUMENT_REMARKS);
        }
    }
    @FXML
    void editCalibrationRemarks(){
        this.editRemarksWindowController=FxmlTools.openVBoxWindow(EDIT_REMARKS_WINDOW);
        if (this.editRemarksWindowController!=null) {
            this.editRemarksWindowController.setStorageWindowController(this);
            this.editRemarksWindowController.setDateType(CALIBRATION_REMARKS);
            this.editRemarksWindowController.setEditRemarksTextArea(this.getStorageDataModel().getCurrentStorage().getCalibrationRemarks());
            this.editRemarksWindowController.setEditRemarksWindowLabel(CALIBRATION_REMARKS);
        }
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
    private void calibrateInstrument(String registerKind,String label){
        this.calibrateInstrumentWindowController = FxmlTools.openVBoxWindow(CALIBRATE_INSTRUMENT_WINDOW);
        if (this.calibrateInstrumentWindowController != null) {
            this.calibrateInstrumentWindowController.setRegisterKind(registerKind);
            this.calibrateInstrumentWindowController.setMainLabel(label);
            this.calibrateInstrumentWindowController.setStorageWindowController(this);
            this.calibrateInstrumentWindowController.setMainController(this.mainController);
            this.storageDataModel.initializeCurrentStorageModel();
            this.calibrateInstrumentWindowController.setInstrumentDataToForm(this.storageDataModel.getCurrentStorageModel().getInstrument(), this.storageDataModel.getCurrentStorage());
        }
    }
    private void spendInstrument(StorageFxModel storageFxModel){
        try {
            FXMLLoader loader = new FXMLLoader(FxmlTools.class.getResource(SPEND_INSTRUMENT_WINDOW));
            VBox vBox = loader.load();
            this.spendInstrumentWindowController=loader.getController();
            if (this.spendInstrumentWindowController != null) {
                this.spendInstrumentWindowController.setMainWindowController(this.mainController);
                this.spendInstrumentWindowController.setStorageWindowController(this);
                this.spendInstrumentWindowController.init(storageFxModel);
                this.storageDataModel.initializeCurrentStorageModel(storageFxModel.getIdStorage());
            }
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(vBox);
            window.initStyle(StageStyle.TRANSPARENT);
            window.setScene(scene);
            window.showAndWait();
        } catch (IOException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }
    @FXML
    private void exportToExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Arkusz1");
        Row row = spreadsheet.createRow(0);
        //Nazwy kolumn
        row.createCell(0).setCellValue("Lp.");
        row.createCell(1).setCellValue("Nazwa");
        row.createCell(2).setCellValue("Typ");
        row.createCell(3).setCellValue("Producent");
        row.createCell(4).setCellValue("Nr fabryczny");
        row.createCell(5).setCellValue("Nr identyfikacyjny");
        row.createCell(6).setCellValue("Zakres pomiarowy");
        row.createCell(7).setCellValue("Długość");
        row.createCell(8).setCellValue("Średnica");
        row.createCell(9).setCellValue("Zleceniodawca");
        row.createCell(10).setCellValue("Data przyjęcia");
        row.createCell(11).setCellValue("Data wzorcowania");
        row.createCell(12).setCellValue("Data wydania");

        int i=0;
        for (StorageFxModel storageElement : this.storageDataModel.getFilteredStorageList()) {
            row = spreadsheet.createRow(i + 1);
            row.createCell(0).setCellValue(i+1);
            row.createCell(1).setCellValue(storageElement.getInstrument().getName());
            row.createCell(2).setCellValue(storageElement.getInstrument().getType());
            row.createCell(3).setCellValue(storageElement.getInstrument().getProducer());
            row.createCell(4).setCellValue(storageElement.getInstrument().getSerialNumber());
            row.createCell(5).setCellValue(storageElement.getInstrument().getIdentificationNumber());
            row.createCell(6).setCellValue(storageElement.getInstrument().getRange());
            row.createCell(7).setCellValue(storageElement.getInstrument().getLength());
            row.createCell(8).setCellValue(storageElement.getInstrument().getDiameter());
            row.createCell(9).setCellValue(storageElement.getInstrument().getApplicant().getShortName());
            row.createCell(10).setCellValue(storageElement.getEntryDate());
            row.createCell(11).setCellValue(storageElement.getCalibrationDates());
            row.createCell(12).setCellValue(storageElement.getInstrumentRemarks());
            i++;
        }
        for(int j=0;j<13;j++){
            spreadsheet.autoSizeColumn(j);
        }
        FileOutputStream fileOut = new FileOutputStream("Magazyn.xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }
    public void decodeInstrumentRemarks(StorageFxModel storage){
        if(storage.getInstrumentRemarks()!=null){//Nie jest nulem
            String temp=CommonTools.deleteWhiteSpaces(storage.getInstrumentRemarks());
            if(temp.length()>0){
                String length="";
                String diameter="";
                if(temp.contains("m/")){
                    int tempLength=temp.length();
                    int index=temp.indexOf("m/");
                    length=String.valueOf(Integer.parseInt(temp.substring(0,index))*1000);
                    diameter=temp.substring(index+2,tempLength-2);
                }
                else if(temp.contains("/")){

                    int tempLength=temp.length();
                    int index=temp.indexOf("/");
                     length=temp.substring(0,index);
                     diameter=temp.substring(index+1,tempLength);
                }
                else if(temp.contains("mm,")){
                    int tempLength=temp.length();
                    int index=temp.indexOf("mm,");
                    length=temp.substring(0,index);
                    diameter=temp.substring(index+3,tempLength-2);

                }
                else if(temp.contains("m,")){
                    int tempLength=temp.length();
                    int index=temp.indexOf("m,");
                    length=String.valueOf(Integer.parseInt(temp.substring(0,index))*1000);
                    diameter=temp.substring(index+2,tempLength-2);

                }
                else if(temp.contains("dł.-")){
                    int tempLength=temp.length();
                    int firstmm=temp.indexOf("mm");
                    int index=temp.indexOf("śr.-");
                    length=temp.substring(4,index-2);
                    diameter=temp.substring(index+4,tempLength-2);

                }
                else if(temp.contains("L-")){
                    int tempLength=temp.length();
                    int firstmm=temp.indexOf("mm");
                    int index=temp.indexOf("Ø-");
                    length=temp.substring(2,index-2);
                    diameter=temp.substring(index+2,tempLength-2);

                }

                    InstrumentModel instrument=searchForInstrument(storage.getInstrument().getIdInstrument());
                    instrument.setLength(length);
                    instrument.setDiameter(diameter);
                    CommonDao commonDao=new CommonDao();
                    commonDao.createOrUpdate(instrument);
                    System.out.println("Długość: "+ length);
                    System.out.println("średnica: "+diameter);
            }
        }
    }
    public InstrumentModel searchForInstrument(Integer value){
        CommonDao commonDao=new CommonDao();
        return commonDao.queryForFirst(InstrumentModel.class, ID_INSTRUMENT, value);
    }
}