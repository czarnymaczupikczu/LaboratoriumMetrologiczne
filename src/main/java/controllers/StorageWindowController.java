package controllers;

import dataModels.StorageDataModel;
import fxModels.StorageFxModel;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


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
    private TableColumn<StorageFxModel, String> idStorageColumn;
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

    private StorageDataModel storehouseElement;
    private StorageFxModel storehouseFxElement=new StorageFxModel();
    private ObjectProperty<StorageFxModel> testowyObiekt = new SimpleObjectProperty<>(new StorageFxModel());

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera StorehouseWindowController ");
        storageStateComboBox.getItems().addAll("Wszystkie","W magazynie");
        storageStateComboBox.setValue("Wszystkie");
        storageYearComboBox.getItems().addAll("2021","2020","2019","2018");
        storageYearComboBox.setValue("2021");
/*
        initializeTableView();
        getStorehouseList();*/
    }

  /*  public void getStorehouseList(){
        this.storehouseElement.listInitialize();
    }
    private void initializeTableView(){
        this.storageTableView.setItems(this.storehouseElement.getStorehouseList());
        this.idStorehouseColumn.setCellValueFactory(cellData->cellData.getValue().indexOfStorehouseModelListProperty());
        this.instrumentNameColumn.setCellValueFactory(cellData->cellData.getValue().idInstrumentProperty());
        this.instrumentTypeColumn.setCellValueFactory(cellData->cellData.getValue().instrumentNameProperty());
        this.instrumentProducerColumn.setCellValueFactory(cellData->cellData.getValue().instrumentTypeProperty());
        this.instrumentSerialNumberColumn.setCellValueFactory(cellData->cellData.getValue().serialNumberProperty());
        this.instrumentIdentificationNumberColumn.setCellValueFactory(cellData->cellData.getValue().identificationNumberProperty());
        this.instrumentRangeColumn.setCellValueFactory(cellData->cellData.getValue().instrumentRangeProperty());
        this.instrumentLengthColumn.setCellValueFactory(cellData->cellData.getValue().instrumentLengthProperty());
        this.instrumentDiameterColumn.setCellValueFactory(cellData->cellData.getValue().instrumentDiameterProperty());
        this.instrumentClientColumn.setCellValueFactory(cellData->cellData.getValue().clientProperty());
        this.addDateColumn.setCellValueFactory(cellData->cellData.getValue().addDateProperty());
        this.calibrationDateColumn.setCellValueFactory(cellData->cellData.getValue().calibrationDateProperty());
        this.leftDateColumn.setCellValueFactory(cellData->cellData.getValue().leftDateProperty());
        this.storehouseTableView.setItems(this.storehouseElement.getStorehouseList());

        this.storehouseTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        this.nameLabel.textProperty().bind(this.storehouseElement.getCurrentStorehouse1().clientProperty());




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


        this.storehouseTableView.prefHeightProperty().bind(storehouseMainVBox.heightProperty().multiply(0.7));
    }
*/
    @FXML
    void showSelectedItems() {
        System.out.println("Rozmiar "+this.storehouseElement.getStorehouseSelectedItemsList().size());
        this.storehouseElement.getStorehouseSelectedItemsList().forEach(element->{
            System.out.println(element.clientProperty().getValue());
        });
    }
}
