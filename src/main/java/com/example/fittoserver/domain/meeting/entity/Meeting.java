package com.example.fittoserver.domain.meeting.entity;

import com.example.fittoserver.domain.challenge.entity.Challenge;
import com.example.fittoserver.domain.workout.entity.WorkoutRecord;
import com.example.fittoserver.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "Meetings")
public class Meeting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkoutRecord.WorkoutType workoutType;

    private String description;

    @Column(nullable = false)
    private Integer maxMembers;

    @Column(nullable = false)
    private Integer memberCount;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isWorkingOut = false;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Challenge> challenges;
}