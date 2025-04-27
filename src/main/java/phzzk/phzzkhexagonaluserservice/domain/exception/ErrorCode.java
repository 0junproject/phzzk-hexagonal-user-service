package phzzk.phzzkhexagonaluserservice.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // ==== AUTH 관련 ====
    JWT_EXPIRED(HttpStatus.FORBIDDEN, ErrorType.AUTH, "JWT01", "JWT 토큰이 만료되었습니다."),
    JWT_INVALID(HttpStatus.UNAUTHORIZED, ErrorType.AUTH, "JWT02", "JWT 토큰이 유효하지 않습니다."),
    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, ErrorType.AUTH, "AUTH01", "인증에 실패했습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, ErrorType.AUTH, "AUTH02", "접근이 거부되었습니다"),

    // ==== USER 관련 ====
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, ErrorType.USER, "USR01", "이메일 형식이 올바르지 않습니다."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, ErrorType.USER, "USR02", "이미 가입된 이메일입니다."),
    INVALID_PASSWORD_FORMAT(HttpStatus.BAD_REQUEST, ErrorType.USER, "USR03", "비밀번호 형식이 올바르지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, ErrorType.USER, "USR04", "사용자를 찾을 수 없습니다."),
    INVALID_NICKNAME_FORMAT(HttpStatus.NOT_FOUND, ErrorType.USER, "USR05", "닉네임 형식이 올바르지 않습니다."),

    // ==== MENU 관련 ====
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, ErrorType.MENU, "MENU01", "메뉴를 찾을 수 없습니다."),
    MENU_ACCESS_DENIED(HttpStatus.FORBIDDEN, ErrorType.MENU, "MENU02", "메뉴에 접근할 수 없습니다."),

    // ==== 시스템/공통 ====
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorType.COMMON, "SYS01", "서버 내부 오류가 발생했습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, ErrorType.COMMON, "SYS02", "잘못된 요청입니다.");

    private final HttpStatus status;
    private final ErrorType errorType;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, ErrorType errorType, String code, String message) {
        this.status = status;
        this.errorType = errorType;
        this.code = code;
        this.message = message;
    }
}