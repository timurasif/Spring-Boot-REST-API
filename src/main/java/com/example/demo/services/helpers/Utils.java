package com.example.demo.services.helpers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Component
@AllArgsConstructor
public class Utils {
    public static String convertDateFormat(LocalDate originalDate) {
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyyMMdd");
        return targetFormat.format(Date.from(originalDate.atStartOfDay().toInstant(java.time.ZoneOffset.UTC)));
    }
}
