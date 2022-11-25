package com.meetingPlanner.meeting.advice;

import com.meetingPlanner.meeting.Exception.RoomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity< Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return ResponseEntity.status(400).body(errorMap);
    }
    @ExceptionHandler(RoomException.class)
    public ResponseEntity< String> handleRoomException(RoomException exception){
        String errorMessage=exception.getMessage();

        return ResponseEntity.status(400).body(errorMessage);
    }
}
