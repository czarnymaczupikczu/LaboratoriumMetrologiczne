package controllers;

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
import java.util.List;

public class NewInstrumentDataWindowController {
    public NewInstrumentDataWindowController(){System.out.println("Konstruktor klasy NewInstrumentDataWindowController");}
    //Kontroler okna poziom wyżej, potrzebny żeby mieć dostęp do InstrumentDataModel (inicjalizacja Comboboxów itd)
    private InstrumentWindowController instrumentWindowController;
    public void setInstrumentWindowController(InstrumentWindowController instrumentWindowController) {
        this.instrumentWindowController = instrumentWindowController;
    }
    //Stała tekstowa informująca do errorLabel
    public static final String ERROR_TEXT="Taka wartość występuje już w bazie danych";

    //Pola klasowe
    private Class<?> cls;
    private String columnName;
    //Inicjalizacja pól powyżej oraz bindowanie, dla Range.class jest trochę inne
    public void init(String columnName, Class<?> cls, String headLabel){
        this.columnName=columnName;
        this.cls=cls;
        this.headLabel.setText(headLabel);
        if (this.cls== RangeModel.class) {
            addButton.disableProperty().bind(newRangeTextField1.textProperty().isEmpty().or(newRangeTextField2.textProperty().isEmpty()).or(newRangeUnitComboBox.valueProperty().isNull()));
            newRangeUnitComboBox.setItems(instrumentWindowController.getInstrumentDataModel().getUnitObservableList());
        }else
        {
            addButton.disableProperty().bind(newValueTextField.textProperty().isEmpty());
        }
    }
    //Wspólne kontenery i kontrolki
    @FXML
    private VBox mainVBox;
    @FXML
    private Label headLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private Button addButton;
    //Pole tekstowe dla okna innego niż Range
    @FXML
    private TextField newValueTextField;
    //Pola tekstowe i combobox przy dodawania nowego zakresu
    @FXML
    private TextField newRangeTextField1;
    @FXML
    private TextField newRangeTextField2;
    @FXML
    private ComboBox<String> newRangeUnitComboBox;

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera NewInstrumentDataWindowController ");
    }
    //Metoda podpięta do przycisku dodaj
    @FXML
    void addNewData() {
        if (this.cls== RangeModel.class) {
            createIfNotExist(this.columnName,cls,"("+newRangeTextField1.getText()+" do "+newRangeTextField2.getText()+") "+newRangeUnitComboBox.getValue());
        }else
        {
            createIfNotExist(this.columnName,cls,newValueTextField.getText());
        }
    }
    @FXML
    void cancelButton() {
        CommonTools.closePaneWindow(mainVBox);
    }
    //Metoda generyczna służąca do dodawania nowych wartości do poszczególnych tabel
    private <T> void createIfNotExist(String columnName, Class<T> cls,String value)  {
        CommonDao commonDao=new CommonDao();
        //Sprawdzam czy w bazie nie ma już takiej wartości zamias = jest Like dzięki czemu jest caseInsensitive
        List<?> dataList=  commonDao.getListWithSimpleLikeSelect(cls,columnName,value);
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
            else if (cls== RangeModel.class){
                commonDao.createBaseModel(new RangeModel(0, value));
            }
            instrumentWindowController.getInstrumentDataModel().init();
            cancelButton();
        }
    }
}
