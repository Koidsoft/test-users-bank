package smartjob.testusersbank.users.infraestructure.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smartjob.testusersbank.users.infraestructure.out.persistence.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
}
