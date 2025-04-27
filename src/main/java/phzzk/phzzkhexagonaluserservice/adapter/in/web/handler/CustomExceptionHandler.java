package phzzk.phzzkhexagonaluserservice.adapter.in.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import phzzk.phzzkhexagonaluserservice.adapter.in.web.mapper.ExceptionMapper;
import phzzk.phzzkhexagonaluserservice.adapter.in.web.response.ErrorResponse;
import phzzk.phzzkhexagonaluserservice.domain.exception.CustomException;
import phzzk.phzzkhexagonaluserservice.domain.exception.ErrorCode;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        // 1. 서버 내부에 디테일 로그 기록 (관리자 용)
        log.error("[CustomException] code={}, type={}, message={}",
                errorCode.getCode(), errorCode.getErrorType(), errorCode.getMessage(), ex);

        // 2. 사용자에게는 짧은 메시지만
        ErrorResponse response = new ErrorResponse(
                errorCode.getCode(),
                mapToUserFriendlyMessage(errorCode) // 사용자 친화 메시지로 변환
        );
        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    private String mapToUserFriendlyMessage(ErrorCode errorCode) {
        if (errorCode.getStatus().is5xxServerError()) {
            return "일시적인 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.";
        }
        // 클라이언트 잘못 (400번대)은 상세 메시지 보여줄 수 있음
        return errorCode.getMessage();
    }
}