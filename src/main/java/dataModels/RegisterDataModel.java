package dataModels;

import fxModels.RegisterFxModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class RegisterDataModel {
    private ObservableList<RegisterFxModel> registerList= FXCollections.observableArrayList();
    private ObjectProperty<RegisterFxModel> currentRegisterElement=new SimpleObjectProperty<>(new RegisterFxModel());
    private ObservableList<RegisterFxModel> registerSelectedItemsList=FXCollections.observableArrayList();
    private FilteredList<RegisterFxModel>   filteredRegisterList=new FilteredList<>(registerList,p->true);
}
