package phzzk.phzzkhexagonaluserservice.adapter.out.persistence.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import phzzk.phzzkhexagonaluserservice.application.port.out.SaveUserPort;

import java.util.EnumMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class PersistenceConfig {

    private final SaveUserPort jpaPersistenceAdapter;
    private final SaveUserPort myBatisPersistenceAdapter;
    private final SaveUserPort mongoPersistenceAdapter;

    @Value("${app.persistence.type}")
    private String persistenceTypeValue;

    @Bean
    public SaveUserPort saveUserPort() {
        Map<PersistenceType, SaveUserPort> strategyMap = new EnumMap<>(PersistenceType.class);
        strategyMap.put(PersistenceType.JPA, jpaPersistenceAdapter);
        strategyMap.put(PersistenceType.MYBATIS, myBatisPersistenceAdapter);
        strategyMap.put(PersistenceType.MONGODB, mongoPersistenceAdapter);

        PersistenceType type = PersistenceType.from(persistenceTypeValue); // 여기서 변환 + 검증

        return strategyMap.get(type);
    }

    @Bean
    public LoadUserPort loadUserPort() {
        return (LoadUserPort) saveUserPort();
    }
}
