package com.meetingPlanner.meeting.entity;

import com.meetingPlanner.meeting.entity.enumerator.MeetingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy ="uuid2")
    private String meetingId;
    private LocalDateTime timeSlot;
    private MeetingType meetingType;
    private int personNumber;
    @ManyToOne
    Room room;
}
