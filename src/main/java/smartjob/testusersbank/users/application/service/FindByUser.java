package smartjob.testusersbank.users.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import smartjob.testusersbank.users.application.in.FindByUserUseCase;
import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.domain.service.UserService;

public class FindByUser implements FindByUserUseCase {
    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public FindByUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User findByUsername(String email) {
        log.info("Finding user by email: {}", email);
        return userService.findByUsername(email);
    }
}
