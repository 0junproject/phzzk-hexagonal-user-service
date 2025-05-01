package phzzk.phzzkhexagonaluserservice.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import phzzk.phzzkhexagonaluserservice.application.port.in.RegisterUserUseCase;
import phzzk.phzzkhexagonaluserservice.application.port.out.PasswordEncoderPort;
import phzzk.phzzkhexagonaluserservice.application.port.out.UserRepositoryPort;
import phzzk.phzzkhexagonaluserservice.domain.exception.CustomException;
import phzzk.phzzkhexagonaluserservice.domain.exception.ErrorCode;
import phzzk.phzzkhexagonaluserservice.domain.model.user.User;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Email;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Nickname;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Password;

@Service
@Slf4j
public class RegisterUserService implements RegisterUserUseCase {

    @Qualifier("selectedUserRepositoryPort")
    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoder;

    public RegisterUserService(
            @Qualifier("selectedUserRepositoryPort") UserRepositoryPort userRepository,
            PasswordEncoderPort passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override

    public User register(String email, String nickname, String rawPassword) {
        // 1. Email VO 생성 → 유효성 검사 포함
        Email emailVO = new Email(email);

        // 2. 이메일 중복 체크
        if (userRepository.existsByEmail(emailVO)) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }

        // 3. Password 암호화 후 VO 생성
        Password encodedPassword = Password.encode(rawPassword, passwordEncoder);

        // 4. Nickname VO 생성
        Nickname nicknameVO = new Nickname(nickname);

        // 5. 도메인 User 생성
        User user = User.createNew(emailVO, encodedPassword, nicknameVO);

        // 6. 저장
        return userRepository.save(user);
    }
}
