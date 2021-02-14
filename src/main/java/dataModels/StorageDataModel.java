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
import javafx.application.Application;
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
                        "INSTRUMENTS.serialNumber, INSTRUMENTS.identificationNumber,INSTRUMENTS.length, \n" +
                        "INSTRUMENTS.diameter,RANGES.rangeName,\n" +
                        "APPLICANTS.idApplicant, APPLICANTS.shortName, APPLICANTS.fullName, APPLICANTS.postCode, APPLICANTS.city, APPLICANTS.street, APPLICANTS.houseNumber, APPLICANTS.flatNumber, APPLICANTS.status,\n" +
                        "STORAGE.entryDate, u1.login, \n" +
                        "GROUP_CONCAT(REGISTER.calibrationDate), GROUP_CONCAT(u2.login), GROUP_CONCAT(REGISTER.cardNumber),\n" +
                        "STORAGE.spendDate, u3.login, STORAGE.storageRemarks\n" +
                        "from STORAGE \n" +
                        "left join REGISTER on STORAGE.idStorage=REGISTER.storage \n" +
                        "join INSTRUMENTS on STORAGE.instrument=INSTRUMENTS.idInstrument\n" +
                        "join APPLICANTS on APPLICANTS.idApplicant=INSTRUMENTS.applicant\n" +
                        "join NAMES on INSTRUMENTS.name=NAMES.idName\n" +
                        "join TYPES on INSTRUMENTS.type=TYPES.idType\n" +
                        "join PRODUCERS on INSTRUMENTS.producer=PRODUCERS.idProducer\n" +
                        "join RANGES on INSTRUMENTS.range=RANGES.idRange\n" +
                        "join USERS u1 on STORAGE.entryUser=u1.idUser\n" +
                        "join USERS u2 on REGISTER.calibrationUser=u2.idUser\n" +
                        "join USERS u3 on STORAGE.spendUser=u3.idUser\n" +
                        "group by idStorage;",
                new RawRowMapper<StorageFxModel>() {
                    @Override
                    public StorageFxModel mapRow(String[] columns, String[] res) throws SQLException {
                        //System.out.println("Wymiar: " +res.length);
                        return createStorageFxModel(res);
                    }
                });
        for (StorageFxModel storageFxModel: rawResults){
            storageList.add(storageFxModel);
        }
    }
    public StorageFxModel createStorageFxModel(String[] results){
        StorageFxModel tempStorageObject = new StorageFxModel();
        //Ustawianie poszczególnych pól w sumie zrobię to tak żeby każde pole było ustawione wprost
        tempStorageObject.setIdStorage(Integer.parseInt(results[0]));
        tempStorageObject.setInstrument(createInstrumentFxModel(results));
        tempStorageObject.setEntryDate(results[19]);
        tempStorageObject.setEntryUser(results[20]);
        tempStorageObject.setCalibrationDates(results[21]);
        tempStorageObject.setCalibrationUsers(results[22]);
        tempStorageObject.setCardNumbers(results[23]);
        tempStorageObject.setSpendDate(results[24]);
        tempStorageObject.setSpendUser(results[25]);
        tempStorageObject.setStorageRemarks(results[26]);
        return tempStorageObject;
    }
    public InstrumentFxModel createInstrumentFxModel(String[] results){
        InstrumentFxModel tempInstrumentObject = new InstrumentFxModel();
        tempInstrumentObject.setIdInstrument(Integer.parseInt(results[1]));
        tempInstrumentObject.setName(results[2]);
        tempInstrumentObject.setType(results[3]);
        tempInstrumentObject.setProducer(results[4]);
        tempInstrumentObject.setSerialNumber(results[5]);
        tempInstrumentObject.setIdentificationNumber(results[6]);
        tempInstrumentObject.setLength(results[7]);
        tempInstrumentObject.setDiameter(results[8]);
        tempInstrumentObject.setRange(results[9]);
        tempInstrumentObject.setApplicant(createApplicantFxModel(results));
        return tempInstrumentObject;
    }
    public ApplicantFxModel createApplicantFxModel(String[] results){
        ApplicantFxModel tempApplicantObject = new ApplicantFxModel();
        tempApplicantObject.setIdApplicant(Integer.parseInt(results[10]));
        tempApplicantObject.setShortName(results[11]);
        tempApplicantObject.setFullName(results[12]);
        tempApplicantObject.setPostCode(results[13]);
        tempApplicantObject.setCity(results[14]);
        tempApplicantObject.setStreet(results[15]);
        tempApplicantObject.setFlatNumber(results[16]);
        tempApplicantObject.setHouseNumber(results[17]);
        tempApplicantObject.setStatus(results[18]);
        return tempApplicantObject;
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
