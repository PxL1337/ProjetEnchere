package fr.eni.projetenchere.bll.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    // Convert a string to a java.sql.Date
    public static Date stringToDate(String dateString) throws ParseException {
        java.util.Date parsedDate = DATE_FORMAT.parse(dateString);
        return new Date(parsedDate.getTime());
    }

    // Convert a java.sql.Date to a string
    public static String dateToString(Date date) {
        return DATE_FORMAT.format(date);
    }

    // Convert a string to a java.sql.Timestamp
    public static Timestamp stringToTimestamp(String datetimeString) throws ParseException {
        java.util.Date parsedDate = DATETIME_FORMAT.parse(datetimeString);
        return new Timestamp(parsedDate.getTime());
    }

    // Convert a java.sql.Timestamp to a string
    public static String timestampToString(Timestamp timestamp) {
        return DATETIME_FORMAT.format(timestamp);
    }


    public static LocalDateTime getDateTimeFromRequest(HttpServletRequest request, String dateParameter, String hourParameter, String minuteParameter, String secondParameter) {
        return LocalDate.parse(request.getParameter(dateParameter))
                .atTime(Integer.parseInt(request.getParameter(hourParameter)),
                        Integer.parseInt(request.getParameter(minuteParameter)),
                        Integer.parseInt(request.getParameter(secondParameter)));
    }

}

