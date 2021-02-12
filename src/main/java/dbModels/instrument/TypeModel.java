package dbModels.instrument;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa implementująca model danych w tabeli TYPES
 */
@DatabaseTable(tableName = "TYPES")
public class TypeModel {

    public static final String ID_TYPE="idType";
    public static final String TYPE_NAME="typeName";

    @DatabaseField(generatedId = true,columnName = ID_TYPE)
    private Integer idType;
    @DatabaseField(columnName = TYPE_NAME)
    private String typeName;

    //Konstruktory
    public TypeModel() {
    }
    public TypeModel(Integer idType, String typeName) {
        this.idType = idType;
        this.typeName = typeName;
    }

    //Gettery i Settery
    public Integer getIdType() {
        return idType;
    }
    public void setIdType(Integer idType) {
        this.idType = idType;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
