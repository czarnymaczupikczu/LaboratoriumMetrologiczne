package dbModels.instrument;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli RANGES
 */
@DatabaseTable(tableName = "RANGES")
public class RangeModel {

    public static final String ID_RANGE="idRange";
    public static final String RANGE_NAME="rangeName";

    @DatabaseField(generatedId = true,columnName = ID_RANGE)
    private Integer idRange;
    @DatabaseField(columnName = RANGE_NAME)
    private String rangeName;

    //Konstruktory
    public RangeModel() {
    }
    public RangeModel(Integer idRange, String rangeName) {
        this.idRange = idRange;
        this.rangeName = rangeName;
    }

    //Gettery i Settery
    public Integer getIdRange() {
        return idRange;
    }
    public void setIdRange(Integer idRange) {
        this.idRange = idRange;
    }
    public String getRangeName() {
        return rangeName;
    }
    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }
}
