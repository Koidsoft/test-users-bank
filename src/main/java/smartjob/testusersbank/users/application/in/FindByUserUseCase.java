package smartjob.testusersbank.users.application.in;

import smartjob.testusersbank.users.domain.model.User;

public interface FindByUserUseCase {
    User findByUsername(String email);
}
