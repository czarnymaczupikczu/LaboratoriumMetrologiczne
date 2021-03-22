package controllers;

import dataModels.ApplicantsDataModel;
import fxModels.ApplicantFxModel;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.Converter;

public class ApplicantsWindowController {
    public ApplicantsWindowController(){
        System.out.println("Konstruktor klasy ApplicantWindowController");
    }

    private MainWindowController mainController;
    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }

    private ApplicantsDataModel applicantsDataModel=new ApplicantsDataModel();
    public ApplicantsDataModel getApplicantsDataModel() {
        return applicantsDataModel;
    }

    private InstrumentWindowController instrumentWindowController;
    public void setInstrumentWindowController(InstrumentWindowController instrumentWindowController) {
        this.instrumentWindowController = instrumentWindowController;
    }

    @FXML private VBox applicantMainVBox;

    @FXML private TextField searchTextField;
    @FXML private Button loadApplicantListButton;
    @FXML private Separator separator1;
    @FXML private Button addApplicantButton;
    @FXML private Button editApplicantButton;
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
        this.separator2.setVisible(false);
        this.exportToExcelButton.setVisible(false);
        this.choseApplicantButton.setVisible(true);
    }

    @FXML
    void addApplicant() {

    }

    @FXML
    void choseApplicant() {
        instrumentWindowController.setApplicantComboBox(this.applicantsDataModel.getCurrentApplicantElement().getShortName());
        instrumentWindowController.getInstrumentDataModel().getFormInstrument().setApplicant(Converter.convertApplicantFxModelToApplicantModel(this.applicantsDataModel.getCurrentApplicantElement()));
        CommonTools.closePaneWindow(applicantMainVBox);
    }

    @FXML
    void editApplicant() {

    }

    @FXML
    void exportToExcel() {

    }

    @FXML
    void getApplicantList() {
        applicantsDataModel.listInitialize();
    }

    @FXML
    void mouseClicked(MouseEvent event) {
        if(event.getClickCount()==2 && (this.choseApplicantButton.visibleProperty().get()==true)){
            instrumentWindowController.setApplicantComboBox(this.applicantsDataModel.getCurrentApplicantElement().getShortName());
            instrumentWindowController.getInstrumentDataModel().getFormInstrument().setApplicant(Converter.convertApplicantFxModelToApplicantModel(this.applicantsDataModel.getCurrentApplicantElement()));
            CommonTools.closePaneWindow(applicantMainVBox);
        }
    }
    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera ApplicantWindowController ");
        initializeTableView();
        addFilter();
    }

}
