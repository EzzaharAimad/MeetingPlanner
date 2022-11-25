package com.meetingPlanner.meeting.service.implementation;

import com.meetingPlanner.meeting.Exception.RoomException;
import com.meetingPlanner.meeting.entity.Meeting;
import com.meetingPlanner.meeting.entity.Room;
import com.meetingPlanner.meeting.entity.enumerator.EquipmentType;
import com.meetingPlanner.meeting.repository.RoomRepository;
import com.meetingPlanner.meeting.service.RoomService;
import com.meetingPlanner.meeting.utils.MatchMeetingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room checkRooms( List<Room> rooms,Meeting meeting) {
        List<Room> availableRooms = roomRepository.availableRooms(rooms);
        if (availableRooms.isEmpty()) {
            throw new RoomException("Sorry no room was found at this date");
        } else {
            List<EquipmentType> equipmentTypeList = MatchMeetingType.matchMeetingTypeToEquipments(meeting.getMeetingType());
            List<Room> roomsFilteredByType = checkRoomsByEquipment(availableRooms, equipmentTypeList);
            return checkRoomsByCapacity(roomsFilteredByType, meeting.getPersonNumber());
        }
    }
    private List<Room> checkRoomsByEquipment(List<Room> rooms, List<EquipmentType> equipmentTypeList){
        List<Room> roomList= rooms.stream().filter(room -> room.getEquipmentType().containsAll(equipmentTypeList)).collect(Collectors.toList());
        if(roomList.isEmpty()){
            throw new RoomException("Sorry no room was found for this type");
        }
        return roomList;
    }
    private Room checkRoomsByCapacity( List<Room> rooms,int capacity){

        Optional<Room> room1= rooms.stream().filter(room->room.getMaxCapacity()*0.7>=capacity).min(Comparator.comparing((room -> room.getMaxCapacity()*0.7-capacity)));
        if(room1.isEmpty()){
            throw new RoomException("Sorry, no room was found for this number of people");
        }
        return room1.get();

    }
}
