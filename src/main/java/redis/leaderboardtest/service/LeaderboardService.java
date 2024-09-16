package redis.leaderboardtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LeaderboardService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String LEADERBOARD_KEY = "leaderboard";

    /**
     * 리더보드에 새로운 사용자와 점수를 추가
     * 사용자 이름과 점수는 Redis의 ZSet(sorted Set)에 저장
     */
    // score = 버튼 누른 시간
    public void addWinner(String username, double score) {
        // LEADERBOARD_KEY에 해당하는 ZSet에 사용자를 추가합니다.
        redisTemplate.opsForZSet().add(LEADERBOARD_KEY, username, score);
    }

    /**
     * 리더보드에서 상위 count 개의 사용자 정보를 Set으로 반환
     */
    public Set<String> getTopWinners(int count) {
        // LEADERBOARD_KEY에 해당하는 ZSet에서 상위 'count' 개의 사용자 정보를 가져옵니다.
        return redisTemplate.opsForZSet().range(LEADERBOARD_KEY, 0, count - 1);
    }

    /**
     * 리더보드의 모든 항목을 초기화
     * LEADERBOARD_KEY에 해당하는 ZSet의 모든 항목을 제거
     * 범위를 0 ~ -1 로 주었기 때문에 모든 항목 제거가 됨
     */
    public void clearLeaderboard() {
        // LEADERBOARD_KEY에 해당하는 ZSet의 모든 항목을 제거합니다.
        redisTemplate.opsForZSet().removeRange(LEADERBOARD_KEY, 0, -1);
    }
}