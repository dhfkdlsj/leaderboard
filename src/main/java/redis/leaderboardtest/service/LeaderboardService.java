package redis.leaderboardtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * LeaderboardService는 Redis를 사용하여 리더보드와 관련된 작업을 처리하는 서비스 클래스입니다.
 * 이 클래스는 리더보드에 대한 CRUD 작업을 수행합니다.
 */
@Service
public class LeaderboardService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String LEADERBOARD_KEY = "leaderboard";

    /**
     * 리더보드에 새로운 사용자와 점수를 추가합니다.
     * 사용자 이름과 점수는 Redis의 ZSet(정렬된 집합)에 저장됩니다.
     *
     * @param username 추가할 사용자의 이름입니다.
     * @param score 사용자의 점수입니다. 현재 시간 등을 점수로 사용할 수 있습니다.
     */
    public void addWinner(String username, double score) {
        // LEADERBOARD_KEY에 해당하는 ZSet에 사용자를 추가합니다.
        redisTemplate.opsForZSet().add(LEADERBOARD_KEY, username, score);
    }

    /**
     * 리더보드에서 상위 사용자들을 가져옵니다.
     * 상위 'count' 개의 사용자 정보를 반환합니다.
     *
     * @param count 반환할 상위 사용자 수입니다.
     * @return 상위 사용자들의 Set입니다.
     */
    public Set<String> getTopWinners(int count) {
        // LEADERBOARD_KEY에 해당하는 ZSet에서 상위 'count' 개의 사용자 정보를 가져옵니다.
        return redisTemplate.opsForZSet().range(LEADERBOARD_KEY, 0, count - 1);
    }

    /**
     * 리더보드의 모든 항목을 초기화합니다.
     * LEADERBOARD_KEY에 해당하는 ZSet의 모든 항목을 제거합니다.
     */
    public void clearLeaderboard() {
        // LEADERBOARD_KEY에 해당하는 ZSet의 모든 항목을 제거합니다.
        redisTemplate.opsForZSet().removeRange(LEADERBOARD_KEY, 0, -1);
    }
}