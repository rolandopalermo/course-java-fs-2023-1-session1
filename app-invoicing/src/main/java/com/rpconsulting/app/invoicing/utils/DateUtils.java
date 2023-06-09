package com.rpconsulting.app.invoicing.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    public static String toString(LocalDate date) {
        return date.format(formatter);
    }

}
