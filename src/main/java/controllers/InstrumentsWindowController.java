package controllers;

import dataModels.InstrumentDataModel;
import dataModels.InstrumentsDataModel;
import dataModels.ShortRegisterDataModel;
import fxModels.InstrumentFxModel;
import fxModels.ShortRegisterFxModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class InstrumentsWindowController {
    public InstrumentsWindowController(){
        System.out.println("Konstruktor klasy InstrumentsWindowController");
    }

    //Główny kontroler powiązany z kontrolerami poszczególnych okien
    private MainWindowController mainController;
    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }

    private InstrumentsDataModel instrumentsDataModel=new InstrumentsDataModel();
    private ShortRegisterDataModel shortRegisterDataModel=new ShortRegisterDataModel();

    @FXML private TextField searchTextField;
    @FXML private Button loadInstrumentListButton;
    @FXML private Button exportToExcelButton;
    //TableView z przyrządami
    @FXML private TableView<InstrumentFxModel> instrumentTableView;
    @FXML private TableColumn<InstrumentFxModel, Integer> idInstrumentColumn;
    @FXML private TableColumn<InstrumentFxModel, String> nameColumn;
    @FXML private TableColumn<InstrumentFxModel, String> typeColumn;
    @FXML private TableColumn<InstrumentFxModel, String> producerColumn;
    @FXML private TableColumn<InstrumentFxModel, String> serialNumberColumn;
    @FXML private TableColumn<InstrumentFxModel, String> identificationNumberColumn;
    @FXML private TableColumn<InstrumentFxModel, String> rangeColumn;
    @FXML private TableColumn<InstrumentFxModel, String> lengthColumn;
    @FXML private TableColumn<InstrumentFxModel, String> diameterColumn;
    @FXML private TableColumn<InstrumentFxModel, String> applicantColumn;
    //TableView z wzorcowaniami
    @FXML private TableView<ShortRegisterFxModel> registerTableView;
    @FXML private TableColumn<ShortRegisterFxModel, Integer> idColumn;
    @FXML private TableColumn<ShortRegisterFxModel, String> cardNumberColumn;
    @FXML private TableColumn<ShortRegisterFxModel, String> calibrationDateColumn;
    @FXML private TableColumn<ShortRegisterFxModel, String> calibrateUserColumn;
    @FXML private TableColumn<ShortRegisterFxModel, String> certificateNumberColumn;
    @FXML private TableColumn<ShortRegisterFxModel, String> documentKindColumn;
    @FXML private TableColumn<ShortRegisterFxModel, String> agreementNumberColumn;
    @FXML private TableColumn<ShortRegisterFxModel, String> entryDateColumn;
    @FXML private TableColumn<ShortRegisterFxModel, String> entryUserColumn;
    @FXML private TableColumn<ShortRegisterFxModel, String> spendDateColumn;
    @FXML private TableColumn<ShortRegisterFxModel, String> spendUserColumn;
    @FXML private TableColumn<ShortRegisterFxModel, String> shortRegisterApplicantColumn;
    //Applicant Labels
    @FXML private Label shortNameLabel;
    @FXML private Label fullNameLabel;
    @FXML private Label cityLabel;
    @FXML private Label streetLabel;
    @FXML private TextArea instrumentRemarksTextArea;
    @FXML private TextArea calibrationRemarksTextArea;


    public void init(){
        initializeInstrumentsTableView();
        initializeRegisterTableView();
        addFilter();
        bindingLabels();
    }

    private void initializeInstrumentsTableView(){
        this.instrumentTableView.setItems(this.instrumentsDataModel.getFilteredInstrumentsList());
        this.idInstrumentColumn.setCellValueFactory(cellData->cellData.getValue().idInstrumentProperty().asObject());
        this.nameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
        this.typeColumn.setCellValueFactory(cellData->cellData.getValue().typeProperty());
        this.producerColumn.setCellValueFactory(cellData->cellData.getValue().producerProperty());
        this.serialNumberColumn.setCellValueFactory(cellData->cellData.getValue().serialNumberProperty());
        this.identificationNumberColumn.setCellValueFactory(cellData->cellData.getValue().identificationNumberProperty());
        this.rangeColumn.setCellValueFactory(cellData->cellData.getValue().rangeProperty());
        this.lengthColumn.setCellValueFactory(cellData->cellData.getValue().lengthProperty());
        this.diameterColumn.setCellValueFactory(cellData->cellData.getValue().diameterProperty());
        this.applicantColumn.setCellValueFactory(cellData->cellData.getValue().getApplicant().shortNameProperty());
        this.instrumentTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.instrumentsDataModel.setCurrentInstrument(newValue);
                this.shortRegisterDataModel.listInitialize(newValue.getConcatIdInstrument());

            }
        });
    }
    private void initializeRegisterTableView(){
        this.registerTableView.setItems(this.shortRegisterDataModel.getRegisterList());
        this.idColumn.setCellValueFactory(cellData->cellData.getValue().idProperty().asObject());
        this.cardNumberColumn.setCellValueFactory(cellData->cellData.getValue().cardNumberProperty());
        this.entryDateColumn.setCellValueFactory(cellData->cellData.getValue().entryDateProperty());
        this.entryUserColumn.setCellValueFactory(cellData->cellData.getValue().entryUserProperty());
        this.calibrationDateColumn.setCellValueFactory(cellData->cellData.getValue().calibrationDateProperty());
        this.calibrateUserColumn.setCellValueFactory(cellData->cellData.getValue().calibrateUserProperty());
        this.identificationNumberColumn.setCellValueFactory(cellData->cellData.getValue().identificationNumberProperty());
        this.spendDateColumn.setCellValueFactory(cellData->cellData.getValue().spendDateProperty());
        this.spendUserColumn.setCellValueFactory(cellData->cellData.getValue().spendUserProperty());
        this.certificateNumberColumn.setCellValueFactory(cellData->cellData.getValue().certificateNumberProperty());
        this.documentKindColumn.setCellValueFactory(cellData->cellData.getValue().documentKindProperty());
        this.agreementNumberColumn.setCellValueFactory(cellData->cellData.getValue().agreementNumberProperty());
        this.shortRegisterApplicantColumn.setCellValueFactory(cellData->cellData.getValue().getApplicantFxModel().shortNameProperty());
        this.registerTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // this.shortRegisterDataModel.listInitialize();
                this.shortRegisterDataModel.setCurrentRegisterElement(newValue);
                bindingLabels();
            }
        });
    }

    private void bindingLabels(){
        this.shortNameLabel.textProperty().bind(this.shortRegisterDataModel.getCurrentRegisterElement().getApplicantFxModel().shortNameProperty());
        this.fullNameLabel.textProperty().bind(this.shortRegisterDataModel.getCurrentRegisterElement().getApplicantFxModel().fullNameProperty());
        this.cityLabel.textProperty().bind(Bindings.concat(this.shortRegisterDataModel.getCurrentRegisterElement().getApplicantFxModel().postCodeProperty()," ",this.shortRegisterDataModel.getCurrentRegisterElement().getApplicantFxModel().cityProperty()));
        this.streetLabel.textProperty().bind(Bindings.concat(this.shortRegisterDataModel.getCurrentRegisterElement().getApplicantFxModel().streetProperty()," ",this.shortRegisterDataModel.getCurrentRegisterElement().getApplicantFxModel().numberProperty()));
        this.instrumentRemarksTextArea.textProperty().bind(this.shortRegisterDataModel.getCurrentRegisterElement().instrumentRemarksProperty());
        this.calibrationRemarksTextArea.textProperty().bind(this.shortRegisterDataModel.getCurrentRegisterElement().calibrationRemarksProperty());
    }
    private void addFilter() {
        searchTextField.textProperty().addListener((value, oldValue, newValue) -> {
            instrumentsDataModel.addFilterToObservableList(newValue);
        });
    }






    @FXML
    void editClient() {

    }

    @FXML
    private void exportToExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Arkusz1");
        Row row = spreadsheet.createRow(0);
        //Nazwy kolumn
        row.createCell(0).setCellValue("Lp. ");
        row.createCell(1).setCellValue("Nazwa");
        row.createCell(2).setCellValue("Typ");
        row.createCell(3).setCellValue("Producent");
        row.createCell(4).setCellValue("Nr fabryczny");
        row.createCell(5).setCellValue("Nr identyfikacyjny");
        row.createCell(6).setCellValue("Zakres pomiarowy");
        row.createCell(7).setCellValue("Długość");
        row.createCell(8).setCellValue("Średnica");
        row.createCell(9).setCellValue("Zleceniodawca");
        int i = 0;
        for (InstrumentFxModel instrumentElement : this.instrumentsDataModel.getFilteredInstrumentsList()) {
            row = spreadsheet.createRow(i + 1);
            row.createCell(0).setCellValue(i+1);
            row.createCell(1).setCellValue(instrumentElement.getName());
            row.createCell(2).setCellValue(instrumentElement.getType());
            row.createCell(3).setCellValue(instrumentElement.getProducer());
            row.createCell(4).setCellValue(instrumentElement.getSerialNumber());
            row.createCell(5).setCellValue(instrumentElement.getIdentificationNumber());
            row.createCell(6).setCellValue(instrumentElement.getRange());
            row.createCell(7).setCellValue(instrumentElement.getLength());
            row.createCell(8).setCellValue(instrumentElement.getDiameter());
            row.createCell(9).setCellValue(instrumentElement.getApplicant().getShortName());
            i++;
        }
        for (int j = 0; j < 10; j++) {
            spreadsheet.autoSizeColumn(j);
        }
        FileOutputStream fileOut = new FileOutputStream("Przyrządy.xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

    @FXML
    void getInstrumentList() {
        this.instrumentsDataModel.listInitialize();
    }
}
