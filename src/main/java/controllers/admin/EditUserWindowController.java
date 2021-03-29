package controllers.admin;

import dbModels.RegisterModel;
import dbModels.StorageModel;
import dbModels.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.database.CommonDao;

import static dbModels.RegisterModel.CALIBRATION_USER;
import static dbModels.StorageModel.ENTRY_USER;
import static dbModels.StorageModel.SPEND_USER;
import static dbModels.UserModel.LOGIN;

public class EditUserWindowController {

    private UserWindowController userWindowController;
    public void setUserWindowController(UserWindowController userWindowController) {
        this.userWindowController = userWindowController;
    }

    private String function="";
    public void setFunction(String function) {
        this.function = function;
    }

    private int selectedUserId=0;
    public void setSelectedUserId(int selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    private final String DELETE_ERROR="Nie można bezpiecznie usunąć elementu";
    private final String ADD_ERROR="Uzytkownik o takim loginie istnieje już w bazie";

    @FXML private VBox mainVBox;
    @FXML private Label userLabel;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField loginTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField initialsTextField;
    @FXML private ComboBox<String> permissionComboBox;
    @FXML private Label errorLabel;


    @FXML
    private void initialize(){
        permissionComboBox.getItems().addAll("admin","user","halfAdmin");
    }

    @FXML
    void saveUser() {
        CommonDao commonDao=new CommonDao();
        if(function.equals("delete")){
            if(commonDao.selectOrStatement(StorageModel.class,ENTRY_USER,selectedUserId,SPEND_USER,selectedUserId).isEmpty() &&
                commonDao.selectAndStatement(RegisterModel.class,CALIBRATION_USER,selectedUserId).isEmpty()){
                commonDao.deleteUser(this.userWindowController.getUserDataModel().getSelectedUser());
                this.userWindowController.getUserDataModel().init();
                CommonTools.closePaneWindow(mainVBox);
            }
            else{
                this.errorLabel.setText(DELETE_ERROR);
            }
        }
        else {
            if(isValidUserData()){
                UserModel userModel=commonDao.queryForFirst(UserModel.class,LOGIN,this.loginTextField.getText());
                if(userModel!=null){
                    if(userModel.getIdUser()==this.selectedUserId){
                        commonDao.createOrUpdate(setFormToUserModel());
                    }
                    else{
                        this.errorLabel.setText(ADD_ERROR);
                    }
                }
                else{
                    commonDao.createOrUpdate(setFormToUserModel());
                }
            }
        }
    }
    @FXML
    void cancelSaveUser() {
        CommonTools.closePaneWindow(mainVBox);
    }

    private boolean isValidUserData() {
        String errorMessage = "";
        if (this.firstNameTextField.getText() == null || this.firstNameTextField.getText().trim().isEmpty()) {
            errorMessage += "Nie wprowadziłeś prawidłowo imienia ! \n";
        }
        if (this.lastNameTextField.getText() == null || this.lastNameTextField.getText().trim().isEmpty()) {
            errorMessage += "Nie wprowadziłeś prawidłowo nazwiska ! \n";
        }
        if (this.loginTextField.getText() == null || this.loginTextField.getText().trim().isEmpty()) {
            errorMessage += "Nie wprowadziłeś prawidłowo loginu ! \n";
        }
        if (this.passwordTextField.getText() == null || this.passwordTextField.getText().trim().isEmpty()) {
            errorMessage += "Nie wprowadziłeś prawidłowo hasła ! \n";
        }
        if (this.initialsTextField.getText() == null || this.initialsTextField.getText().trim().isEmpty()) {
            errorMessage += "Nie wprowadziłeś prawidłowo inicjałów ! \n";
        }
        if (this.permissionComboBox.getValue() == null ) {
            errorMessage += "Nie wybrałeś poziomu dostępu ! \n";
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
    public void setUserToForm(UserModel userModel){
        this.firstNameTextField.setText(userModel.getFirstName());
        this.lastNameTextField.setText(userModel.getLastName());
        this.loginTextField.setText(userModel.getLogin());
        this.passwordTextField.setText(userModel.getPassword());
        this.initialsTextField.setText(userModel.getInitials());
        this.permissionComboBox.setValue(userModel.getPermissionLevel());
    }
    public void setUserFormEditableFalse(){
        this.firstNameTextField.setEditable(false);
        this.lastNameTextField.setEditable(false);
        this.loginTextField.setEditable(false);
        this.passwordTextField.setEditable(false);
        this.initialsTextField.setEditable(false);
        this.permissionComboBox.setEditable(false);
    }
    private UserModel setFormToUserModel(){
        UserModel userModel=new UserModel();
        userModel.setIdUser(this.selectedUserId);
        userModel.setFirstName(this.firstNameTextField.getText().trim());
        userModel.setLastName(this.lastNameTextField.getText().trim());
        userModel.setLogin(this.loginTextField.getText().trim());
        userModel.setPassword(this.passwordTextField.getText().trim());
        userModel.setPermissionLevel(this.permissionComboBox.getValue().trim());
        userModel.setInitials(this.initialsTextField.getText().trim());
        return userModel;
    }
    public void setUserLabel(String label){
        this.userLabel.setText(label);
    }

}
