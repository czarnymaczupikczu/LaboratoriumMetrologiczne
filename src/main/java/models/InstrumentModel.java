package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import models.instrument.InstrumentNameModel;
import models.instrument.InstrumentProducerModel;
import models.instrument.InstrumentRangeModel;
import models.instrument.InstrumentTypeModel;

/**
 * Klasa implementująca model danych w tabeli INSTRUMENTS
 */
@DatabaseTable(tableName = "INSTRUMENTS")
public class InstrumentModel {
    @DatabaseField(generatedId = true)
    private Integer idInstrument;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private InstrumentNameModel instrumentName;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private InstrumentTypeModel instrumentType;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private InstrumentProducerModel instrumentProducer;
    @DatabaseField
    private String serialNumber;
    @DatabaseField
    private String identificationNumber;
    @DatabaseField
    private Integer length;
    @DatabaseField
    private Integer diameter;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private InstrumentRangeModel instrumentRange;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ApplicantModel applicant;

    //Konstruktory
    public InstrumentModel() {
    }
    public InstrumentModel(Integer idInstrument, InstrumentNameModel instrumentName, InstrumentTypeModel instrumentType, InstrumentProducerModel instrumentProducer, String serialNumber, String identificationNumber, Integer length, Integer diameter, InstrumentRangeModel instrumentRange, ApplicantModel applicant) {
        this.idInstrument = idInstrument;
        this.instrumentName = instrumentName;
        this.instrumentType = instrumentType;
        this.instrumentProducer = instrumentProducer;
        this.serialNumber = serialNumber;
        this.identificationNumber = identificationNumber;
        this.length = length;
        this.diameter = diameter;
        this.instrumentRange = instrumentRange;
        this.applicant = applicant;
    }

    //Gettery i Settery
    public Integer getIdInstrument() {
        return idInstrument;
    }
    public void setIdInstrument(Integer idInstrument) {
        this.idInstrument = idInstrument;
    }
    public InstrumentNameModel getInstrumentName() {
        return instrumentName;
    }
    public void setInstrumentName(InstrumentNameModel instrumentName) {
        this.instrumentName = instrumentName;
    }
    public InstrumentTypeModel getInstrumentType() {
        return instrumentType;
    }
    public void setInstrumentType(InstrumentTypeModel instrumentType) {
        this.instrumentType = instrumentType;
    }
    public InstrumentProducerModel getInstrumentProducer() {
        return instrumentProducer;
    }
    public void setInstrumentProducer(InstrumentProducerModel instrumentProducer) {
        this.instrumentProducer = instrumentProducer;
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
    public Integer getLength() {
        return length;
    }
    public void setLength(Integer length) {
        this.length = length;
    }
    public Integer getDiameter() {
        return diameter;
    }
    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }
    public InstrumentRangeModel getInstrumentRange() {
        return instrumentRange;
    }
    public void setInstrumentRange(InstrumentRangeModel instrumentRange) {
        this.instrumentRange = instrumentRange;
    }
    public ApplicantModel getApplicant() {
        return applicant;
    }
    public void setApplicant(ApplicantModel applicant) {
        this.applicant = applicant;
    }
}
