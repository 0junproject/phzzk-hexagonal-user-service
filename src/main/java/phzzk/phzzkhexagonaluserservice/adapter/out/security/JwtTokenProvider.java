package phzzk.phzzkhexagonaluserservice.adapter.out.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // Access Token 설정
    @Value("${jwt.access-token.secret}")
    private String accessTokenSecret;

    @Value("${jwt.access-token.expiration}")
    private long accessTokenExpirationMillis;

    @Value("${jwt.refresh-token.secret}")
    private String refreshTokenSecret;

    @Value("${jwt.refresh-token.expiration}")
    private long refreshTokenExpirationMillis;


    // Access Token 발급
    public String generateAccessToken(String userId) {
        return generateToken(
                userId,
                accessTokenSecret,
                accessTokenExpirationMillis
        );
    }

    // Refresh Token 발급
    public String generateRefreshToken(String userId) {
        return generateToken(
                userId,
                refreshTokenSecret,
                refreshTokenExpirationMillis
        );
    }

    // Access Token 검증
    public boolean validateAccessToken(String token) {
        return validateToken(token, accessTokenSecret);
    }

    // Refresh Token 검증
    public boolean validateRefreshToken(String token) {
        return validateToken(token, refreshTokenSecret);
    }

    // Access Token에서 UserId 추출
    public String getUserIdFromAccessToken(String token) {
        return getUserIdFromToken(token, accessTokenSecret);
    }

    // == 내부 메서드 ==

    private String generateToken(String userId, String secret, long expirationMillis) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMillis);

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean validateToken(String token, String secret) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false; // 만료, 위조 등 모두 실패 처리
        }
    }

    private String getUserIdFromToken(String token, String secret) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}