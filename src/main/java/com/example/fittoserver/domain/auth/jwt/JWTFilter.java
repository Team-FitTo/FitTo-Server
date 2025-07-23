package com.example.fittoserver.domain.auth.jwt;

import com.example.fittoserver.domain.user.repository.UserRepository;
import com.example.fittoserver.global.common.api.status.ErrorStatus;
import com.example.fittoserver.global.common.util.HashIdUtil;
import com.example.fittoserver.global.exception.GeneralException;
import com.example.fittoserver.domain.user.UserEntity;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final HashIdUtil hashIdUtil;
    private final UserRepository userRepository;

    public JWTFilter(JWTUtil jwtUtil, HashIdUtil hashIdUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.hashIdUtil = hashIdUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.isExpired(token)) {
            throw new GeneralException(ErrorStatus.ACCESS_TOKEN_EXPIRED);
        }

        String category = jwtUtil.getCategory(token);
        if (!"access".equals(category)) {
            throw new GeneralException(ErrorStatus.INVALID_ACCESS_TOKEN);
        }

        String hashedUserId = jwtUtil.getUserId(token);
        Long userId = hashIdUtil.decode(hashedUserId);
        if (userId == null) {
            throw new GeneralException(ErrorStatus.INVALID_ACCESS_TOKEN);
        }

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Authentication authToken =
                new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(() -> user.getRole()));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}

