package phzzk.phzzkhexagonaluserservice.domain.model.user;

import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Email;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Nickname;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Password;

import java.time.LocalDateTime;

class User {
    private String id;                // USR_UUID
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