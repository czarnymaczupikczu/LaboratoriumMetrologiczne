package controllers;

import dataModels.ApplicantsDataModel;
import fxModels.ApplicantFxModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.CommonTools;
import utils.Converter;
import utils.FxmlTools;

import java.io.FileOutputStream;
import java.io.IOException;

public class ApplicantsWindowController {
    public ApplicantsWindowController(){
        System.out.println("Konstruktor klasy ApplicantWindowController");
    }

    private MainWindowController mainController;
    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }

    private NewInstrumentWindowController newInstrumentWindowController;
    public void setInstrumentWindowController(NewInstrumentWindowController newInstrumentWindowController) {
        this.newInstrumentWindowController = newInstrumentWindowController;
    }
    private EditInstrumentWindowController editInstrumentWindowController;
    public void setEditInstrumentWindowController(EditInstrumentWindowController editInstrumentWindowController) {
        this.editInstrumentWindowController = editInstrumentWindowController;
    }

    private NewApplicantWindowController newApplicantWindowController;

    private ApplicantsDataModel applicantsDataModel=new ApplicantsDataModel();
    public ApplicantsDataModel getApplicantsDataModel() {
        return applicantsDataModel;
    }

    private final String NEW_APPLICANTS_WINDOW="/fxml/NewApplicantWindow.fxml";
    private final String EDIT_APPLICANT_LABEL="Edycja zleceniodawcy";
    private final String DELETE_APPLICANT_LABEL="Usuwanie zleceniodawcy";

    @FXML private VBox applicantMainVBox;
    @FXML private TextField searchTextField;
    @FXML private Button loadApplicantListButton;
    @FXML private Separator separator1;
    @FXML private Button addApplicantButton;
    @FXML private Button editApplicantButton;
    @FXML private Button deleteApplicantButton;
    @FXML private Separator separator2;
    @FXML private Button exportToExcelButton;

    @FXML private TableView<ApplicantFxModel> applicantTableView;
    @FXML private TableColumn<ApplicantFxModel, Integer> idApplicantColumn;
    @FXML private TableColumn<ApplicantFxModel, String> shortNameColumn;
    @FXML private TableColumn<ApplicantFxModel, String> fullNameColumn;
    @FXML private TableColumn<ApplicantFxModel, String> postCodeColumn;
    @FXML private TableColumn<ApplicantFxModel, String> cityColumn;
    @FXML private TableColumn<ApplicantFxModel, String> streetColumn;
    @FXML private TableColumn<ApplicantFxModel, String> numberColumn;
    @FXML private TableColumn<ApplicantFxModel, String> statusColumn;

    @FXML private Button choseApplicantButton;


    private void initializeTableView(){
        this.applicantTableView.setItems(this.applicantsDataModel.getFilteredApplicantList());
        this.idApplicantColumn.setCellValueFactory(cellData->cellData.getValue().idApplicantProperty().asObject());
        this.shortNameColumn.setCellValueFactory(cellData->cellData.getValue().shortNameProperty());
        this.fullNameColumn.setCellValueFactory(cellData->cellData.getValue().fullNameProperty());
        this.postCodeColumn.setCellValueFactory(cellData->cellData.getValue().postCodeProperty());
        this.cityColumn.setCellValueFactory(cellData->cellData.getValue().cityProperty());
        this.streetColumn.setCellValueFactory(cellData->cellData.getValue().streetProperty());
        this.numberColumn.setCellValueFactory(cellData->cellData.getValue().numberProperty());
        this.statusColumn.setCellValueFactory(cellData->cellData.getValue().statusProperty());
        this.applicantTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
                updateBindings(newValue);
            }
        });
        bindingSizeProperty();
        this.choseApplicantButton.disableProperty().bind(this.applicantsDataModel.getCurrentApplicantElement().shortNameProperty().isEmpty());
    }
    private void bindingSizeProperty(){
        this.applicantTableView.prefHeightProperty().bind(applicantMainVBox.heightProperty().multiply(0.85));
    }
    private void addFilter(){
        searchTextField.textProperty().addListener((value,oldValue, newValue) ->{
            applicantsDataModel.addFilterToObservableList(newValue);
        } );
    }
    private void updateBindings(ApplicantFxModel applicant){
        this.applicantsDataModel.getCurrentApplicantElement().setIdApplicant(applicant.getIdApplicant());
        this.applicantsDataModel.getCurrentApplicantElement().setShortName(applicant.getShortName());
        this.applicantsDataModel.getCurrentApplicantElement().setFullName(applicant.getFullName());
        this.applicantsDataModel.getCurrentApplicantElement().setPostCode(applicant.getPostCode());
        this.applicantsDataModel.getCurrentApplicantElement().setCity(applicant.getCity());
        this.applicantsDataModel.getCurrentApplicantElement().setStreet(applicant.getStreet());
        this.applicantsDataModel.getCurrentApplicantElement().setNumber(applicant.getNumber());
        this.applicantsDataModel.getCurrentApplicantElement().setStatus(applicant.getStatus());
    }
    public void hideButton(){
        this.loadApplicantListButton.setVisible(false);
        this.separator1.setVisible(false);
        this.addApplicantButton.setVisible(false);
        this.editApplicantButton.setVisible(false);
        this.deleteApplicantButton.setVisible(false);
        this.separator2.setVisible(false);
        this.exportToExcelButton.setVisible(false);
        this.choseApplicantButton.setVisible(true);
    }

    @FXML
    void addApplicant() {
        this.newApplicantWindowController= FxmlTools.openVBoxWindow(NEW_APPLICANTS_WINDOW);
        this.newApplicantWindowController.setApplicantsWindowController(this);
    }

    @FXML
    void choseApplicant() {
        if(this.newApplicantWindowController!=null) {
            this.newInstrumentWindowController.setApplicantComboBox(this.applicantsDataModel.getCurrentApplicantElement().getShortName());
            this.newInstrumentWindowController.getInstrumentDataModel().getFormInstrument().setApplicant(Converter.convertApplicantFxModelToApplicantModel(this.applicantsDataModel.getCurrentApplicantElement()));
        }
        else if(this.editInstrumentWindowController!=null){
            this.editInstrumentWindowController.setApplicantComboBox(this.applicantsDataModel.getCurrentApplicantElement().getShortName());
            this.editInstrumentWindowController.getInstrumentDataModel().getFormInstrument().setApplicant(Converter.convertApplicantFxModelToApplicantModel(this.applicantsDataModel.getCurrentApplicantElement()));
        }
        CommonTools.closePaneWindow(applicantMainVBox);
    }

    @FXML
    void editApplicant() {
        if(!this.applicantsDataModel.getCurrentApplicantElement().getCity().isEmpty()) {
            this.newApplicantWindowController = FxmlTools.openVBoxWindow(NEW_APPLICANTS_WINDOW);
            this.newApplicantWindowController.setApplicantsWindowController(this);
            this.newApplicantWindowController.setApplicantToForm(this.applicantsDataModel.getCurrentApplicantElement());
            this.newApplicantWindowController.setSelectedApplicantId(this.applicantsDataModel.getCurrentApplicantElement().getIdApplicant());
            this.newApplicantWindowController.setApplicantLabel(EDIT_APPLICANT_LABEL);
        }
    }
    @FXML
    void deleteApplicant(){
        if(!this.applicantsDataModel.getCurrentApplicantElement().getCity().isEmpty()) {
            this.newApplicantWindowController = FxmlTools.openVBoxWindow(NEW_APPLICANTS_WINDOW);
            this.newApplicantWindowController.setApplicantsWindowController(this);
            this.newApplicantWindowController.setApplicantToForm(this.applicantsDataModel.getCurrentApplicantElement());
            this.newApplicantWindowController.setSelectedApplicantId(this.applicantsDataModel.getCurrentApplicantElement().getIdApplicant());
            this.newApplicantWindowController.setApplicantLabel(DELETE_APPLICANT_LABEL);
            this.newApplicantWindowController.setFunction("delete");
        }
    }
    @FXML
    void exportToExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Arkusz1");
        Row row = spreadsheet.createRow(0);
        //Nazwy kolumn
        row.createCell(0).setCellValue("Lp. ");
        row.createCell(1).setCellValue("Skrót");
        row.createCell(2).setCellValue("Pełna nazwa");
        row.createCell(3).setCellValue("Kod pocztowy");
        row.createCell(4).setCellValue("Miejscowość");
        row.createCell(5).setCellValue("Ulica");
        row.createCell(6).setCellValue("Nr domu");

        int i = 0;
        for (ApplicantFxModel applicant : this.applicantsDataModel.getFilteredApplicantList()) {
            row = spreadsheet.createRow(i + 1);
            row.createCell(0).setCellValue(i+1);
            row.createCell(1).setCellValue(applicant.getShortName());
            row.createCell(2).setCellValue(applicant.getFullName());
            row.createCell(3).setCellValue(applicant.getPostCode());
            row.createCell(4).setCellValue(applicant.getCity());
            row.createCell(5).setCellValue(applicant.getStreet());
            row.createCell(6).setCellValue(applicant.getNumber());
            i++;
        }
        for (int j = 0; j < 8; j++) {
            spreadsheet.autoSizeColumn(j);
        }
        FileOutputStream fileOut = new FileOutputStream("Zleceniodawcy.xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

    @FXML
    void getApplicantList() {
        applicantsDataModel.listInitialize();
    }

    @FXML
    void mouseClicked(MouseEvent event) {
        if(event.getClickCount()==2 && (this.choseApplicantButton.visibleProperty().get()==true)){
            choseApplicant();
        }
    }
    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera ApplicantWindowController ");
        initializeTableView();
        addFilter();
    }

}
