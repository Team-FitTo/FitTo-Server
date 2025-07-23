package com.example.fittoserver.global.config;

import com.example.fittoserver.domain.auth.jwt.CustomLogoutFilter;
import com.example.fittoserver.domain.auth.jwt.JWTFilter;
import com.example.fittoserver.domain.auth.jwt.JWTUtil;
import com.example.fittoserver.domain.auth.service.RefreshTokenService;
import com.example.fittoserver.domain.user.repository.UserRepository;
import com.example.fittoserver.global.common.util.HashIdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTUtil jwtUtil;
    private final HashIdUtil hashIdUtil;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    public SecurityConfig(JWTUtil jwtUtil, HashIdUtil hashIdUtil, UserRepository userRepository, RefreshTokenService refreshTokenService) {
        this.jwtUtil = jwtUtil;
        this.hashIdUtil = hashIdUtil;
        this.userRepository = userRepository;
        this.refreshTokenService = refreshTokenService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disable
        http
                .csrf((auth) -> auth.disable());
        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());
        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());
        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/auth/**", "/").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**").permitAll()
                        .anyRequest().authenticated());
        //JWT 검증 Filter 추가
        http
                .addFilterBefore(new JWTFilter(jwtUtil, hashIdUtil, userRepository), UsernamePasswordAuthenticationFilter.class);
        // 로그아웃 필터
        http
                .addFilterBefore(new CustomLogoutFilter(refreshTokenService, jwtUtil), LogoutFilter.class);
        //세션 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
