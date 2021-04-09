package controllers.admin;

import dataModels.CommonDataModel;
import fxModels.CommonFxModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.FxmlTools;

public class CommonWindowController {

    public CommonWindowController(){
        System.out.println("Konstruktor klasy CommonWindowController");
    }

    private CommonDataModel commonDataModel=new CommonDataModel();

    private CommonEditController commonEditController;

    private final String COMMON_EDIT_WINDOW="/fxml/admin/CommonEditWindow.fxml";
    private static final String NEW_ELEMENT="Dodanie nowe elementu";
    private static final String EDIT_ELEMENT="Edycja elementu";
    private static final String DELETE_ELEMENT="Usuwanie elementu";

    @FXML private VBox commonWindowVBox;
    @FXML private TableView<CommonFxModel> commonTableView;
    @FXML private TableColumn<CommonFxModel, Integer> idColumn;
    @FXML private TableColumn<CommonFxModel, String> valueColumn;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    @FXML private Label mainLabel;

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera CommonWindowController ");
        this.commonTableView.setItems(this.commonDataModel.getDataObservableList());
        this.idColumn.setCellValueFactory(cellData->cellData.getValue().idProperty().asObject());
        this.valueColumn.setCellValueFactory(cellData->cellData.getValue().valueProperty());
        this.commonTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
                this.commonDataModel.getCurrentElement().setId(newValue.getId());
                this.commonDataModel.getCurrentElement().setValue(newValue.getValue());
            }
        });
        editButton.disableProperty().bind(this.commonDataModel.getCurrentElement().valueProperty().isEmpty());
        deleteButton.disableProperty().bind(this.commonDataModel.getCurrentElement().valueProperty().isEmpty());
    }


    @FXML
    void addNew() {
        this.commonEditController=FxmlTools.openVBoxWindow(COMMON_EDIT_WINDOW);
        if(this.commonEditController!=null) {
            this.commonEditController.setMainLabel(NEW_ELEMENT);
            this.commonDataModel.setFunction("new");
            this.commonEditController.setCommonWindowController(this);
            this.commonDataModel.getCurrentElement().setId(0);
        }
    }
    @FXML
    void edit() {
        if(this.commonDataModel.getCurrentElement().getValue()!=null){
            this.commonEditController=FxmlTools.openVBoxWindow(COMMON_EDIT_WINDOW);
            if(this.commonEditController!=null) {
                this.commonEditController.setMainTextField(this.commonDataModel.getCurrentElement().getValue());
                this.commonEditController.setMainLabel(EDIT_ELEMENT);
                this.commonDataModel.setFunction("edit");
                this.commonEditController.setCommonWindowController(this);
            }
        }
    }
    @FXML
    void delete() {
        if(this.commonDataModel.getCurrentElement().getValue()!=null){
            this.commonEditController=FxmlTools.openVBoxWindow(COMMON_EDIT_WINDOW);
            if(this.commonEditController!=null) {
                this.commonEditController.setMainTextField(this.commonDataModel.getCurrentElement().getValue());
                this.commonEditController.setMainTextFieldEditable(false);
                this.commonEditController.setMainLabel(DELETE_ELEMENT);
                this.commonDataModel.setFunction("delete");
                this.commonEditController.setCommonWindowController(this);
            }
        }
    }


    @FXML
    void cancel() {
        CommonTools.closePaneWindow(commonWindowVBox);
    }
    public CommonDataModel getCommonDataModel() {
        return commonDataModel;
    }
    public void setCommonDataModel(CommonDataModel commonDataModel) {
        this.commonDataModel = commonDataModel;
    }
    public void setLabel(String label){
        this.mainLabel.setText(label);
    }
}
