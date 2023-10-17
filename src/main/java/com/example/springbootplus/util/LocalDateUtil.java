package com.example.springbootplus.util;

import javafx.util.converter.LocalDateTimeStringConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {
    public static String now(){
        LocalDateTime now = LocalDateTime.now();

        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

    }
}
