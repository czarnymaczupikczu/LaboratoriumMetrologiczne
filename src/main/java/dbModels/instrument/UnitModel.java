package dbModels.instrument;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli UNITS
 */
@DatabaseTable(tableName = "UNITS")
public class UnitModel implements BaseModel {

    public static final String ID_UNIT="idUnit";
    public static final String UNIT_NAME="unitName";

    @DatabaseField(generatedId = true, columnName = ID_UNIT)
    private Integer idUnit;
    @DatabaseField(columnName = UNIT_NAME)
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
    public String getName(){
        return unitName;
    }
}
