package phzzk.phzzkhexagonaluserservice.application.port.out;

import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Nickname;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.UserId;

public interface ChannelCreatePort {
    String createChannel(UserId userId, Nickname nickname);
}
