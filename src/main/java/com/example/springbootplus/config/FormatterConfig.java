package com.example.springbootplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class FormatterConfig {

    @Bean
    public Formatter<LocalDate> localDateFormatter() {
        return new Formatter<LocalDate>() {
            @Override
            public LocalDate parse(String text, Locale locale) throws ParseException {
                return LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
            }

            @Override
            public String print(LocalDate object, Locale locale) {
                return DateTimeFormatter.ISO_LOCAL_DATE.format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        return new Formatter<LocalDateTime>() {
            @Override
            public String print(LocalDateTime localDateTime, Locale locale) {
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return formatter.format(localDateTime);
            }

            @Override
            public LocalDateTime parse(String text, Locale locale) throws ParseException {
                if (text.length() > 10 && text.charAt(10) == 'T') {
                    return text.endsWith("Z") ? LocalDateTime.ofInstant(Instant.parse(text), ZoneOffset.UTC) : LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                } else if (text.length() == 10) {//处理yyyy-MM-dd的格式
                    return LocalDateTime.parse(text + " 00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                } else {
                    return LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
            }
        };
    }
}
