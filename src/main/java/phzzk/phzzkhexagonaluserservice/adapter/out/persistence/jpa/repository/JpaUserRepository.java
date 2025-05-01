package phzzk.phzzkhexagonaluserservice.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phzzk.phzzkhexagonaluserservice.adapter.out.persistence.jpa.entity.JpaUserEntity;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<JpaUserEntity, String> {
    Optional<JpaUserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
