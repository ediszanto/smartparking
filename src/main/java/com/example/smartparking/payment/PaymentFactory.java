package com.example.smartparking.payment;

import com.example.smartparking.exceptions.NotFoundException;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PaymentFactory {

    final static Map<String, Payment> payMap = new HashMap<>();
    static {

        payMap.put("S", new S());
        payMap.put("M", new M());
        payMap.put("L", new L());
        payMap.put("XL", new XL());
    }

    public static Double calculatePayment(String size, Duration duration){
        return Optional.ofNullable(payMap.get(size)).map(p -> p.pay(duration)).orElseThrow(() -> new NotFoundException("Requested size is not recognized"));
    }


}
