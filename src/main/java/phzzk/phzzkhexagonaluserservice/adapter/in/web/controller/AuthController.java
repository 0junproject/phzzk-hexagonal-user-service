package phzzk.phzzkhexagonaluserservice.adapter.in.web.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phzzk.phzzkhexagonaluserservice.adapter.in.security.userdetails.SecurityUser;
import phzzk.phzzkhexagonaluserservice.adapter.in.web.request.LoginRequest;
import phzzk.phzzkhexagonaluserservice.adapter.in.web.request.RegisterRequest;
import phzzk.phzzkhexagonaluserservice.adapter.in.web.response.SuccessResponse;
import phzzk.phzzkhexagonaluserservice.adapter.in.web.response.UserDto;
import phzzk.phzzkhexagonaluserservice.adapter.out.security.JwtTokenProvider;
import phzzk.phzzkhexagonaluserservice.application.port.in.RegisterUserUseCase;
import phzzk.phzzkhexagonaluserservice.application.service.RegisterUserService;
import phzzk.phzzkhexagonaluserservice.domain.model.user.User;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final RegisterUserUseCase registerUserUseCase;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<UserDto>> login(
            @RequestBody LoginRequest request,
            HttpServletResponse response
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        String token = jwtTokenProvider.generateAccessToken(user.getUsername());

        // ✅ JWT를 HttpOnly 쿠키로 설정
        Cookie cookie = new Cookie("access_token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // HTTPS 환경에서만 전송. 개발 중엔 false로 설정 가능
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1시간
        response.addCookie(cookie);

        return ResponseEntity.ok(new SuccessResponse<>(UserDto.from(user.getUser())));
    }
    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<UserDto>> register(
            @RequestBody @Valid RegisterRequest request,
            HttpServletResponse response
    ) {
        // 1. 도메인 유저 생성 및 저장
        User user = registerUserUseCase.register(
                request.getEmail(),
                request.getNickname(),
                request.getPassword()
        );

        log.info(user.getId().getValue());
        // 2. JWT 생성
        String token = jwtTokenProvider.generateAccessToken(user.getId().getValue());

        // 3. JWT를 HttpOnly 쿠키에 저장
        Cookie cookie = new Cookie("access_token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // HTTPS 환경에서 true
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1시간
        response.addCookie(cookie);

        // 4. 사용자 정보 응답
        return ResponseEntity.ok(new SuccessResponse<>(UserDto.from(user)));
    }
}
