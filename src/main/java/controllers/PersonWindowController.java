package controllers;

import dataModels.PersonDataModel;
import fxModels.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonWindowController {

    private PersonDataModel personDataModel=new PersonDataModel();
    @FXML
    private TableView<Person> nameTableView;
    @FXML
    private TableColumn<Person, String> nameTableColumn;
    @FXML
    private Label nameLabel;
    @FXML
    public void initialize(){
        personDataModel.listInitialize();
        nameTableColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
        nameTableView.setItems(personDataModel.getPersonObservableList());




        //nameLabel.textProperty().bind(personDataModel.getSinglePerson2().nameProperty());

        nameTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
                //1. Option doesn't work
                personDataModel.setSinglePerson(newValue);
                nameLabel.textProperty().bind(personDataModel.getSinglePerson().nameProperty());
                //System.out.println(personDataModel.getSinglePerson().getName());
                //2. Option works
                //personDataModel.getSinglePerson().setName(newValue.getName());
                System.out.println(personDataModel.getSinglePerson().getName());
            }
        });

    }

}
