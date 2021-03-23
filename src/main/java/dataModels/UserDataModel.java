package dataModels;

import dbModels.UserModel;
import dbModels.instrument.BaseModel;
import fxModels.CommonFxModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.database.CommonDao;

import java.util.List;

public class UserDataModel {

    private ObservableList<UserModel> usersObservableList= FXCollections.observableArrayList();
    private UserModel selectedUser=new UserModel();

    public void init(){
        usersObservableList.clear();
        CommonDao commonDao = new CommonDao();
        List<UserModel> dataList = commonDao.queryForAll(UserModel.class);
        dataList.forEach(element ->{
            usersObservableList.add(element);
        });
    }

    public ObservableList<UserModel> getUsersObservableList() {
        return usersObservableList;
    }
    public void setUsersObservableList(ObservableList<UserModel> usersObservableList) {
        this.usersObservableList = usersObservableList;
    }
    public UserModel getSelectedUser() {
        return selectedUser;
    }
    public void setSelectedUser(UserModel selectedUser) {
        this.selectedUser = selectedUser;
    }
}
