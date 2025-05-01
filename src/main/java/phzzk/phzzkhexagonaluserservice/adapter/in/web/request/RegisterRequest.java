package phzzk.phzzkhexagonaluserservice.adapter.in.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {

    @Email
    @NotBlank
    @Schema(description = "이메일", example = "user@example.com")
    private String email;

    @NotBlank
    @Schema(description = "닉네임", example = "phzzk_user")
    private String nickname;

    @NotBlank
    @Schema(description = "비밀번호", example = "strongpassword123!")
    private String password;
}
