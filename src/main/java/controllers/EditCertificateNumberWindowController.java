package controllers;


import controllers.RegisterWindowController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.database.CommonDao;


public class EditCertificateNumberWindowController {


    private RegisterWindowController registerWindowController;
    public void setRegisterWindowController(RegisterWindowController registerWindowController) {
        this.registerWindowController = registerWindowController;
    }

    @FXML private VBox mainVBox;
    @FXML private TextField certificateNumberTextField;
    @FXML private ComboBox<String> documentKindComboBox;
    @FXML private TextField agreementNumberTextField;
    @FXML private Label certificateNumberInformationLabel;

    @FXML
    public void initialize(){
        documentKindComboBox.getItems().addAll("Świadectwo wzorcowania","Protokół odmowy wzorcowania");
        documentKindComboBox.setValue("Świadectwo wzorcowania");
    }
    public void init(){
        this.certificateNumberTextField.setText(this.registerWindowController.getRegisterDataModel().getCurrentRegisterModel().getCertificateNumber());
        if(this.registerWindowController.getRegisterDataModel().getCurrentRegisterModel().getDocumentKind()!=null) {
            if(this.registerWindowController.getRegisterDataModel().getCurrentRegisterModel().getDocumentKind().equals("ŚW")) {
                this.documentKindComboBox.setValue("Świadectwo wzorcowania");
            }
            else{
                this.documentKindComboBox.setValue("Protokół odmowy wzorcowania");
            }
        }
        this.agreementNumberTextField.setText(this.registerWindowController.getRegisterDataModel().getCurrentRegisterModel().getAgreementNumber());
    }

    @FXML
    void save() {
        if(this.certificateNumberTextField.getText().contains(this.registerWindowController.getRegisterDataModel().getCurrentRegisterModel().getCardNumber())) {
            this.registerWindowController.getRegisterDataModel().getCurrentRegisterModel().setCertificateNumber(certificateNumberTextField.getText());
            if(this.documentKindComboBox.getValue().equals("Świadectwo wzorcowania")){
                this.registerWindowController.getRegisterDataModel().getCurrentRegisterModel().setDocumentKind("ŚW");
            }else{
                this.registerWindowController.getRegisterDataModel().getCurrentRegisterModel().setDocumentKind("PO");
            }
            this.registerWindowController.getRegisterDataModel().getCurrentRegisterModel().setAgreementNumber(this.agreementNumberTextField.getText());
            CommonDao commonDao=new CommonDao();
            commonDao.createOrUpdate(this.registerWindowController.getRegisterDataModel().getCurrentRegisterModel());
            cancel();
        }else{
            this.certificateNumberInformationLabel.setText("Nieprawidłowy numer świadectwa/protokołu");
        }
    }


    @FXML
    void cancel() {
        CommonTools.closePaneWindow(mainVBox);
    }




}
