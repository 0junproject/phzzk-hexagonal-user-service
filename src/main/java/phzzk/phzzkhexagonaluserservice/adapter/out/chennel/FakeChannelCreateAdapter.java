package phzzk.phzzkhexagonaluserservice.adapter.out.chennel;

import org.springframework.stereotype.Component;
import phzzk.phzzkhexagonaluserservice.application.port.out.ChannelCreatePort;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.Nickname;
import phzzk.phzzkhexagonaluserservice.domain.model.user.vo.UserId;

import java.util.UUID;

@Component
public class FakeChannelCreateAdapter implements ChannelCreatePort {
    @Override
    public String createChannel(UserId userId, Nickname nickname) {
        // 실제로는 채널 서비스에 HTTP 요청을 보내야 함
        return "cnl_" + UUID.randomUUID();
    }
}
