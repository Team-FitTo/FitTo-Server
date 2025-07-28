package com.example.fittoserver.domain.meeting.entity;

import com.example.fittoserver.domain.challenge.entity.ChallengeParticipant;
import com.example.fittoserver.domain.user.entity.UserEntity;
import com.example.fittoserver.global.common.entity.BaseEntity;
import com.example.fittoserver.global.common.util.DurationConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "members")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Builder.Default
    @Convert(converter = DurationConverter.class)
    private Duration todayWorkoutTime = Duration.ZERO;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isWorkingOut = false;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChallengeParticipant> challengeParticipants;
}
