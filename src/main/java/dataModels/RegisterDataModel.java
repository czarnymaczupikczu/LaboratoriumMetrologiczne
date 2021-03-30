package dataModels;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import dbModels.RegisterModel;
import fxModels.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import utils.DatabaseTools;
import utils.ShowAlert;
import java.sql.SQLException;

public class RegisterDataModel {
    private ObservableList<RegisterFxModel> registerList= FXCollections.observableArrayList();
    private ObjectProperty<RegisterFxModel> currentRegisterElement=new SimpleObjectProperty<>(new RegisterFxModel());
    private ObservableList<RegisterFxModel> registerSelectedItemsList=FXCollections.observableArrayList();
    private FilteredList<RegisterFxModel>   filteredRegisterList=new FilteredList<>(registerList,p->true);
    private String registerType;

    public void listInitialize(String registerState, String registerYear) {
        registerList.clear();
        try {
            Dao<RegisterModel,Integer> registerDao = DaoManager.createDao(DatabaseTools.getConnectionSource(), RegisterModel.class);
            GenericRawResults<RegisterFxModel> rawResults=registerDao.queryRaw(
                    "Select \n" +
                            "REGISTER.idRegister,REGISTER.idRegisterByYear,\n"+
                            "STORAGE.idStorage, \n" +
                            "INSTRUMENTS.idInstrument,NAMES.instrumentName,TYPES.typeName, PRODUCERS.producerName, \n" +
                            "INSTRUMENTS.serialNumber, INSTRUMENTS.identificationNumber,INSTRUMENTS.length, \n" +
                            "INSTRUMENTS.diameter,RANGES.rangeName,\n" +
                            "APPLICANTS.idApplicant, APPLICANTS.shortName, APPLICANTS.fullName, APPLICANTS.postCode, APPLICANTS.city, APPLICANTS.street, APPLICANTS.number, APPLICANTS.status,\n" +
                            "STORAGE.entryDate, u1.initials, \n" +
                            "STORAGE.spendDate, u3.initials, STORAGE.instrumentRemarks,\n" +
                            "REGISTER.cardNumber, REGISTER.calibrationDate, u2.login,\n" +
                            "REGISTER.certificateNumber, REGISTER.documentKind, REGISTER.agreementNumber, REGISTER.state, REGISTER.registerRemarks \n"+
                            "from REGISTER \n" +
                            "join STORAGE on REGISTER.storage=STORAGE.idStorage \n" +
                            "join INSTRUMENTS on STORAGE.instrument=INSTRUMENTS.idInstrument\n" +
                            "join APPLICANTS on INSTRUMENTS.applicant=APPLICANTS.idApplicant\n" +
                            "join NAMES on INSTRUMENTS.name=NAMES.idName\n" +
                            "join TYPES on INSTRUMENTS.type=TYPES.idType\n" +
                            "join PRODUCERS on INSTRUMENTS.producer=PRODUCERS.idProducer\n" +
                            "join RANGES on INSTRUMENTS.range=RANGES.idRange\n" +
                            "join USERS u1 on STORAGE.entryUser=u1.idUser\n" +
                            "join USERS u2 on REGISTER.calibrationUser=u2.idUser\n" +
                            "left join USERS u3 on STORAGE.spendUser=u3.idUser\n" +
                            createSQLStatement(registerState,registerYear)+";",
                    new RawRowMapper<RegisterFxModel>() {
                        @Override
                        public RegisterFxModel mapRow(String[] columns, String[] res) throws SQLException {
                            return createRegisterFxModel(res);
                        }
                    });
            int i=1;
            for (RegisterFxModel registerFxModel: rawResults){
                registerList.add(registerFxModel);
                i=i+1;
            }
        } catch (SQLException e) {
            ShowAlert.display(e.getMessage());
        }
    }
    public String createSQLStatement(String registerState, String registerYear){
        String sqlStatement = null;
        if(registerState.equals(registerYear)){   //Wszystkie i wszystkie
            sqlStatement="WHERE REGISTER.registerKind='"+this.registerType+"'";
        }else if(registerState.equals("Wszystkie") && !registerYear.equals("Wszystkie")){
            sqlStatement="WHERE REGISTER.registerKind='"+this.registerType+"' AND REGISTER.calibrationDate LIKE '%"+registerYear+"%'";
        }else if(!registerState.equals("Wszystkie") && registerYear.equals("Wszystkie")) {
            sqlStatement = "WHERE REGISTER.registerKind='"+this.registerType+"' AND REGISTER.state ='"+registerState+"'";
        }
        else {
            sqlStatement="WHERE REGISTER.registerKind='"+this.registerType+"' AND REGISTER.state = '"+registerState+"' AND REGISTER.calibrationDate LIKE '%"+registerYear+"%'";
        }
        return sqlStatement;
    }
   public RegisterFxModel createRegisterFxModel(String[] results){
        RegisterFxModel tempRegisterObject=new RegisterFxModel();
        tempRegisterObject.setIdRegister(Integer.parseInt(results[0]));
        tempRegisterObject.setIdRegisterByYear(Integer.parseInt(results[1]));
        tempRegisterObject.setStorage(createShortStorageFxModel(results));
        tempRegisterObject.setCardNumber(results[25]);
        tempRegisterObject.setCalibrationDate(results[26]);
        tempRegisterObject.setCalibrationUser(results[27]);
        tempRegisterObject.setCertificateNumber(results[28]);
        tempRegisterObject.setDocumentKind(results[29]);
        tempRegisterObject.setAgreementNumber(results[30]);
        tempRegisterObject.setState(results[31]);
        tempRegisterObject.setRegisterRemarks(results[32]);
        return tempRegisterObject;
   }

    public ShortStorageFxModel createShortStorageFxModel(String[] results){
        ShortStorageFxModel tempStorageObject = new ShortStorageFxModel();
        //Ustawianie poszczególnych pól w sumie zrobię to tak żeby każde pole było ustawione wprost
        tempStorageObject.setIdStorage(Integer.parseInt(results[2]));
        tempStorageObject.setInstrument(createInstrumentFxModel(results));
        tempStorageObject.setEntryDate(results[20]);
        tempStorageObject.setEntryUser(results[21]);
        tempStorageObject.setSpendDate(results[22]);
        tempStorageObject.setSpendUser(results[23]);
        tempStorageObject.setInstrumentRemarks(results[24]);
        return tempStorageObject;
    }
    public InstrumentFxModel createInstrumentFxModel(String[] results){
        InstrumentFxModel tempInstrumentObject = new InstrumentFxModel();
        tempInstrumentObject.setIdInstrument(Integer.parseInt(results[3]));
        tempInstrumentObject.setName(results[4]);
        tempInstrumentObject.setType(results[5]);
        tempInstrumentObject.setProducer(results[6]);
        tempInstrumentObject.setSerialNumber(results[7]);
        tempInstrumentObject.setIdentificationNumber(results[8]);
        tempInstrumentObject.setLength(results[9]);
        tempInstrumentObject.setDiameter(results[10]);
        tempInstrumentObject.setRange(results[11]);
        tempInstrumentObject.setApplicant(createApplicantFxModel(results));
        return tempInstrumentObject;
    }
    public ApplicantFxModel createApplicantFxModel(String[] results){
        ApplicantFxModel tempApplicantObject = new ApplicantFxModel();
        tempApplicantObject.setIdApplicant(Integer.parseInt(results[12]));
        tempApplicantObject.setShortName(results[13]);
        tempApplicantObject.setFullName(results[14]);
        tempApplicantObject.setPostCode(results[15]);
        tempApplicantObject.setCity(results[16]);
        tempApplicantObject.setStreet(results[17]);
        tempApplicantObject.setNumber(results[18]);
        tempApplicantObject.setStatus(results[19]);
        return tempApplicantObject;
    }
     public void addFilterToObservableList(String newValue){
        filteredRegisterList.setPredicate(item -> {
            if (item.getStorage().getInstrument().getName().toUpperCase().contains(newValue.toUpperCase())||item.getStorage().getInstrument().getType().toUpperCase().contains(newValue.toUpperCase())||
                    item.getStorage().getInstrument().getProducer().toUpperCase().contains(newValue.toUpperCase())||item.getStorage().getInstrument().getSerialNumber().toUpperCase().contains(newValue.toUpperCase())||
                    item.getStorage().getInstrument().getIdentificationNumber().toUpperCase().contains(newValue.toUpperCase())||item.getStorage().getInstrument().getRange().toUpperCase().contains(newValue.toUpperCase())||
                    item.getStorage().getInstrument().getApplicant().getShortName().toUpperCase().contains(newValue.toUpperCase())) {
                return true;
            } else {
                return false;
            }
        });
    }

    public ObservableList<RegisterFxModel> getRegisterList() {
        return registerList;
    }
    public void setRegisterList(ObservableList<RegisterFxModel> registerList) {
        this.registerList = registerList;
    }
    public RegisterFxModel getCurrentRegisterElement() {
        return currentRegisterElement.get();
    }
    public ObjectProperty<RegisterFxModel> currentRegisterElementProperty() {
        return currentRegisterElement;
    }
    public void setCurrentRegisterElement(RegisterFxModel currentRegisterElement) {
        this.currentRegisterElement.set(currentRegisterElement);
    }
    public ObservableList<RegisterFxModel> getRegisterSelectedItemsList() {
        return registerSelectedItemsList;
    }
    public void setRegisterSelectedItemsList(ObservableList<RegisterFxModel> registerSelectedItemsList) {
        this.registerSelectedItemsList = registerSelectedItemsList;
    }
    public FilteredList<RegisterFxModel> getFilteredRegisterList() {
        return filteredRegisterList;
    }
    public void setFilteredRegisterList(FilteredList<RegisterFxModel> filteredRegisterList) {
        this.filteredRegisterList = filteredRegisterList;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

}
