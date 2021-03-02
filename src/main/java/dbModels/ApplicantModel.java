package dbModels;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli APPLICANTS
 */
@DatabaseTable(tableName = "APPLICANTS")
public class ApplicantModel {

    public static final String ID_APPLICANT="idApplicant";
    public static final String SHORT_NAME="shortName";
    public static final String FULL_NAME="fullName";
    public static final String POST_CODE="postCode";
    public static final String CITY="city";
    public static final String STREET="street";
    public static final String NUMBER="number";
    public static final String APPLICANT_STATUS="status";

    @DatabaseField(generatedId = true, columnName=ID_APPLICANT)
    private Integer idApplicant;
    @DatabaseField(columnName = SHORT_NAME)
    private String shortName;
    @DatabaseField(dataType = DataType.LONG_STRING,columnName = FULL_NAME)
    private String fullName;
    @DatabaseField(columnName = POST_CODE)
    private String postCode;
    @DatabaseField(columnName = CITY)
    private String city;
    @DatabaseField(columnName = STREET)
    private String street;
    @DatabaseField(columnName = NUMBER)
    private String number;
    @DatabaseField(columnName = APPLICANT_STATUS)
    private String status;

    //Konstruktory
    public ApplicantModel() {

    }
    public ApplicantModel(Integer idApplicant, String shortName, String fullName, String postCode, String city, String street, String number, String status) {
        this.idApplicant = idApplicant;
        this.shortName = shortName;
        this.fullName = fullName;
        this.postCode = postCode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.status = status;
    }

    //Gettery i Settery
    public Integer getIdApplicant() {
        return idApplicant;
    }
    public void setIdApplicant(Integer idApplicant) {
        this.idApplicant = idApplicant;
    }
    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
