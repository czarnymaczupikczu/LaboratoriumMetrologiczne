package dataModels;

import fxModels.Person;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonDataModel {
    private ObservableList<Person> personObservableList= FXCollections.observableArrayList();
    private SimpleObjectProperty<Person> singlePerson=new SimpleObjectProperty<>(new Person());

    public void listInitialize(){
        personObservableList.clear();
        personObservableList.add(new Person(new SimpleStringProperty("John")));
        personObservableList.add(new Person(new SimpleStringProperty("Mike")));
        personObservableList.add(new Person(new SimpleStringProperty("Ana")));
    }
    public ObservableList<Person> getPersonObservableList() {
        return personObservableList;
    }
    public void setPersonObservableList(ObservableList<Person> personObservableList) {
        this.personObservableList = personObservableList;
    }
    public Person getSinglePerson() {
        return singlePerson.get();
    }
    public SimpleObjectProperty<Person> singlePersonProperty() {
        return singlePerson;
    }
    public void setSinglePerson(Person singlePerson) {
        this.singlePerson.set(singlePerson);
    }
}
