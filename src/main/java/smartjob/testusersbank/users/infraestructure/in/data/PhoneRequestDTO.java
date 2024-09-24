package smartjob.testusersbank.users.infraestructure.in.data;

import com.fasterxml.jackson.annotation.JsonSetter;

public class PhoneRequestDTO {
    private String number;
    private String cityCode;
    private String countryCode;

    public PhoneRequestDTO() {
    }

    public PhoneRequestDTO(String number, String cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
