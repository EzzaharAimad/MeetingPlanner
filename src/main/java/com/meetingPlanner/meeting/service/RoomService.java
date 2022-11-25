package com.meetingPlanner.meeting.service;

import com.meetingPlanner.meeting.entity.Meeting;
import com.meetingPlanner.meeting.entity.Room;

import java.util.List;

public interface RoomService {
    Room checkRooms( List<Room> rooms,Meeting meeting);
}
