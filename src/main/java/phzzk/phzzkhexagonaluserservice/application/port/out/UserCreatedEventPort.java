package phzzk.phzzkhexagonaluserservice.application.port.out;

import phzzk.phzzkhexagonaluserservice.domain.model.user.User;

public interface UserCreatedEventPort {
    void sendUserCreated(User user);
}
