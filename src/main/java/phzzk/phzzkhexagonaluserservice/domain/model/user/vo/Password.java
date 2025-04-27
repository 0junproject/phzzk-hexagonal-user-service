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

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private static final Pattern PASSWORD_REGEX =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}:;'<>,.?/]).{8,20}$");

    private final String value; // 항상 암호화된 상태

    /**
     * 평문 비밀번호를 받아 Password 객체 생성
     * @param rawPassword 평문 비밀번호
     * @return 암호화된 Password 객체
     */
    public static Password fromRaw(String rawPassword) {
        return new Password(encodeAndValidate(rawPassword));
    }

    /**
     * 이미 암호화된 비밀번호를 받아 Password 객체 생성
     * @param encodedPassword 암호화된 비밀번호
     * @return Password 객체
     */
    public static Password fromEncoded(String encodedPassword) {
        if (encodedPassword == null || encodedPassword.isBlank()) {
            throw new IllegalArgumentException("Encoded password must not be null or blank.");
        }
        return new Password(encodedPassword);
    }

    /**
     * 평문 비밀번호와 암호화된 비밀번호 비교
     * @param rawPassword 평문 비밀번호
     * @return 일치 여부
     */
    public boolean matches(String rawPassword) {
        return ENCODER.matches(rawPassword, this.value);
    }

    /**
     * 내부 생성자 - 항상 안전한 값만 받는다
     */
    private Password(String value) {
        this.value = value;
    }

    /**
     * 평문 비밀번호 검증 후 암호화
     */
    private static String encodeAndValidate(String rawPassword) {
        if (rawPassword == null || !PASSWORD_REGEX.matcher(rawPassword).matches()) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD_FORMAT);
        }
        return ENCODER.encode(rawPassword);
    }
}

