package com.meetingPlanner.meeting.repository;

import com.meetingPlanner.meeting.entity.Meeting;
import com.meetingPlanner.meeting.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface MeetingRepository extends JpaRepository<Meeting,String> {
    @Query("select meeting.room from Meeting meeting where meeting.timeSlot between :start and :end ")
    List<Room> unavailableRooms(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
