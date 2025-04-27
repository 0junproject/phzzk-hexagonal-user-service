package phzzk.phzzkhexagonaluserservice.domain.model.user.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import phzzk.phzzkhexagonaluserservice.domain.exception.CustomException;
import phzzk.phzzkhexagonaluserservice.domain.exception.ErrorCode;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class Email {
    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private final String value;

    public Email(String value) {
        if (value == null || !EMAIL_REGEX.matcher(value).matches()) {
            throw new CustomException(ErrorCode.INVALID_EMAIL_FORMAT);
        }
        this.value = value;
    }
}
