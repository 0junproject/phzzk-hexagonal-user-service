package phzzk.phzzkhexagonaluserservice.adapter.out.persistence.jpa.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import phzzk.phzzkhexagonaluserservice.adapter.out.persistence.jpa.entity.JpaUserEntity;
import phzzk.phzzkhexagonaluserservice.domain.model.user.AuthProvider;
import phzzk.phzzkhexagonaluserservice.domain.model.user.Role;
import phzzk.phzzkhexagonaluserservice.domain.model.user.User;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Email;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Nickname;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Password;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.UserId;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JpaUserMapper {

    private final ModelMapper modelMapper;

    public User toDomain(JpaUserEntity entity) {
        return new User(
                new UserId(entity.getId()),
                new Email(entity.getEmail()),
                new Password(entity.getPassword()),
                new Nickname(entity.getNickname()),
                entity.getProvider(),
                entity.getProviderId(),
                false, //isEmailVerified
                null, //ownedChannelId
                entity.getRole(),
                entity.getLastLoginAt(),
                entity.getRegisteredAt()
        );
    }

    public JpaUserEntity toEntity(User user) {
        return JpaUserEntity.builder()
                .id(user.getId().getValue())  // 반드시 포함
                .email(user.getEmail().getValue())
                .password(user.getPassword().getValue())
                .nickname(user.getNickname().getValue())
                .role(user.getRole())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .build();
    }
}
