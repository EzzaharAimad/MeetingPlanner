package com.meetingPlanner.meeting.service.implementation;
import com.meetingPlanner.meeting.Exception.RoomException;
import com.meetingPlanner.meeting.entity.Meeting;
import com.meetingPlanner.meeting.entity.Room;
import com.meetingPlanner.meeting.entity.enumerator.EquipmentType;
import com.meetingPlanner.meeting.entity.enumerator.MeetingType;
import com.meetingPlanner.meeting.repository.RoomRepository;
import com.meetingPlanner.meeting.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class RoomServiceImplTest {
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;

    @BeforeEach
    void setUp(){
        roomService=new RoomServiceImpl(roomRepository);
    }
    @Test
    void does_it_get_available_rooms_from_unavailable_rooms(){
        //given
        Meeting meeting=new Meeting();
        meeting.setTimeSlot(LocalDateTime.parse("2000-10-01T13:00"));
        meeting.setPersonNumber(10);
        meeting.setMeetingType(MeetingType.RS);
        Room room=new Room("E1002", 10, Arrays.asList(EquipmentType.SCREEN));
        Room room1=new Room("E1003", 8, Arrays.asList(EquipmentType.CABLE));
        Room room2=new Room("E1004", 4, Arrays.asList(EquipmentType.BOARD));
        Room room3=new Room("E2001", 4, Arrays.asList(EquipmentType.NOTHING));
        List<Room> roomList=new ArrayList<>(Arrays.asList(room3,room1,room2,room));
        Room expectedRoom=new  Room("E1001", 23, Arrays.asList(EquipmentType.NOTHING));

        //when
        Room actualRoom=roomService.checkRooms(roomList,meeting);

        //then
        Assertions.assertEquals(expectedRoom,actualRoom);

    }

    @Test
    void is_room_available_in_this_date(){
        //given
        Meeting meeting=new Meeting();
        meeting.setTimeSlot(LocalDateTime.parse("2000-10-01T13:00"));
        meeting.setPersonNumber(10);
        meeting.setMeetingType(MeetingType.RS);
        Room room10=new Room("E1001", 23, Arrays.asList(EquipmentType.NOTHING));
        roomRepository.save(room10);
        Room room=new Room("E1002", 10, Arrays.asList(EquipmentType.SCREEN));
        Room room1=new Room("E1003", 8, Arrays.asList(EquipmentType.CABLE));
        Room room2=new Room("E1004", 4, Arrays.asList(EquipmentType.BOARD));
        Room room3=new Room("E2001", 4, Arrays.asList(EquipmentType.NOTHING));
        Room room4=new Room("E2002", 15, Arrays.asList(EquipmentType.SCREEN, EquipmentType.WEBCAM));
        Room room5=new Room("E2003", 7, Arrays.asList(EquipmentType.NOTHING));
        Room room6=new Room("E2004", 9, Arrays.asList(EquipmentType.BOARD));
        Room room7=new Room("E3001", 13, Arrays.asList(EquipmentType.SCREEN, EquipmentType.WEBCAM, EquipmentType.CABLE));
        Room room8=new Room("E3002", 8, Arrays.asList(EquipmentType.NOTHING));
        Room room9=new Room("E3004", 4, Arrays.asList(EquipmentType.NOTHING));
        List<Room> roomList=new ArrayList<>(Arrays.asList(room,room1,room2,room3,room4,room5,room6,room7,room8,room9,room10));

        //when
        String actualMessage=Assertions.assertThrows(RoomException.class,()->{
            roomService.checkRooms(roomList,meeting);
                }).getMessage();

        //then
        Assertions.assertEquals("Sorry no room was found at this date",actualMessage);

    }

    @Test
    void is_room_available_for_this_type(){
        //given
        Meeting meeting=new Meeting();
        meeting.setTimeSlot(LocalDateTime.parse("2000-10-01T13:00"));
        meeting.setPersonNumber(10);
        meeting.setMeetingType(MeetingType.RC);
        Room room10=new Room("E1001", 23, Arrays.asList(EquipmentType.NOTHING));
        roomRepository.save(room10);
        Room room=new Room("E1002", 10, Arrays.asList(EquipmentType.SCREEN));
        Room room1=new Room("E1003", 8, Arrays.asList(EquipmentType.CABLE));
        Room room2=new Room("E1004", 4, Arrays.asList(EquipmentType.BOARD));
        Room room3=new Room("E2001", 4, Arrays.asList(EquipmentType.NOTHING));
        List<Room> roomList=new ArrayList<>(Arrays.asList(room,room1,room2,room3,room10));

        //when
        String actualMessage=Assertions.assertThrows(RoomException.class,()->{
            roomService.checkRooms(roomList,meeting);
        }).getMessage();

        //then
        Assertions.assertEquals("Sorry no room was found for this type",actualMessage);
    }

    @Test
    void is_room_available_for_this_number_of_people(){
        //given
        Meeting meeting=new Meeting();
        meeting.setTimeSlot(LocalDateTime.parse("2000-10-01T13:00"));
        meeting.setPersonNumber(20);
        meeting.setMeetingType(MeetingType.RS);
        Room room10=new Room("E1001", 23, Arrays.asList(EquipmentType.NOTHING));
        roomRepository.save(room10);
        Room room=new Room("E1002", 10, Arrays.asList(EquipmentType.SCREEN));
        Room room1=new Room("E1003", 8, Arrays.asList(EquipmentType.CABLE));
        Room room2=new Room("E1004", 4, Arrays.asList(EquipmentType.BOARD));
        Room room3=new Room("E2001", 4, Arrays.asList(EquipmentType.NOTHING));
        List<Room> roomList=new ArrayList<>(Arrays.asList(room,room1,room2,room3,room10));

        //when
        String actualMessage=Assertions.assertThrows(RoomException.class,()->{
            roomService.checkRooms(roomList,meeting);
        }).getMessage();

        //then
        Assertions.assertEquals("Sorry, no room was found for this number of people",actualMessage);
    }
}