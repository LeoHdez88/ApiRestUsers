package usersApiRest.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import usersApiRest.entities.User;
import usersApiRest.model.UserResponseDTO;
import usersApiRest.repositories.UserResposity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserResposity userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Mock data
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(UUID.randomUUID().toString());
        user.setActive(true);

        // Mock repository response
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        // Execute the service method
        List<UserResponseDTO> result = userService.getAllUsers();

        // Assertions
        assertEquals(1, result.size());
        assertEquals("Juan Rodriguez", result.get(0).getName());
        assertEquals("juan@rodriguez.org", result.get(0).getEmail());
    }
}