package com.meetingPlanner.meeting;

import com.meetingPlanner.meeting.entity.Room;
import com.meetingPlanner.meeting.entity.enumerator.EquipmentType;
import com.meetingPlanner.meeting.repository.MeetingRepository;
import com.meetingPlanner.meeting.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class MeetingPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingPlannerApplication.class, args);
	}
	@Bean
	CommandLineRunner start(RoomRepository roomRepository, MeetingRepository meetingRepository){
		return args -> {
			Room room1=new Room("E1001", 23, Arrays.asList(EquipmentType.NOTHING));
			roomRepository.save(room1);
			roomRepository.save(new Room("E1002", 10, Arrays.asList(EquipmentType.SCREEN)));
			roomRepository.save(new Room("E1003", 8, Arrays.asList(EquipmentType.CABLE)));
			roomRepository.save(new Room("E1004", 4, Arrays.asList(EquipmentType.BOARD)));
			roomRepository.save(new Room("E2001", 4, Arrays.asList(EquipmentType.NOTHING)));
			roomRepository.save(new Room("E2002", 15, Arrays.asList(EquipmentType.SCREEN, EquipmentType.WEBCAM)));
			roomRepository.save(new Room("E2003", 7, Arrays.asList(EquipmentType.NOTHING)));
			roomRepository.save(new Room("E2004", 9, Arrays.asList(EquipmentType.BOARD)));
			roomRepository.save(new Room("E3001", 13, Arrays.asList(EquipmentType.SCREEN, EquipmentType.WEBCAM, EquipmentType.CABLE)));
			roomRepository.save(new Room("E3002", 8, Arrays.asList(EquipmentType.NOTHING)));
			roomRepository.save(new Room("E3004", 4, Arrays.asList(EquipmentType.NOTHING)));

		};

	}

}
