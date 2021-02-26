package controllers;

import com.j256.ormlite.stmt.QueryBuilder;
import dbModels.instrument.NameModel;
import dbModels.instrument.ProducerModel;
import dbModels.instrument.RangeModel;
import dbModels.instrument.TypeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.database.CommonDao;

import java.sql.SQLException;
import java.util.List;


public class NewInstrumentDataWindowController {

    public NewInstrumentDataWindowController(){System.out.println("Konstruktor klasy NewInstrumentDataWindowController");}
    //Kontroler okna poziom wyżej
    private InstrumentWindowController instrumentWindowController;
    public void setInstrumentWindowController(InstrumentWindowController instrumentWindowController) {
        this.instrumentWindowController = instrumentWindowController;
    }
    public static final String ERROR_TEXT="Taka wartość występuje już w bazie danych";


    private Class<?> cls;
    private String columnName;

    public void init(String columnName, Class<?> cls, String headLabel){
        this.columnName=columnName;
        this.cls=cls;
        this.headLabel.setText(headLabel);
        if (this.cls== RangeModel.class) {
            addButton.disableProperty().bind(newRangeTextField1.textProperty().isEmpty().or(newRangeTextField2.textProperty().isEmpty()));
        }else
        {
            addButton.disableProperty().bind(newValueTextField.textProperty().isEmpty());
        }
    }
    @FXML
    private VBox mainVBox;
    @FXML
    private Label headLabel;
    @FXML
    private TextField newValueTextField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button addButton;

    @FXML
    private TextField newRangeTextField1;

    @FXML
    private TextField newRangeTextField2;

    @FXML
    private ComboBox<String> newRangeUnitComboBox;

    @FXML
    private Label newInstrumentRangeLabel;

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera NewInstrumentDataWindowController ");

    }











    @FXML
    void addNewData() {
        createIfNotExist(this.columnName,cls,newValueTextField.getText());
    }
    @FXML
    void cancelButton() {
        CommonTools.closePaneWindow(mainVBox);
    }
    private <T> void createIfNotExist(String columnName, Class<T> cls,String value)  {
        CommonDao commonDao=new CommonDao();
        QueryBuilder commonQueryBuilder=commonDao.getQueryBuilder(cls);
        List<?> dataList=  commonDao.getListWithSimpleLikeSelect(cls,columnName,value);                 //commonDao.queryForEq(columnName,cls,value);
        if (dataList.size()>0){
            this.errorLabel.setText(ERROR_TEXT);
        }else{
            if (cls==NameModel.class) {
                commonDao.createBaseModel(new NameModel(0, value));
            }else if (cls== TypeModel.class){
                commonDao.createBaseModel(new TypeModel(0, value));
            }else if (cls== ProducerModel.class){
                commonDao.createBaseModel(new ProducerModel(0, value));
            }
            instrumentWindowController.getInstrumentDataModel().init();
            cancelButton();
        }
    }


}
