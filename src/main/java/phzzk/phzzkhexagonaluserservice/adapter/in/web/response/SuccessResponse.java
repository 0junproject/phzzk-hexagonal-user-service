package phzzk.phzzkhexagonaluserservice.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SuccessResponse<T> {
    private final String code = "SUCCESS";
    private final String message = "성공";
    private final T data;
}