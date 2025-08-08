package com.example.fittoserver.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthResponseDTO {
    @Builder
    @Getter
    public static class SignUpRes {
        private Long memberId;
        private String createAt;
    }

    @Builder
    @Getter
    public static class LoginWithAdminRes {
        private String access;
        private String refresh;
    }

    @Builder
    @Getter
    public static class LoginRes {
        private String userId;
    }

    @Getter
    @AllArgsConstructor
    public static class LoginResult {
        private AuthResponseDTO.LoginRes loginRes;
        private String accessToken;
        private String refreshToken;
    }
}
