package phzzk.phzzkhexagonaluserservice.domain.model.user.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import phzzk.phzzkhexagonaluserservice.application.port.out.PasswordEncoderPort;
import phzzk.phzzkhexagonaluserservice.domain.exception.CustomException;
import phzzk.phzzkhexagonaluserservice.domain.exception.ErrorCode;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class Password {
    private final String value;

    public Password(String value) {
        this.value = value;
    }

    public static Password encode(String raw, PasswordEncoderPort encoder) {
        return new Password(encoder.encode(raw));
    }

    public String getValue() {
        return value;
    }
}

