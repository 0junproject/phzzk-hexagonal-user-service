package phzzk.phzzkhexagonaluserservice.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Email;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Nickname;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Password;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.UserId;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    private UserId id;                // USR_UUID
    private Email email;              // VO
    private Password password;        // VO (nullable)
    private Nickname nickname;        // VO
    private AuthProvider provider;          // String or Enum
    private String providerId;        // String
    private boolean isEmailVerified;  // boolean
    private String ownedChannelId;
    private Role role;                // Enum
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static User createNew(Email email, Password password, Nickname nickname) {
        return new User(
                UserId.createNew(),
                email,
                password,
                nickname,
                AuthProvider.LOCAL,
                UUID.randomUUID().toString(), // providerId
                false,                        // isEmailVerified
                null,                         // ownedChannelId
                Role.USER,
                now(),
                now()
        );
    }

    public static User createWithChannel(Email email,
                                         Password password,
                                         Nickname nickname, UserId userId, String ownedChannelId
    ) {
        return new User(userId, email, password, nickname, AuthProvider.LOCAL,
                UUID.randomUUID().toString(), false, ownedChannelId, Role.USER, now(), now());
    }
}