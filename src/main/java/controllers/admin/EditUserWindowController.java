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

public class EditUserWindowController {

    private UserWindowController userWindowController;

    public void setUserWindowController(UserWindowController userWindowController) {
        this.userWindowController = userWindowController;
    }

    private String function;
    public void setFunction(String function) {
        this.function = function;
    }

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
        Integer selectedUserId=this.userWindowController.getUserDataModel().getSelectedUser().getIdUser();
        if(function.equals("delete")){
            this.userLabel.setText("USUWANIE UŻYTKOWNIKA");
            if(commonDao.selectWithTwoOrConditions(StorageModel.class,"entryUser",selectedUserId,"spendUser",selectedUserId)==null &&
                    commonDao.getListWithSimpleSelect(RegisterModel.class,"calibrationUser",selectedUserId)==null){
                this.errorLabel.setText("Można usuwać");
                commonDao.deleteUser(this.userWindowController.getUserDataModel().getSelectedUser());
                this.userWindowController.getUserDataModel().init();
                CommonTools.closePaneWindow(mainVBox);
            }
            else{
                this.errorLabel.setText("Nie można usuwać");
            }
        }
        else if (function.equals("edit")){
            if(isValidUserData()){
                UserModel editedUser=commonDao.queryForFirst(UserModel.class,"login",this.loginTextField.getText());
                if(editedUser!=null){
                    if(editedUser.getIdUser()==this.userWindowController.getUserDataModel().getSelectedUser().getIdUser()){
                        this.errorLabel.setText("To ten sam user cwaniaku");
                    }
                    else{
                        this.errorLabel.setText("Użytkownik o takim loginie już istnieje w bazie");
                    }
                }
                else{
                    editedUser.setIdUser(this.userWindowController.getUserDataModel().getSelectedUser().getIdUser());
                    editedUser.setFirstName(this.firstNameTextField.getText());
                    editedUser.setLastName(this.lastNameTextField.getText());
                    editedUser.setLogin(this.loginTextField.getText());
                    editedUser.setPassword(this.passwordTextField.getText());
                    editedUser.setPermissionLevel(this.permissionComboBox.getValue());
                    editedUser.setInitials(this.initialsTextField.getText());
                    commonDao.createOrUpdate(editedUser);
                }
            }

        }else if(function.equals("new")){
            if(this.firstNameTextField.getText()==null){
                System.out.println("aha");
            }
            if(isValidUserData()){
                UserModel editedUser=commonDao.queryForFirst(UserModel.class,"login",this.loginTextField.getText());
                if(editedUser!=null){
                    if(editedUser.getIdUser()==this.userWindowController.getUserDataModel().getSelectedUser().getIdUser()){
                        this.errorLabel.setText("To ten sam user cwaniaku");
                    }
                    else{
                        this.errorLabel.setText("Użytkownik o takim loginie już istnieje w bazie");
                    }
                }
                else{
                    editedUser.setIdUser(this.userWindowController.getUserDataModel().getSelectedUser().getIdUser());
                    editedUser.setFirstName(this.firstNameTextField.getText());
                    editedUser.setLastName(this.lastNameTextField.getText());
                    editedUser.setLogin(this.loginTextField.getText());
                    editedUser.setPassword(this.passwordTextField.getText());
                    editedUser.setPermissionLevel(this.permissionComboBox.getValue());
                    editedUser.setInitials(this.initialsTextField.getText());
                    commonDao.createOrUpdate(editedUser);
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
        if (firstNameTextField.getText() == null || firstNameTextField.getText().length() == 0) {
            errorMessage += "Nie wprowadziłeś prawidłowo imienia ! \n";
        }
        if (lastNameTextField.getText() == null || lastNameTextField.getText().length() == 0) {
            errorMessage += "Nie wprowadziłeś prawidłowo nazwiska ! \n";
        }
        if (loginTextField.getText() == null || loginTextField.getText().length() == 0) {
            errorMessage += "Nie wprowadziłeś prawidłowo loginu ! \n";
        }
        if (passwordTextField.getText() == null || passwordTextField.getText().length() == 0) {
            errorMessage += "Nie wprowadziłeś prawidłowo hasła ! \n";
        }
        if (initialsTextField.getText() == null || initialsTextField.getText().length() == 0) {
            errorMessage += "Nie wprowadziłeś prawidłowo inicjałów ! \n";
        }
        if (permissionComboBox.getValue() == null ) {
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
}
