package phzzk.phzzkhexagonaluserservice.application.port.out;

import phzzk.phzzkhexagonaluserservice.domain.model.user.User;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Email;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.UserId;

import java.util.Optional;

public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findById(UserId userId);

    Optional<User> findByEmail(Email email);

    void delete(UserId userId);

    boolean existsByEmail(Email email);
}