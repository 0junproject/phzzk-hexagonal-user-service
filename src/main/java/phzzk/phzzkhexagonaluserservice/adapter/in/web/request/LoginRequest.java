package phzzk.phzzkhexagonaluserservice.adapter.in.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {

    @Schema(description = "사용자 이메일", example = "user@example.com")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "사용자 비밀번호", example = "password1234")
    @NotBlank
    private String password;
}
