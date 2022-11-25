package com.meetingPlanner.meeting.service;

import com.meetingPlanner.meeting.dto.MeetingDTO;

public interface MeetingService {
    String reserve(MeetingDTO meetingDTO);
}
