package utils;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dbModels.*;
import dbModels.instrument.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Klasa zawiera metody do obsługi bazy danych.
 */
public class DatabaseTools {
    private final static String DATABASE_URL = "jdbc:sqlite:baza.db";
    private static ConnectionSource connectionSource;
    private static Dao<UserModel, Integer> userModelDao;

    /**
     * Metoda służąca do nawiązywania połączenia z bazą danych
     */
    public static void createConnectionSource(){
        try {
            connectionSource = new JdbcConnectionSource(DATABASE_URL);
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }

    /**
     * Metoda zwraca obiekt ConnectionSource, połączenie do bazy danych
     * @return ConnectionSource
     */
    public static ConnectionSource getConnectionSource(){
        if(connectionSource == null){
            createConnectionSource();
        }
        return connectionSource;
    }
    /**
     * Metoda służąca do zamykania połączenia z bazą danych
     */
    public static void closeConnection(){
        if(connectionSource != null){
            try {
                connectionSource.close();
            } catch (IOException e) {
                CommonTools.displayAlert(e.getMessage());
            }
        }
    }

    /**
     * Metoda do inicjalizacji bazy danych. Tworzenia nowych tabel itd
     */
    public static void initDatabase(){
        try {
            TableUtils.createTableIfNotExists(DatabaseTools.getConnectionSource(), NameModel.class);
            TableUtils.createTableIfNotExists(DatabaseTools.getConnectionSource(), ProducerModel.class);
            TableUtils.createTableIfNotExists(DatabaseTools.getConnectionSource(), RangeModel.class);
            TableUtils.createTableIfNotExists(DatabaseTools.getConnectionSource(), TypeModel.class);
            TableUtils.createTableIfNotExists(DatabaseTools.getConnectionSource(), UnitModel.class);
            TableUtils.createTableIfNotExists(DatabaseTools.getConnectionSource(), ApplicantModel.class);
            TableUtils.createTableIfNotExists(DatabaseTools.getConnectionSource(), InstrumentModel.class);
            TableUtils.createTableIfNotExists(DatabaseTools.getConnectionSource(), RegisterModel.class);
            TableUtils.createTableIfNotExists(DatabaseTools.getConnectionSource(), StorageModel.class);
            TableUtils.createTableIfNotExists(DatabaseTools.getConnectionSource(), UserModel.class);
            TableUtils.createTableIfNotExists(DatabaseTools.getConnectionSource(), YearModel.class);
        } catch (SQLException e) {
            CommonTools.displayAlert(e.getMessage());
        }
    }


}


/*
            TableUtils.createTableIfNotExists(dbSqlite.getConnectionSource(), clientModel.class);
            TableUtils.createTableIfNotExists(dbSqlite.getConnectionSource(), instrumentModel.class);
            TableUtils.createTableIfNotExists(dbSqlite.getConnectionSource(), instrumentNameModel.class);
            TableUtils.createTableIfNotExists(dbSqlite.getConnectionSource(), instrumentProducerModel.class);
            TableUtils.createTableIfNotExists(dbSqlite.getConnectionSource(), instrumentRangeModel.class);
            TableUtils.createTableIfNotExists(dbSqlite.getConnectionSource(), instrumentTypeModel.class);
            TableUtils.createTableIfNotExists(dbSqlite.getConnectionSource(), register2Model.class);
            TableUtils.createTableIfNotExists(dbSqlite.getConnectionSource(), registerModel.class);
            TableUtils.createTableIfNotExists(dbSqlite.getConnectionSource(), storehouseModel.class);
            TableUtils.createTableIfNotExists(dbSqlite.getConnectionSource(), unitModel.class);

            TableUtils.createTableIfNotExists(dbSqlite.getConnectionSource(), yearModel.class);
        */