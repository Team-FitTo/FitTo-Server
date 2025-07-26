package com.example.fittoserver.domain.workout.entity;

import com.example.fittoserver.domain.user.entity.UserEntity;
import com.example.fittoserver.global.common.entity.BaseEntity;
import com.example.fittoserver.global.common.util.DurationConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "workout_records")
public class WorkoutRecord extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkoutType workoutType;

    @Column(nullable = false)
    @Convert(converter = DurationConverter.class)
    private Duration workoutTime;

    @Column(nullable = false)
    private LocalDateTime recordedAt;

    public enum WorkoutType {
        RUNNING, CYCLING, STRENGTH
    }
}
