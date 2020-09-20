package com.desafio.hotmart.reuse.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class DateUtil {

    public static LocalDate convertCalendarToLocalDate (Calendar date) {
        return LocalDateTime.ofInstant(date.toInstant(),
                date.getTimeZone().toZoneId()).toLocalDate();
    }

    public static LocalDate getAtualDate() {
        Calendar today = Calendar.getInstance();
        return LocalDateTime.ofInstant(today.toInstant(),
                today.getTimeZone().toZoneId()).toLocalDate();
    }


    public static boolean isToday(Calendar date) {
        LocalDate atualDate = getAtualDate();
        return atualDate.isEqual(convertCalendarToLocalDate(date));
    }
}
