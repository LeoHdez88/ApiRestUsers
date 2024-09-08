package usersApiRest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import usersApiRest.model.PhoneDTO;
import usersApiRest.model.UserResponseDTO;
import usersApiRest.services.UserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Mock data
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(UUID.randomUUID());
        userResponseDTO.setName("Julito Maraña");
        userResponseDTO.setEmail("JulioM@Correo.org");
        userResponseDTO.setCreated(LocalDateTime.now());
        userResponseDTO.setModified(LocalDateTime.now());
        userResponseDTO.setLastLogin(LocalDateTime.now());
        userResponseDTO.setToken(UUID.randomUUID().toString());
        userResponseDTO.setActive(true);
        userResponseDTO.setPhones(Arrays.asList(new PhoneDTO("1234567", "1", "57")));

        // Mock service response
        when(userService.getAllUsers()).thenReturn(Arrays.asList(userResponseDTO));

        // Execute the controller method
        ResponseEntity<List<UserResponseDTO>> response = userController.getAllUsers();

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Julito Maraña", response.getBody().get(0).getName());
    }
}