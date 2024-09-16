package redis.leaderboardtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisConfig는 Spring Boot 애플리케이션의 Redis 관련 설정을 담당하는 클래스입니다.
 * 이 클래스는 Spring의 @Configuration 어노테이션을 통해
 * RedisTemplate을 빈으로 등록하고 Redis와의 연결을 구성합니다.
 */
@Configuration
public class RedisConfig {

    /**
     * RedisTemplate을 빈으로 등록하는 메소드입니다.
     * RedisTemplate은 Redis와 상호작용할 때 사용하는 템플릿 클래스입니다.
     * 이 템플릿을 통해 Redis의 데이터 구조를 쉽게 다룰 수 있습니다.
     *
     * @param connectionFactory Redis와의 연결을 위한 RedisConnectionFactory 인스턴스입니다.
     * @return 설정된 RedisTemplate 인스턴스입니다.
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        // RedisTemplate 인스턴스를 생성합니다.
        RedisTemplate<String, String> template = new RedisTemplate<>();

        // RedisConnectionFactory를 설정하여 Redis 서버와의 연결을 구성합니다.
        template.setConnectionFactory(connectionFactory);

        // Redis의 키를 직렬화하는 방법을 설정합니다.
        // 여기서는 StringRedisSerializer를 사용하여 문자열 형태의 키를 직렬화합니다.
        template.setKeySerializer(new StringRedisSerializer());

        // Redis의 값을 직렬화하는 방법을 설정합니다.
        // 여기서는 StringRedisSerializer를 사용하여 문자열 형태의 값을 직렬화합니다.
        template.setValueSerializer(new StringRedisSerializer());

        // 설정이 완료된 RedisTemplate을 반환합니다.
        return template;
    }
}