package dbModels;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli STOREHOUSE
 */

@DatabaseTable(tableName = "STORAGE")
public class StorageModel {

    public static final String ID_STORAGE="idStorage";
    public static final String INSTRUMENT="instrument";
    public static final String ENTRY_DATE="entryDate";
    public static final String ENTRY_USER="entryUser";
    public static final String SPEND_DATE="spendDate";
    public static final String SPEND_USER="spendUser";
    public static final String STORAGE_REMARKS="storageRemarks";
    public static final String CALIBRATION_REMARKS="calibrationRemarks";

    @DatabaseField(generatedId = true,columnName = ID_STORAGE)
    private Integer idStorage;
    @DatabaseField(foreign = true,foreignAutoRefresh = true, columnName = INSTRUMENT)
    private InstrumentModel instrument;
    @DatabaseField(columnName = ENTRY_DATE)
    private String entryDate;
    @DatabaseField(foreign=true,foreignAutoRefresh = true, columnName = ENTRY_USER)
    private UserModel entryUser;
    @DatabaseField(columnName = SPEND_DATE)
    private String spendDate;
    @DatabaseField(foreign=true,foreignAutoRefresh = true, columnName = SPEND_USER)
    private UserModel spendUser;
    @DatabaseField(dataType = DataType.LONG_STRING, columnName = STORAGE_REMARKS)
    private String storageRemarks;
    @DatabaseField(dataType = DataType.LONG_STRING, columnName = CALIBRATION_REMARKS)
    private String calibrationRemarks;

    //Konstruktory
    public StorageModel() {
    }
    public StorageModel(Integer idStorage, InstrumentModel instrument, String entryDate, UserModel entryUser, String spendDate, UserModel spendUser, String storageRemarks, String calibrationRemarks) {
        this.idStorage = idStorage;
        this.instrument = instrument;
        this.entryDate = entryDate;
        this.entryUser = entryUser;
        this.spendDate = spendDate;
        this.spendUser = spendUser;
        this.storageRemarks = storageRemarks;
        this.calibrationRemarks=calibrationRemarks;
    }

    //Gettery i Settery
    public Integer getIdStorage() {
        return idStorage;
    }
    public void setIdStorage(Integer idStorage) {
        this.idStorage = idStorage;
    }
    public InstrumentModel getInstrument() {
        return instrument;
    }
    public void setInstrument(InstrumentModel instrument) {
        this.instrument = instrument;
    }
    public String getEntryDate() {
        return entryDate;
    }
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
    public UserModel getEntryUser() {
        return entryUser;
    }
    public void setEntryUser(UserModel entryUser) {
        this.entryUser = entryUser;
    }
    public String getSpendDate() {
        return spendDate;
    }
    public void setSpendDate(String spendDate) {
        this.spendDate = spendDate;
    }
    public UserModel getSpendUser() {
        return spendUser;
    }
    public void setSpendUser(UserModel spendUser) {
        this.spendUser = spendUser;
    }
    public String getStorageRemarks() {
        return storageRemarks;
    }
    public void setStorageRemarks(String storageRemarks) {
        this.storageRemarks = storageRemarks;
    }
    public String getCalibrationRemarks() {
        return calibrationRemarks;
    }
    public void setCalibrationRemarks(String calibrationRemarks) {
        this.calibrationRemarks = calibrationRemarks;
    }
}
