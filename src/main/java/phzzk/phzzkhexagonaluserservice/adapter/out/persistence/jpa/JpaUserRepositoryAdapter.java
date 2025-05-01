package phzzk.phzzkhexagonaluserservice.adapter.out.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import phzzk.phzzkhexagonaluserservice.adapter.out.persistence.jpa.entity.JpaUserEntity;
import phzzk.phzzkhexagonaluserservice.adapter.out.persistence.jpa.mapper.JpaUserMapper;
import phzzk.phzzkhexagonaluserservice.adapter.out.persistence.jpa.repository.JpaUserRepository;
import phzzk.phzzkhexagonaluserservice.application.port.out.UserRepositoryPort;
import phzzk.phzzkhexagonaluserservice.domain.model.user.User;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Email;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.UserId;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaRepository;
    private final JpaUserMapper mapper;

    @Override
    public User save(User user) {
        JpaUserEntity entity = mapper.toEntity(user);
        JpaUserEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return jpaRepository.findById(userId.getValue())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return jpaRepository.findByEmail(email.getValue())
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaRepository.existsByEmail(email.getValue());
    }

    @Override
    public void delete(UserId userId) {
        jpaRepository.deleteById(userId.getValue());
    }
}