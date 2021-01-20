package me.lucko.helper.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.util.Date;

public final class DateUtils {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    public static final DateTimeFormatter MINUTES_WITH_SECONDS_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static final ZoneId MOSCOW_ZONE = ZoneId.of("Europe/Moscow");

    private static final String[] MONTHS = {"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};

    public static LocalDateTime fromDate(Date date) {
        return date == null ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }

    public static Date toDate(LocalDateTime date) {
        if (date == null) return null;
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDate date) {
        if (date == null) return null;
        return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String formatDate(Date date) {
        return format(date, DATE_FORMATTER);
    }

    public static String formatDate(LocalDate date) {
        if (date == null) return "";

        return DATE_FORMATTER.format(date);
    }

    public static String formatDateTime(Date date) {
        return format(date, DATE_TIME_FORMATTER);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return "";

        return DATE_TIME_FORMATTER.format(dateTime);
    }

    public static String format(Date date, DateTimeFormatter formatter) {
        if (date == null) return "";

        return formatter.format(fromDate(date));
    }

    public static Date parseDate(String text) {
        return parse(text, DATE_FORMATTER);
    }

    public static Date parse(String text, DateTimeFormatter formatter) {
        if (StringUtils.isEmpty(text)) return null;

        TemporalAccessor temporal;
        try {
            temporal = formatter.parse(text.trim());
        } catch (DateTimeParseException e) {
            return null;
        }

        LocalDate date = temporal.query(TemporalQueries.localDate());
        if (date == null) return null;

        LocalTime time = temporal.query(TemporalQueries.localTime());
        if (time == null) {
            time = LocalTime.of(0, 0, 0, 0);
        }

        return toDate(LocalDateTime.of(date, time));
    }

    public static String getMonth(int month) {
        return month > 0 && month <= MONTHS.length ? MONTHS[month - 1] : null;
    }

    public static String formatTime(Date date) {
        if (date == null) return "";

        return MINUTES_WITH_SECONDS_FORMATTER.format(fromDate(date));
    }

    public static String formatDayWithMonth(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth() + " " + MONTHS[localDate.getMonthValue() - 1];
    }

    private DateUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

}
