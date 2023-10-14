package com.project.buensabor.services;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Service
public class DateService {

    public LocalDateTime dateNow(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        //System.out.println("zonedTime: "+zonedDateTime.toString());

        return localDateTime;
    }

    public LocalDateTime plusMinutes(LocalDateTime localDateTime, Long minutes){
        LocalDateTime plusHours = localDateTime.plusMinutes(minutes);
        return plusHours;
    }
}
