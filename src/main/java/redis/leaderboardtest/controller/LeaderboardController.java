package redis.leaderboardtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.leaderboardtest.service.LeaderboardService;

import java.util.Set;

/**
 * LeaderboardController는 리더보드와 관련된 HTTP 요청 및 STOMP 메시지를 처리하는 컨트롤러 클래스입니다.
 * 이 클래스는 RESTful API 엔드포인트와 WebSocket 메시지를 처리합니다.
 */
@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 리더보드의 상위 항목을 가져오는 GET 요청을 처리합니다.
     * 클라이언트는 이 엔드포인트를 통해 현재 리더보드를 조회할 수 있습니다.
     *
     * @return 현재 리더보드 상위 10명의 닉네임을 포함한 Set을 ResponseEntity로 반환합니다.
     */
    @GetMapping
    public ResponseEntity<Set<String>> getLeaderboard() {
        // 상위 10명의 닉네임을 가져옵니다.
        Set<String> topWinners = leaderboardService.getTopWinners(10);
        // 리더보드 정보를 HTTP 응답으로 반환합니다.
        return ResponseEntity.ok(topWinners);
    }

    /**
     * 리더보드를 초기화하는 POST 요청을 처리합니다.
     * 클라이언트는 이 엔드포인트를 통해 리더보드를 초기화할 수 있습니다.
     *
     * @return HTTP 200 OK 상태 코드를 반환합니다.
     */
    @PostMapping("/clear")
    public ResponseEntity<Void> clearLeaderboard() {
        // 리더보드를 초기화합니다.
        leaderboardService.clearLeaderboard();
        // 리더보드 업데이트 메시지를 구독 중인 클라이언트에게 전송합니다.
        messagingTemplate.convertAndSend("/topic/leaderboard", leaderboardService.getTopWinners(10));
        // HTTP 200 OK 상태 코드를 반환합니다.
        return ResponseEntity.ok().build();
    }

    /**
     * STOMP 메시지를 처리하는 메소드입니다.
     * 클라이언트가 "/app/addWinner" 경로로 메시지를 전송하면 이 메소드가 호출됩니다.
     * 메시지 본문에 포함된 닉네임을 리더보드에 추가하고, 리더보드의 최신 상태를 클라이언트에게 브로드캐스트합니다.
     *
     * @param nickname 추가할 닉네임입니다.
     * @return 업데이트된 리더보드 상위 10명을 포함한 Set을 반환합니다.
     */
    @MessageMapping("/addWinner")
    @SendTo("/topic/leaderboard")
    public Set<String> addWinner(String nickname) {
        // 현재 시간을 점수로 사용하여 리더보드에 닉네임을 추가합니다.
        double score = System.currentTimeMillis();
        leaderboardService.addWinner(nickname, score);
        // 리더보드의 상위 10명을 가져와 반환합니다.
        return leaderboardService.getTopWinners(10);
    }
}