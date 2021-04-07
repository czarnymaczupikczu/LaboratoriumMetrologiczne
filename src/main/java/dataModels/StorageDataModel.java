package dataModels;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.QueryBuilder;
import dbModels.RegisterModel;
import dbModels.StorageModel;
import fxModels.ApplicantFxModel;
import fxModels.InstrumentFxModel;
import fxModels.StorageFxModel;
import fxModels.VeryShortRegisterFxModel;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.w3c.dom.ls.LSOutput;
import utils.DatabaseTools;
import utils.ShowAlert;
import utils.database.CommonDao;

import java.sql.SQLException;
import java.util.List;

import static dbModels.StorageModel.ID_STORAGE;

public class StorageDataModel {
    private ObservableList<StorageFxModel> storageList= FXCollections.observableArrayList();
    private ObjectProperty<StorageFxModel> currentStorage= new SimpleObjectProperty<>(new StorageFxModel());
    private ObservableList<StorageFxModel> storageSelectedItemsList=FXCollections.observableArrayList();
    private FilteredList<StorageFxModel> filteredStorageList= new FilteredList<>(storageList,p->true);
    private StorageModel currentStorageModel=new StorageModel();
    private VeryShortRegisterFxModel veryShortRegisterFxModel;


    public void listInitialize(String storageState, String storageYear) {
        storageList.clear();
        Dao<StorageModel,Integer> storageDao= null;
        try {
            storageDao = DaoManager.createDao(DatabaseTools.getConnectionSource(), StorageModel.class);
            GenericRawResults<StorageFxModel> rawResults=storageDao.queryRaw(
                "Select \n" +
                        "STORAGE.idStorage, \n" +
                        "INSTRUMENTS.idInstrument,NAMES.instrumentName,TYPES.typeName, PRODUCERS.producerName, \n" +
                        "INSTRUMENTS.serialNumber, INSTRUMENTS.identificationNumber,INSTRUMENTS.length, \n" +
                        "INSTRUMENTS.diameter,RANGES.rangeName,\n" +
                        "APPLICANTS.idApplicant, APPLICANTS.shortName, APPLICANTS.fullName, APPLICANTS.postCode, APPLICANTS.city, APPLICANTS.street, APPLICANTS.number, APPLICANTS.status,\n" +
                        "STORAGE.entryDate, u1.initials, \n" +
                        "GROUP_CONCAT(REGISTER.calibrationDate,', '), GROUP_CONCAT(u2.initials,', '), GROUP_CONCAT(REGISTER.cardNumber,', '),\n" +
                        "STORAGE.spendDate, u3.initials, STORAGE.instrumentRemarks, STORAGE.calibrationRemarks\n" +
                        "from STORAGE \n" +
                        "left join REGISTER on STORAGE.idStorage=REGISTER.storage \n" +
                        "join INSTRUMENTS on STORAGE.instrument=INSTRUMENTS.idInstrument\n" +
                        "join APPLICANTS on APPLICANTS.idApplicant=INSTRUMENTS.applicant\n" +
                        "join NAMES on INSTRUMENTS.name=NAMES.idName\n" +
                        "join TYPES on INSTRUMENTS.type=TYPES.idType\n" +
                        "join PRODUCERS on INSTRUMENTS.producer=PRODUCERS.idProducer\n" +
                        "join RANGES on INSTRUMENTS.range=RANGES.idRange\n" +
                        "join USERS u1 on STORAGE.entryUser=u1.idUser\n" +
                        "left join USERS u2 on REGISTER.calibrationUser=u2.idUser\n" +
                        "left join USERS u3 on STORAGE.spendUser=u3.idUser\n" +
                        createSQLStatement(storageState,storageYear)+"\n"+
                        "group by idStorage;",
                new RawRowMapper<StorageFxModel>() {
                    @Override
                    public StorageFxModel mapRow(String[] columns, String[] res) throws SQLException {
                        return createStorageFxModel(res);
                    }
                });
            int i=1;
            for (StorageFxModel storageFxModel: rawResults){
                storageFxModel.setStorageIndex(i);
                storageList.add(storageFxModel);
                i=i+1;
            }
        } catch (SQLException e) {
            ShowAlert.display(e.getMessage());
        }
    }
    public void initializeCurrentStorageModel(){
        CommonDao commonDao=new CommonDao();
        currentStorageModel=commonDao.queryForFirst(StorageModel.class,ID_STORAGE,this.currentStorage.get().getIdStorage());
    }
    public void addFilterToObservableList(String newValue){
        filteredStorageList.setPredicate(item -> {
            if (item.getInstrument().getName().toUpperCase().contains(newValue.toUpperCase())||item.getInstrument().getType().toUpperCase().contains(newValue.toUpperCase())||
                    item.getInstrument().getProducer().toUpperCase().contains(newValue.toUpperCase())||item.getInstrument().getSerialNumber().toUpperCase().contains(newValue.toUpperCase())||
                    item.getInstrument().getIdentificationNumber().toUpperCase().contains(newValue.toUpperCase())||item.getInstrument().getRange().toUpperCase().contains(newValue.toUpperCase())||
                    item.getInstrument().getApplicant().getShortName().toUpperCase().contains(newValue.toUpperCase())) {
                return true;
            } else {
                return false;
            }
        });
    }
    public String createSQLStatement(String storageState, String storageYear){
        String sqlStatement = null;
        if(storageState.equals(storageYear)){   //Wszystkie i wszystkie
            sqlStatement="";
        }else if(storageState.equals("Wszystkie") && !storageYear.equals("Wszystkie")){
            sqlStatement="WHERE STORAGE.entryDate LIKE '%"+storageYear+"%' OR STORAGE.spendDate LIKE '%"+storageYear+"%'";
        }else if(!storageState.equals("Wszystkie") && storageYear.equals("Wszystkie")) {
            sqlStatement = "WHERE STORAGE.spendDate = ''";
        }
        else {
            sqlStatement="WHERE STORAGE.spendDate = '' AND STORAGE.entryDate LIKE '%"+storageYear+"%'";
        }
        return sqlStatement;
    }
    public StorageFxModel createStorageFxModel(String[] results){
        StorageFxModel tempStorageObject = new StorageFxModel();
        //Ustawianie poszczególnych pól w sumie zrobię to tak żeby każde pole było ustawione wprost
        tempStorageObject.setIdStorage(Integer.parseInt(results[0]));
        tempStorageObject.setInstrument(createInstrumentFxModel(results));
        tempStorageObject.setEntryDate(results[18]);
        tempStorageObject.setEntryUser(results[19]);
        if(results[21]!=null) {
            tempStorageObject.setCalibrationDates(results[20]);
            tempStorageObject.setCalibrationUsers(results[21]);
            tempStorageObject.setCardNumbers(results[22]);
        }else
        {
            tempStorageObject.setCalibrationDates("");
            tempStorageObject.setCalibrationUsers("");
            tempStorageObject.setCardNumbers("");
        }
        if(results[24]!=null) {
            tempStorageObject.setSpendDate(results[23]);
            tempStorageObject.setSpendUser(results[24]);
        }else
        {
            tempStorageObject.setSpendDate("");
            tempStorageObject.setSpendUser("");
        }
        tempStorageObject.setInstrumentRemarks(results[25]);
        tempStorageObject.setCalibrationRemarks(results[26]);
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
        tempApplicantObject.setNumber(results[16]);
        tempApplicantObject.setStatus(results[17]);
        return tempApplicantObject;
    }

    public void initializeVeryShortRegisterFxModel(String registerKind){

        try {
            Dao<RegisterModel,Integer> registerDao = DaoManager.createDao(DatabaseTools.getConnectionSource(), RegisterModel.class);
            GenericRawResults<String[]> rawResults=registerDao.queryRaw(
                    "Select \n" +
                            "MAX(REGISTER.idRegister),REGISTER.idRegisterByYear, REGISTER.cardNumber, REGISTER.calibrationDate \n" +
                            "FROM REGISTER WHERE REGISTER.registerKind LIKE '"+registerKind+"';");
            this.veryShortRegisterFxModel= new VeryShortRegisterFxModel(rawResults.getResults().get(0));

        } catch (SQLException e) {
            ShowAlert.display(e.getMessage());
        }
    }
    //Settery i gettery
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
    public FilteredList<StorageFxModel> getFilteredStorageList() {
        return filteredStorageList;
    }
    public void setFilteredStorageList(FilteredList<StorageFxModel> filteredStorageList) {
        this.filteredStorageList = filteredStorageList;
    }
    public StorageModel getCurrentStorageModel() {
        return currentStorageModel;
    }
    public void setCurrentStorageModel(StorageModel currentStorageModel) {
        this.currentStorageModel = currentStorageModel;
    }
    public VeryShortRegisterFxModel getVeryShortRegisterFxModel() {
        return veryShortRegisterFxModel;
    }
    public void setVeryShortRegisterFxModel(VeryShortRegisterFxModel veryShortRegisterFxModel) {
        this.veryShortRegisterFxModel = veryShortRegisterFxModel;
    }
}
