package phzzk.phzzkhexagonaluserservice.application.port.in;

import phzzk.phzzkhexagonaluserservice.domain.model.user.User;

public interface RegisterUserUseCase {
    User register(String email, String nickname, String password);
}
