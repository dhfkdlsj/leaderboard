package redis.leaderboardtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocketConfig는 Spring Boot 애플리케이션에서 WebSocket 및 STOMP 메시징을 설정하는 클래스입니다.
 * 이 클래스는 WebSocket 메시지 브로커와 STOMP 엔드포인트를 구성합니다.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * STOMP 엔드포인트를 등록하는 메소드입니다.
     * 클라이언트가 WebSocket 서버에 연결할 때 사용할 엔드포인트를 설정합니다.
     *
     * @param registry STOMP 엔드포인트를 등록하는 데 사용되는 StompEndpointRegistry 인스턴스입니다.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // "/ws" 엔드포인트를 추가하고, 클라이언트가 SockJS를 통해 연결할 수 있도록 설정합니다.
        registry.addEndpoint("/ws").withSockJS();
    }

    /**
     * 메시지 브로커를 구성하는 메소드입니다.
     * 클라이언트와 서버 간의 메시지 라우팅을 설정합니다.
     *
     * @param registry 메시지 브로커를 설정하는 데 사용되는 MessageBrokerRegistry 인스턴스입니다.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 구독할 수 있는 경로를 설정합니다.
        // "/topic" 경로를 사용하는 메시지를 클라이언트에게 브로드캐스트합니다.
        registry.enableSimpleBroker("/topic");

        // 서버가 메시지를 수신할 경로를 설정합니다.
        // 클라이언트가 "/app" 경로를 통해 서버로 메시지를 전송할 수 있습니다.
        registry.setApplicationDestinationPrefixes("/app");
    }
}