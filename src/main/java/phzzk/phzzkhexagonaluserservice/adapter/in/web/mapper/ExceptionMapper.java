package phzzk.phzzkhexagonaluserservice.adapter.in.web.mapper;

import phzzk.phzzkhexagonaluserservice.adapter.in.web.response.ErrorResponse;
import phzzk.phzzkhexagonaluserservice.domain.exception.ErrorCode;

public class ExceptionMapper {
    public static ErrorResponse toErrorResponse(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
    }
}