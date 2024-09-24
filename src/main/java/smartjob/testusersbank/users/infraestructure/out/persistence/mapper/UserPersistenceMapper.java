package smartjob.testusersbank.users.infraestructure.out.persistence.mapper;

import smartjob.testusersbank.users.domain.model.Phone;
import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.domain.model.UserMetadata;
import smartjob.testusersbank.users.infraestructure.out.persistence.entity.PhoneEntity;
import smartjob.testusersbank.users.infraestructure.out.persistence.entity.UserEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserPersistenceMapper {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private UserPersistenceMapper() {
        throw new RuntimeException("Class should not be instantiated");
    }

    public static UserEntity toEntity(User user, String password, String token) {
        return new UserEntity
                .Builder(user.getEmail(),
                user.getName())
                .password(Base64.getEncoder().encodeToString(password.getBytes()))
                .token(token)
                .phones(user.getPhones().stream().map(phone -> new PhoneEntity(
                        phone.getPhone(),
                        phone.getCityCode(),
                        phone.getCountryCode()
                )).toList())
                .build();
    }

    public static User toUser(UserEntity entity) {
        if(Objects.isNull(entity)) {
            return null;
        }

        List<Phone> phones =Objects.nonNull(entity.getPhones())? entity.getPhones().stream().map(phone -> new Phone(phone.getPhone(), phone.getCityCode(), phone.getCountryCode())).toList():Collections.emptyList();
        UserMetadata userMetadata = new UserMetadata(entity.getToken(),
               !Objects.isNull(entity.getLastLogin())?dateFormat.format(entity.getLastLogin()):"",
                !Objects.isNull(entity.getCreated())?dateFormat.format(entity.getCreated()):"",
                !Objects.isNull(entity.getUpdated())?dateFormat.format(entity.getUpdated()):"");

        return new User.Builder(
                entity.getEmail(),
                entity.getName(),
                phones)
                .id(!Objects.isNull(entity.getId())? entity.getId().toString():"")
                .enable(entity.isEnable())
                .metadata(userMetadata)
                .build();
    }
}
