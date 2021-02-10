package models.instrument;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli INSTRUMENT_TYPES
 */
@DatabaseTable(tableName = "INSTRUMENT_TYPES")
public class InstrumentTypeModel {
    @DatabaseField(generatedId = true)
    private Integer idInstrumentType;
    @DatabaseField
    private String instrumentType;

    //Konstruktory
    public InstrumentTypeModel() {
    }
    public InstrumentTypeModel(Integer idInstrumentType, String instrumentType) {
        this.idInstrumentType = idInstrumentType;
        this.instrumentType = instrumentType;
    }

    //Gettery i Settery
    public Integer getIdInstrumentType() {
        return idInstrumentType;
    }
    public void setIdInstrumentType(Integer idInstrumentType) {
        this.idInstrumentType = idInstrumentType;
    }
    public String getInstrumentType() {
        return instrumentType;
    }
    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }
}
