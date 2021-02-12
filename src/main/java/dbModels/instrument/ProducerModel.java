package dbModels.instrument;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli PRODUCERS
 */
@DatabaseTable(tableName = "PRODUCERS")
public class ProducerModel {
    public static final String ID_PRODUCER="idProducer";
    public static final String PRODUCER_NAME="producerName";

    @DatabaseField(generatedId = true,columnName = ID_PRODUCER)
    private Integer idProducer;
    @DatabaseField(columnName = PRODUCER_NAME)
    private String producerName;

    //Konstruktory
    public ProducerModel() {
    }
    public ProducerModel(Integer idProducer, String producerName) {
        this.idProducer = idProducer;
        this.producerName = producerName;
    }

    //Gettery i Settery
    public Integer getIdProducer() {
        return idProducer;
    }
    public void setIdProducer(Integer idProducer) {
        this.idProducer = idProducer;
    }
    public String getProducerName() {
        return producerName;
    }
    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }
}
