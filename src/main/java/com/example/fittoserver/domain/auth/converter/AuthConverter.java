package com.example.fittoserver.domain.auth.converter;

import com.example.fittoserver.domain.auth.dto.AuthResponseDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuthConverter {

    // 날짜를 포맷하는 메서드
    private static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    public static AuthResponseDTO.LoginRes toLoginRes(String hashedUserId){
        return AuthResponseDTO.LoginRes.builder()
                .userId(hashedUserId)
                .build();
    }
}
