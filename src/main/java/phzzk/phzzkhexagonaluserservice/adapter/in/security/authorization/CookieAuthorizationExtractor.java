package phzzk.phzzkhexagonaluserservice.adapter.in.security.authorization;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CookieAuthorizationExtractor implements AuthorizationExtractor {

    @Value("${jwt.access-token-cookie-name}")
    private String accessTokenCookieName;

    @Override
    public String extract(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        return Arrays.stream(request.getCookies())
                .filter(cookie -> accessTokenCookieName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getType() {
        return "COOKIE";
    }
}
