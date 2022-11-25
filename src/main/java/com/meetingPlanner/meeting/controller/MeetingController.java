package com.meetingPlanner.meeting.controller;

import com.meetingPlanner.meeting.dto.MeetingDTO;
import com.meetingPlanner.meeting.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/meeting")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    @PostMapping("/reserve")
    public ResponseEntity<String> reserve(@RequestBody @Valid MeetingDTO meetingDTO){
        return ResponseEntity.status(200).body(meetingService.reserve(meetingDTO));
    }
}
