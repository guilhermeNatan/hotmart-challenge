package com.desafio.hotmart.reuse.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class DateUtil {

    public static LocalDate convertCalendarToLocalDate (Calendar date) {
        return LocalDateTime.ofInstant(date.toInstant(),
                date.getTimeZone().toZoneId()).toLocalDate();
    }


}
