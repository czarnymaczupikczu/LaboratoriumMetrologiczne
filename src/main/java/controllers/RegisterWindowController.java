package controllers;

import dataModels.RegisterDataModel;
import fxModels.RegisterFxModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.CommonTools;
import utils.FxmlTools;
import utils.database.CommonDao;

import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterWindowController {
    public RegisterWindowController() {
        System.out.println("Konstruktor klasy RegisterWindowController");
    }

    //Główny kontroler powiązany z kontrolerami poszczególnych okien
    private MainWindowController mainController;
    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }

    //Pola prywatne
    private final RegisterDataModel registerDataModel = new RegisterDataModel();
    public RegisterDataModel getRegisterDataModel() {
        return registerDataModel;
    }

    private static final String EDIT_DATE_WINDOW="/fxml/EditDateWindow.fxml";
    private static final String EDIT_CERTIFICATE_NUMBER_WINDOW="/fxml/EditCertificateNumberWindow.fxml";
    private static final String CALIBRATION_DATE="Data wzorcowania";
    private static final String TITLE_MESSAGE="Anulowanie wzorcowania";
    private static final String MESSAGE="Czy na pewno chcesz anulować wzorcowanie ?";

    @FXML private TextField searchTextField;

    @FXML private ComboBox<String> registerStateComboBox;
    @FXML private ComboBox<String> registerYearComboBox;
    //TableView
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
    @FXML private TableColumn<RegisterFxModel, String> calibrateUserColumn;
    @FXML private TableColumn<RegisterFxModel, String> certificateNumberColumn;
    @FXML private TableColumn<RegisterFxModel, String> documentKindColumn;
    @FXML private TableColumn<RegisterFxModel, String> agreementNumberColumn;
    @FXML private TableColumn<RegisterFxModel, String> stateColumn;
    //Labels
    @FXML private Label shortNameLabel;
    @FXML private Label fullNameLabel;
    @FXML private Label cityLabel;
    @FXML private Label streetLabel;
    @FXML private Label entryLabel;
    @FXML private Label spendLabel;
    @FXML private TextArea instrumentRemarksTextArea;
    @FXML private TextArea calibrationRemarksTextArea;

    public void init() {
        initializeComboBoxes();
        initializeTableView();
        bindingLabels();
        addFilter();
    }
    private void initializeTableView() {
        this.registerTableView.setItems(this.registerDataModel.getFilteredRegisterList());
        this.cardNumberColumn.setCellValueFactory(cellData -> cellData.getValue().cardNumberProperty());
        this.calibrationDateColumn.setCellValueFactory(cellData -> cellData.getValue().calibrationDateProperty());
        this.idRegisterColumn.setCellValueFactory(cellData -> cellData.getValue().idRegisterByYearProperty().asObject());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().getStorage().getInstrument().nameProperty());
        this.typeColumn.setCellValueFactory(cellData -> cellData.getValue().getStorage().getInstrument().typeProperty());
        this.producerColumn.setCellValueFactory(cellData -> cellData.getValue().getStorage().getInstrument().producerProperty());
        this.serialNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getStorage().getInstrument().serialNumberProperty());
        this.identificationNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getStorage().getInstrument().identificationNumberProperty());
        this.rangeColumn.setCellValueFactory(cellData -> cellData.getValue().getStorage().getInstrument().rangeProperty());
        this.lengthColumn.setCellValueFactory(cellData -> cellData.getValue().getStorage().getInstrument().lengthProperty());
        this.diameterColumn.setCellValueFactory(cellData -> cellData.getValue().getStorage().getInstrument().diameterProperty());
        this.applicantColumn.setCellValueFactory(cellData -> cellData.getValue().getStorage().getInstrument().getApplicant().shortNameProperty());
        this.calibrateUserColumn.setCellValueFactory(cellData -> cellData.getValue().calibrationUserProperty());
        this.certificateNumberColumn.setCellValueFactory(cellData -> cellData.getValue().certificateNumberProperty());
        this.documentKindColumn.setCellValueFactory(cellData -> cellData.getValue().documentKindProperty());
        this.agreementNumberColumn.setCellValueFactory(cellData -> cellData.getValue().agreementNumberProperty());
        this.stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        //this.registerTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.registerTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.registerDataModel.setCurrentRegisterElement(newValue);
                bindingLabels();
                System.out.println(this.registerDataModel.getRegisterList().indexOf(this.registerDataModel.getCurrentRegisterElement()));
                System.out.println(this.registerDataModel.getFilteredRegisterList().indexOf(this.registerDataModel.getCurrentRegisterElement()));
                /*if (this.registerTableView.getSelectionModel().getSelectedItems().size() < 2) { //Gdy jest multiple selection to zostaje ciągle ten sam obiekt
                    updateBindings(newValue);
                }
                this.registerDataModel.getRegisterSelectedItemsList().clear();
                this.registerDataModel.getRegisterSelectedItemsList().addAll(this.registerTableView.getSelectionModel().getSelectedItems());*/
            }
        });
        this.instrumentRemarksTextArea.setWrapText(true);
        this.instrumentRemarksTextArea.setDisable(true);
        this.instrumentRemarksTextArea.setStyle("-fx-opacity: 1.0;");
        this.calibrationRemarksTextArea.setWrapText(true);
        this.calibrationRemarksTextArea.setDisable(true);
        this.calibrationRemarksTextArea.setStyle("-fx-opacity: 1.0;");
    }
    private void bindingLabels() {
        this.shortNameLabel.textProperty().bind(this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().shortNameProperty());
        this.fullNameLabel.textProperty().bind(this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().fullNameProperty());
        this.cityLabel.textProperty().bind(Bindings.concat(this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().postCodeProperty(), " ", this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().cityProperty()));
        this.streetLabel.textProperty().bind(Bindings.concat(this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().streetProperty(), " ", this.registerDataModel.getCurrentRegisterElement().getStorage().getInstrument().getApplicant().numberProperty()));
        this.entryLabel.textProperty().bind(Bindings.concat(this.registerDataModel.getCurrentRegisterElement().getStorage().entryUserProperty(), " ", this.registerDataModel.getCurrentRegisterElement().getStorage().entryDateProperty()));
        if (registerDataModel.getCurrentRegisterElement().getStorage().getSpendUser()==null) {
            this.registerDataModel.getCurrentRegisterElement().getStorage().setSpendDate("");
            this.registerDataModel.getCurrentRegisterElement().getStorage().setSpendUser("");
        }
        this.spendLabel.textProperty().bind(Bindings.concat(this.registerDataModel.getCurrentRegisterElement().getStorage().spendUserProperty(), " ", this.registerDataModel.getCurrentRegisterElement().getStorage().spendDateProperty()));
        this.instrumentRemarksTextArea.textProperty().bind(this.registerDataModel.getCurrentRegisterElement().getStorage().instrumentRemarksProperty());
        this.calibrationRemarksTextArea.textProperty().bind(this.registerDataModel.getCurrentRegisterElement().getStorage().calibrationRemarksProperty());
    }
    private void initializeComboBoxes() {
        this.registerStateComboBox.getItems().addAll(mainController.getMainDataModel().getRegisterStateComboBoxList());
        this.registerStateComboBox.setValue(mainController.getMainDataModel().getRegisterStateComboBoxList().get(0));
        this.registerYearComboBox.getItems().addAll(mainController.getMainDataModel().getYearComboBoxList());
        this.registerYearComboBox.setValue(mainController.getMainDataModel().getYearComboBoxList().get(mainController.getMainDataModel().getYearComboBoxList().size() - 1));
    }
    private void addFilter() {
        searchTextField.textProperty().addListener((value, oldValue, newValue) -> registerDataModel.addFilterToObservableList(newValue));
    }
    @FXML
    void loadRegisterList() {
        System.out.println(registerStateComboBox.getValue() + " " + registerYearComboBox.getValue());
        registerDataModel.listInitialize(registerStateComboBox.getValue(), registerYearComboBox.getValue());

    }

    @FXML
    void cancelCalibration() {
        this.registerDataModel.initializeCurrentRegisterModel();
        if(CommonTools.display(TITLE_MESSAGE,MESSAGE)){
            this.registerDataModel.getCurrentRegisterModel().setState("OFF");
            CommonDao commonDao=new CommonDao();
            commonDao.createOrUpdate(this.registerDataModel.getCurrentRegisterModel());
        }
    }

    @FXML
    void editCalibrationDate() {
        loadEditDateWindow();
    }

    @FXML
    void editCertificateNumber() {
        EditCertificateNumberWindowController editCertificateNumberWindowController = FxmlTools.openVBoxWindow(EDIT_CERTIFICATE_NUMBER_WINDOW);
        if(editCertificateNumberWindowController !=null){
            editCertificateNumberWindowController.setRegisterWindowController(this);
            this.registerDataModel.initializeCurrentRegisterModel();
            editCertificateNumberWindowController.init();
        }
    }
    private void loadEditDateWindow(){
        EditDateWindowController editDateWindowController = FxmlTools.openVBoxWindow(EDIT_DATE_WINDOW);
        if(editDateWindowController !=null){
            editDateWindowController.setRegisterWindowController(this);
            editDateWindowController.setDateType(CALIBRATION_DATE);
            editDateWindowController.setEditDateWindowLabel(CALIBRATION_DATE);
            this.registerDataModel.initializeCurrentRegisterModel();
        }
    }
    @FXML
    private void exportToExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Arkusz1");
        Row row = spreadsheet.createRow(0);
        //Nazwy kolumn
        row.createCell(0).setCellValue("Lp. ");
        row.createCell(1).setCellValue("Nr karty");
        row.createCell(2).setCellValue("Data wzorcowania");
        row.createCell(3).setCellValue("Nazwa");
        row.createCell(4).setCellValue("Typ");
        row.createCell(5).setCellValue("Producent");
        row.createCell(6).setCellValue("Nr fabryczny");
        row.createCell(7).setCellValue("Nr identyfikacyjny");
        row.createCell(8).setCellValue("Zakres");
        row.createCell(9).setCellValue("Zleceniodawca");
        row.createCell(10).setCellValue("Wzorcujący");
        row.createCell(11).setCellValue("Nr Świadectwa/Protokołu");
        row.createCell(12).setCellValue("ŚW/PO");
        row.createCell(13).setCellValue("Umowa");
        row.createCell(14).setCellValue("Stan");

        int i = 0;
        for (RegisterFxModel registerElement : this.registerDataModel.getFilteredRegisterList()) {
            row = spreadsheet.createRow(i + 1);
            row.createCell(0).setCellValue(i+1);
            row.createCell(1).setCellValue(registerElement.getCardNumber());
            row.createCell(2).setCellValue(registerElement.getCalibrationDate());
            row.createCell(3).setCellValue(registerElement.getStorage().getInstrument().getName());
            row.createCell(4).setCellValue(registerElement.getStorage().getInstrument().getType());
            row.createCell(5).setCellValue(registerElement.getStorage().getInstrument().getProducer());
            row.createCell(6).setCellValue(registerElement.getStorage().getInstrument().getSerialNumber());
            row.createCell(7).setCellValue(registerElement.getStorage().getInstrument().getIdentificationNumber());
            row.createCell(8).setCellValue(registerElement.getStorage().getInstrument().getRange());
            row.createCell(9).setCellValue(registerElement.getStorage().getInstrument().getApplicant().getShortName());
            row.createCell(10).setCellValue(registerElement.getCalibrationUser());
            row.createCell(11).setCellValue(registerElement.certificateNumberProperty().get());
            row.createCell(12).setCellValue(registerElement.getDocumentKind());
            row.createCell(13).setCellValue(registerElement.getAgreementNumber());
            row.createCell(14).setCellValue(registerElement.getState());
            i++;
        }
        for (int j = 0; j < 14; j++) {
            spreadsheet.autoSizeColumn(j);
        }
        FileOutputStream fileOut = new FileOutputStream("Rejestr"+this.registerDataModel.getRegisterType()+".xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }
}
