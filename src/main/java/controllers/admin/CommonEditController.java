package controllers.admin;

import dbModels.instrument.BaseModel;
import dbModels.instrument.NameModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utils.database.CommonDao;

import java.util.List;
import java.util.stream.IntStream;

public class CommonEditController {
    public CommonEditController(){
        System.out.println("Konstruktor klasy CommonEditController");
    }

    private CommonWindowController commonWindowController=new CommonWindowController();
    public void setCommonWindowController(CommonWindowController commonWindowController) {
        this.commonWindowController = commonWindowController;
    }

    static final String EDIT_ERROR = "Taka wartość istnieje już w bazie danych";

    @FXML private VBox mainVBox;
    @FXML private Label mainLabel;
    @FXML private TextField mainTextField;
    @FXML private Label errorLabel;
    @FXML private Button saveButton;

    private String function;

    public void setFunction(String function) {
        this.function = function;
    }

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera CommonEditController ");
        //this.saveButton.disableProperty().bind(this.mainTextField.textProperty().isEmpty());
    }


    @FXML
    void cancel() {

    }

    @FXML
    void save() {

    }




}
