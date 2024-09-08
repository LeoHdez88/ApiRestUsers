package usersApiRest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import usersApiRest.entities.User;

import java.util.UUID;

public interface UserResposity extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
