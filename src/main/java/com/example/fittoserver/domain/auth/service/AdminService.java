package com.example.fittoserver.domain.auth.service;

import com.example.fittoserver.domain.auth.converter.AuthConverter;
import com.example.fittoserver.domain.auth.dto.AuthRequestDTO;
import com.example.fittoserver.domain.auth.dto.AuthResponseDTO;
import com.example.fittoserver.domain.user.entity.UserEntity;
import com.example.fittoserver.domain.user.service.UserService;
import com.example.fittoserver.global.common.api.status.ErrorStatus;
import com.example.fittoserver.global.common.util.HashIdUtil;
import com.example.fittoserver.global.exception.GeneralException;
import com.example.fittoserver.global.security.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Value("${admin.login-key}")
    private String adminKey;

    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final HashIdUtil hashIdUtil;

    public AuthResponseDTO.LoginWithAdminRes loginWithAdmin(AuthRequestDTO.LoginWithAdminReq request) {

        // 키 검증
        if (!adminKey.equals(request.getAdminKey())) {
            throw new GeneralException(ErrorStatus.ACCESS_DENY);
        }

        UserEntity admin = userService.getAdmin()
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        String hashedUserId = hashIdUtil.encode(admin.getId());

        String access = jwtUtil.createJwt("access", hashedUserId, admin.getRole(), 600_000L);      // 10분
        String refresh = jwtUtil.createJwt("refresh", hashedUserId, admin.getRole(), 86_400_000L); // 24시간

        return AuthConverter.toLoginWithAdminRes(access, refresh);
    }
}
