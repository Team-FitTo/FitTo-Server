package com.example.fittoserver.domain.user.repository;

import com.example.fittoserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByKakaoId(Long kakaoId);
    Optional<UserEntity> findByRole(String role);
}
