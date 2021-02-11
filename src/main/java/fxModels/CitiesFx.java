package fxModels;

public class CitiesFx {
    private String idCity;
    private String cityName;
    private String idUser;
    private String userName;
    private String cityId;


    public CitiesFx(String idCity, String cityName, String idUser, String userName, String cityId) {
        this.idCity = idCity;
        this.cityName = cityName;
        this.idUser = idUser;
        this.userName = userName;
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "CitiesFx{" +
                "idCity='" + idCity + '\'' +
                ", cityName='" + cityName + '\'' +
                ", idUser='" + idUser + '\'' +
                ", userName='" + userName + '\'' +
                ", cityId='" + cityId + '\'' +
                '}';
    }
}
