package phzzk.phzzkhexagonaluserservice.adapter.out.persistence.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import phzzk.phzzkhexagonaluserservice.application.port.out.UserRepositoryPort;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class PersistenceConfig {

    @Qualifier("jpaUserRepositoryAdapter")
    private final UserRepositoryPort jpaAdapter;

    @Qualifier("mybatisUserRepositoryAdapter")
    private final UserRepositoryPort myBatisAdapter;

    @Qualifier("mongoUserRepositoryAdapter")
    private final UserRepositoryPort mongoAdapter;

    @Value("${app.persistence.type}")
    private String persistenceTypeValue;

    public PersistenceConfig(
            @Qualifier("jpaUserRepositoryAdapter") UserRepositoryPort jpaAdapter,
            @Qualifier("mybatisUserRepositoryAdapter") UserRepositoryPort myBatisAdapter,
            @Qualifier("mongoUserRepositoryAdapter") UserRepositoryPort mongoAdapter
    ) {
        this.jpaAdapter = jpaAdapter;
        this.myBatisAdapter = myBatisAdapter;
        this.mongoAdapter = mongoAdapter;
    }

    @Bean
    public UserRepositoryPort userRepositoryPort() {
        Map<PersistenceType, UserRepositoryPort> strategyMap = new EnumMap<>(PersistenceType.class);
        strategyMap.put(PersistenceType.JPA, jpaAdapter);
        strategyMap.put(PersistenceType.MYBATIS, myBatisAdapter);
        strategyMap.put(PersistenceType.MONGODB, mongoAdapter);

        PersistenceType type = PersistenceType.from(persistenceTypeValue);  // ← 여기서도 검증 로직이 명확해야 좋음

        UserRepositoryPort port = strategyMap.get(type);
        if (port == null) {
            throw new IllegalStateException("지원하지 않는 PersistenceType: " + persistenceTypeValue);
        }
        return port;
    }
}
