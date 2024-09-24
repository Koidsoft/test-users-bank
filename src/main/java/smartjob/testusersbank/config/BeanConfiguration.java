package smartjob.testusersbank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import smartjob.testusersbank.users.application.in.CreateUserUseCase;
import smartjob.testusersbank.users.application.in.FindByUserUseCase;
import smartjob.testusersbank.users.application.out.persistence.UserPersistence;
import smartjob.testusersbank.users.application.service.CreateUser;
import smartjob.testusersbank.users.application.service.FindByUser;
import smartjob.testusersbank.users.domain.service.UserService;
import smartjob.testusersbank.users.infraestructure.out.persistence.UserPersistenceHibernate;
import smartjob.testusersbank.users.infraestructure.out.persistence.repository.UserRepository;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserPersistence userPersistence(UserRepository userRepository) {
        return new UserPersistenceHibernate(userRepository);
    }

    @Bean
    public UserService userService(UserPersistence userPersistence) {
        return new UserService(userPersistence);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserService userService) {
        return new CreateUser(userService);
    }
    @Bean
    public FindByUserUseCase findByUserUseCase(UserService userService) {
        return new FindByUser(userService);
    }
}
