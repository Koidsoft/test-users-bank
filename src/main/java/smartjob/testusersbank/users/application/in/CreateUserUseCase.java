package smartjob.testusersbank.users.application.in;

import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.infraestructure.out.persistence.utils.UserException;

public interface CreateUserUseCase {
    User saveUser(User user, String password) throws UserException;
}
