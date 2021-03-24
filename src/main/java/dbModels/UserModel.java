package dbModels;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * Klasa implementująca model danych w tabeli USERS
 */
@DatabaseTable(tableName = "USERS")
public class UserModel implements ComplexModel {

    public static final String ID_USER ="idUser";
    public static final String FIRST_NAME="firstName";
    public static final String LAST_NAME="lastName";
    public static final String LOGIN="login";
    public static final String PASSWORD="password";
    public static final String PERMISSION_LEVEL="permissionLevel";
    public static final String INITIALS="initials";
    public static final String STYLE_SHEET="styleSheet";

    @DatabaseField(generatedId = true,columnName = ID_USER)
    private Integer idUser;
    @DatabaseField(columnName = FIRST_NAME)
    private String firstName;
    @DatabaseField(columnName = LAST_NAME)
    private String lastName;
    @DatabaseField(columnName = LOGIN)
    private String login;
    @DatabaseField(columnName = PASSWORD)
    private String password;
    @DatabaseField(columnName = PERMISSION_LEVEL)
    private String permissionLevel; //admin,User
    @DatabaseField(columnName = INITIALS)
    private String initials;
    @DatabaseField(columnName = STYLE_SHEET)
    private String styleSheet; //być może kiedyś coś takiego będzie

    //Konstruktory
    public UserModel() {
    }
    public UserModel(UserModel user){
        this.idUser = user.getIdUser();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.permissionLevel = user.getPermissionLevel();
        this.initials=user.getInitials();
        this.styleSheet = user.getStyleSheet();
    }
    public UserModel(Integer idUser, String firstName, String lastName, String login, String password, String permissionLevel, String initials,String styleSheet) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.permissionLevel = permissionLevel;
        this.initials=initials;
        this.styleSheet = styleSheet;
    }

    //Gettery i Settery
    public Integer getIdUser() {
        return idUser;
    }
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPermissionLevel() {
        return permissionLevel;
    }
    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
    public String getInitials() {
        return initials;
    }
    public void setInitials(String initials) {
        this.initials = initials;
    }
    public String getStyleSheet() {
        return styleSheet;
    }
    public void setStyleSheet(String styleSheet) {
        this.styleSheet = styleSheet;
    }


}
