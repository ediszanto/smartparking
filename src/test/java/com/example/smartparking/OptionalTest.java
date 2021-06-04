package com.example.smartparking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class OptionalTest {

    public static final String SOMETHING = "something";
    public static final String NOTHING = "";
    public static final String NULL = null;
//    public static final int INT =;

    @Test
    public void createOptional_withSth() {
        Optional<String> opt1 = Optional.of(SOMETHING);
        Optional<String> opt2 = Optional.of(NOTHING);

        System.out.println(opt1);
        System.out.println(opt2);
        System.out.println(opt2.isEmpty());

        assertTrue(opt1.isPresent());
        assertTrue(opt2.isPresent());
//        assertTrue(opt2.isEmpty());
    }

    @Test
    public void createOptional_withNull() {
        //Optional<String> opt2 = Optional.of(NULL);
        Optional<String> opt2 = Optional.ofNullable(NULL);

        System.out.println(opt2);

        //opt2.get();

         assertFalse(opt2.isPresent());
    }

    @Test
    public void createEmptyOptional() {
        Optional<Object> opt = Optional.empty();

        assertFalse(opt.isPresent());
        //assertTrue(opt.isEmpty());
    }

    @Test
    public void refactorCode1() {
        String value = SOMETHING;

        if (value != null) {
            System.out.println(value.length());
        }

        if(Optional.ofNullable(value).isPresent()) {
            System.out.println(value.length());
        }

        Optional.ofNullable(value).ifPresent(v -> System.out.println(v.length()));
    }

    @Test
    public void refactorCode2() {
        String value = SOMETHING;
        int stringLength = 3;

        /*if (value != null) {
            stringLength = value.length();
        } else {
            stringLength = 0;
        }*/

        stringLength = Optional.ofNullable(value)
                .map(val -> val.length())
                .orElse(0);

        assertEquals(9, stringLength);
    }

    @Test
    public void refactorCode3() {
        String value = SOMETHING;

        if (value != null) {
            System.out.println(value.length());
        } else {
            System.out.println("Value not present!!!");
        }

        Optional.ofNullable(value).ifPresentOrElse(System.out::println,
                () -> System.out.println("Value not present!!!"));
    }


    @Test
    public void refactorCode4() {
        String value = SOMETHING;

        if (value != null) {
            System.out.println(value.length());
        } else {
            throw new RuntimeException("Value not present!!!");
        }

        Optional.ofNullable(value)
                .map(val -> val.length())
                .orElseThrow(() -> new RuntimeException("Value not present!!!"));
    }

    // Get the non empty one or default
    @Test
    public void refactorCode5() {
        String value1 = NULL;
        String value2 = SOMETHING;
        int result;

        if (value1 != null) {
            result = value1.length();
        } else {
            if (value2 != null) {
                result = value2.length();
            } else {
                throw new RuntimeException("Value not present!!!");
            }
        }

        //result = value1 != null ? value2 != null ? value2.length() : throw new RuntimeException(): 0;

        result = Optional.ofNullable(value1)
                .or(() -> Optional.ofNullable(value2))
                .map(val -> val.length())
                .orElseThrow(
                        () -> new RuntimeException("Value not present!!!"));

        System.out.println(result);
    }

    // TODO Given a list of strings count null vs something
    @Test
    public void refactorCode6() {

        List<String> list = Arrays.asList("ab", "abb", null, "abbba", null);

        AtomicInteger successCounter = new AtomicInteger();
        AtomicInteger onEmptyOptionalCounter = new AtomicInteger();

        list.stream()
                .forEach(s ->
                        Optional.ofNullable(s)
                                .ifPresentOrElse(
                                        s1 -> successCounter.getAndIncrement(),
                                        () -> onEmptyOptionalCounter.getAndIncrement())
                );

        System.out.println(successCounter.get());
        System.out.println(onEmptyOptionalCounter.get());
    }
}
