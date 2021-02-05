package controllers;

import dataModels.StorehouseDataModel;
import fxModels.StorehouseFxModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


public class StorehouseWindowController {
    public StorehouseWindowController(){
        System.out.println("Konstruktor klasy StorehouseWindowController");
    }

    @FXML
    private ComboBox storehouseWindowStateComboBox;
    @FXML
    private ComboBox <String> storehouseWindowYearComboBox;
    @FXML
    private VBox storehouseMainVBox;

    @FXML
    private TableView<StorehouseFxModel> storehouseTableView;
    @FXML
    private TableColumn<StorehouseFxModel, String> idStorehouseColumn;
    @FXML
    private TableColumn<StorehouseFxModel, String> instrumentNameColumn;
    @FXML
    private TableColumn<StorehouseFxModel, String> instrumentTypeColumn;
    @FXML
    private TableColumn<StorehouseFxModel, String> instrumentProducerColumn;
    @FXML
    private TableColumn<StorehouseFxModel, String> instrumentSerialNumberColumn;
    @FXML
    private TableColumn<StorehouseFxModel, String> instrumentIdentificationNumberColumn;
    @FXML
    private TableColumn<StorehouseFxModel, String> instrumentRangeColumn;
    @FXML
    private TableColumn<StorehouseFxModel,String> instrumentLengthColumn;
    @FXML
    private TableColumn<StorehouseFxModel,String> instrumentDiameterColumn;
    @FXML
    private TableColumn<StorehouseFxModel, String> instrumentClientColumn;
    @FXML
    private TableColumn<StorehouseFxModel, String> addDateColumn;
    @FXML
    private TableColumn<StorehouseFxModel, String> calibrationDateColumn;
    @FXML
    private TableColumn<StorehouseFxModel, String> leftDateColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField searchTextField;

    private StorehouseDataModel storehouseElement;
    private StorehouseFxModel   storehouseFxElement=new StorehouseFxModel();
    private ObjectProperty<StorehouseFxModel> testowyObiekt = new SimpleObjectProperty<>(new StorehouseFxModel());

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera StorehouseWindowController ");
        storehouseWindowStateComboBox.getItems().add("Wszystkie");
        storehouseWindowStateComboBox.getItems().add("Magazyn");
        storehouseWindowYearComboBox.getItems().add("2020");
        storehouseWindowYearComboBox.getItems().add("2021");
        this.storehouseElement=new StorehouseDataModel();
        this.storehouseFxElement.setClient("Problem");

        initializeTableView();
        getStorehouseList();
    }

    public void getStorehouseList(){
        this.storehouseElement.listInitialize();
    }
    private void initializeTableView(){
        this.storehouseTableView.setItems(this.storehouseElement.getStorehouseList());
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

    @FXML
    void showSelectedItems() {
        System.out.println("Rozmiar "+this.storehouseElement.getStorehouseSelectedItemsList().size());
        this.storehouseElement.getStorehouseSelectedItemsList().forEach(element->{
            System.out.println(element.clientProperty().getValue());
        });
    }
}
