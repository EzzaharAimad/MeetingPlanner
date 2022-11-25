package com.meetingPlanner.meeting.service.implementation;

import com.meetingPlanner.meeting.Exception.RoomException;
import com.meetingPlanner.meeting.dto.MeetingDTO;
import com.meetingPlanner.meeting.entity.Meeting;
import com.meetingPlanner.meeting.entity.Room;
import com.meetingPlanner.meeting.mapper.MeetingMapper;
import com.meetingPlanner.meeting.repository.MeetingRepository;
import com.meetingPlanner.meeting.service.MeetingService;
import com.meetingPlanner.meeting.service.RoomService;
import com.meetingPlanner.meeting.utils.WorkScheduleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private MeetingMapper meetingMapper;
    @Autowired
    private RoomService roomService;

    public MeetingServiceImpl(MeetingRepository meetingRepository, MeetingMapper meetingMapper, RoomService roomService) {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
        this.roomService = roomService;
    }

    @Override
    public String reserve(MeetingDTO meetingDTO) {
        Meeting meeting=meetingMapper.fromMeetingDtoToMeeting(meetingDTO);
        validationWorkHourDay(meeting.getTimeSlot());
        List<Room> unavailableRooms= meetingRepository.unavailableRooms(meeting.getTimeSlot().minusHours(1),meeting.getTimeSlot().plusHours(1)) ;
        Room roomToReserve= roomService.checkRooms(unavailableRooms,meeting);
        meeting.setRoom(roomToReserve);
        meetingRepository.save(meeting);
        return roomToReserve.getRoomName()+" is reserved successfully, with the capacity of "+meeting.getPersonNumber()+" people at "+ meeting.getTimeSlot().getHour()+":"+meeting.getTimeSlot().getMinute()+" "+meeting.getTimeSlot().getDayOfWeek()+" "+meeting.getTimeSlot().getDayOfMonth()+" "+meeting.getTimeSlot().getMonth()+" "+meeting.getTimeSlot().getYear();
    }

    private void validationWorkHourDay(LocalDateTime timeSlot) {
        if(!WorkScheduleValidation.dayValidation(timeSlot)){
            throw new RoomException("No meetings on the weekend");
        }
        if(!WorkScheduleValidation.hourValidation(timeSlot)){
            throw new RoomException("You're out of work hours");
        }
        if(!WorkScheduleValidation.minuteValidation(timeSlot)){
            if (timeSlot.getHour()==19)throw new RoomException("You should book the room at "+timeSlot.getHour()+":00");
            else throw new RoomException("You should book the room at "+timeSlot.getHour()+":00 or wait till it's "+timeSlot.plusHours(1).getHour()+":00");
        }
    }
}
