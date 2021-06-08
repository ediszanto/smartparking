package com.example.smartparking.payment;

import lombok.NoArgsConstructor;
import java.time.Duration;

@NoArgsConstructor
public class XL implements Payment{

    @Override
    public Double pay(Duration duration) {
        if(duration.toMinutes() < 60){
            return 3.0;
        }
        return (double) (duration.toMinutes()/60) * 3;
    }
}
