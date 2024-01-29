package com.example.demo.services.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilsTest {

    private Utils utils;
    @Test
    public void convertDateFormatTest(){
        LocalDate date = LocalDate.of(2024, 01, 27);
        String expectedResult = "20240127";
        assertEquals(expectedResult, Utils.convertDateFormat(date));
    }
}