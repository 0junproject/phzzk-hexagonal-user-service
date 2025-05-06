package phzzk.phzzkhexagonaluserservice.adapter.out.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreatedEventPayload {
    private String userId;
    private String email;
    private String nickname;
}
