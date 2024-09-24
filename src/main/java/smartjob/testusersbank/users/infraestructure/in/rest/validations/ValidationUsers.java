package smartjob.testusersbank.users.infraestructure.in.rest.validations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import smartjob.testusersbank.users.infraestructure.in.data.UserRequestDTO;

import java.util.Objects;
import java.util.regex.Pattern;
@Component
public class ValidationUsers {
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    @Value("${key.password.regexp}")
    private String regexPassword;

    public  boolean validateSaveUser(UserRequestDTO userRequestDTO) {
        if (ObjectUtils.isEmpty(userRequestDTO.getName())) {
            return false;
        } else if (ObjectUtils.isEmpty(userRequestDTO.getEmail()) || !Pattern.compile(EMAIL_REGEX).matcher(userRequestDTO.getEmail()).matches()) {
            return false;
        } else if (ObjectUtils.isEmpty(userRequestDTO.getPhones())) {
            return false;
        }

        boolean isPhoneNull = userRequestDTO.getPhones()
                .stream()
                .anyMatch(phone -> ObjectUtils.isEmpty(phone.getCityCode()) || ObjectUtils.isEmpty(phone.getNumber()) || ObjectUtils.isEmpty(phone.getCountryCode()));
        if (isPhoneNull) {
            return false;
        }
        if (Objects.isNull(userRequestDTO.getPassword()) || !Pattern.compile(regexPassword).matcher(userRequestDTO.getPassword()).matches()){
            return false;
        }
        return true;
    }
}
