package phzzk.phzzkhexagonaluserservice.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import phzzk.phzzkhexagonaluserservice.adapter.out.kafka.model.UserCreatedEventPayload;
import phzzk.phzzkhexagonaluserservice.application.port.out.UserCreatedEventPort;
import phzzk.phzzkhexagonaluserservice.domain.model.user.User;

@Component
@RequiredArgsConstructor
public class UserCreatedKafkaProducer implements UserCreatedEventPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void sendUserCreated(User user) {
        try {
            UserCreatedEventPayload payload = new UserCreatedEventPayload(
                    user.getId().toString(),
                    user.getEmail().getValue(),
                    user.getNickname().getValue()
            );

            String message = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send("user.created", user.getId().toString(), message);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Kafka 메시지 직렬화 실패", e);
        }
    }
}
