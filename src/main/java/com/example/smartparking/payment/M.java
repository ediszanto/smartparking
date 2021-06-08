package com.example.smartparking.payment;

import lombok.NoArgsConstructor;
import java.time.Duration;


@NoArgsConstructor
public class M implements Payment{

    @Override
    public Double pay(Duration duration) {
        System.out.println(duration.toMinutes());
        if(duration.toMinutes() < 60){
            return 2.0;
        }
        return (double) (duration.toMinutes()/60) * 2;
    }
}
