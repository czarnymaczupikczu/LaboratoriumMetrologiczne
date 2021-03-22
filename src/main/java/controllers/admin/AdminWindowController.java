package controllers.admin;

import controllers.LoginWindowController;
import controllers.MainWindowController;
import dbModels.YearModel;
import dbModels.instrument.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import utils.FxmlTools;

public class AdminWindowController {
    public AdminWindowController(){
        System.out.println("Konstruktor klasy AdminWindowController");
    }

    private MainWindowController mainController;
    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }

    private CommonWindowController commonWindowController;


    private final String COMMON_WINDOW="/fxml/admin/CommonWindow.fxml";

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera AdminWindowController ");
    }


    @FXML
    private VBox adminWindowVBox;


    @FXML
    void nameEdit() {
        this.commonWindowController= FxmlTools.openVBoxWindow(COMMON_WINDOW, "Nazwa");
        System.out.println("1");
        this.commonWindowController.getCommonDataModel().setCls(NameModel.class);
        System.out.println("2");
        this.commonWindowController.getCommonDataModel().init();


    }

    @FXML
    void producerEdit() {
        this.commonWindowController= FxmlTools.openVBoxWindow(COMMON_WINDOW, "Nazwa");
        this.commonWindowController.getCommonDataModel().setCls(ProducerModel.class);
        this.commonWindowController.getCommonDataModel().init();
    }

    @FXML
    void rangeEdit() {
        this.commonWindowController= FxmlTools.openVBoxWindow(COMMON_WINDOW, "Nazwa");
        this.commonWindowController.getCommonDataModel().setCls(RangeModel.class);
        this.commonWindowController.getCommonDataModel().init();
    }

    @FXML
    void typeEdit() {
        this.commonWindowController= FxmlTools.openVBoxWindow(COMMON_WINDOW, "Nazwa");
        this.commonWindowController.getCommonDataModel().setCls(TypeModel.class);
        this.commonWindowController.getCommonDataModel().init();
    }

    @FXML
    void unitEdit() {
        this.commonWindowController= FxmlTools.openVBoxWindow(COMMON_WINDOW, "Nazwa");
        this.commonWindowController.getCommonDataModel().setCls(UnitModel.class);
        this.commonWindowController.getCommonDataModel().init();
    }

    @FXML
    void userEdit() {

    }

    @FXML
    void yearEdit() {
        this.commonWindowController= FxmlTools.openVBoxWindow(COMMON_WINDOW, "Nazwa");
        this.commonWindowController.getCommonDataModel().setCls(YearModel.class);
        this.commonWindowController.getCommonDataModel().init();
    }

    @FXML
    void copyDataBase() {

    }
}
