package usersApiRest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import usersApiRest.entities.User;
import usersApiRest.model.UserRequestDTO;
import usersApiRest.model.UserResponseDTO;
import usersApiRest.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@Validated
@Tag(name = "Recursos de Usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary =  "Endpoint para obtener todos los usuarios")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Endpoint para crear un nuevo usuario")
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO user) {
        try {
            // Llama al servicio para crear un nuevo usuario
            UserResponseDTO newUser = userService.createUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (ResponseStatusException ex) {
            // Manejo de errores personalizados con ResponseStatusException
            return new ResponseEntity<>(createMessageError(ex.getReason()), ex.getStatusCode());
        } catch (Exception ex) {
            // Manejo de otros errores genéricos
            return new ResponseEntity<>(createMessageError("Error inesperado"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método privado para crear un objeto JSON de error
    private Map<String, String> createMessageError(String mensaje) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("mensaje", mensaje);
        return errorResponse;
    }
}