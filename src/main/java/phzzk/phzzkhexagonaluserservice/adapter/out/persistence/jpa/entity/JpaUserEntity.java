package phzzk.phzzkhexagonaluserservice.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import phzzk.phzzkhexagonaluserservice.domain.model.user.AuthProvider;
import phzzk.phzzkhexagonaluserservice.domain.model.user.Role;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class JpaUserEntity {

    @Id
    private String id;  // ex: usr_550e8400-e29b-41d4-a716-446655440000

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider;

    @Column(nullable = false)
    private String providerId;

    private String profileImageUrl;

    private LocalDateTime registeredAt;

    private LocalDateTime lastLoginAt;
}
