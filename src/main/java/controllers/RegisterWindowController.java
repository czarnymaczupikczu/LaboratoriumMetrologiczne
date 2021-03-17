package controllers;

import dataModels.RegisterDataModel;
import fxModels.RegisterFxModel;
import fxModels.StorageFxModel;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.control.skin.TableViewSkin;
import javafx.scene.layout.VBox;

public class RegisterWindowController {
    public RegisterWindowController(){
        System.out.println("Konstruktor klasy RegisterWindowController");
    }

    //Pola prywatne
    private RegisterDataModel registerDataModel=new RegisterDataModel();
    public RegisterDataModel getRegisterDataModel() {
        return registerDataModel;
    }

    //Główny kontroler powiązany z kontrolerami poszczególnych okien
    private MainWindowController mainController;
    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }

    @FXML private VBox registerMainVBox;

    @FXML private TextField searchTextField;

    @FXML private ComboBox<String> registerStateComboBox;
    @FXML private ComboBox<String> registerYearComboBox;

    @FXML private TableView<RegisterFxModel> registerTableView;
    @FXML private TableColumn<RegisterFxModel, Integer> idRegisterColumn;
    @FXML private TableColumn<RegisterFxModel, String> cardNumberColumn;
    @FXML private TableColumn<RegisterFxModel, String> calibrationDateColumn;
    @FXML private TableColumn<RegisterFxModel, String> nameColumn;
    @FXML private TableColumn<RegisterFxModel, String> typeColumn;
    @FXML private TableColumn<RegisterFxModel, String> producerColumn;
    @FXML private TableColumn<RegisterFxModel, String> serialNumberColumn;
    @FXML private TableColumn<RegisterFxModel, String> identificationNumberColumn;
    @FXML private TableColumn<RegisterFxModel, String> rangeColumn;
    @FXML private TableColumn<RegisterFxModel, String> lengthColumn;
    @FXML private TableColumn<RegisterFxModel, String> diameterColumn;
    @FXML private TableColumn<RegisterFxModel, String> applicantColumn;
    @FXML private TableColumn<RegisterFxModel, String> certificateNumberColumn;
    @FXML private TableColumn<RegisterFxModel, String> documentKindColumn;
    @FXML private TableColumn<RegisterFxModel, String> agreementNumberColumn;
    @FXML private TableColumn<RegisterFxModel, String> stateColumn;

    @FXML private Label shortNameLabel;
    @FXML private Label fullNameLabel;
    @FXML private Label cityLabel;
    @FXML private Label streetLabel;
    @FXML private Label entryLabel;
    @FXML private Label calibrationLabel;
    @FXML private Label spendLabel;
    @FXML private Label cardNumberLabel;
    @FXML private TextArea storageRemarksTextArea;

    public void init(){
        initializeComboBoxes();
        initializeTableView();
        bindingLabels();
        addFilter();
    }

    private void initializeTableView(){
        this.registerTableView.setItems(this.registerDataModel.getFilteredRegisterList());
        this.cardNumberColumn.setCellValueFactory(cellData->cellData.getValue().cardNumberProperty());
        this.calibrationDateColumn.setCellValueFactory(cellData->cellData.getValue().calibrationDateProperty());
        this.idRegisterColumn.setCellValueFactory(cellData->cellData.getValue().idRegisterByYearProperty().asObject());
        this.nameColumn.setCellValueFactory(cellData->cellData.getValue().getStorage().getInstrument().nameProperty());
        this.typeColumn.setCellValueFactory(cellData->cellData.getValue().getStorage().getInstrument().typeProperty());
        this.producerColumn.setCellValueFactory(cellData->cellData.getValue().getStorage().getInstrument().producerProperty());
        this.serialNumberColumn.setCellValueFactory(cellData->cellData.getValue().getStorage().getInstrument().serialNumberProperty());
        this.identificationNumberColumn.setCellValueFactory(cellData->cellData.getValue().getStorage().getInstrument().identificationNumberProperty());
        this.rangeColumn.setCellValueFactory(cellData->cellData.getValue().getStorage().getInstrument().rangeProperty());
        this.lengthColumn.setCellValueFactory(cellData->cellData.getValue().getStorage().getInstrument().lengthProperty());
        this.diameterColumn.setCellValueFactory(cellData->cellData.getValue().getStorage().getInstrument().diameterProperty());
        this.applicantColumn.setCellValueFactory(cellData->cellData.getValue().getStorage().getInstrument().getApplicant().shortNameProperty());
        this.certificateNumberColumn.setCellValueFactory(cellData->cellData.getValue().certificateNumberProperty());
        this.documentKindColumn.setCellValueFactory(cellData->cellData.getValue().documentKindProperty());
        this.agreementNumberColumn.setCellValueFactory(cellData->cellData.getValue().agreementNumberProperty());
        this.stateColumn.setCellValueFactory(cellData->cellData.getValue().stateProperty());

        this.registerTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.registerTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
                if (this.registerTableView.getSelectionModel().getSelectedItems().size() < 2){ //Gdy jest multiple selection to zostaje ciągle ten sam obiekt
                   updateBindings(newValue);
                }
                this.registerDataModel.getRegisterSelectedItemsList().clear();
                this.registerDataModel.getRegisterSelectedItemsList().addAll(this.registerTableView.getSelectionModel().getSelectedItems());
            }
        });
    }

    private void bindingLabels(){
        this.shortNameLabel.textProperty().bind(this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().shortNameProperty());
        this.fullNameLabel.textProperty().bind(this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().fullNameProperty());
        this.cityLabel.textProperty().bind(Bindings.concat(this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().postCodeProperty()," ",this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().cityProperty()));
        this.streetLabel.textProperty().bind(Bindings.concat(this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().streetProperty()," ",this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().numberProperty()));
        this.entryLabel.textProperty().bind(Bindings.concat(this.registerDataModel.getCurrentRegisterElement().getStorage().entryUserProperty()," ",this.registerDataModel.getCurrentRegisterElement().getStorage().entryDateProperty()));
        this.calibrationLabel.textProperty().bind(Bindings.concat(this.registerDataModel.getCurrentRegisterElement().calibrationUserProperty()," ",this.registerDataModel.getCurrentRegisterElement().calibrationDateProperty()));
        this.spendLabel.textProperty().bind(Bindings.concat(this.registerDataModel.getCurrentRegisterElement().getStorage().spendUserProperty()," ",this.registerDataModel.getCurrentRegisterElement().getStorage().spendDateProperty()));
        this.cardNumberLabel.textProperty().bind(this.registerDataModel.getCurrentRegisterElement().cardNumberProperty());
        this.storageRemarksTextArea.textProperty().bind(this.registerDataModel.getCurrentRegisterElement().getStorage().storageRemarksProperty());
    }
    private void updateBindings(RegisterFxModel registerElement){
        this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().setShortName(registerElement.getStorage().getInstrument().getApplicant().getShortName());
        this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().setFullName(registerElement.getStorage().getInstrument().getApplicant().getFullName());
        this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().setPostCode(registerElement.getStorage().getInstrument().getApplicant().getPostCode());
        this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().setCity(registerElement.getStorage().getInstrument().getApplicant().getCity());
        this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().setStreet(registerElement.getStorage().getInstrument().getApplicant().getStreet());
        this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().setNumber(registerElement.getStorage().getInstrument().getApplicant().getNumber());
        this.registerDataModel.getCurrentRegisterElement().getStorage().setEntryDate(registerElement.getStorage().getEntryDate());
        this.registerDataModel.getCurrentRegisterElement().getStorage().setEntryUser(registerElement.getStorage().getEntryUser());
        this.registerDataModel.getCurrentRegisterElement().setCalibrationDate(registerElement.getCalibrationDate());
        this.registerDataModel.getCurrentRegisterElement().setCalibrationUser(registerElement.getCalibrationUser());
        this.registerDataModel.getCurrentRegisterElement().setCardNumber(registerElement.getCardNumber());
        if(registerElement.getStorage().spendUserProperty().isNull().getValue()){
            this.registerDataModel.getCurrentRegisterElement().getStorage().setSpendDate("");
            this.registerDataModel.getCurrentRegisterElement().getStorage().setSpendUser("");
        }
        else{
            this.registerDataModel.getCurrentRegisterElement().getStorage().setSpendDate(registerElement.getStorage().getSpendDate());
            this.registerDataModel.getCurrentRegisterElement().getStorage().setSpendUser(registerElement.getStorage().getSpendUser());
        }
        this.registerDataModel.getCurrentRegisterElement().getStorage().setStorageRemarks(registerElement.getStorage().getStorageRemarks());

    }
    private void initializeComboBoxes(){
        this.registerStateComboBox.getItems().addAll(mainController.getMainDataModel().getRegisterStateComboBoxList());
        this.registerStateComboBox.setValue(mainController.getMainDataModel().getRegisterStateComboBoxList().get(0));
        this.registerYearComboBox.getItems().addAll(mainController.getMainDataModel().getYearComboBoxList());
        this.registerYearComboBox.setValue(mainController.getMainDataModel().getYearComboBoxList().get(mainController.getMainDataModel().getYearComboBoxList().size()-1));
    }


    private void addFilter(){
        searchTextField.textProperty().addListener((value,oldValue, newValue) ->{
            registerDataModel.addFilterToObservableList(newValue);
        } );
    }


    @FXML
    void loadRegisterList() {
        System.out.println(registerStateComboBox.getValue()+" "+registerYearComboBox.getValue());
        registerDataModel.listInitialize(registerStateComboBox.getValue(),registerYearComboBox.getValue());

    }



    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera RegisterWindowController");
    }
}
