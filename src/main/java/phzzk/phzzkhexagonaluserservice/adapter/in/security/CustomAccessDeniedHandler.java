package phzzk.phzzkhexagonaluserservice.adapter.in.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import phzzk.phzzkhexagonaluserservice.adapter.in.web.response.ErrorResponse;
import phzzk.phzzkhexagonaluserservice.domain.exception.ErrorCode;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorCode accessDenied = ErrorCode.ACCESS_DENIED;

        ErrorResponse errorResponse = new ErrorResponse(
                accessDenied.getCode(),
                accessDenied.getMessage()
        );

        response.setStatus(HttpStatus.FORBIDDEN.value()); // 403
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(UTF_8.name());

        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(jsonResponse);
    }
}