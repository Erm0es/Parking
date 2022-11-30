package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class ValidateTime {
      public boolean isInFuture(LocalDateTime stoptime){
        return stoptime.isAfter(LocalDateTime.now());    }
   }


