package smartjob.testusersbank.users.infraestructure.in.data;

import java.util.List;

public class UserRequestDTO {
    private String email;
    private String name;
    private String password;
    private List<PhoneRequestDTO> phones;

    public UserRequestDTO() {
    }

    public UserRequestDTO( String email, String name, String password, List<PhoneRequestDTO> phones) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phones = phones;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<PhoneRequestDTO> getPhones() {
        return phones;
    }
}
