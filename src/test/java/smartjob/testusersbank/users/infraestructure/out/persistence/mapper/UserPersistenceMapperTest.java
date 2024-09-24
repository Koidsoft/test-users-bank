package smartjob.testusersbank.users.infraestructure.out.persistence.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.infraestructure.out.persistence.UserPersistenceHibernate;
import smartjob.testusersbank.users.infraestructure.out.persistence.entity.UserEntity;
import smartjob.testusersbank.users.infraestructure.out.persistence.repository.UserRepository;
import smartjob.testusersbank.users.infraestructure.out.persistence.utils.UserException;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserPersistenceHibernateTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserPersistenceHibernate userPersistenceHibernate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUserShouldSaveUserSuccessfully() throws UserException {
        User user = new User.Builder("test@example.com", "Test User", Collections.emptyList())
                .id(UUID.randomUUID().toString()).build();
        String password = "password123";
        UserEntity userEntity = UserPersistenceMapper.toEntity(user, password, UUID.randomUUID().toString());

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        User savedUser = userPersistenceHibernate.saveUser(user, password);

        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void saveUserShouldThrowUserExceptionWhenSaveFails() {
        User user = new User.Builder("test@example.com", "Test User", Collections.emptyList())
                .build();
        String password = "password123";

        when(userRepository.save(any(UserEntity.class))).thenReturn(null);

        assertThrows(UserException.class, () -> userPersistenceHibernate.saveUser(user, password));
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void findUserByEmailShouldReturnUserSuccessfully() {
        String email = "test@example.com";
        UserEntity userEntity = new UserEntity.Builder(email, "Test User").id(UUID.randomUUID()).build();

        when(userRepository.findByEmail(email)).thenReturn(userEntity);

        User foundUser = userPersistenceHibernate.findUserByEmail(email);

        assertNotNull(foundUser);
        assertEquals(email, foundUser.getEmail());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void findUserByEmailShouldReturnNullWhenUserNotFound() {
        String email = "notfound@example.com";

        when(userRepository.findByEmail(email)).thenReturn(null);

        User foundUser = userPersistenceHibernate.findUserByEmail(email);

        assertNull(foundUser);
        verify(userRepository, times(1)).findByEmail(email);
    }
}
