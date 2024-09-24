package smartjob.testusersbank.users.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.domain.service.UserService;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FindByUserTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private FindByUser findByUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUsernameShouldReturnUser() {
        String email = "test@example.com";
        User user = new User.Builder(email, "Test User", Collections.emptyList())
                .id("1234")
                .build();

        when(userService.findByUsername(email)).thenReturn(user);

        User result = findByUser.findByUsername(email);

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
        verify(userService, times(1)).findByUsername(email);
    }

    @Test
    void findByUsernameShouldReturnNullWhenUserNotFound() {
        String email = "notfound@example.com";

        when(userService.findByUsername(email)).thenReturn(null);

        User result = findByUser.findByUsername(email);

        assertNull(result);
        verify(userService, times(1)).findByUsername(email);
    }
}
