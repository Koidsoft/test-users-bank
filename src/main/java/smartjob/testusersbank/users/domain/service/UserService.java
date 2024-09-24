package smartjob.testusersbank.users.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import smartjob.testusersbank.users.application.out.persistence.UserPersistence;
import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.infraestructure.out.persistence.utils.UserException;

public class UserService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final UserPersistence userPersistence;

    public UserService(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }
    public User save(User user,String password) throws UserException {
        log.info("Save user service {}", user);
        return userPersistence.saveUser(user, password);
    }
    public User findByUsername(String email)  {
        log.info("Find user service {}", email);
        return userPersistence.findUserByEmail(email);
    }
}
