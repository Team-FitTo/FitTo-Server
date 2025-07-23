package com.example.fittoserver.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

public class AuthRequestDTO {

    @Getter
    public static class LoginReq{

        @NotEmpty
        private String accessCode;
    }
}
