package com.MuhammedSosun.tutorials._2_week;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDate {

    //this method is more practical for bussineses
    public static void LocalDateMethod() {
        LocalDateTime now = LocalDateTime.now();
        Locale locale = new Locale("tr","TR");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH:mm:ss",locale);
        System.out.println("Current Time" + now);
        System.out.println("Current Time" + now.format(dateTimeFormatter));
    }

    public static void main(String[] args) {
        LocalDateMethod();

    }
}