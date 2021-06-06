package com.example.smartparking.payment;

import lombok.NoArgsConstructor;
import java.time.Duration;

@NoArgsConstructor
public class XL implements Payment{

    @Override
    public Double pay(Duration duration) {
        return (double) (duration.toMinutes()/60) * 3;
    }
}
