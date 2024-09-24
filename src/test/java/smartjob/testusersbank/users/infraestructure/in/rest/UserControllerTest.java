package smartjob.testusersbank.users.infraestructure.in.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import smartjob.testusersbank.users.application.in.CreateUserUseCase;
import smartjob.testusersbank.users.application.in.FindByUserUseCase;
import smartjob.testusersbank.users.domain.model.User;
import smartjob.testusersbank.users.infraestructure.in.data.PhoneRequestDTO;
import smartjob.testusersbank.users.infraestructure.in.data.UserRequestDTO;
import smartjob.testusersbank.users.infraestructure.in.rest.validations.ValidationUsers;
import smartjob.testusersbank.users.infraestructure.out.persistence.utils.UserException;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class UserControllerTest {

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private FindByUserUseCase findByUserUseCase;

    @Mock
    private ValidationUsers validationUsers;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void saveUserShouldReturnBadRequestWhenValidationFails() throws Exception {
        UserRequestDTO request = new UserRequestDTO("invalid-email","Test User","12345",Collections.singletonList(new PhoneRequestDTO("123456789", "01", "57")));


        when(validationUsers.validateSaveUser(any(UserRequestDTO.class))).thenReturn(false);

        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"invalid-email\",\"name\":\"Test User\",\"password\":\"12345\",\"phones\":[{\"number\":\"123456789\",\"cityCode\":\"01\",\"countryCode\":\"57\"}]}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("Error los campos son obligatorios"));

        verify(validationUsers, times(1)).validateSaveUser(any(UserRequestDTO.class));
    }

    @Test
    void saveUserShouldReturnBadRequestWhenUserAlreadyExists() throws Exception {


        when(validationUsers.validateSaveUser(any(UserRequestDTO.class))).thenReturn(true);
        when(findByUserUseCase.findByUsername(anyString())).thenReturn(new User.Builder("test@example.com", "Test User", Collections.emptyList()).build());

        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"name\":\"Test User\",\"password\":\"password123\",\"phones\":[{\"number\":\"123456789\",\"cityCode\":\"01\",\"countryCode\":\"57\"}]}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("Error el usuario con email: test@example.com Ya se encuentra registrado"));

        verify(findByUserUseCase, times(1)).findByUsername(anyString());
    }

    @Test
    void saveUserShouldReturnCreatedWhenUserIsSaved() throws Exception, UserException {
        UserRequestDTO request = new UserRequestDTO("test@example.com","Test User","12345",Collections.singletonList(new PhoneRequestDTO("123456789", "01", "57")));

        when(validationUsers.validateSaveUser(any(UserRequestDTO.class))).thenReturn(true);
        when(findByUserUseCase.findByUsername(anyString())).thenReturn(null);
        User savedUser = new User.Builder("test@example.com", "Test User", Collections.emptyList()).id("456456888").build();
        when(createUserUseCase.saveUser(any(User.class), anyString())).thenReturn(savedUser);

        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"name\":\"Test User\",\"password\":\"password123\",\"phones\":[{\"number\":\"123456789\",\"cityCode\":\"01\",\"countryCode\":\"57\"}]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty());

        verify(createUserUseCase, times(1)).saveUser(any(User.class), anyString());
    }
}
