package com.example.fittoserver.application.auth;

import com.example.fittoserver.domain.auth.converter.AuthConverter;
import com.example.fittoserver.domain.auth.dto.AuthRequestDTO;
import com.example.fittoserver.domain.auth.dto.AuthResponseDTO;
import com.example.fittoserver.domain.auth.service.KakaoAuthService;
import com.example.fittoserver.domain.auth.service.SignUpService;
import com.example.fittoserver.domain.auth.service.ReissueService;
import com.example.fittoserver.domain.user.UserEntity;
import com.example.fittoserver.global.common.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    private final SignUpService signUpService;
    private final ReissueService reissueService;
    private final KakaoAuthService kakaoAuthService;

    @Operation(summary = "카카오 로그인 및 회원가입")
    @PostMapping("/login/kakao")
    public ApiResponse<AuthResponseDTO.LoginRes> loginWithKakao(@RequestParam("code") String accessCode) {
        return ApiResponse.onSuccess(kakaoAuthService.kakaoLogin(accessCode));
    }

//    @Operation(summary = "회원가입", description = "Post (username, password, nickname, gender)")
//    @PostMapping("/signup")
//    public ApiResponse<AuthResponseDTO.SignUpRes> signUpProcess(@RequestBody @Valid AuthRequestDTO.SignUpReq request) {
//        UserEntity newUser = signUpService.signUpProcess(request);
//        return ApiResponse.onSuccess(AuthConverter.toSignUpRes(newUser));
//    }

    @Operation(summary = "토큰 재발행", description = "refresh=refreshToken Cookie 요청 (Swagger에서는 쿠키 테스트 불가능하므로 포스트맨 사용 권장)")
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        return reissueService.reissue(request, response);
    }
}