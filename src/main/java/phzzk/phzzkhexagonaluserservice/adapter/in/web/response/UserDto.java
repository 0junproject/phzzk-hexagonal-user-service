package phzzk.phzzkhexagonaluserservice.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import phzzk.phzzkhexagonaluserservice.domain.model.user.User;

@Getter
@AllArgsConstructor
public class UserDto {
    private String id;
    private String email;
    private String nickname;
    private String role;

    public static UserDto from(User user) {
        return new UserDto(
                user.getId().getValue(),
                user.getEmail().getValue(),
                user.getNickname().getValue(),
                user.getRole().name()
        );
    }
}

