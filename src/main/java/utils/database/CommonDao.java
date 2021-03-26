package utils.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import dbModels.ComplexModel;
import dbModels.UserModel;
import dbModels.instrument.BaseModel;
import utils.CommonTools;
import utils.DatabaseTools;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Klasa implementująca wspólne DAO z dostępem do wszystkich tabel
 */
public class CommonDao {
    protected final ConnectionSource connectionSource;

    public CommonDao() {
        this.connectionSource = DatabaseTools.getConnectionSource();
    }
    public <T, I> List<T> queryForEq (String columnName, Class<T> cls,String value){
        Dao<T, I> dao=getDao(cls);
        try {
            return dao.queryForEq(columnName,value);
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }
    public <T, I> void createBaseModel(BaseModel baseModel){
        Dao<T, I> dao=getDao((Class<T>) baseModel.getClass());
        try {
            dao.create((T) baseModel);
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }
    public <T, I> void create(ComplexModel complexModel){
        Dao<T, I> dao=getDao((Class<T>) complexModel.getClass());
        try {
            dao.create((T) complexModel);
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }
    public <T, I> void delete(BaseModel baseModel){
        Dao<T, I> dao=getDao((Class<T>) baseModel.getClass());
        try {
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }
    public void deleteUser(UserModel userModel){
        Dao<UserModel, Integer> dao=getDao(UserModel.class);
        try {
            dao.delete(userModel);
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }


    public <T, I> Dao<T,I> getDao (Class<T> cls){
        try {
            return DaoManager.createDao(connectionSource,cls);
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }
    public <T, I>QueryBuilder<T, I> getQueryBuilder(Class<T> cls){
        Dao<T, I> dao=getDao(cls);
        return dao.queryBuilder();
    }


    public < T extends ComplexModel,I> void createOrUpdate( ComplexModel complexModel) {
        Dao <T,I>  dao=getDao((Class<T>) complexModel.getClass());
        try {
            dao.createOrUpdate((T)complexModel);
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally {
            this.closeDbConnection();
        }
    }
    public < T extends BaseModel,I> void createOrUpdateBaseModel( BaseModel baseModel) {
        Dao <T,I>  dao=getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T)baseModel);
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally {
            this.closeDbConnection();
        }
    }
    //Ogólnie różne zapytania
    //Zwraca całą zawrtość tabeli
    public <T, I> List<T> queryForAll(Class<T> cls){
        try {
            Dao<T,I> dao=getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }
    //Różne wersje zapytania select pierwszy parametr to zawsze Class, potem nazwa kolumny i wartość
    public <T, I> List<T> selectAndStatement(Class<T> cls, String columnName, String value){ //string
        try {
            Dao<T,I> dao=getDao(cls);
            return dao.query(dao.queryBuilder().where().eq(columnName,value).prepare());
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }
    public <T, I> List<T> selectAndStatement(Class<T> cls, String columnName, Integer value){ //integer
        try {
            Dao<T,I> dao=getDao(cls);
            return dao.query(dao.queryBuilder().where().eq(columnName,value).prepare());
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }
    public <T, I> List<T> selectAndStatement(Class<T> cls, String columnName1, String value1, String columnaName2, String value2){ //string i string
        try {
            Dao<T,I> dao=getDao(cls);
            return dao.query(dao.queryBuilder().where().eq(columnName1,value1).and().eq(columnaName2,value2).prepare());
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }
    public <T, I> List<T> selectAndStatement(Class<T> cls, String columnName1, String value1, String columnaName2, Integer value2){ //string i integer
        try {
            Dao<T,I> dao=getDao(cls);
            return dao.query(dao.queryBuilder().where().eq(columnName1,value1).and().eq(columnaName2,value2).prepare());
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }
    public <T, I> List<T> selectOrStatement(Class<T> cls, String columnName1, Integer value1, String columnaName2, Integer value2){ //integer i integer
        try {
            Dao<T,I> dao=getDao(cls);
            return dao.query(dao.queryBuilder().where().eq(columnName1,value1).or().eq(columnaName2,value2).prepare());
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }
    public <T, I> List<T> selectAndStatement(Class<T> cls, String columnName1, String value1, String columnName2, String value2, String columnName3, Integer value3){ //string i string i integer
        try {
            Dao<T,I> dao=getDao(cls);
            return dao.query(dao.queryBuilder().where().eq(columnName1,value1).and().eq(columnName2,value2).and().eq(columnName3,value3).prepare());
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }
    //Selecty z like zamiast where
    public <T, I> T queryForFirstWithFullLike(Class<T> cls,String columnName, String value){
        try {
            Dao<T,I> dao=getDao(cls);
            return  dao.queryForFirst(dao.queryBuilder().where().like(columnName,"%"+value+"%").prepare());
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }


    public <T, I> List<T> selectAndWithFullLikeStatement(Class<T> cls, String columnName1, String value1, String columnName2, String value2, String columnName3, String value3){
        try {
            Dao<T,I> dao=getDao(cls);
            return dao.query(dao.queryBuilder().where().eq(columnName1,value1).and().eq(columnName2,value2).and().like(columnName3,"%"+value3+"%").prepare());
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }


    public <T, I> List<T> selectAndWithLikeStatement(Class<T> cls, String columnName, String value){
        try {
            Dao<T,I> dao=getDao(cls);
            return dao.query(dao.queryBuilder().where().like(columnName,value).prepare());
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }

    public <T, I> T queryForFirst(Class<T> cls,String columnName, String value){
        try {
            Dao<T,I> dao=getDao(cls);
            return  dao.queryForFirst(dao.queryBuilder().where().like(columnName,value).prepare());
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }
    public <T, I> T queryForFirst(Class<T> cls,String columnName, Integer value){
        try {
            Dao<T,I> dao=getDao(cls);
            return  dao.queryForFirst(dao.queryBuilder().where().like(columnName,value).prepare());
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }finally{
            this.closeDbConnection();
        }
        return null;
    }














    private void closeDbConnection(){
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }


}
