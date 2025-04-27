package phzzk.phzzkhexagonaluserservice.adapter.out.persistence.config;

import java.util.Arrays;

public enum PersistenceType {
    JPA,
    MYBATIS,
    MONGODB;

    public static PersistenceType from(String value) {
        return Arrays.stream(values())
                .filter(type -> type.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "잘못된 PersistenceType 설정입니다: " + value +
                                " (허용값: " + Arrays.toString(values()) + ")"
                ));
    }
}
