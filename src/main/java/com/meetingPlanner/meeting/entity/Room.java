package com.meetingPlanner.meeting.entity;

import com.meetingPlanner.meeting.entity.enumerator.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private String roomName;
    private int maxCapacity;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<EquipmentType> equipmentType;

}
