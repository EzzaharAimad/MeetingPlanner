package com.meetingPlanner.meeting.service.implementation;

import com.meetingPlanner.meeting.dto.MeetingDTO;
import com.meetingPlanner.meeting.entity.Meeting;
import com.meetingPlanner.meeting.entity.Room;
import com.meetingPlanner.meeting.entity.enumerator.EquipmentType;
import com.meetingPlanner.meeting.entity.enumerator.MeetingType;
import com.meetingPlanner.meeting.mapper.MeetingMapper;
import com.meetingPlanner.meeting.repository.MeetingRepository;
import com.meetingPlanner.meeting.repository.RoomRepository;
import com.meetingPlanner.meeting.service.MeetingService;
import com.meetingPlanner.meeting.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MeetingServiceImplTest {

    private MeetingService meetingService;
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private MeetingMapper meetingMapper;
    @Autowired
    private RoomService roomService;

    @BeforeEach
    void setUp(){
        meetingService=new MeetingServiceImpl(meetingRepository,meetingMapper,roomService);
    }

    @Test
    void does_the_room_suit_the_meeting(){
        //given
        MeetingDTO meetingDTO=new MeetingDTO("2000-10-01 13:00","RS",8);
        Room room=new  Room("E1001", 23, Arrays.asList(EquipmentType.NOTHING));
        Meeting meeting=new Meeting();
        meeting.setMeetingType(MeetingType.RS);
        meeting.setTimeSlot(LocalDateTime.parse("2000-10-01T13:00"));
        meeting.setPersonNumber(8);

        //when
        String actualMeeting=meetingService.reserve(meetingDTO);

        //then
        assertEquals(room.getRoomName()+" is reserved successfully, with the capacity of "+meeting.getPersonNumber()+" people at "+ meeting.getTimeSlot().getHour()+":"+meeting.getTimeSlot().getMinute()+" "+meeting.getTimeSlot().getDayOfWeek()+" "+meeting.getTimeSlot().getDayOfMonth()+" "+meeting.getTimeSlot().getMonth()+" "+meeting.getTimeSlot().getYear(),actualMeeting);
    }

}