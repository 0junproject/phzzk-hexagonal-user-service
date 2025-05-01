package phzzk.phzzkhexagonaluserservice.adapter.out.persistence.mongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import phzzk.phzzkhexagonaluserservice.application.port.out.UserRepositoryPort;
import phzzk.phzzkhexagonaluserservice.domain.model.user.User;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Email;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.UserId;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class mongoUserRepositoryAdapter implements UserRepositoryPort {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return Optional.empty();
    }

    @Override
    public void delete(UserId userId) {

    }

    @Override
    public boolean existsByEmail(Email email) {
        return false;
    }
}
