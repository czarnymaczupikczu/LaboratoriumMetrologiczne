package dataModels;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import dbModels.UserModel;
import dbModels.YearModel;
import utils.DatabaseTools;
import utils.ShowAlert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Obiekty klasy MainDataModel będzie zawierał wspólne dane dla kontrolerów przynajmniej taka jest pierwsza wersja
 */
public class MainDataModel {

    private YearModel year=new YearModel();
    private UserModel user=new UserModel();

    private List<String> yearComboBoxList = new ArrayList<>(initYearList());
    private List<String> storageStateComboBoxList= Arrays.asList("Wszystkie","W magazynie");
    private List<String> registerStateComboBoxList=Arrays.asList("Wszystkie","ON","OFF");

    public List<String> initYearList() {
        List<String> tempYearList=new ArrayList<>();
        try {
            Dao<YearModel, Integer> yearDao = DaoManager.createDao(DatabaseTools.getConnectionSource(), YearModel.class);
            List<YearModel> yearList = yearDao.queryForAll();
            this.year.setIdYear((yearList.get(yearList.size()-1)).getIdYear());
            this.year.setYear((yearList.get(yearList.size()-1)).getYear());
            tempYearList.add("Wszystkie");
            yearList.forEach(year -> {
                tempYearList.add(year.getYear());
            });
            DatabaseTools.closeConnection();
        } catch (SQLException e) {
            ShowAlert.display(e.getMessage());
        }
        return tempYearList;
    }

    public YearModel getYear() {
        return year;
    }
    public void setYear(YearModel year) {
        this.year = year;
    }
    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }
    public List<String> getYearComboBoxList() {
        return yearComboBoxList;
    }
    public void setYearComboBoxList(List<String> yearComboBoxList) {
        this.yearComboBoxList = yearComboBoxList;
    }
    public List<String> getStorageStateComboBoxList() {
        return storageStateComboBoxList;
    }
    public void setStorageStateComboBoxList(List<String> storageStateComboBoxList) {
        this.storageStateComboBoxList = storageStateComboBoxList;
    }
    public List<String> getRegisterStateComboBoxList() {
        return registerStateComboBoxList;
    }
    public void setRegisterStateComboBoxList(List<String> registerStateComboBoxList) {
        this.registerStateComboBoxList = registerStateComboBoxList;
    }
}
