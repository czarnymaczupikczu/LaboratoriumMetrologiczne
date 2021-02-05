package fxModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private StringProperty name=new SimpleStringProperty();
    public Person() {
    }
    public Person(StringProperty name) {
        this.name = name;
    }
    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }
}
