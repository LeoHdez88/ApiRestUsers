package usersApiRest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import usersApiRest.entities.Phone;
import usersApiRest.entities.User;
import usersApiRest.model.PhoneDTO;
import usersApiRest.model.UserRequestDTO;
import usersApiRest.model.UserResponseDTO;
import usersApiRest.repositories.UserResposity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static usersApiRest.utility.Constans.EMAIL_REGEX;
import static usersApiRest.utility.Constans.PASSWORD_REGEX;

@Service
public class UserService {

    private final UserResposity userRepository;

    @Autowired
    public UserService(UserResposity userRepository) {
        this.userRepository = userRepository;
    }

    // Método para obtener todos los usuarios
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserResponseDTO).collect(Collectors.toList());
    }

    // Método para crear un nuevo usuario
    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        validateUser(userRequest);
        User user = mapToUser(userRequest);

        // Guardar el usuario en la base de datos
        User savedUser = userRepository.save(user);

        return mapToUserResponseDTO(savedUser);
    }

    private void validateUser(UserRequestDTO userRequest){
        // Validación del formato del correo (aunque ya debería ser validado por la anotación @Email en la entidad)
        if (!isValidEmail(userRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de correo inválido");
        }

        // Validación de la contraseña (debería obtenerse de una propiedad configurada)
        if (!userRequest.getPassword().matches(PASSWORD_REGEX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de contraseña inválido");
        }

        User existingUser = userRepository.findByEmail(userRequest.getEmail());
        // Verificar si el correo ya está registrado
        if (existingUser != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya está registrado");
        }

    }

    // Método para convertir un User a UserResponseDTO
    private UserResponseDTO mapToUserResponseDTO(User user) {
        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCreated(user.getCreated());
        response.setModified(user.getModified());
        response.setLastLogin(user.getLastLogin());
        response.setToken(user.getToken());
        response.setActive(user.isActive());
        response.setPhones(
                user.getPhones().stream().map(phone -> new PhoneDTO(
                        phone.getCitycode(),
                        phone.getNumber(),
                        phone.getCountrycode()
                )).collect(Collectors.toList())
        );
        return response;
    }

    // Método para Convertir userRequestDTO de solicitud a entidad User
    private User mapToUser(UserRequestDTO userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setActive(true);
        user.setToken(UUID.randomUUID().toString());

        // Mapeo de teléfonos
        List<Phone> phones = userRequest.getPhones().stream()
                .map(phoneDTO -> {
                    Phone phone = new Phone();
                    phone.setNumber(phoneDTO.getNumber());
                    phone.setCitycode(phoneDTO.getCitycode());
                    phone.setCountrycode(phoneDTO.getContrycode());
                    return phone;
                }).collect(Collectors.toList());
        user.setPhones(phones);

        return user;
    }

    private boolean isValidEmail(String email) {
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }
}