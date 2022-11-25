package com.meetingPlanner.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class MeetingDTO {
    @Pattern(regexp ="^\\d\\d\\d\\d\\-([0]{0,1}[1-9]|1[012])\\-([1-9]|([012][0-9])|(3[01]))\\s([0-1]?[0-9]|2?[0-3]):([0-5]\\d)$",message = "you should enter the date in the following format yyyy-MM-dd HH:mm" )
    @NotNull(message="The time slot should not be null.")
    private String timeSlot;
    @NotNull(message = "The meeting type should not be null")
    @Pattern(regexp ="RC|RS|SPEC|VC",message = "The possible types are : RS, RC, VC, or SPEC" )
    private  String meetingType;
    @NotNull(message = "The number of people should not be null")
    @Min(value = 0, message = "The minimum possible is 0 person")
    @Max(value = 100, message = "The maximum possible is 100 person")
    private int personNumber;
}
