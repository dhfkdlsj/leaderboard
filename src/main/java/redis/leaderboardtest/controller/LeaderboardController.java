package redis.leaderboardtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.leaderboardtest.service.LeaderboardService;

import java.util.Set;

/**
 * LeaderboardController : 리더보드와 관련된 HTTP 요청 및 STOMP 메시지를 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    // SimpMessagingTemplate : WebSocket을 사용하는 애플리케이션에서 메시지를 전송하는 데 사용되는 클래스
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // 리더보드에서 사용자 읽어서 가져오기
    @GetMapping
    public ResponseEntity<Set<String>> getLeaderboard() {
        // 상위 10명의 닉네임을 가져오기
        Set<String> topWinners = leaderboardService.getTopWinners(10);
        // 리더보드 정보를 HTTP 응답으로 반환
        return ResponseEntity.ok(topWinners);
    }

    // 리더보드 초기화 하기
    @PostMapping("/clear")
    public ResponseEntity<Void> clearLeaderboard() {
        // 리더보드를 초기화
        leaderboardService.clearLeaderboard();
        // 리더보드 업데이트 메시지를 구독 중인 클라이언트에게 전송
        messagingTemplate.convertAndSend("/topic/leaderboard", leaderboardService.getTopWinners(10));
        // HTTP 200 OK 상태 코드를 반환
        return ResponseEntity.ok().build();
    }


     // STOMP 메시지를 처리하는 메소드
    @MessageMapping("/addWinner")
    @SendTo("/topic/leaderboard")
    public Set<String> addWinner(String nickname) {
        // 현재 시간을  스코어로 사용하여 리더보드에 닉네임을 추가
        double score = System.currentTimeMillis();
        leaderboardService.addWinner(nickname, score);
        // 리더보드의 상위 10명을 가져와 반환
        return leaderboardService.getTopWinners(10);
    }
}