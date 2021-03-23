package controllers.admin;


import dbModels.InstrumentModel;
import dbModels.RegisterModel;
import dbModels.YearModel;
import dbModels.instrument.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.database.CommonDao;

import java.lang.reflect.Method;
import java.util.List;

public class CommonEditController {
    public CommonEditController(){
        System.out.println("Konstruktor klasy CommonEditController");
    }

    private CommonWindowController commonWindowController;
    public void setCommonWindowController(CommonWindowController commonWindowController) {
        this.commonWindowController = commonWindowController;
    }

    private final String ERROR="Taki element istnieje już w bazie";
    private final String DELETE_ERROR="Nie można bezpiecznie usunąć elementu";

    @FXML private VBox mainVBox;
    @FXML private Label mainLabel;
    @FXML private TextField mainTextField;
    @FXML private Label errorLabel;
    @FXML private Button saveButton;

    @FXML
    public void initialize(){
        this.saveButton.disableProperty().bind(this.mainTextField.textProperty().isEmpty());
    }
    public <T extends BaseModel> T returnBaseModelObject(Class cls, Integer id, String value){
        if(cls==NameModel.class){
            return (T) new NameModel(id,value);
        }else if(cls==TypeModel.class){
            return (T) new TypeModel(id,value);
        }
        else if(cls==ProducerModel.class){
            return (T) new ProducerModel(id,value);
        }
        else if(cls==RangeModel.class){
            return (T) new RangeModel(id,value);
        }
        else if(cls==UnitModel.class){
            return (T) new UnitModel(id,value);
        }
        else if(cls==YearModel.class){
            return (T) new YearModel(id,value);
        }else{
            return null;
        }
    }

    @FXML
    void save() {
        Class tempClass=this.commonWindowController.getCommonDataModel().getCls();
        Integer tempId=this.commonWindowController.getCommonDataModel().getCurrentElement().getId();
        String tempValue=this.commonWindowController.getCommonDataModel().getCurrentElement().getValue();
        String columnNameDelete=this.commonWindowController.getCommonDataModel().getColumnNameDelete();
        String columnNameEdit=this.commonWindowController.getCommonDataModel().getColumnNameEdit();
        String function=this.commonWindowController.getCommonDataModel().getFunction();
        CommonDao commonDao=new CommonDao();
        if(function.equals("delete")){
            if(tempClass==YearModel.class){
                if(commonDao.queryForFirstWithFullLike(RegisterModel.class,columnNameDelete,tempValue)==null){
                    this.errorLabel.setText("Można usuwać");
                    commonDao.delete(returnBaseModelObject(tempClass,tempId,tempValue));
                    this.commonWindowController.getCommonDataModel().init();
                    CommonTools.closePaneWindow(mainVBox);
                }
                else{
                    this.errorLabel.setText("Nie można usuwać");
                }
            }
            else if(tempClass==UnitModel.class){
                if(commonDao.queryForFirstWithFullLike(RangeModel.class,columnNameDelete,tempValue)==null){
                    this.errorLabel.setText("Można usuwać");
                    commonDao.delete(returnBaseModelObject(tempClass,tempId,tempValue));
                    this.commonWindowController.getCommonDataModel().init();
                    CommonTools.closePaneWindow(mainVBox);
                }else{
                    this.errorLabel.setText("Nie można usuwać");
                }
            }else {
                if (commonDao.queryForFirst(InstrumentModel.class, columnNameDelete, tempId) == null) {
                    this.errorLabel.setText("Można usuwać");
                    commonDao.delete(returnBaseModelObject(tempClass,tempId,tempValue));
                    this.commonWindowController.getCommonDataModel().init();
                    CommonTools.closePaneWindow(mainVBox);
                } else {
                    this.errorLabel.setText("Nie można usuwać");
                }
            }
        }
        else{//edit new
            if(this.commonWindowController.getCommonDataModel().getCurrentElement()!=null){
                tempId=this.commonWindowController.getCommonDataModel().getCurrentElement().getId();
            }
            tempValue=this.mainTextField.getText();
            List<BaseModel> dataList=commonDao.getListWithSimpleLikeSelect(tempClass,columnNameEdit,tempValue);
            if(dataList.size()>0 ) {
                if (dataList.get(0).getId() != tempId) {
                    this.errorLabel.setText("Taka wartość istnieje już w bazie tłoku");
                }
                else {
                    CommonTools.closePaneWindow(mainVBox);
                }
            }
            else{
                this.errorLabel.setText("można edytować");
                commonDao.createOrUpdateBaseModel(returnBaseModelObject(tempClass,tempId,tempValue));
                this.commonWindowController.getCommonDataModel().init();
                CommonTools.closePaneWindow(mainVBox);
            }
        }
    }
      @FXML
    void cancel() {
        CommonTools.closePaneWindow(mainVBox);
    }

    public void setMainTextField(String value) {
        this.mainTextField.setText(value);
    }
    public void setMainTextFieldEditable(Boolean value){
        this.mainTextField.setEditable(value);
    }
    public void setMainLabel(String value){
        this.mainLabel.setText(value);
    }


}
