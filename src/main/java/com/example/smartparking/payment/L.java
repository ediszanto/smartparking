package com.example.smartparking.payment;

import lombok.NoArgsConstructor;
import java.time.Duration;

@NoArgsConstructor
public class L implements Payment{

    @Override
    public Double pay(Duration duration) {
        if(duration.toMinutes() < 60){
            return 2.5;
        }
        return (double) (duration.toMinutes()/60) * 2.5;
    }
}
