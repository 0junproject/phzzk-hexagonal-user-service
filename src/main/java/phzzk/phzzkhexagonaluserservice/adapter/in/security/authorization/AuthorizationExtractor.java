package phzzk.phzzkhexagonaluserservice.adapter.in.security.authorization;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthorizationExtractor {
    String extract(HttpServletRequest request);
    String getType(); // BEARER, COOKIE 구분용
}