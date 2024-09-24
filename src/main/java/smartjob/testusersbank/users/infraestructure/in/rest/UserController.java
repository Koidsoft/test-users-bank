package smartjob.testusersbank.users.infraestructure.in.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smartjob.testusersbank.users.application.in.CreateUserUseCase;
import smartjob.testusersbank.users.application.in.FindByUserUseCase;
import smartjob.testusersbank.users.domain.model.Phone;
import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.infraestructure.in.data.UserRequestDTO;
import smartjob.testusersbank.users.infraestructure.in.data.UserResponseDTO;
import smartjob.testusersbank.users.infraestructure.in.rest.validations.ValidationUsers;
import smartjob.testusersbank.users.infraestructure.out.persistence.utils.UserException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final FindByUserUseCase findByUserUseCase;
    private final ValidationUsers validationUsers;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public UserController(CreateUserUseCase createUserUseCase, FindByUserUseCase findByUserUseCase, ValidationUsers validationUsers) {
        this.createUserUseCase = createUserUseCase;
        this.findByUserUseCase = findByUserUseCase;
        this.validationUsers = validationUsers;
    }

    @PostMapping(value = "/v1/users", name = "create user")
    public ResponseEntity<?> saveUser(@RequestBody UserRequestDTO userRequestDTO) {
        log.info("save user controller");
        if (!validationUsers.validateSaveUser(userRequestDTO)) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje","Error los campos son obligatorios"));
        } else if (findByUserUseCase.findByUsername(userRequestDTO.getEmail()) != null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje","Error el usuario con email: ".concat(userRequestDTO.getEmail()).concat(" Ya se encuentra registrado")));
        }
        List<Phone> phones = userRequestDTO
                             .getPhones()
                             .stream()
                             .map(phone -> new Phone(phone.getNumber(), phone.getCityCode(), phone.getCountryCode())).toList();
        User user = null;

        try {
            user = createUserUseCase.saveUser(new User.Builder(userRequestDTO.getEmail(), userRequestDTO.getName(), phones).build(), userRequestDTO.getPassword());
        } catch (UserException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(new UserResponseDTO(user.getId(),
                !Objects.isNull(user.getMetadata())?user.getMetadata().getCreated():"",
                !Objects.isNull(user.getMetadata())?user.getMetadata().getModified():"",
                !Objects.isNull(user.getMetadata())? user.getMetadata().getLastLogin():"",
                !Objects.isNull(user.getMetadata())?user.getMetadata().getToken():"",
                                                        user.isEnable()),HttpStatus.CREATED);
    }
}
