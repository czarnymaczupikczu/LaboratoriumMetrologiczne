package dbModels.instrument;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli NAMES
 */
@DatabaseTable(tableName = "NAMES")
public class NameModel implements BaseModel {

    public static final String ID_NAME="idName";
    public static final String INSTRUMENT_NAME="instrumentName";

    @DatabaseField(generatedId = true,columnName = ID_NAME)
    private Integer idName;
    @DatabaseField(columnName = INSTRUMENT_NAME)
    private String instrumentName;

    //Konstruktory
    public NameModel() {
    }
    public NameModel(Integer idName, String instrumentName) {
        this.idName = idName;
        this.instrumentName = instrumentName;
    }
    public static  NameModel returnBaseModel(BaseModel baseModel){
        return new NameModel(baseModel.getId(),baseModel.getName());
    }
    //Gettery i Settery
    public Integer getIdName() {
        return idName;
    }
    public void setIdName(Integer idName) {
        this.idName = idName;
    }
    public String getInstrumentName() {
        return instrumentName;
    }
    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getName(){
        return instrumentName;
    }
    public Integer getId(){return idName;}

    //To string dla testów

    @Override
    public String toString() {
        return "NameModel{" +
                "idName=" + idName +
                ", instrumentName='" + instrumentName + '\'' +
                '}';
    }
}
