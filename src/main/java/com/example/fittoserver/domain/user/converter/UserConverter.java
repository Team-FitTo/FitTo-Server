package com.example.fittoserver.domain.user.converter;

import com.example.fittoserver.domain.auth.dto.AuthRequestDTO;
import com.example.fittoserver.domain.auth.dto.KakaoDTO;
import com.example.fittoserver.domain.user.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserConverter {

    //    UserEntity 객체를 만드는 작업 (클라이언트가 준 DTO to Entity)
    public static UserEntity toUser(KakaoDTO.KakaoProfile kakaoProfile){

        return UserEntity.builder()
                .kakaoId(kakaoProfile.getId())
//                .role("ROLE_USER")
//                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }
}
