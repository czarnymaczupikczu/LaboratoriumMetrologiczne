package controllers;

import dataModels.InstrumentDataModel;
import dbModels.instrument.NameModel;
import dbModels.instrument.ProducerModel;
import dbModels.instrument.RangeModel;
import dbModels.instrument.TypeModel;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

import static dbModels.instrument.NameModel.INSTRUMENT_NAME;
import static dbModels.instrument.ProducerModel.PRODUCER_NAME;
import static dbModels.instrument.TypeModel.TYPE_NAME;
import static dbModels.instrument.RangeModel.RANGE_NAME;

public class InstrumentWindowController {

    //Deklaracja stałych ze ścieżkami do widoków fxml
    private static final String NEW_INSTRUMENT_DATA_WINDOW = "/fxml/NewInstrumentDataWindow.fxml";
    private static final String NEW_INSTRUMENT_RANGE_WINDOW="/fxml/NewInstrumentRangeWindow.fxml";
    private static final String NEW_NAME="Dodawanie nowej nazwy przyrządu";
    private static final String NEW_TYPE="Dodawanie nowego typu przyrządu";
    private static final String NEW_PRODUCER="Dodawanie nowego producenta przyrządu";
    private static final String NEW_RANGE="Dodawanie nowego zakresu przyrządu";


    public InstrumentWindowController(){
        System.out.println("Konstruktor klasy InstrumentWindowController");
    }
    private NewInstrumentDataWindowController newInstrumentDataWindowController;

    @FXML
    private ComboBox<String> nameComboBox;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private ComboBox<String> producerComboBox;

    @FXML
    private ComboBox<String> rangeComboBox;

    @FXML
    private ComboBox<String> applicantComboBox;

    @FXML
    private DatePicker entryDatePicker;





    private InstrumentDataModel instrumentDataModel= new InstrumentDataModel();
    public InstrumentDataModel getInstrumentDataModel() {
        return instrumentDataModel;
    }

    @FXML
    public void initialize() throws SQLException {
        System.out.println("Metoda initialize kontrolera InstrumentWindowController ");
        instrumentDataModel.init();
        initializeComboBox();
        bindComboBox();
    }

    private void initializeComboBox(){
        initComboBox(nameComboBox,instrumentDataModel.getFilteredNames());
        initComboBox(typeComboBox,instrumentDataModel.getFilteredTypes());
        initComboBox(producerComboBox,instrumentDataModel.getFilteredProducers());
        initComboBox(rangeComboBox,instrumentDataModel.getFilteredRange());
    }
    private void bindComboBox(){
        typeComboBox.disableProperty().bind(nameComboBox.valueProperty().isNull());
        producerComboBox.disableProperty().bind(typeComboBox.valueProperty().isNull());
        rangeComboBox.disableProperty().bind(producerComboBox.valueProperty().isNull());
        applicantComboBox.disableProperty().bind(rangeComboBox.valueProperty().isNull());


    }
    @FXML
    void saveOnAction() {
        System.out.println(instrumentDataModel.getDataModel(nameComboBox.getValue(),instrumentDataModel.getNameList()));
        System.out.println(instrumentDataModel.getNameList().size());
    }
    @FXML
    void cancelOnAction() {

    }
    @FXML
    void addNewNameOnAction() {
        newInstrumentDataWindowController=loadVBoxWindow(NEW_INSTRUMENT_DATA_WINDOW);
        newInstrumentDataWindowController.setInstrumentWindowController(this);
        newInstrumentDataWindowController.init(INSTRUMENT_NAME, NameModel.class,NEW_NAME);
    }
    @FXML
    void addNewProducerOnAction() {
        newInstrumentDataWindowController=loadVBoxWindow(NEW_INSTRUMENT_DATA_WINDOW);
        newInstrumentDataWindowController.setInstrumentWindowController(this);
        newInstrumentDataWindowController.init(PRODUCER_NAME, ProducerModel.class,NEW_PRODUCER);
    }
    @FXML
    void addNewRangeOnAction() {
        newInstrumentDataWindowController=loadVBoxWindow(NEW_INSTRUMENT_RANGE_WINDOW);
        newInstrumentDataWindowController.setInstrumentWindowController(this);
        newInstrumentDataWindowController.init(RANGE_NAME, RangeModel.class,NEW_RANGE);
    }
    @FXML
    void addNewTypeOnAction() {
        newInstrumentDataWindowController=loadVBoxWindow(NEW_INSTRUMENT_DATA_WINDOW);
        newInstrumentDataWindowController.setInstrumentWindowController(this);
        newInstrumentDataWindowController.init(TYPE_NAME, TypeModel.class,NEW_TYPE);
    }
    @FXML
    void checkByIdentificationNumberOnAction() {

    }
    @FXML
    void checkBySerialNumberOnAction() {

    }
    @FXML
    void todayOnAction() {

    }
    private <T> T loadVBoxWindow(String resource){
        T instrumentData;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            VBox vBox = loader.load();
            instrumentData=loader.getController();
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(vBox);
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
                filteredList.setPredicate(item -> {
                    if (item.toUpperCase().startsWith(newValue.toUpperCase())) {
                        return true;
                    } else {
                        return false;
                    }
                });
            }
        });
        comboBox.setItems(filteredList);
    }



}
