package com.example.fittoserver.global.common.api.status;

import com.example.fittoserver.global.common.api.BaseCode;
import com.example.fittoserver.global.common.api.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseCode {

// ========================
// 400 Bad Request
// ========================
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400_000", "잘못된 요청입니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "400_001", "입력값이 올바르지 않습니다."),
    INVALID_FILE_FORMAT(HttpStatus.BAD_REQUEST, "400_002", "업로드된 파일 형식이 올바르지 않습니다."),
    NULL_VALUE(HttpStatus.BAD_REQUEST, "400_003", "Null 값이 들어왔습니다."),
    TEST_ERROR(HttpStatus.BAD_REQUEST, "400_004", "테스트 에러입니다."),
    PAGE_NOT_EXIST(HttpStatus.BAD_REQUEST, "PAGE001", "페이지가 0 이하입니다."),


// ========================
// 401 Unauthorized
// ========================
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401_000", "인증되지 않은 사용자입니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "401_001", "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "401_002", "토큰이 만료되었습니다."),
    TOKEN_CATEGORY_MISMATCH(HttpStatus.UNAUTHORIZED, "401_003", "토큰 형식이 일치하지 않습니다."),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "401_004", "인증 정보가 올바르지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "401_005", "토큰이 생성되지 않았습니다."),
    INVALID_LOGIN(HttpStatus.UNAUTHORIZED, "401_006", "로그인이 필요합니다."),

    // 액세스 토큰
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "401_010", "액세스 토큰이 만료되었습니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "401_011", "액세스 토큰이 유효하지 않습니다."),
    ACCESS_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "401_012", "액세스 토큰이 존재하지 않습니다."),

    // 리프레시 토큰
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "401_020", "리프레시 토큰이 만료되었습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "401_021", "리프레시 토큰이 유효하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "401_022", "저장된 리프레시 토큰이 존재하지 않습니다."),
    REFRESH_TOKEN_MISMATCH(HttpStatus.UNAUTHORIZED, "401_023", "저장된 리프레시 토큰과 일치하지 않습니다."),


// ========================
// 403 Forbidden
// ========================
    FORBIDDEN(HttpStatus.FORBIDDEN, "403_000", "접속 권한이 없습니다."),
    ACCESS_DENY(HttpStatus.FORBIDDEN, "403_001", "접근이 거부되었습니다."),


// ========================
// 404 Not Found
// ========================

    NOT_FOUND_END_POINT(HttpStatus.NOT_FOUND, "404_000", "요청한 대상이 존재하지 않습니다."),

    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "404_010", "해당 사용자를 찾을 수 없습니다."),

    // MEETING
    MEETING_NOT_FOUND(HttpStatus.NOT_FOUND, "404_020", "해당 미팅을 찾을 수 없습니다."),

    // MEMBER
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "404_030", "해당 멤버를 찾을 수 없습니다."),

    // CHALLENGE
    CHALLENGE_NOT_FOUND(HttpStatus.NOT_FOUND, "404_040", "해당 챌린지를 찾을 수 없습니다."),

    // WORKOUT
    WORKOUT_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "404_050", "해당 운동기록을 찾을 수 없습니다."),



// ========================
// 409 Conflict
// ========================

    // USER
    USER_IS_EXIST(HttpStatus.CONFLICT, "409_010", "사용자가 이미 존재합니다."),


// ========================
// 500 Internal Server Error
// ========================

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500_000", "서버 내부 오류입니다."),
    _PARSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500_001", "JSON 파싱 에러."),

    // 외부 API
    _KAKAO_API_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500_010", "KAKAO API 오류입니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ResponseDTO getReason() {
        return ResponseDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ResponseDTO getReasonHttpStatus() {
        return ResponseDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}