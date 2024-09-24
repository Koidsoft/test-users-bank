package smartjob.testusersbank.users.domain.model;

public class Phone {
    private final String phone;
    private final String cityCode;
    private final String countryCode;

    public Phone(String phone, String cityCode, String countryCode) {
        this.phone = phone;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
