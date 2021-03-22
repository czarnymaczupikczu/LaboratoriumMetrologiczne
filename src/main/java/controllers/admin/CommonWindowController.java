package controllers.admin;

import dataModels.CommonDataModel;
import dbModels.instrument.BaseModel;
import fxModels.CommonFxModel;
import javafx.fxml.FXML;
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
    private CommonEditController commonEditController=new CommonEditController();

    private final String COMMON_EDIT_WINDOW="/fxml/admin/CommonEditController.fxml";

    @FXML private VBox commonWindowVBox;
    @FXML private TableView<CommonFxModel> commonTableView;
    @FXML private TableColumn<CommonFxModel, Integer> idColumn;
    @FXML private TableColumn<CommonFxModel, String> valueColumn;

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera CommonWindowController ");
        this.commonTableView.setItems(this.commonDataModel.getDataObservableList());
        this.idColumn.setCellValueFactory(cellData->cellData.getValue().idProperty().asObject());
        this.valueColumn.setCellValueFactory(cellData->cellData.getValue().valueProperty());
        this.commonTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
                this.commonDataModel.setCurrentElement(newValue);
            }
        });
    }




    @FXML
    void addNew() {
       // this.commonEditController= FxmlTools.openVBoxWindow(COMMON_EDIT_WINDOW,"nazwa");
       // this.commonEditController.setCommonWindowController(this);
        //this.commonEditController.setFunction("new");
    }
    @FXML
    void edit() {

    }
    @FXML
    void delete() {

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
}
