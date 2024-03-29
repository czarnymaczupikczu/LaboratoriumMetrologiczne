package controllers;

import dataModels.InstrumentDataModel;
import dbModels.InstrumentModel;
import dbModels.StorageModel;
import dbModels.instrument.*;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.CommonTools;
import utils.database.CommonDao;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static dbModels.InstrumentModel.*;
import static dbModels.StorageModel.INSTRUMENT;
import static dbModels.StorageModel.SPEND_DATE;
import static dbModels.instrument.NameModel.INSTRUMENT_NAME;
import static dbModels.instrument.ProducerModel.PRODUCER_NAME;
import static dbModels.instrument.TypeModel.TYPE_NAME;
import static dbModels.instrument.RangeModel.RANGE_NAME;

public class NewInstrumentWindowController {

    public NewInstrumentWindowController(){
        System.out.println("Konstruktor klasy InstrumentWindowController");
    }

    //Deklaracja stałych ze ścieżkami do widoków fxml lub stałe tekstowe
    private static final String NEW_INSTRUMENT_DATA_WINDOW = "/fxml/NewInstrumentDataWindow.fxml";
    private static final String NEW_INSTRUMENT_RANGE_WINDOW="/fxml/NewInstrumentRangeWindow.fxml";
    private static final String APPLICANTS_WINDOW="/fxml/ApplicantsWindow.fxml";
    private static final String NEW_NAME="Dodawanie nowej nazwy przyrządu";
    private static final String NEW_TYPE="Dodawanie nowego typu przyrządu";
    private static final String NEW_PRODUCER="Dodawanie nowego producenta przyrządu";
    private static final String NEW_RANGE="Dodawanie nowego zakresu przyrządu";
    private static final String HEADER_TEXT="Błąd przy dodawaniu przyrządu do magazynu";
    private static final String STORAGE_MESSAGE="Przyrząd znajduje się już w magazynie";

    //Główny kontroler powiązany z kontrolerami poszczególnych okien
    private MainWindowController mainController;
    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }
    private StorageWindowController storageWindowController;
    public void setStorageWindowController(StorageWindowController storageWindowController) {
        this.storageWindowController = storageWindowController;
    }

    private final InstrumentDataModel instrumentDataModel= new InstrumentDataModel();
    public InstrumentDataModel getInstrumentDataModel() {
        return instrumentDataModel;
    }

    private NewInstrumentDataWindowController newInstrumentDataWindowController;
    private ApplicantsWindowController applicantsWindowController;

    @FXML private VBox mainVBox;
    @FXML private ComboBox<String> nameComboBox;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private ComboBox<String> producerComboBox;
    @FXML private ComboBox<String> rangeComboBox;
    @FXML private ComboBox<String> applicantComboBox;

    @FXML private TextField serialNumberTextField;
    @FXML private Label searchBySerialNumberLabel;
    @FXML private Button checkBySerialNumberButton;
    @FXML private TextField identificationNumberTextField;
    @FXML private Label searchByIdentificationNumberLabel;
    @FXML private Button checkByIdentificationNumberButton;
    @FXML private TextField lengthTextField;
    @FXML private TextField diameterTextField;

    @FXML private Button saveButton;
    @FXML private TextArea instrumentRemarks;
    @FXML private TextArea calibrationRemarks;

    @FXML private DatePicker entryDatePicker;

    @FXML
    public void initialize() throws SQLException {
        System.out.println("Metoda initialize kontrolera InstrumentWindowController ");
        this.instrumentDataModel.init();
        initializeComboBox();
        bindComboBox();
        this.instrumentRemarks.setWrapText(true);
        this.calibrationRemarks.setWrapText(true);
    }
    //Poszczególne przyciski
    @FXML
    private void addNewNameOnAction() {
        this.newInstrumentDataWindowController=loadVBoxWindow(NEW_INSTRUMENT_DATA_WINDOW);
        if(this.newInstrumentDataWindowController!=null) {
            this.newInstrumentDataWindowController.setInstrumentWindowController(this);
            this.newInstrumentDataWindowController.init(INSTRUMENT_NAME, NameModel.class, NEW_NAME);
        }
    }
    @FXML
    private void addNewTypeOnAction() {
        this.newInstrumentDataWindowController=loadVBoxWindow(NEW_INSTRUMENT_DATA_WINDOW);
        if(this.newInstrumentDataWindowController!=null) {
            this.newInstrumentDataWindowController.setInstrumentWindowController(this);
            this.newInstrumentDataWindowController.init(TYPE_NAME, TypeModel.class, NEW_TYPE);
        }
    }
    @FXML
    private void addNewProducerOnAction() {
        this.newInstrumentDataWindowController=loadVBoxWindow(NEW_INSTRUMENT_DATA_WINDOW);
        if(this.newInstrumentDataWindowController!=null) {
            this.newInstrumentDataWindowController.setInstrumentWindowController(this);
            this.newInstrumentDataWindowController.init(PRODUCER_NAME, ProducerModel.class, NEW_PRODUCER);
        }
    }
    @FXML
    private void addNewRangeOnAction() {
        this.newInstrumentDataWindowController=loadVBoxWindow(NEW_INSTRUMENT_RANGE_WINDOW);
        if(this.newInstrumentDataWindowController!=null) {
            this.newInstrumentDataWindowController.setInstrumentWindowController(this);
            this.newInstrumentDataWindowController.init(RANGE_NAME, RangeModel.class, NEW_RANGE);
        }
    }
    @FXML
    private void checkBySerialNumberOnAction() {
        if(!CommonTools.deleteWhiteSpaces(this.serialNumberTextField.getText()).isEmpty()) {
            checkByNumber(SERIAL_NUMBER, CommonTools.deleteWhiteSpaces(this.serialNumberTextField.getText()), this.searchBySerialNumberLabel, this.searchByIdentificationNumberLabel);
        }
    }
    @FXML
    private void checkByIdentificationNumberOnAction() {
        if(!CommonTools.deleteWhiteSpaces(this.identificationNumberTextField.getText()).isEmpty()){
            checkByNumber(IDENTIFICATION_NUMBER, CommonTools.deleteWhiteSpaces(this.identificationNumberTextField.getText()), this.searchByIdentificationNumberLabel, this.searchBySerialNumberLabel);
        }
    }
    @FXML
    void applicantComboBoxOnAction() {
        this.applicantsWindowController=loadVBoxWindow(APPLICANTS_WINDOW);
        if(this.applicantsWindowController!=null) {
            this.applicantsWindowController.setInstrumentWindowController(this);
            this.applicantsWindowController.getApplicantsDataModel().listInitializeApplicantsActive();
            this.applicantsWindowController.hideButton();
        }
    }
    @FXML
    void todayOnAction() {
        entryDatePicker.setValue(LocalDate.now());
    }

    @FXML
    void save() {
        createFormInstrument();
        if (isValidInstrumentData(this.instrumentDataModel.getFormInstrument())){
            addInstrumentToInstruments(this.instrumentDataModel.getFormInstrument());
            addInstrumentToStorage(this.instrumentDataModel.getFormInstrument());
        }
    }
    @FXML
    private void cancel() {
        CommonTools.closePaneWindow(mainVBox);
    }





    //Pomocnicze
    private void initializeComboBox(){
        initComboBox(this.nameComboBox,this.instrumentDataModel.getFilteredNames());
        initComboBox(this.typeComboBox,this.instrumentDataModel.getFilteredTypes());
        initComboBox(this.producerComboBox,this.instrumentDataModel.getFilteredProducers());
        initComboBox(this.rangeComboBox,this.instrumentDataModel.getFilteredRange());
    }
    private void bindComboBox(){
        this.typeComboBox.disableProperty().bind(this.nameComboBox.valueProperty().isNull());
        this.producerComboBox.disableProperty().bind(this.typeComboBox.valueProperty().isNull());
        this. rangeComboBox.disableProperty().bind(this.producerComboBox.valueProperty().isNull());
        this.checkBySerialNumberButton.disableProperty().bind(this.serialNumberTextField.textProperty().isEmpty());
        this.checkByIdentificationNumberButton.disableProperty().bind(this.identificationNumberTextField.textProperty().isEmpty());
        this.saveButton.disableProperty().bind(this.serialNumberTextField.textProperty().isEmpty().and(this.identificationNumberTextField.textProperty().isEmpty()));
    }
    private void addInstrumentToInstruments(InstrumentModel instrument){
        CommonDao commonDao=new CommonDao();
        String sNumber=this.serialNumberTextField.getText();
        String iNumber=this.identificationNumberTextField.getText();
        Integer idApplicant = instrument.getApplicant().getIdApplicant();
        List<InstrumentModel> lista=new ArrayList<>();
        if(!sNumber.isEmpty() && !iNumber.isEmpty()) {
            lista = commonDao.selectAndStatement(InstrumentModel.class, SERIAL_NUMBER, sNumber, IDENTIFICATION_NUMBER, iNumber, APPLICANT, idApplicant);
        }else if(!sNumber.isEmpty()){
            lista=commonDao.selectAndStatement(InstrumentModel.class,SERIAL_NUMBER,sNumber,APPLICANT,idApplicant);
        }else if(!iNumber.isEmpty()){
            lista=commonDao.selectAndStatement(InstrumentModel.class,IDENTIFICATION_NUMBER,iNumber,APPLICANT,idApplicant);
        }
        if(lista.isEmpty()){
            commonDao.create(instrument);
        }else{
            instrument.setIdInstrument(lista.get(lista.size()-1).getIdInstrument());
        }
    }
    private void addInstrumentToStorage(InstrumentModel instrument){
        CommonDao commonDao=new CommonDao();
        List<StorageModel> storageList = commonDao.selectAndStatement(StorageModel.class, SPEND_DATE, "", INSTRUMENT, instrument.getIdInstrument());
        if(storageList.isEmpty()){
            StorageModel storageModel=new StorageModel();
            storageModel.setInstrument(instrument);
            storageModel.setEntryDate(this.entryDatePicker.getValue().toString());
            storageModel.setEntryUser(this.mainController.getMainDataModel().getUser());
            storageModel.setInstrumentRemarks(this.instrumentRemarks.getText());
            storageModel.setCalibrationRemarks(this.calibrationRemarks.getText());
            commonDao.create(storageModel);
            this.storageWindowController.loadStorageList();
            cancel();
        }
        else{
            CommonTools.displayMessage(HEADER_TEXT, STORAGE_MESSAGE);
        }
    }
    private void spendInstrumentFromStorage(){

    }
    private <T> T loadVBoxWindow(String resource){
        T instrumentData;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            VBox vBox = loader.load();
            instrumentData=loader.getController();
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            //window.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(vBox);
            scene.getStylesheets().add("css/main.css");
            window.setScene(scene);
            window.show();
            return instrumentData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Metoda służy do konfiguracji ComboBoxów, żeby można było filtrować w nich wartości
     */
    private void initComboBox(ComboBox<String> comboBox, FilteredList<String> filteredList){
        comboBox.setEditable(true);
        comboBox.getEditor().textProperty().addListener((v, oldValue, newValue) -> {
            final TextField editor = comboBox.getEditor();
            final String selected = comboBox.getSelectionModel().getSelectedItem();
            if (selected == null || !selected.equals(editor.getText())) {
                filteredList.setPredicate(item -> item.toUpperCase().startsWith(newValue.toUpperCase()));
            }
        });
        comboBox.setItems(filteredList);
    }
    private void setInstrumentDataToForm(InstrumentModel instrument){
        this.nameComboBox.setValue(instrument.getName().getInstrumentName());
        this.typeComboBox.setValue(instrument.getType().getTypeName());
        this.producerComboBox.setValue(instrument.getProducer().getProducerName());
        this.serialNumberTextField.setText(instrument.getSerialNumber());
        this.identificationNumberTextField.setText(instrument.getIdentificationNumber());
        this.lengthTextField.setText(instrument.getLength());
        this.diameterTextField.setText(instrument.getDiameter());
        this.rangeComboBox.setValue(instrument.getRange().getRangeName());
        this.applicantComboBox.setValue(instrument.getApplicant().getShortName());
    }
    private <T extends BaseModel> T getValue(List<T> list, String value){
        for(T element:list){
            if(element.getName().equals(value)){
                return element;
            }
        }
        return null;
    }
    public void setApplicantComboBox(String value){
        this.applicantComboBox.setValue(value);
    }
    private void checkByNumber(String numberKind, String number, Label firstLabel, Label secondLabel){
        this.instrumentDataModel.setFindInstrument(this.instrumentDataModel.searchForInstrument(numberKind,number));
        if (this.instrumentDataModel.getFindInstrument()==null){
            firstLabel.setTextFill(Color.RED);
        }else{
            setInstrumentDataToForm(this.instrumentDataModel.getFindInstrument());
            System.out.println(this.instrumentDataModel.getFindInstrument().getApplicant().getFullName());
            this.instrumentDataModel.getFormInstrument().setApplicant(this.instrumentDataModel.getFindInstrument().getApplicant());
            String shortName=this.instrumentDataModel.getFindInstrument().getApplicant().getShortName();
            if(shortName.equals("ZC/CT") || shortName.equals("ZC/CK")){
                this.instrumentDataModel.getFormInstrument().setApplicant(this.instrumentDataModel.searchForApplicant("TE"));
            }
            else if(shortName.equals("ZC/CA")){
                this.instrumentDataModel.getFormInstrument().setApplicant(this.instrumentDataModel.searchForApplicant("TA"));
            }
            else if(shortName.equals("ZC/CM")){
                this.instrumentDataModel.getFormInstrument().setApplicant(this.instrumentDataModel.searchForApplicant("AM"));
            }
            else if(shortName.equals("TL")){
                this.instrumentDataModel.getFormInstrument().setApplicant(this.instrumentDataModel.searchForApplicant("SL"));
            }
            else if(shortName.equals("ZO/OP")){
                this.instrumentDataModel.getFormInstrument().setApplicant(this.instrumentDataModel.searchForApplicant("SP"));
            }
            else if(shortName.equals("ZO/OM")){
                this.instrumentDataModel.getFormInstrument().setApplicant(this.instrumentDataModel.searchForApplicant("SM"));
            }
            else if(shortName.equals("ZCh/ChM")){
                this.instrumentDataModel.getFormInstrument().setApplicant(this.instrumentDataModel.searchForApplicant("TM"));
            }
            this.applicantComboBox.setValue(this.instrumentDataModel.getFormInstrument().getApplicant().getShortName());
            System.out.println(this.instrumentDataModel.getFormInstrument().getApplicant().getFullName());
            firstLabel.setTextFill(Color.WHITE);
            secondLabel.setTextFill(Color.WHITE);
        }
    }
    private boolean isValidInstrumentData(InstrumentModel instrument){
        String errorMessage = "";
        if(instrument.getName()==null){
            errorMessage+="Nie wybrałeś nazwy urządzenia ! \n";
        }
        if (instrument.getType()==null){
            errorMessage+="Nie wybrałeś typu urządzenia ! \n";
        }
        if (instrument.getProducer()==null){
            errorMessage+="Nie wybrałeś producenta urządzenia ! \n";
        }
        if ((instrument.getSerialNumber().isEmpty())&&(instrument.getIdentificationNumber().isEmpty())) {
            errorMessage +=   "Przyrząd musi posiadać numer fabryczny lub numer identyfikacyjny ! \n";
        }
        if(instrument.getRange()==null){
            errorMessage += "Nie wybrałeś zakresu urządzenia ! \n";
        }
        if(instrument.getApplicant()==null){
            errorMessage += "Nie wybrałeś zleceniodawcy ! \n";
        }
        if (this.entryDatePicker.getValue() == null ) {
            errorMessage += "Nie wybrałeś prawidłowo daty dodania ! \n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nieprawidłowe dane");
            alert.setHeaderText("Proszę wprowadzić prawidłowe dane dla podanych niżej pól");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    private void createFormInstrument(){
        this.instrumentDataModel.getFormInstrument().setName(getValue(this.instrumentDataModel.getNameList(), this.nameComboBox.getValue()));
        this.instrumentDataModel.getFormInstrument().setType(getValue(this.instrumentDataModel.getTypeList(),this.typeComboBox.getValue()));
        this.instrumentDataModel.getFormInstrument().setProducer(getValue(this.instrumentDataModel.getProducerList(),this.producerComboBox.getValue()));
        this.instrumentDataModel.getFormInstrument().setSerialNumber(CommonTools.deleteWhiteSpaces(this.serialNumberTextField.getText()));
        this.instrumentDataModel.getFormInstrument().setIdentificationNumber(CommonTools.deleteWhiteSpaces(this.identificationNumberTextField.getText()));
        this.instrumentDataModel.getFormInstrument().setLength(CommonTools.deleteWhiteSpaces(this.lengthTextField.getText()));
        this.instrumentDataModel.getFormInstrument().setDiameter(CommonTools.deleteWhiteSpaces(this.diameterTextField.getText()));
        this.instrumentDataModel.getFormInstrument().setRange(getValue(this.instrumentDataModel.getRangeList(),this.rangeComboBox.getValue()));
    }
    public void disabelTextArea(){
        this.calibrationRemarks.setDisable(true);
    }
}
