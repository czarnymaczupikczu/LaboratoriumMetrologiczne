package dbModels;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import dbModels.instrument.NameModel;
import dbModels.instrument.ProducerModel;
import dbModels.instrument.RangeModel;
import dbModels.instrument.TypeModel;

/**
 * Klasa implementująca model danych w tabeli INSTRUMENTS
 */
@DatabaseTable(tableName = "INSTRUMENTS")
public class InstrumentModel {

    public static final String ID_INSTRUMENT="idInstrument";
    public static final String NAME="name";
    public static final String TYPE="type";
    public static final String PRODUCER="producer";
    public static final String SERIAL_NUMBER="serialNumber";
    public static final String IDENTIFICATION_NUMBER="identificationNumber";
    public static final String LENGTH="length";
    public static final String DIAMETER="diameter";
    public static final String RANGE="range";
    public static final String APPLICANT="applicant";

    @DatabaseField(generatedId = true,columnName = ID_INSTRUMENT)
    private Integer idInstrument;
    @DatabaseField(foreign = true, foreignAutoRefresh = true,columnName = NAME)
    private NameModel name;
    @DatabaseField(foreign = true, foreignAutoRefresh = true,columnName = TYPE)
    private TypeModel type;
    @DatabaseField(foreign = true, foreignAutoRefresh = true,columnName = PRODUCER)
    private ProducerModel producer;
    @DatabaseField(columnName = SERIAL_NUMBER)
    private String serialNumber;
    @DatabaseField(columnName = IDENTIFICATION_NUMBER)
    private String identificationNumber;
    @DatabaseField(columnName = LENGTH)
    private String length;
    @DatabaseField(columnName = DIAMETER)
    private String diameter;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = RANGE)
    private RangeModel range;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = APPLICANT)
    private ApplicantModel applicant;

    //Konstruktory
    public InstrumentModel() {
    }
    public InstrumentModel(Integer idInstrument, NameModel name, TypeModel type, ProducerModel producer, String serialNumber, String identificationNumber, String length, String diameter, RangeModel range, ApplicantModel applicant) {
        this.idInstrument = idInstrument;
        this.name = name;
        this.type = type;
        this.producer = producer;
        this.serialNumber = serialNumber;
        this.identificationNumber = identificationNumber;
        this.length = length;
        this.diameter = diameter;
        this.range = range;
        this.applicant = applicant;
    }

    //Gettery i Settery
    public Integer getIdInstrument() {
        return idInstrument;
    }
    public void setIdInstrument(Integer idInstrument) {
        this.idInstrument = idInstrument;
    }
    public NameModel getName() {
        return name;
    }
    public void setName(NameModel name) {
        this.name = name;
    }
    public TypeModel getType() {
        return type;
    }
    public void setType(TypeModel type) {
        this.type = type;
    }
    public ProducerModel getProducer() {
        return producer;
    }
    public void setProducer(ProducerModel producer) {
        this.producer = producer;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getIdentificationNumber() {
        return identificationNumber;
    }
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public String getDiameter() {
        return diameter;
    }
    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }
    public RangeModel getRange() {
        return range;
    }
    public void setRange(RangeModel range) {
        this.range = range;
    }
    public ApplicantModel getApplicant() {
        return applicant;
    }
    public void setApplicant(ApplicantModel applicant) {
        this.applicant = applicant;
    }
}
