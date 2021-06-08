package com.example.smartparking.payment;

import lombok.NoArgsConstructor;
import java.time.Duration;

@NoArgsConstructor
public class S  implements Payment{


    @Override
    public Double pay(Duration duration) {
        if(duration.toMinutes() < 60){
            return 1.5;
        }
        return (double) (duration.toMinutes()/60) * 1.5;
    }
}
