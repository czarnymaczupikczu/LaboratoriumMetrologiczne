package utils;

import dbModels.ApplicantModel;
import dbModels.InstrumentModel;
import dbModels.RegisterModel;
import dbModels.StorageModel;
import fxModels.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {
    /**
     * Metoda do konwersji daty na format ISO. Wykorzystywana w kontrolerach DatePicker
     */
    public static StringConverter<LocalDate> converter;
    public static StringConverter<LocalDate> getConverter(){
        converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        return converter;
    }
    public static RegisterFxModel convertRegisterModelToRegisterFxModel(RegisterModel model){
        RegisterFxModel fxModel=new RegisterFxModel();
        fxModel.setIdRegister(model.getIdRegister());
        fxModel.setIdRegisterByYear(model.getIdRegisterByYear());
        fxModel.setStorage(convertStorageModelToShortStorageFxModel(model.getStorage()));
        fxModel.setCardNumber(model.getCardNumber());
        fxModel.setCalibrationDate(model.getCalibrationDate());
        fxModel.setCalibrationUser(model.getCalibrationUser().getInitials());
        fxModel.setCertificateNumber(model.getCertificateNumber());
        fxModel.setDocumentKind(model.getDocumentKind());
        fxModel.setAgreementNumber(model.getAgreementNumber());
        fxModel.setState(model.getState());
        fxModel.setRegisterRemarks(model.getRegisterRemarks());

        return fxModel;
    }

    public static ShortStorageFxModel convertStorageModelToShortStorageFxModel(StorageModel model){
        ShortStorageFxModel fxModel=new ShortStorageFxModel();
        //fxModel.setIdStorage(model.getIdStorage());
        fxModel.setIdStorage(1);
        fxModel.setInstrument(convertInstrumentModelToInstrumentFxModel(model.getInstrument()));
        fxModel.setEntryUser(model.getEntryUser().getInitials());
        fxModel.setEntryUser("");
        fxModel.setSpendDate(model.getSpendDate());
        fxModel.setSpendUser(model.getSpendUser().getInitials());
        fxModel.setInstrumentRemarks(model.getInstrumentRemarks());
        fxModel.setCalibrationRemarks(model.getCalibrationRemarks());

        return fxModel;
    }
    public static StorageModel convertStorageFxModelToStorageModel(StorageFxModel fxModel){
        StorageModel model=new StorageModel();
        model.setIdStorage(fxModel.getIdStorage());

        return model;
    }
    public static InstrumentFxModel convertInstrumentModelToInstrumentFxModel(InstrumentModel model){
        InstrumentFxModel fxModel=new InstrumentFxModel();
        fxModel.setIdInstrument(model.getIdInstrument());
        fxModel.setName(model.getName().getInstrumentName());
        fxModel.setType(model.getType().getTypeName());
        fxModel.setProducer(model.getProducer().getProducerName());
        fxModel.setSerialNumber(model.getSerialNumber());
        fxModel.setIdentificationNumber(model.getIdentificationNumber());
        fxModel.setLength(model.getLength());
        fxModel.setDiameter(model.getDiameter());
        fxModel.setRange(model.getRange().getRangeName());
        fxModel.setApplicant(convertApplicantModelToApplicantFxModel(model.getApplicant()));

        return fxModel;
    }
    public static InstrumentModel convertInstrumentFxModelToInstrumentModel(InstrumentFxModel fxModel){
        InstrumentModel model=new InstrumentModel();
        model.setIdInstrument(fxModel.getIdInstrument());

        return model;
    }
    public static ApplicantFxModel convertApplicantModelToApplicantFxModel(ApplicantModel model){
        ApplicantFxModel fxModel=new ApplicantFxModel();
        fxModel.setIdApplicant(model.getIdApplicant());
        fxModel.setShortName(model.getShortName());
        fxModel.setFullName(model.getFullName());
        fxModel.setPostCode(model.getPostCode());
        fxModel.setCity(model.getCity());
        fxModel.setStreet(model.getStreet());
        fxModel.setNumber(model.getNumber());
        fxModel.setStatus(model.getStatus());

        return fxModel;
    }
    public static ApplicantModel convertApplicantFxModelToApplicantModel(ApplicantFxModel model){
        ApplicantModel dataModel=new ApplicantModel();
        dataModel.setIdApplicant(model.getIdApplicant());
        dataModel.setShortName(model.getShortName());
        dataModel.setFullName(model.getFullName());
        dataModel.setPostCode(model.getPostCode());
        dataModel.setCity(model.getCity());
        dataModel.setStreet(model.getStreet());
        dataModel.setNumber(model.getNumber());
        dataModel.setStatus(model.getStatus());

        return dataModel;
    }
}
