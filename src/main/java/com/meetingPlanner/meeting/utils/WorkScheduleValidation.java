package com.meetingPlanner.meeting.utils;

import java.time.LocalDateTime;

public abstract class WorkScheduleValidation {

    public  static boolean dayValidation(LocalDateTime dateTime){
        return  dateTime.getDayOfWeek().toString()!="SUNDAY" && dateTime.getDayOfWeek().toString()!="SATURDAY";
    }
    public static boolean hourValidation(LocalDateTime dateTime){
        return  dateTime.getHour()>8 && dateTime.getHour()<20;
    }

    public static boolean minuteValidation(LocalDateTime dateTime){
        return dateTime.getMinute()==0;
    }

}
