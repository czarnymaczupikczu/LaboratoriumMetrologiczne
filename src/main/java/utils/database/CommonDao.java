package utils.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
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
    public <T, I> List<T> getListWithSimpleLikeSelect(Class<T> cls,String columnName, String value){
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
    public <T, I> List<T> selectWithTwoConditions(Class<T> cls,String columnName1, String value1,String columnaName2, String value2){
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
    private void closeDbConnection(){
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }


}
