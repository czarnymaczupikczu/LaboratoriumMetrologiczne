package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli YEARS
 */
@DatabaseTable(tableName = "YEARS")
public class YearModel {

    public static final String ID_YEAR="idYear";
    public static final String YEAR="year";

    @DatabaseField(generatedId = true,columnName = ID_YEAR)
    private Integer idYear;
    @DatabaseField(columnName = YEAR)
    private String year;

    //Konstruktory
    public YearModel() {
    }
    public YearModel(Integer idYear, String year) {
        this.idYear = idYear;
        this.year = year;
    }

    //Gettery i Settery
    public Integer getIdYear() {
        return idYear;
    }
    public void setIdYear(Integer idYear) {
        this.idYear = idYear;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
}
