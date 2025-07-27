package com.example.fittoserver.domain.challenge.entity;

import com.example.fittoserver.domain.meeting.entity.Member;
import com.example.fittoserver.global.common.entity.BaseEntity;
import com.example.fittoserver.global.common.util.DurationConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "challenge_participants")
public class ChallengeParticipant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    @Builder.Default
    @Convert(converter = DurationConverter.class)
    private Duration totalTime = Duration.ZERO;

    @Column(nullable = false)
    @Builder.Default
    private Boolean hasAccepted = false;
}
