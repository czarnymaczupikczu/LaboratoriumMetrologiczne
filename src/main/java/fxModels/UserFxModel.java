package fxModels;

import com.j256.ormlite.field.DatabaseField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserFxModel {

    private IntegerProperty idUser=new SimpleIntegerProperty();
    private StringProperty firstName=new SimpleStringProperty();
    private StringProperty lastName=new SimpleStringProperty();
    private StringProperty login=new SimpleStringProperty();
    private StringProperty password=new SimpleStringProperty();
    private StringProperty permissionLevel=new SimpleStringProperty();
    private StringProperty styleSheet=new SimpleStringProperty();

    //Konstruktory
    public UserFxModel() {
    }
    public UserFxModel(int idUser, String firstName, String lastName, String login, String password, String permissionLevel, String styleSheet) {
        this.idUser = new SimpleIntegerProperty(idUser);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.permissionLevel = new SimpleStringProperty(permissionLevel);
        this.styleSheet = new SimpleStringProperty(styleSheet);
    }

    //Gettery i Settery
    public int getIdUser() {
        return idUser.get();
    }
    public IntegerProperty idUserProperty() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser.set(idUser);
    }
    public String getFirstName() {
        return firstName.get();
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    public String getLastName() {
        return lastName.get();
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    public String getLogin() {
        return login.get();
    }
    public StringProperty loginProperty() {
        return login;
    }
    public void setLogin(String login) {
        this.login.set(login);
    }
    public String getPassword() {
        return password.get();
    }
    public StringProperty passwordProperty() {
        return password;
    }
    public void setPassword(String password) {
        this.password.set(password);
    }
    public String getPermissionLevel() {
        return permissionLevel.get();
    }
    public StringProperty permissionLevelProperty() {
        return permissionLevel;
    }
    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel.set(permissionLevel);
    }
    public String getStyleSheet() {
        return styleSheet.get();
    }
    public StringProperty styleSheetProperty() {
        return styleSheet;
    }
    public void setStyleSheet(String styleSheet) {
        this.styleSheet.set(styleSheet);
    }
}
