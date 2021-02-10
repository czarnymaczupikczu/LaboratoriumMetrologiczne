package models.instrument;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli INSTRUMENT_RANGES
 */
@DatabaseTable(tableName = "INSTRUMENT_RANGES")
public class InstrumentRangeModel {
    @DatabaseField(generatedId = true)
    private Integer idInstrumentRange;
    @DatabaseField
    private String instrumentRange;

    //Konstruktory
    public InstrumentRangeModel() {
    }
    public InstrumentRangeModel(Integer idInstrumentRange, String instrumentRange) {
        this.idInstrumentRange = idInstrumentRange;
        this.instrumentRange = instrumentRange;
    }
    //Gettery i Settery
    public Integer getIdInstrumentRange() {
        return idInstrumentRange;
    }
    public void setIdInstrumentRange(Integer idInstrumentRange) {
        this.idInstrumentRange = idInstrumentRange;
    }
    public String getInstrumentRange() {
        return instrumentRange;
    }
    public void setInstrumentRange(String instrumentRange) {
        this.instrumentRange = instrumentRange;
    }
}
