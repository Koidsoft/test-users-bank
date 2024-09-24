package smartjob.testusersbank.users.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import smartjob.testusersbank.users.application.in.CreateUserUseCase;
import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.domain.service.UserService;
import smartjob.testusersbank.users.infraestructure.out.persistence.utils.UserException;

public class CreateUser implements CreateUserUseCase {
    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User saveUser(User user, String password) throws UserException {
        log.info("Create user: {}", user.toString());
        return userService.save(user, password);
    }
}
