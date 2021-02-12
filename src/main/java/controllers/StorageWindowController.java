package controllers;

import dataModels.StorageDataModel;
import fxModels.InstrumentFxModel;
import fxModels.StorageFxModel;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.sql.SQLException;


public class StorageWindowController {
    public StorageWindowController(){
        System.out.println("Konstruktor klasy StorehouseWindowController");
    }

    //ComboBox
    @FXML
    private ComboBox<String> storageStateComboBox;
    @FXML
    private ComboBox<String> storageYearComboBox;

    @FXML
    private VBox storehouseMainVBox;
    //TableView
    @FXML
    private TableView<StorageFxModel> storageTableView;
    @FXML
    private TableColumn<StorageFxModel, Integer> idStorageColumn;
    @FXML
    private TableColumn<StorageFxModel, String> nameColumn;
    @FXML
    private TableColumn<StorageFxModel, String> typeColumn;
    @FXML
    private TableColumn<StorageFxModel, String> producerColumn;
    @FXML
    private TableColumn<StorageFxModel, String> serialNumberColumn;
    @FXML
    private TableColumn<StorageFxModel, String> identificationNumberColumn;
    @FXML
    private TableColumn<StorageFxModel, String> rangeColumn;
    @FXML
    private TableColumn<StorageFxModel,String> lengthColumn;
    @FXML
    private TableColumn<StorageFxModel,String> diameterColumn;
    @FXML
    private TableColumn<StorageFxModel, String> applicantColumn;
    @FXML
    private TableColumn<StorageFxModel, String> entryDateColumn;
    @FXML
    private TableColumn<StorageFxModel, String> calibrationDateColumn;
    @FXML
    private TableColumn<StorageFxModel, String> spendDateColumn;
    //Labels
    @FXML
    private Label shortNameLabel;
    @FXML
    private Label fullNameLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label entryLabel;
    @FXML
    private Label calibrationLabel;
    @FXML
    private Label spendLabel;
    @FXML
    private Label cardNumberLabel;
    @FXML
    private TextArea storageRemarksTextArea;
    @FXML
    private TextField searchTextField;

    private StorageDataModel storageDataModel=new StorageDataModel();

    @FXML
    public void initialize() throws SQLException {
        System.out.println("Metoda initialize kontrolera StorehouseWindowController ");
        this.storageStateComboBox.getItems().addAll("Wszystkie","W magazynie");
        this.storageStateComboBox.setValue("Wszystkie");
        this.storageYearComboBox.getItems().addAll("2021","2020","2019","2018");
        this.storageYearComboBox.setValue("2021");
        storageDataModel.listInitialize();
        initializeTableView();

    }


    private void initializeTableView(){
        this.storageTableView.setItems(this.storageDataModel.getStorageList());
        this.idStorageColumn.setCellValueFactory(cellData->cellData.getValue().idStorageProperty().asObject());
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

       // this.nameLabel.textProperty().bind(this.storehouseElement.getCurrentStorehouse1().clientProperty());


/*

        this.storehouseTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
                if (this.storehouseTableView.getSelectionModel().getSelectedItems().size() < 2){ //Gdy jest multiple selection to zostaje ciągle ten sam obiekt
                    this.storehouseElement.getCurrentStorehouse().setClient(this.storehouseTableView.getSelectionModel().getSelectedItems().get(0).getClient());
                    this.nameLabel.textProperty().bind(this.storehouseElement.getCurrentStorehouse().clientProperty());
                }
                this.storehouseElement.getStorehouseSelectedItemsList().clear();
                this.storehouseElement.getStorehouseSelectedItemsList().addAll(this.storehouseTableView.getSelectionModel().getSelectedItems());

            }
        });


        this.storehouseTableView.prefHeightProperty().bind(storehouseMainVBox.heightProperty().multiply(0.7));*/
    }

    @FXML
    void showSelectedItems() {

    }
}
