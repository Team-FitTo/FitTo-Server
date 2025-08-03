package com.example.fittoserver.global.security.handler;

import com.example.fittoserver.global.common.api.ApiResponse;
import com.example.fittoserver.global.common.api.status.ErrorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ErrorStatus errorStatus = ErrorStatus.UNAUTHORIZED;

        response.setStatus(errorStatus.getHttpStatus().value());
        response.setContentType("application/json;charset=UTF-8");

        ApiResponse<?> errorResponse = ApiResponse.onFailure(
                errorStatus.getCode(),
                errorStatus.getMessage(),
                null
        );

        String json = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(json);
    }
}