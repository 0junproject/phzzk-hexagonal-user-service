package phzzk.phzzkhexagonaluserservice.domain.model.user.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import phzzk.phzzkhexagonaluserservice.domain.exception.CustomException;
import phzzk.phzzkhexagonaluserservice.domain.exception.ErrorCode;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class Password {
    private final String encodedPassword;

    public Password(String encodedPassword) {
        if (encodedPassword == null || encodedPassword.isBlank()) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD_FORMAT);
        }
        this.encodedPassword = encodedPassword;
    }
}

