package models.instrument;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli INSTRUMENT_NAMES
 */
@DatabaseTable(tableName = "INSTRUMENT_NAMES")
public class InstrumentNameModel {
    @DatabaseField(generatedId = true)
    private Integer idInstrumentName;
    @DatabaseField
    private String instrumentName;

    //Konstruktory
    public InstrumentNameModel() {
    }
    public InstrumentNameModel(Integer idInstrumentName, String instrumentName) {
        this.idInstrumentName = idInstrumentName;
        this.instrumentName = instrumentName;
    }

    //Gettery i Settery
    public Integer getIdInstrumentName() {
        return idInstrumentName;
    }
    public void setIdInstrumentName(Integer idInstrumentName) {
        this.idInstrumentName = idInstrumentName;
    }
    public String getInstrumentName() {
        return instrumentName;
    }
    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }
}
