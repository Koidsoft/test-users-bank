package smartjob.testusersbank.users.infraestructure.out.persistence;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import smartjob.testusersbank.users.application.out.persistence.UserPersistence;
import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.infraestructure.out.persistence.entity.UserEntity;
import smartjob.testusersbank.users.infraestructure.out.persistence.mapper.UserPersistenceMapper;
import smartjob.testusersbank.users.infraestructure.out.persistence.repository.UserRepository;
import smartjob.testusersbank.users.infraestructure.out.persistence.utils.UserException;

import java.util.*;

public class UserPersistenceHibernate implements UserPersistence {
    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public UserPersistenceHibernate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User saveUser(User user, String password) throws UserException {
        UserEntity userEntity = UserPersistenceMapper.toEntity(user,password, UUID.randomUUID().toString());

        log.info("Saving user: {} phones {}", userEntity, userEntity.getPhones());
        UserEntity savedUser = userRepository.save(userEntity);
        if(Objects.isNull(savedUser)) {
            throw new UserException("User save failed");
        }
        return UserPersistenceMapper.toUser(savedUser);
    }

    @Override
    public User findUserByEmail(String email) {
        log.info("Finding user by email: {}", email);
        UserEntity userEntity = userRepository.findByEmail(email);
        return UserPersistenceMapper.toUser(userEntity);
    }
}
