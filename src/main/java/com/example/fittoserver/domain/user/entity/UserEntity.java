package com.example.fittoserver.domain.user.entity;

import com.example.fittoserver.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true)
    private Long kakaoId;

    @Column(nullable = false)
    @Builder.Default
    private String role = "ROLE_USER";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    public enum AccountStatus {
        ACTIVE,         // 활성
        INACTIVE,       // 비활성
        LOCKED,         // 계정 잠김
    }
}
