package phzzk.phzzkhexagonaluserservice.domain.model.user.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import phzzk.phzzkhexagonaluserservice.domain.exception.CustomException;
import phzzk.phzzkhexagonaluserservice.domain.exception.ErrorCode;

@Getter
@EqualsAndHashCode
public class Nickname {
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 20;

    private final String value;

    public Nickname(String value) {
        if (value == null || value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new CustomException(ErrorCode.INVALID_NICKNAME_FORMAT);
        }
        this.value = value;
    }
}