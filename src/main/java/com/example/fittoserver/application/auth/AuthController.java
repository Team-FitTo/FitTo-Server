package com.example.fittoserver.application.auth;

import com.example.fittoserver.domain.auth.dto.AuthRequestDTO;
import com.example.fittoserver.domain.auth.dto.AuthResponseDTO;
import com.example.fittoserver.domain.auth.service.KakaoAuthService;
import com.example.fittoserver.domain.auth.service.ReissueService;
import com.example.fittoserver.global.common.api.ApiResponse;
import com.example.fittoserver.global.common.util.CookieUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@Tag(name = "인증 API", description = "회원가입 및 토큰 재발급 API")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ReissueService reissueService;
    private final KakaoAuthService kakaoAuthService;

    @Operation(summary = "카카오 로그인 및 회원가입")
    @PostMapping("/login/kakao")
    public ApiResponse<AuthResponseDTO.LoginRes> loginWithKakao(
            @Valid @RequestBody AuthRequestDTO.LoginReq request,
            HttpServletResponse response) {

        AuthResponseDTO.LoginResult loginResult = kakaoAuthService.kakaoLogin(request.getAccessCode());

        // 응답 생성
        response.setHeader("Authorization", "Bearer " + loginResult.getAccessToken());
        Cookie refreshCookie = CookieUtil.createCookie("refresh", loginResult.getRefreshToken());
        response.addCookie(refreshCookie);
        return ApiResponse.onSuccess(loginResult.getLoginRes());
    }

    @Operation(summary = "토큰 재발행")
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        return reissueService.reissue(request, response);
    }
}