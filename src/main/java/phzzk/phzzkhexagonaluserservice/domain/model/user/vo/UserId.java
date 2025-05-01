package phzzk.phzzkhexagonaluserservice.domain.model.user.vo;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Value
@Slf4j
public class UserId {

    private static final String PREFIX = "usr_";

    private final String value;

    public UserId(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid UserId format: " + value);
        }
        this.value = value;
    }

    public static UserId of(String raw) {
        return new UserId(raw);
    }

    public static UserId createNew() {
        return new UserId(PREFIX + UUID.randomUUID());
    }

    public String getValue() {
        return value;
    }

    public boolean isPrefixed() {
        return value.startsWith(PREFIX);
    }

    private boolean isValid(String value) {
        if (!value.startsWith(PREFIX)) return false;
        String uuidPart = value.substring(PREFIX.length());
        try {
            UUID.fromString(uuidPart);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
