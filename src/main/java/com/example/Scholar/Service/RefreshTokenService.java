package com.example.Scholar.Service;

public interface RefreshTokenService {
    String createRefreshToken(Long userId);
    String verifyAndGenerateAccessToken(String refreshToken);
}
