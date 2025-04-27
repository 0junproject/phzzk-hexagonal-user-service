package phzzk.phzzkhexagonaluserservice.adapter.in.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import phzzk.phzzkhexagonaluserservice.adapter.in.security.authorization.AuthorizationExtractor;
import phzzk.phzzkhexagonaluserservice.adapter.in.security.jwt.JwtTokenProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Value("${jwt.authorization-type}")
    private String authorizationType; // BEARER or COOKIE

    private final JwtTokenProvider jwtTokenProvider;
    private final Map<String, AuthorizationExtractor> authorizationExtractors;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        //전략 패턴 사용
        AuthorizationExtractor extractor = authorizationExtractors.get(authorizationType.toUpperCase());
        if (extractor == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractor.extract(request);

        if (StringUtils.hasText(token) && jwtTokenProvider.validateAccessToken(token)) {
            String userId = jwtTokenProvider.getUserIdFromAccessToken(token);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userId,         // Principal (여기서는 간단하게 userId)
                            null,           // Credentials (비밀번호는 null)
                            null            // Authorities (권한 목록, 지금은 비워둠)
                    );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
