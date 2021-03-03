package utils;

import dbModels.ApplicantModel;
import dbModels.InstrumentModel;
import dbModels.RegisterModel;
import dbModels.StorageModel;
import fxModels.ApplicantFxModel;
import fxModels.InstrumentFxModel;
import fxModels.RegisterFxModel;
import fxModels.ShortStorageFxModel;

public class Converter {

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
        fxModel.setStorageRemarks(model.getStorageRemarks());

        return fxModel;
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
}
