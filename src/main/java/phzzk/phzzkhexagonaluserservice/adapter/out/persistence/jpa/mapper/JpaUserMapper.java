package phzzk.phzzkhexagonaluserservice.adapter.out.persistence.jpa.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import phzzk.phzzkhexagonaluserservice.adapter.out.persistence.jpa.entity.JpaUserEntity;
import phzzk.phzzkhexagonaluserservice.domain.model.user.User;

@Component
@RequiredArgsConstructor
public class JpaUserMapper {

    private final ModelMapper modelMapper;

    public User toDomain(JpaUserEntity entity) {
        return modelMapper.map(entity, User.class);
    }

    public JpaUserEntity toEntity(User domain) {
        return modelMapper.map(domain, JpaUserEntity.class);
    }
}
