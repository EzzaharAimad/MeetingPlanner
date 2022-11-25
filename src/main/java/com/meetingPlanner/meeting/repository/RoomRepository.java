package com.meetingPlanner.meeting.repository;

import com.meetingPlanner.meeting.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    @Query("select room from Room room where room not in :rooms")
    List<Room> availableRooms(@Param("rooms") List<Room> unavailableRooms);

}
