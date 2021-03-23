package controllers.admin;

import dataModels.UserDataModel;
import dbModels.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import utils.CommonTools;
import utils.FxmlTools;

public class UserWindowController {
    public UserWindowController(){
        System.out.println("Konstruktor klasy UserWindowController");
    }
    private UserDataModel userDataModel=new UserDataModel();
    public UserDataModel getUserDataModel() {
        return userDataModel;
    }
    private EditUserWindowController editUserWindowController;

    private final String EDIT_USER_WINDOW="/fxml/admin/EditUserWindow.fxml";


    @FXML private VBox mainVBox;

    @FXML private TableView<UserModel> userTableView;
    @FXML private TableColumn<UserModel, Integer> idUserColumn;
    @FXML private TableColumn<UserModel, String> firstNameColumn;
    @FXML private TableColumn<UserModel, String> lastNameColumn;
    @FXML private TableColumn<UserModel, String> loginColumn;
    @FXML private TableColumn<UserModel, String> passwordColumn;
    @FXML private TableColumn<UserModel, String> permissionLevelColumn;
    @FXML private TableColumn<UserModel, String> initialsColumn;
    @FXML private TableColumn<UserModel, String> cssStyleColumn;

    @FXML
    public void initialize(){
        System.out.println("Metoda initialize kontrolera UserWindowController ");
        this.idUserColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        this.passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        this.permissionLevelColumn.setCellValueFactory(new PropertyValueFactory<>("permissionLevel"));
        this.initialsColumn.setCellValueFactory(new PropertyValueFactory<>("initials"));
        this.cssStyleColumn.setCellValueFactory(new PropertyValueFactory<>("styleSheet"));
        this.userTableView.setItems(this.userDataModel.getUsersObservableList());
        this.userTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
               this.userDataModel.setSelectedUser(newValue);
            }
        });
    }


    @FXML
    void addUser() {
        if(this.userDataModel.getSelectedUser().getIdUser()!=null){
            this.editUserWindowController= FxmlTools.openVBoxWindow(EDIT_USER_WINDOW);
        }
    }

    @FXML
    void cancel() {
        CommonTools.closePaneWindow(mainVBox);
    }

    @FXML
    void deleteUser() {
        if(this.userDataModel.getSelectedUser().getIdUser()!=null){
            this.editUserWindowController= FxmlTools.openVBoxWindow(EDIT_USER_WINDOW);
            this.editUserWindowController.setUserToForm(this.userDataModel.getSelectedUser());
            this.editUserWindowController.setUserFormEditableFalse();
            this.editUserWindowController.setUserWindowController(this);
        }
    }

    @FXML
    void editUser() {
        if(this.userDataModel.getSelectedUser().getIdUser()!=null){
            this.editUserWindowController= FxmlTools.openVBoxWindow(EDIT_USER_WINDOW);
            this.editUserWindowController.setUserToForm(this.userDataModel.getSelectedUser());
        }
    }

}
