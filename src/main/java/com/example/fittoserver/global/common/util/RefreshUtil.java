package com.example.fittoserver.global.common.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class RefreshUtil {

    private final RedisTemplate<String, String> redisTemplate;

    public RefreshUtil(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Refresh token을 Redis에 저장
    public void addRefreshToken(String hashedUserId, String refreshToken, long expirationTimeInMillis) {
        redisTemplate.opsForValue().set(hashedUserId, refreshToken, expirationTimeInMillis, TimeUnit.MILLISECONDS);
    }

    // Refresh token을 Redis에서 삭제
    public void removeRefreshToken(String hashedUserId) {
        redisTemplate.delete(hashedUserId);
    }

    // Refresh token을 Redis에서 가져오기
    public String getRefreshToken(String hashedUserId) {
        System.out.println("Received refresh token: " + redisTemplate.opsForValue().get(hashedUserId));
        return redisTemplate.opsForValue().get(hashedUserId);
    }
}
