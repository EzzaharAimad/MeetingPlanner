package com.meetingPlanner.meeting.mapper;

import com.meetingPlanner.meeting.dto.MeetingDTO;
import com.meetingPlanner.meeting.entity.Meeting;
import com.meetingPlanner.meeting.entity.enumerator.MeetingType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class MeetingMapperTest {
    private MeetingMapper meetingMapper;
    @BeforeEach
    void setUp(){
        meetingMapper=new MeetingMapper();
    }
    @Test
    void is_meetingDTO_mapped_to_meeting(){
        //given
        MeetingDTO meetingDTO=new MeetingDTO("2000-10-01 13:00","RC",10);

        //when
        Meeting meeting=meetingMapper.fromMeetingDtoToMeeting(meetingDTO);

        //then
        Assertions.assertEquals(meetingDTO.getPersonNumber(),meeting.getPersonNumber());
        Assertions.assertEquals(MeetingType.RC,meeting.getMeetingType());
        Assertions.assertEquals( LocalDateTime.parse("2000-10-01T13:00"),meeting.getTimeSlot());
    }
}