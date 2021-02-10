package models.instrument;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli INSTRUMENT_PRODUCERS
 */
@DatabaseTable(tableName = "INSTRUMENT_PRODUCERS")
public class InstrumentProducerModel {
    @DatabaseField(generatedId = true)
    private Integer idInstrumentProducer;
    @DatabaseField
    private String instrumentProducer;

    //Konstruktory
    public InstrumentProducerModel() {
    }
    public InstrumentProducerModel(Integer idInstrumentProducer, String instrumentProducer) {
        this.idInstrumentProducer = idInstrumentProducer;
        this.instrumentProducer = instrumentProducer;
    }

    //Gettery i Settery
    public Integer getIdInstrumentProducer() {
        return idInstrumentProducer;
    }
    public void setIdInstrumentProducer(Integer idInstrumentProducer) {
        this.idInstrumentProducer = idInstrumentProducer;
    }
    public String getInstrumentProducer() {
        return instrumentProducer;
    }
    public void setInstrumentProducer(String instrumentProducer) {
        this.instrumentProducer = instrumentProducer;
    }
}
