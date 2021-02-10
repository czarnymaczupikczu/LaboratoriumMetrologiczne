package models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli STOREHOUSE
 */

@DatabaseTable(tableName = "STOREHOUSE")
public class StorehouseModel {
    @DatabaseField(generatedId = true)
    private Integer idStorehouse;
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private InstrumentModel instrument;
    @DatabaseField
    private String addDate;
    @DatabaseField(foreign=true,foreignAutoRefresh = true)
    private UserModel userWhoAdd;
    @DatabaseField
    private String leftDate;
    @DatabaseField(foreign=true,foreignAutoRefresh = true)
    private UserModel userWhoLeft;
    @DatabaseField(dataType = DataType.LONG_STRING)
    private String remarks;

    //Konstruktory
    public StorehouseModel() {
    }
    public StorehouseModel(Integer idStorehouse, InstrumentModel instrument, String addDate, UserModel userWhoAdd, String leftDate, UserModel userWhoLeft, String remarks) {
        this.idStorehouse = idStorehouse;
        this.instrument = instrument;
        this.addDate = addDate;
        this.userWhoAdd = userWhoAdd;
        this.leftDate = leftDate;
        this.userWhoLeft = userWhoLeft;
        this.remarks = remarks;
    }

    //Gettery i Settery
    public Integer getIdStorehouse() {
        return idStorehouse;
    }
    public void setIdStorehouse(Integer idStorehouse) {
        this.idStorehouse = idStorehouse;
    }
    public InstrumentModel getInstrument() {
        return instrument;
    }
    public void setInstrument(InstrumentModel instrument) {
        this.instrument = instrument;
    }
    public String getAddDate() {
        return addDate;
    }
    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }
    public UserModel getUserWhoAdd() {
        return userWhoAdd;
    }
    public void setUserWhoAdd(UserModel userWhoAdd) {
        this.userWhoAdd = userWhoAdd;
    }
    public String getLeftDate() {
        return leftDate;
    }
    public void setLeftDate(String leftDate) {
        this.leftDate = leftDate;
    }
    public UserModel getUserWhoLeft() {
        return userWhoLeft;
    }
    public void setUserWhoLeft(UserModel userWhoLeft) {
        this.userWhoLeft = userWhoLeft;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
