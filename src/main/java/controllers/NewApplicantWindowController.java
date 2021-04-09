package controllers;

import dbModels.ApplicantModel;
import dbModels.InstrumentModel;
import fxModels.ApplicantFxModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.database.CommonDao;

import java.util.List;

import static dbModels.ApplicantModel.APPLICANT_STATUS;
import static dbModels.ApplicantModel.SHORT_NAME;
import static dbModels.InstrumentModel.APPLICANT;

public class NewApplicantWindowController {
    public NewApplicantWindowController(){
        System.out.println("Konstruktor klasy NewApplicantWindowController");
    }

    private final String ADD_ERROR="W bazie danych istnieje już Zleceniodawca o takiej nazwie";
    private final String DELETE_ERROR="Nie można usunąć Zleceniodawcy";

    private ApplicantsWindowController applicantsWindowController;
    public void setApplicantsWindowController(ApplicantsWindowController applicantsWindowController) {
        this.applicantsWindowController = applicantsWindowController;
    }

    private String function="";
    public void setFunction(String function) {
        this.function = function;
    }

    private int selectedApplicantId=0;
    public void setSelectedApplicantId(int selectedApplicantId) {
        this.selectedApplicantId = selectedApplicantId;
    }

    @FXML private VBox mainVBox;
    @FXML private TextField shortNameTextField;
    @FXML private TextField fullNameTextField;
    @FXML private TextField postCodeTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField streetTextField;
    @FXML private TextField numberTextField;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private Label applicantLabel;
    @FXML private Label errorLabel;

    @FXML
    private void initialize(){
        this.statusComboBox.getItems().addAll("aktywny","nieaktywny");
    }
    @FXML
    private void save() {
        CommonDao commonDao=new CommonDao();
        if(function.equals("delete")){
            if(commonDao.selectAndStatement(InstrumentModel.class,APPLICANT,this.selectedApplicantId).isEmpty()){
                commonDao.deleteComplexModel(setFormToApplicantModel());
                CommonTools.closePaneWindow(mainVBox);
            }
            else{
                this.errorLabel.setText(DELETE_ERROR);
            }
        }
        else {
            if (isValidClientData()) {
                List<ApplicantModel> applicantsList = commonDao.selectAndWithLikeStatement(ApplicantModel.class, SHORT_NAME, this.shortNameTextField.getText().trim(), APPLICANT_STATUS, "aktywny");
                if (applicantsList.isEmpty()) {
                    commonDao.createOrUpdate(setFormToApplicantModel());
                    cancel();
                } else {
                    if (applicantsList.get(0).getIdApplicant() == this.selectedApplicantId) {
                        commonDao.createOrUpdate(setFormToApplicantModel());
                        cancel();
                    } else {
                        this.errorLabel.setText(ADD_ERROR);
                    }
                }
            }
        }
    }
    @FXML
    void cancel() {
        CommonTools.closePaneWindow(this.mainVBox);
    }
    public void setApplicantToForm(ApplicantFxModel applicantFxModel){
        this.shortNameTextField.setText(applicantFxModel.getShortName());
        this.fullNameTextField.setText(applicantFxModel.getFullName());
        this.postCodeTextField.setText(applicantFxModel.getPostCode());
        this.cityTextField.setText(applicantFxModel.getCity());
        this.streetTextField.setText(applicantFxModel.getStreet());
        this.numberTextField.setText(applicantFxModel.getNumber());
        this.statusComboBox.setValue(applicantFxModel.getStatus());
    }
    private ApplicantModel setFormToApplicantModel(){
        ApplicantModel applicantModel=new ApplicantModel();
        applicantModel.setIdApplicant(this.selectedApplicantId);
        applicantModel.setShortName(this.shortNameTextField.getText().trim());
        applicantModel.setFullName(this.fullNameTextField.getText().trim());
        applicantModel.setPostCode(this.postCodeTextField.getText().trim());
        applicantModel.setCity(this.cityTextField.getText().trim());
        applicantModel.setStreet(this.streetTextField.getText().trim());
        applicantModel.setNumber(this.numberTextField.getText().trim());
        applicantModel.setStatus(this.statusComboBox.getValue().trim());
        return applicantModel;
    }
    private boolean isValidClientData() {
        String errorMessage = "";
        if (this.shortNameTextField.getText() == null || this.shortNameTextField.getText().trim().isEmpty()) {
            errorMessage += "Nie wprowadziłeś prawidłowo skróconej nazwy ! \n";
        }
        if (this.fullNameTextField.getText() == null || this.fullNameTextField.getText().trim().isEmpty()) {
            errorMessage += "Nie wprowadziłeś prawidłowo pełnej nazwy ! \n";
        }
        if (this.postCodeTextField.getText() == null || this.postCodeTextField.getText().trim().isEmpty()) {
            errorMessage += "Nie wprowadziłeś prawidłowo kodu pocztowego ! \n";
        }
        if (this.cityTextField.getText() == null || this.cityTextField.getText().trim().isEmpty()) {
            errorMessage += "Nie wprowadziłeś prawidłowo nazwy miejscowości ! \n";
        }
        if (this.streetTextField.getText() == null || this.streetTextField.getText().trim().isEmpty()) {
            errorMessage += "Nie wprowadziłeś prawidłowo ulicy ! \n";
        }
        if (this.numberTextField.getText() == null || this.numberTextField.getText().trim().isEmpty()) {
            errorMessage += "Nie wprowadziłeś prawidłowo numeru domu ! \n";
        }
        if (this.statusComboBox.getValue() == null ) {
            errorMessage += "Nie wybrałeś czy zleceniodawca jes aktywny ! \n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nieprawidłowe dane");
            alert.setHeaderText("Proszę wprowadzić prawidłowe dane dla podanych niżej pól");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    public void setApplicantLabel(String label){
        this.applicantLabel.setText(label);

    }
}
