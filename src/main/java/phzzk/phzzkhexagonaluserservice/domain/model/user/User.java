package phzzk.phzzkhexagonaluserservice.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Email;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Nickname;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Password;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.UserId;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    private UserId id;                // USR_UUID
    private Email email;              // VO
    private Password password;        // VO (nullable)
    private Nickname nickname;        // VO
    private String provider;          // String or Enum
    private String providerId;        // String
    private boolean isEmailVerified;  // boolean
    private String ownedChannelId;
    private Role role;                // Enum
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}