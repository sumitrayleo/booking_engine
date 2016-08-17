package com.cognizant.orchestration.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Formatter Utility class for Date. It takes in an integer and converts into a formatted date string
 * 
 */
public final class DateFormatterUtil {

    private DateFormatterUtil() {
    }

    /**
     * This utility method creates a formatted date from integer date
     * 
     * @param requestedDateTime
     * @return String date
     */
    public static String createFormattedDate(final Integer requestedDateTime) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String requestDate = null;
        if (requestedDateTime != null) {
            requestDate = dateFormat.format(new Date(requestedDateTime * 1000L));
        } else {
            requestDate = dateFormat.format(Calendar.getInstance().getTime());
        }
        return requestDate;
    }
}
