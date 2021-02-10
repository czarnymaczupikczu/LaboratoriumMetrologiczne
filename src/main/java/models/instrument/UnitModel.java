package models.instrument;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli UNITS
 */
@DatabaseTable(tableName = "UNITS")
public class UnitModel {
    @DatabaseField(generatedId = true)
    private Integer idUnit;
    @DatabaseField
    private String unitName;

    //Konstruktory
    public UnitModel() {
    }
    public UnitModel(Integer idUnit, String unitName) {
        this.idUnit = idUnit;
        this.unitName = unitName;
    }

    //Gettery i Settery
    public Integer getIdUnit() {
        return idUnit;
    }
    public void setIdUnit(Integer idUnit) {
        this.idUnit = idUnit;
    }
    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
