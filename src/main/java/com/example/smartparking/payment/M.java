package com.example.smartparking.payment;

import lombok.NoArgsConstructor;
import java.time.Duration;


@NoArgsConstructor
public class M implements Payment{

    @Override
    public Double pay(Duration duration) {
        return (double) (duration.toMinutes()/60) * 2;
    }
}
