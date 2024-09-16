package redis.leaderboardtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /**
     * 스프링 프레임워크에 포함되어있는 레디스 라이브러리 사용.  -> 추후 AWS ElastiCache로 변경
     * RedisTemplate은 Redis와 상호작용할 때 사용하는 템플릿 클래스
     **/
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        // RedisTemplate 생성
        RedisTemplate<String, String> template = new RedisTemplate<>();

        // RedisConnectionFactory를 설정하여 Redis 서버와의 연결을 구성
        // 이 프로젝트의 경우 application.yml 파일에 host와 port를 지정
        // 스프링 부트는 이를 바탕으로 RedisConnectionFactory를 설정
        template.setConnectionFactory(connectionFactory);

        /**
         * 기본적으로 Redis는 값을 바이트 형태로 저장하기 때문 자바 객체를 Redis에 저장하려면 직렬화가 필요.
         * 직렬화(Serialization)란, 객체를 저장하거나 네트워크를 통해 전송하기 위해 객체를 바이트 스트림으로 변환하는 과정
         **/

        // Redis의 키를 직렬화하는 방법을 설정
        // 여기서는 StringRedisSerializer를 사용하여 문자열 형태의 키를 직렬화
        template.setKeySerializer(new StringRedisSerializer());

        // Redis의 값을 직렬화하는 방법을 설정
        // 여기서는 StringRedisSerializer를 사용하여 문자열 형태의 값을 직렬화
        template.setValueSerializer(new StringRedisSerializer());

        // 설정이 완료된 RedisTemplate을 반환
        return template;
    }
}