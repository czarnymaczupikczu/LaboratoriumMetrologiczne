package dataModels;

import dbModels.UserModel;
import dbModels.YearModel;
import utils.DatabaseTools;
import utils.database.CommonDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Obiekty klasy MainDataModel będzie zawierał wspólne dane dla kontrolerów przynajmniej taka jest pierwsza wersja
 */
public class MainDataModel {

    private final YearModel year=new YearModel();
    private UserModel user=new UserModel();

    private final List<String> yearComboBoxList = new ArrayList<>(initYearList());
    private final List<String> storageStateComboBoxList= Arrays.asList("Wszystkie","W magazynie");
    private final List<String> registerStateComboBoxList=Arrays.asList("Wszystkie","ON","OFF");

    public List<String> initYearList() {
        List<String> tempYearList=new ArrayList<>();
        CommonDao commonDao=new CommonDao();
            List<YearModel> yearList = commonDao.queryForAll(YearModel.class);
            this.year.setIdYear((yearList.get(yearList.size()-1)).getIdYear());
            this.year.setYear((yearList.get(yearList.size()-1)).getYear());
            tempYearList.add("Wszystkie");
        for (YearModel yearModel : yearList) {
            tempYearList.add(yearModel.getYear());
        }
        DatabaseTools.closeConnection();
        return tempYearList;
    }

    public YearModel getYear() {
        return year;
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
    public List<String> getStorageStateComboBoxList() {
        return storageStateComboBoxList;
    }
    public List<String> getRegisterStateComboBoxList() {
        return registerStateComboBoxList;
    }

}
