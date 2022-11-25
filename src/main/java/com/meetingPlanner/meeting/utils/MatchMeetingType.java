package com.meetingPlanner.meeting.utils;

import com.meetingPlanner.meeting.entity.enumerator.EquipmentType;
import com.meetingPlanner.meeting.entity.enumerator.MeetingType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract  class MatchMeetingType {
    public static  List<EquipmentType> matchMeetingTypeToEquipments(MeetingType meetingType) {
        List<EquipmentType> equipmentTypes = new ArrayList<>();
        switch (meetingType) {
            case RC:
                return new ArrayList<EquipmentType>(Arrays.asList(EquipmentType.SCREEN, EquipmentType.BOARD, EquipmentType.CABLE));
            case VC:
                return new ArrayList<EquipmentType>(Arrays.asList(EquipmentType.SCREEN, EquipmentType.WEBCAM, EquipmentType.CABLE));
            case SPEC:
                return  new ArrayList<EquipmentType>(Arrays.asList(EquipmentType.BOARD));
            case RS:
                return  new ArrayList<EquipmentType>(Arrays.asList(EquipmentType.NOTHING));
        }
        return equipmentTypes;
    }
}
