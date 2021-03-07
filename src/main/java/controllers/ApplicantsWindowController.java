package controllers;

import dataModels.ApplicantsDataModel;
import fxModels.ApplicantFxModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

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

    @FXML private VBox applicantMainVBox;

    @FXML private TextField searchTextField;

    @FXML private Button addApplicantButton;
    @FXML private Button editApplicantButton;

    @FXML private Button loadApplicantListButton;
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
                this.applicantsDataModel.setCurrentApplicantElement(newValue);
            }
        });
        bindingSizeProperty();
    }
    private void bindingSizeProperty(){
        this.applicantTableView.prefHeightProperty().bind(applicantMainVBox.heightProperty().multiply(0.8));
    }
    private void addFilter(){
        searchTextField.textProperty().addListener((value,oldValue, newValue) ->{
            applicantsDataModel.addFilterToObservableList(newValue);
        } );
    }


    @FXML
    void addApplicant() {

    }

    @FXML
    void choseApplicant() {

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
    public void initialize(){
        System.out.println("Metoda initialize kontrolera ApplicantWindowController ");
        initializeTableView();
        addFilter();
    }

}
