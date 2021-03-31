package dataModels;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import dbModels.InstrumentModel;
import fxModels.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import utils.DatabaseTools;
import utils.ShowAlert;
import java.sql.SQLException;

public class InstrumentsDataModel {
    private ObservableList<InstrumentFxModel> instrumentsList= FXCollections.observableArrayList();
    private ObjectProperty<InstrumentFxModel> currentInstrument= new SimpleObjectProperty<>(new InstrumentFxModel());
    private FilteredList<InstrumentFxModel> filteredInstrumentsList= new FilteredList<>(instrumentsList, p->true);

    public void listInitialize(){
        instrumentsList.clear();
        try {
            Dao<InstrumentModel,Integer> instrumentDao = DaoManager.createDao(DatabaseTools.getConnectionSource(), InstrumentModel.class);
            GenericRawResults<InstrumentFxModel> rawResults=instrumentDao.queryRaw(
                    "Select \n" +
                            "INSTRUMENTS.idInstrument,NAMES.instrumentName,TYPES.typeName, PRODUCERS.producerName, \n" +
                            "INSTRUMENTS.serialNumber, INSTRUMENTS.identificationNumber,INSTRUMENTS.length, \n" +
                            "INSTRUMENTS.diameter,RANGES.rangeName,\n" +
                            "APPLICANTS.idApplicant, GROUP_CONCAT(APPLICANTS.shortName,', '), APPLICANTS.fullName, APPLICANTS.postCode, APPLICANTS.city, APPLICANTS.street, APPLICANTS.number, APPLICANTS.status,GROUP_CONCAT(INSTRUMENTS.idInstrument,',')\n" +
                            "from INSTRUMENTS \n" +
                            "join APPLICANTS on INSTRUMENTS.applicant=APPLICANTS.idApplicant \n" +
                            "join NAMES on INSTRUMENTS.name=NAMES.idName \n" +
                            "join TYPES on INSTRUMENTS.type=TYPES.idType \n" +
                            "join PRODUCERS on INSTRUMENTS.producer=PRODUCERS.idProducer \n" +
                            "join RANGES on INSTRUMENTS.range=RANGES.idRange \n" +
                            "GROUP BY INSTRUMENTS.serialNumber, INSTRUMENTS.identificationNumber \n" +
                            "ORDER BY INSTRUMENTS.idInstrument;",
                    new RawRowMapper<InstrumentFxModel>() {
                        @Override
                        public InstrumentFxModel mapRow(String[] columns, String[] res) throws SQLException {
                            return createInstrumentFxModel(res);
                        }
                    });
            int i=1;
            for (InstrumentFxModel instrumentFxModel: rawResults){
                instrumentsList.add(instrumentFxModel);
                i=i+1;
            }
        } catch (SQLException e) {
            ShowAlert.display(e.getMessage());
        }
    }
    public void addFilterToObservableList(String newValue){
        filteredInstrumentsList.setPredicate(item -> {
            if (item.getName().toUpperCase().contains(newValue.toUpperCase())||item.getType().toUpperCase().contains(newValue.toUpperCase())||
                    item.getProducer().toUpperCase().contains(newValue.toUpperCase())||item.getSerialNumber().toUpperCase().contains(newValue.toUpperCase())||
                    item.getIdentificationNumber().toUpperCase().contains(newValue.toUpperCase())||item.getRange().toUpperCase().contains(newValue.toUpperCase())||
                    item.getApplicant().getShortName().toUpperCase().contains(newValue.toUpperCase())) {
                return true;
            } else {
                return false;
            }
        });
    }
    public InstrumentFxModel createInstrumentFxModel(String[] results){
        InstrumentFxModel tempInstrumentObject = new InstrumentFxModel();
        tempInstrumentObject.setIdInstrument(Integer.parseInt(results[0]));
        tempInstrumentObject.setConcatIdInstrument(results[17]);
        tempInstrumentObject.setName(results[1]);
        tempInstrumentObject.setType(results[2]);
        tempInstrumentObject.setProducer(results[3]);
        tempInstrumentObject.setSerialNumber(results[4]);
        tempInstrumentObject.setIdentificationNumber(results[5]);
        tempInstrumentObject.setLength(results[6]);
        tempInstrumentObject.setDiameter(results[7]);
        tempInstrumentObject.setRange(results[8]);
        tempInstrumentObject.setApplicant(createApplicantFxModel(results));
        return tempInstrumentObject;
    }
    public ApplicantFxModel createApplicantFxModel(String[] results){
        ApplicantFxModel tempApplicantObject = new ApplicantFxModel();
        tempApplicantObject.setIdApplicant(Integer.parseInt(results[9]));
        tempApplicantObject.setShortName(results[10]);
        tempApplicantObject.setFullName(results[11]);
        tempApplicantObject.setPostCode(results[12]);
        tempApplicantObject.setCity(results[13]);
        tempApplicantObject.setStreet(results[14]);
        tempApplicantObject.setNumber(results[15]);
        tempApplicantObject.setStatus(results[16]);
        return tempApplicantObject;
    }

    public ObservableList<InstrumentFxModel> getInstrumentsList() {
        return instrumentsList;
    }
    public void setInstrumentsList(ObservableList<InstrumentFxModel> instrumentsList) {
        this.instrumentsList = instrumentsList;
    }
    public InstrumentFxModel getCurrentInstrument() {
        return currentInstrument.get();
    }
    public ObjectProperty<InstrumentFxModel> currentInstrumentProperty() {
        return currentInstrument;
    }
    public void setCurrentInstrument(InstrumentFxModel currentInstrument) {
        this.currentInstrument.set(currentInstrument);
    }
    public FilteredList<InstrumentFxModel> getFilteredInstrumentsList() {
        return filteredInstrumentsList;
    }
    public void setFilteredInstrumentsList(FilteredList<InstrumentFxModel> filteredInstrumentsList) {
        this.filteredInstrumentsList = filteredInstrumentsList;
    }
}
