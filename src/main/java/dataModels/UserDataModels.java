package dataModels;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.QueryBuilder;
import fxModels.CitiesFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dbModels.City;
import dbModels.User;
import utils.DatabaseTools;

import java.sql.SQLException;

public class UserDataModels {

    private ObservableList<User> usersList= FXCollections.observableArrayList();
    private ObservableList<CitiesFx> cityList=FXCollections.observableArrayList();
    private User user= new User();
    
    public void initialize() throws SQLException {
        usersList.clear();
        Dao<User, Integer> userDao= DaoManager.createDao(DatabaseTools.getConnectionSource(),User.class);
        Dao<City, Integer> cityDao=DaoManager.createDao(DatabaseTools.getConnectionSource(),City.class);
        //List<User> users = userDao.queryForAll();
        QueryBuilder<User, Integer> userQueryBuilder= userDao.queryBuilder();
        QueryBuilder<City, Integer> cityQueryBuilder=cityDao.queryBuilder();
        //
        //cityQueryBuilder.leftJoin(userQueryBuilder).prepareStatementString()
        GenericRawResults<CitiesFx> rawResults=cityDao.queryRaw("select *from cities LEFT JOIN"+" user_test ON cities.idCity=user_test.city_id",
                new RawRowMapper<CitiesFx>() {
                    @Override
                    public CitiesFx mapRow(String[] strings, String[] strings1) throws SQLException {


                        System.out.println("Wymiar: " +strings1.length);
                        return new CitiesFx(strings1[0], strings1[1],strings1[2],strings1[3],strings1[4]);
                    }
                });
        for (CitiesFx citiesFx : rawResults){
            cityList.add(citiesFx);
           // System.out.println(citiesFx.toString());
        }


       // usersList.addAll(users);
    }
    public void show(){
       cityList.forEach(city ->{
           System.out.println(city.toString());
       });
    }
    
    
    public UserDataModels() {
    }
    public ObservableList<User> getUsersList() {
        return usersList;
    }
    public void setUsersList(ObservableList<User> usersList) {
        this.usersList = usersList;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
