package com.example.fittoserver.domain.auth.service;

import com.example.fittoserver.domain.auth.converter.AuthConverter;
import com.example.fittoserver.domain.auth.dto.AuthResponseDTO;
import com.example.fittoserver.domain.auth.dto.KakaoDTO;
import com.example.fittoserver.domain.auth.jwt.JWTUtil;
import com.example.fittoserver.domain.auth.util.KakaoUtil;
import com.example.fittoserver.domain.user.entity.UserEntity;
import com.example.fittoserver.domain.user.converter.UserConverter;
import com.example.fittoserver.domain.user.repository.UserRepository;
import com.example.fittoserver.global.common.util.HashIdUtil;
import com.example.fittoserver.global.common.util.RefreshUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final KakaoUtil kakaoUtil;
    private final RefreshUtil refreshUtil;
    private final HashIdUtil hashIdUtil;

    public AuthResponseDTO.LoginResult kakaoLogin(String accessCode) {
        KakaoDTO.OAuthToken oAuthToken = kakaoUtil.requestToken(accessCode);
        KakaoDTO.KakaoProfile kakaoProfile = kakaoUtil.requestProfile(oAuthToken);
        Long kakaoProfileId = kakaoProfile.getId();

        UserEntity user = userRepository.findByKakaoId(kakaoProfileId)
                .orElseGet(() -> createNewUser(kakaoProfile));

        String hashedUserId = hashIdUtil.encode(user.getId());

        String access = jwtUtil.createJwt("access", hashedUserId, user.getRole(), 600_000L);      // 10분
        String refresh = jwtUtil.createJwt("refresh", hashedUserId, user.getRole(), 86_400_000L); // 24시간

        refreshUtil.addRefreshToken(hashedUserId, refresh, 86_400_000L);

        return new AuthResponseDTO.LoginResult(AuthConverter.toLoginRes(hashedUserId), access, refresh);
    }

    private UserEntity createNewUser(KakaoDTO.KakaoProfile kakaoProfile) {
        UserEntity newUser = UserConverter.toUser(kakaoProfile);
        return userRepository.save(newUser);
    }
}
