package fxModels;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CommonFxModel {
    private IntegerProperty id=new SimpleIntegerProperty();
    private StringProperty value=new SimpleStringProperty();

    //Konstruktory
    public CommonFxModel() {
    }
    public CommonFxModel(int id, String value) {
        this.id = new SimpleIntegerProperty(id);
        this.value = new SimpleStringProperty(value);
    }

    //Gettery i Settery
    public int getId() {
        return id.get();
    }
    public IntegerProperty idProperty() {
        return id;
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public String getValue() {
        return value.get();
    }
    public StringProperty valueProperty() {
        return value;
    }
    public void setValue(String value) {
        this.value.set(value);
    }
}
