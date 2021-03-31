package dataModels;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import dbModels.RegisterModel;
import fxModels.ApplicantFxModel;
import fxModels.ShortRegisterFxModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DatabaseTools;
import utils.ShowAlert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShortRegisterDataModel {
    private ObservableList<ShortRegisterFxModel> registerList= FXCollections.observableArrayList();
    private ObjectProperty<ShortRegisterFxModel> currentRegisterElement=new SimpleObjectProperty<>(new ShortRegisterFxModel());
    public void listInitialize(String concatIdInstrument){
        registerList.clear();
        try {
            Dao<RegisterModel,Integer> registerDao = DaoManager.createDao(DatabaseTools.getConnectionSource(), RegisterModel.class);
            GenericRawResults<ShortRegisterFxModel> rawResults=registerDao.queryRaw(
                    "Select \n" +
                            "REGISTER.cardNumber,STORAGE.entryDate, u1.login,\n"+
                            "REGISTER.calibrationDate, u2.login,\n" +
                            "STORAGE.spendDate, u3.login,\n" +
                            "REGISTER.certificateNumber, REGISTER.documentKind, REGISTER.agreementNumber, \n"+
                            "STORAGE.instrumentRemarks,STORAGE.calibrationRemarks, \n" +
                            "APPLICANTS.idApplicant, APPLICANTS.shortName, APPLICANTS.fullName, APPLICANTS.postCode, APPLICANTS.city, APPLICANTS.street, APPLICANTS.number, APPLICANTS.status \n" +
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
                            "left join USERS u3 on STORAGE.spendUser=u3.idUser \n"+
                            "WHERE REGISTER.state ='ON' AND ("+ decodeConcatIdInstrument(concatIdInstrument)+");",
                    new RawRowMapper<ShortRegisterFxModel>() {
                        @Override
                        public ShortRegisterFxModel mapRow(String[] columns, String[] res) throws SQLException {
                            return createShortRegisterFxModel(res);
                        }
                    });
            int i=1;
            for (ShortRegisterFxModel shortRegisterFxModel: rawResults){
                shortRegisterFxModel.setId(i);
                registerList.add(shortRegisterFxModel);
                i=i+1;
            }
        } catch (SQLException e) {
            ShowAlert.display(e.getMessage());
        }
    }
    private ShortRegisterFxModel createShortRegisterFxModel(String[] results){
        ShortRegisterFxModel tempRegisterObject=new ShortRegisterFxModel();
        tempRegisterObject.setCardNumber(results[0]);
        tempRegisterObject.setEntryDate(results[1]);
        tempRegisterObject.setEntryUser(results[2]);
        tempRegisterObject.setCalibrationDate(results[3]);
        tempRegisterObject.setCalibrateUser(results[4]);
        tempRegisterObject.setSpendDate(results[5]);
        tempRegisterObject.setSpendUser(results[6]);
        tempRegisterObject.setCertificateNumber(results[7]);
        tempRegisterObject.setDocumentKind(results[8]);
        tempRegisterObject.setAgreementNumber(results[9]);
        tempRegisterObject.setInstrumentRemarks(results[10]);
        tempRegisterObject.setCalibrationRemarks(results[11]);
        tempRegisterObject.setApplicantFxModel(createApplicantFxModel(results));
        return tempRegisterObject;
    }
    private ApplicantFxModel createApplicantFxModel(String[] results){
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
    private String decodeConcatIdInstrument(String concatIdInstrument){
        String condition1="INSTRUMENTS.idInstrument=";
        String condition2=" OR INSTRUMENTS.idInstrument=";
        String endCondition="";
        int start=0;
        int end=concatIdInstrument.length();
        List<Integer> datalist=new ArrayList<>();
        while((end=concatIdInstrument.indexOf(",",start))>-1){
            datalist.add(Integer.parseInt(concatIdInstrument.substring(start,end)));
            start=end+1;
        }
        end=concatIdInstrument.length();
        System.out.println("Start= "+start+" end= "+ end);
        datalist.add(Integer.parseInt(concatIdInstrument.substring(start, end)));
        for(int i=0;i<datalist.size();i++){
            if (i==0){
                endCondition=condition1+datalist.get(i);
                System.out.println(datalist.get(i));
            }
            else{
                endCondition=endCondition+condition2+datalist.get(i);
            }
        }
        System.out.println(endCondition);
        return endCondition;
    }
    public ObservableList<ShortRegisterFxModel> getRegisterList() {
        return registerList;
    }
    public void setRegisterList(ObservableList<ShortRegisterFxModel> registerList) {
        this.registerList = registerList;
    }
    public ShortRegisterFxModel getCurrentRegisterElement() {
        return currentRegisterElement.get();
    }
    public ObjectProperty<ShortRegisterFxModel> currentRegisterElementProperty() {
        return currentRegisterElement;
    }
    public void setCurrentRegisterElement(ShortRegisterFxModel currentRegisterElement) {
        this.currentRegisterElement.set(currentRegisterElement);
    }
}
