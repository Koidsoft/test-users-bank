package smartjob.testusersbank.users.application.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.domain.service.UserService;
import smartjob.testusersbank.users.infraestructure.out.persistence.utils.UserException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserTest {


    private UserService userService;

    private CreateUser createUser;

    @BeforeEach
    public void setUp() {
        userService = mock(UserService.class);
        createUser = new CreateUser(userService);
    }

    @Test
    public void saveUserShouldSaveUserSuccessfully() throws UserException {
        User user = new User.Builder("test@example.com", "Test User", Collections.emptyList())
                .id("1234")
                .enable(true)
                .build();
        String password = "securePassword123";

        when(userService.save(user, password)).thenReturn(user);

        User result = createUser.saveUser(user, password);

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
        verify(userService, times(1)).save(user, password);
    }

    @Test
    public void saveUserShouldThrowUserException() throws UserException {
        User user = new User.Builder("test@example.com", "Test User", Collections.emptyList())
                .id("1234")
                .enable(true)
                .build();
        String password = "securePassword123";

        when(userService.save(user, password)).thenThrow(new UserException("Error"));

        assertThrows(UserException.class, () -> createUser.saveUser(user, password));
        verify(userService, times(1)).save(user, password);
    }
}
