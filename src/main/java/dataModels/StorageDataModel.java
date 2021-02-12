package dataModels;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.QueryBuilder;
import dbModels.StorageModel;
import fxModels.ApplicantFxModel;
import fxModels.InstrumentFxModel;
import fxModels.StorageFxModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DatabaseTools;

import java.sql.SQLException;

public class StorageDataModel {
    private ObservableList<StorageFxModel> storageList= FXCollections.observableArrayList();
    private ObjectProperty<StorageFxModel> currentStorage= new SimpleObjectProperty<>(new StorageFxModel());
    private ObservableList<StorageFxModel> storageSelectedItemsList=FXCollections.observableArrayList();


    public void listInitialize() throws SQLException {
        storageList.clear();
        Dao<StorageModel,Integer> storageDao= DaoManager.createDao(DatabaseTools.getConnectionSource(), StorageModel.class);
        QueryBuilder<StorageModel, Integer> storageQueryBuilder= storageDao.queryBuilder();
        GenericRawResults<StorageFxModel> rawResults=storageDao.queryRaw(
                "Select \n" +
                        "STORAGE.idStorage, \n" +
                        "INSTRUMENTS.idInstrument,NAMES.instrumentName,TYPES.typeName, PRODUCERS.producerName, \n" +
                        "INSTRUMENTS.serialNumber, INSTRUMENTS.identificationNumber, RANGES.rangeName,\n" +
                        "INSTRUMENTS.length,INSTRUMENTS.diameter,\n" +
                        "APPLICANTS.idApplicant, APPLICANTS.shortName, APPLICANTS.fullName, APPLICANTS.postCode, APPLICANTS.city, APPLICANTS.street, APPLICANTS.houseNumber, APPLICANTS.flatNumber, APPLICANTS.status,\n" +
                        "STORAGE.entryDate, STORAGE.entryUser, \n" +
                        "GROUP_CONCAT(REGISTER.calibrationDate), GROUP_CONCAT(REGISTER.calibrationUser), GROUP_CONCAT(REGISTER.cardNumber),\n" +
                        "STORAGE.spendDate, STORAGE.spendUser, STORAGE.storageRemarks\n" +
                        "from STORAGE \n" +
                        "left join REGISTER on STORAGE.idStorage=REGISTER.storage \n" +
                        "join INSTRUMENTS on STORAGE.instrument=INSTRUMENTS.idInstrument\n" +
                        "join APPLICANTS on APPLICANTS.idApplicant=INSTRUMENTS.applicant\n" +
                        "join NAMES on INSTRUMENTS.name=NAMES.idName\n" +
                        "join TYPES on INSTRUMENTS.type=TYPES.idType\n" +
                        "join PRODUCERS on INSTRUMENTS.producer=PRODUCERS.idProducer\n" +
                        "join RANGES on INSTRUMENTS.range=RANGES.idRange\n" +
                        "group by idStorage;",
                new RawRowMapper<StorageFxModel>() {
                    @Override
                    public StorageFxModel mapRow(String[] columns, String[] res) throws SQLException {
                        System.out.println("Wymiar: " +res.length);
                        return new StorageFxModel(Integer.parseInt(res[0]),
                                new InstrumentFxModel(Integer.parseInt(res[1]), res[2], res[3],res[4], res[5], res[6] ,res[7],res[8], res[9],
                                        new ApplicantFxModel(Integer.parseInt(res[10]), res[11], res[12], res[13], res[14], res[15], res[16], res[17], res[18])),
                                res[19],res[20],res[21],res[22],res[23],res[24],res[25],res[26]);
                    }
                });
        for (StorageFxModel storageFxModel: rawResults){
            storageList.add(storageFxModel);
            // System.out.println(citiesFx.toString());
        }
    }
    public ObservableList<StorageFxModel> getStorageList() {
        return storageList;
    }
    public void setStorageList(ObservableList<StorageFxModel> storageList) {
        this.storageList = storageList;
    }
    public StorageFxModel getCurrentStorage() {
        return currentStorage.get();
    }
    public ObjectProperty<StorageFxModel> currentStorageProperty() {
        return currentStorage;
    }
    public void setCurrentStorage(StorageFxModel currentStorage) {
        this.currentStorage.set(currentStorage);
    }
    public ObservableList<StorageFxModel> getStorageSelectedItemsList() {
        return storageSelectedItemsList;
    }
    public void setStorageSelectedItemsList(ObservableList<StorageFxModel> storageSelectedItemsList) {
        this.storageSelectedItemsList = storageSelectedItemsList;
    }
}
