package fxModels;

import utils.Converter;

import java.time.LocalDate;

public class VeryShortRegisterFxModel {
    private Integer idRegister;
    private Integer idRegisterByYear;
    private String cardNumber;
    private LocalDate calibrationDate;

    public VeryShortRegisterFxModel() {
    }

    public VeryShortRegisterFxModel(Integer idRegister, Integer idRegisterByYear, String cardNumber, String calibrationDate) {
        this.idRegister = idRegister;
        this.idRegisterByYear = idRegisterByYear;
        this.cardNumber = cardNumber;
        this.calibrationDate = Converter.getConverter().fromString(calibrationDate);
    }
    public VeryShortRegisterFxModel(String[] result) {
        this.idRegister = Integer.parseInt(result[0]);
        this.idRegisterByYear = Integer.parseInt(result[1]);
        this.cardNumber = result[2];
        this.calibrationDate = Converter.getConverter().fromString(result[3]);
    }

    public Integer getIdRegister() {
        return idRegister;
    }
    public void setIdRegister(Integer idRegister) {
        this.idRegister = idRegister;
    }
    public Integer getIdRegisterByYear() {
        return idRegisterByYear;
    }
    public void setIdRegisterByYear(Integer idRegisterByYear) {
        this.idRegisterByYear = idRegisterByYear;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public LocalDate getCalibrationDate() {
        return calibrationDate;
    }
    public void setCalibrationDate(LocalDate calibrationDate) {
        this.calibrationDate = calibrationDate;
    }

    @Override
    public String toString() {
        return "VeryShortRegisterFxModel{" +
                "idRegister=" + idRegister +
                ", idRegisterByYear=" + idRegisterByYear +
                ", cardNumber='" + cardNumber + '\'' +
                ", calibrationDate='" + calibrationDate + '\'' +
                '}';
    }
}
