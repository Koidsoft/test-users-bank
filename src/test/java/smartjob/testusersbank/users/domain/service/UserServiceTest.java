package smartjob.testusersbank.users.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import smartjob.testusersbank.users.application.out.persistence.UserPersistence;
import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.infraestructure.out.persistence.utils.UserException;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserPersistence userPersistence;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUserShouldSaveUserSuccessfully() throws UserException {
        User user = new User.Builder("test@example.com", "Test User", Collections.emptyList())
                .id("1234")
                .build();
        String password = "password123";

        when(userPersistence.saveUser(user, password)).thenReturn(user);

        User result = userService.save(user, password);

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
        verify(userPersistence, times(1)).saveUser(user, password);
    }

    @Test
    void saveUserShouldThrowUserException() throws UserException {
        User user = new User.Builder("test@example.com", "Test User", Collections.emptyList())
                .id("1234")
                .build();
        String password = "password123";

        when(userPersistence.saveUser(user, password)).thenThrow(new UserException("Error saving user"));

        assertThrows(UserException.class, () -> userService.save(user, password));
        verify(userPersistence, times(1)).saveUser(user, password);
    }

    @Test
    void findByUsernameShouldReturnUser() {
        String email = "test@example.com";
        User user = new User.Builder(email, "Test User", Collections.emptyList())
                .id("1234")
                .build();

        when(userPersistence.findUserByEmail(email)).thenReturn(user);

        User result = userService.findByUsername(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(userPersistence, times(1)).findUserByEmail(email);
    }

    @Test
    void findByUsernameShouldReturnNullWhenUserNotFound() {
        String email = "notfound@example.com";

        when(userPersistence.findUserByEmail(email)).thenReturn(null);

        User result = userService.findByUsername(email);

        assertNull(result);
        verify(userPersistence, times(1)).findUserByEmail(email);
    }
}
