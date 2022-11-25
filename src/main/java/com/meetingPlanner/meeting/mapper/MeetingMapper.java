package com.meetingPlanner.meeting.mapper;

import com.meetingPlanner.meeting.dto.MeetingDTO;
import com.meetingPlanner.meeting.entity.Meeting;
import com.meetingPlanner.meeting.entity.enumerator.MeetingType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MeetingMapper {
    public Meeting fromMeetingDtoToMeeting(MeetingDTO meetingDTO){
        Meeting meeting = new Meeting();
        meeting.setMeetingType(MeetingType.valueOf(meetingDTO.getMeetingType()));
        meeting.setPersonNumber(meetingDTO.getPersonNumber());
        meeting.setTimeSlot(fromStringToLocalDateTime(meetingDTO.getTimeSlot()));
        return meeting;
    }
    private LocalDateTime fromStringToLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(date, formatter);
    }

}
