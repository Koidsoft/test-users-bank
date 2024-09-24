package smartjob.testusersbank.users.application.out.persistence;


import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.infraestructure.out.persistence.utils.UserException;
public interface UserPersistence {
    User saveUser(User user, String password) throws UserException;
    User findUserByEmail(String email);
}
