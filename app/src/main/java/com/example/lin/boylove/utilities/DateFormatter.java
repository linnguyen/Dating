package com.example.lin.boylove.utilities;

import android.content.Context;
import android.util.Log;

import com.example.lin.boylove.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public final class DateFormatter {
    /**
     * Time zone
     */
    private static String timeZone = "UTC";

    private static final String LOG_TAG = "DateFormatter";

    private DateFormatter() {
        throw new AssertionError();
    }

    public static String format(Date date, Template template) {
        return format(date, template.get());
    }

    public static String format(Date date, String format) {
        if (date == null) return "";
        return new SimpleDateFormat(format, Locale.getDefault())
                .format(date);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("Dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("Dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    public static boolean isSameYear(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("Dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameYear(cal1, cal2);
    }

    public static boolean isSameYear(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("Dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));
    }

    public static boolean isToday(Calendar calendar) {
        return isSameDay(calendar, Calendar.getInstance());
    }

    public static boolean isToday(Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }

    public static boolean isYesterday(Calendar calendar) {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        return isSameDay(calendar, yesterday);
    }

    public static boolean isYesterday(Date date) {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        return isSameDay(date, yesterday.getTime());
    }

    public static boolean isCurrentYear(Date date) {
        return isSameYear(date, Calendar.getInstance().getTime());
    }

    public static boolean isCurrentYear(Calendar calendar) {
        return isSameYear(calendar, Calendar.getInstance());
    }

    /**
     * Get time ago of given date
     *
     * @param context Context
     * @param date    Date object
     * @param style   DateTimeStyle
     * @return Time ago string
     */
    public static String getTimeAgo(Context context, Date date, DateTimeStyle style) {
        double seconds = getDateDiff(new Date(), date, DateTimeUnits.SECONDS);
        double minutes = seconds / 60;
        double hours = minutes / 60;
        double days = hours / 24;
        String phrase;
        String s;
        if (seconds <= 0) {
            phrase = context.getString(R.string.time_ago_now);
        } else if (seconds < 45) {
            s = style.equals(DateTimeStyle.AGO_FULL_STRING) ? context.getString(R.string.time_ago_full_seconds) :
                    context.getString(R.string.time_ago_seconds);
            phrase = String.format(s, Math.round(seconds));
        } else if (seconds < 90) {
            s = style.equals(DateTimeStyle.AGO_FULL_STRING) ? context.getString(R.string.time_ago_full_minute) :
                    context.getString(R.string.time_ago_minute);
            phrase = String.format(s, 1);
        } else if (minutes < 45) {
            s = style.equals(DateTimeStyle.AGO_FULL_STRING) ? context.getString(R.string.time_ago_full_minutes) :
                    context.getString(R.string.time_ago_minutes);
            phrase = String.format(s, Math.round(minutes));
        } else if (minutes < 90) {
            s = style.equals(DateTimeStyle.AGO_FULL_STRING) ? context.getString(R.string.time_ago_full_hour) :
                    context.getString(R.string.time_ago_hour);
            phrase = String.format(s, 1);
        } else if (hours < 24) {
            s = style.equals(DateTimeStyle.AGO_FULL_STRING) ? context.getString(R.string.time_ago_full_hours) :
                    context.getString(R.string.time_ago_hours);
            phrase = String.format(s, Math.round(hours));
        } else if (hours < 42) {
            if (isYesterday(date)) {
                phrase = context.getString(R.string.time_ago_yesterday_at, formatTime(date));
            } else {
                phrase = formatWithStyle(date, style.equals(DateTimeStyle.AGO_FULL_STRING) ?
                        DateTimeStyle.FULL : DateTimeStyle.SHORT);
            }
        } else if (days < 30) {
            s = style.equals(DateTimeStyle.AGO_FULL_STRING) ? context.getString(R.string.time_ago_full_days) :
                    context.getString(R.string.time_ago_days);
            phrase = String.format(s, Math.round(days));
        } else if (days < 45) {
            s = style.equals(DateTimeStyle.AGO_FULL_STRING) ? context.getString(R.string.time_ago_full_month) :
                    context.getString(R.string.time_ago_month);
            phrase = String.format(s, 1);
        } else if (days < 365) {
            s = style.equals(DateTimeStyle.AGO_FULL_STRING) ? context.getString(R.string.time_ago_full_months) :
                    context.getString(R.string.time_ago_months);
            phrase = String.format(s, Math.round(days / 30));
        } else {
            phrase = formatWithStyle(date, style.equals(DateTimeStyle.AGO_FULL_STRING) ?
                    DateTimeStyle.FULL : DateTimeStyle.SHORT);
        }
        return phrase;
    }

    /**
     * Get time ago of given date
     *
     * @param context    Context
     * @param dateString Representing a date time string
     * @return Time ago string
     */
    public static String getTimeAgo(Context context, String dateString) {
        return getTimeAgo(context, formatDate(dateString),DateTimeStyle.AGO_FULL_STRING);
    }
    /**
     * Get time ago of given date
     *
     * @param context    Context
     * @param date Representing a date time string
     * @return Time ago string
     */
    public static String getTimeAgo(Context context, Date date) {
        return getTimeAgo(context, date,DateTimeStyle.AGO_FULL_STRING);
    }

    /**
     * Get difference between two dates
     *
     * @param nowDate  Current date
     * @param oldDate  Date to compare
     * @param dateDiff Difference Unit
     * @return Difference
     */
    public static int getDateDiff(String nowDate, Date oldDate, DateTimeUnits dateDiff) {
        return getDateDiff(formatDate(nowDate), oldDate, dateDiff);
    }

    /**
     * Get difference between two dates
     *
     * @param nowDate  Current date
     * @param oldDate  Date to compare
     * @param dateDiff Difference Unit
     * @return Difference
     */
    public static int getDateDiff(Date nowDate, Date oldDate, DateTimeUnits dateDiff) {
        long diffInMs = nowDate.getTime() - oldDate.getTime();
        int days = (int) TimeUnit.MILLISECONDS.toDays(diffInMs);
        int hours = (int) (TimeUnit.MILLISECONDS.toHours(diffInMs) - TimeUnit.DAYS.toHours(days));
        int minutes = (int) (TimeUnit.MILLISECONDS.toMinutes(diffInMs) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diffInMs)));
        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(diffInMs);
        switch (dateDiff) {
            case DAYS:
                return days;
            case SECONDS:
                return seconds;
            case MINUTES:
                return minutes;
            case HOURS:
                return hours;
            case MILLISECONDS:
            default:
                return (int) diffInMs;
        }
    }

    /**
     * Convert a Java Date object to String
     *
     * @param date   Date Object
     * @param locale Locale
     * @return Date Object string representation
     */
    public static String formatDate(Date date, Locale locale) {
        SimpleDateFormat iso8601Format = new SimpleDateFormat(Template.DATE_TIME_PATTERN_0.get(), locale);
        iso8601Format.setTimeZone(TimeZone.getTimeZone(timeZone));
        return iso8601Format.format(date);
    }

    /**
     * Get localized date string (Using default locale)
     *
     * @param date Date string
     * @return Formatted localized date string
     */
    public static String formatWithStyle(String date, DateTimeStyle style) {
        return formatWithStyle(date, style, Locale.getDefault());
    }

    /**
     * Get localized date string (Using default locale)
     *
     * @param date Date string
     * @return Formatted localized date string
     */
    public static String formatWithStyle(String date, DateTimeStyle style, Locale locale) {
        return formatWithStyle(formatDate(date), style, locale);
    }

    /**
     * Get localized date string (Using default locale)
     *
     * @param date Date string
     * @return Formatted localized date string
     */
    public static String formatWithStyle(Date date, DateTimeStyle style) {
        return formatWithStyle(date, style, Locale.getDefault());
    }

    /**
     * Convert a date string to Java Date Object
     *
     * @param dateString Date String
     * @param locale     Locale
     * @return Java Date Object
     */
    public static Date formatDate(String dateString, Locale locale) {
        SimpleDateFormat iso8601Format = new SimpleDateFormat(getDatePattern(dateString), locale);
        iso8601Format.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date date = null;
        if (dateString != null) {
            try {
                date = iso8601Format.parse(dateString.trim());
            } catch (ParseException e) {
                Log.e(LOG_TAG, "formatDate >> Fail to parse supplied date string >> " + dateString);
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * Convert a Java Date object to String
     *
     * @param date Date Object
     * @return Date Object string representation
     */
    public static String formatDate(Date date) {
        return formatDate(date, Locale.getDefault());
    }

    /**
     * Convert a date string to Java Date Object
     *
     * @param date Date String
     * @return Java Date Object
     */
    public static Date formatDate(String date) {
        return formatDate(date, Locale.getDefault());
    }

    /**
     * Get localized date string
     *
     * @param date Date string
     * @return Formatted localized date string
     */
    public static String formatWithStyle(Date date, DateTimeStyle style, Locale locale) {
        return formatWithPattern(date, getPatternForStyle(style), locale);
    }

    /**
     * Format date using a given pattern
     * and apply supplied locale
     *
     * @param date    Date Object
     * @param pattern Pattern
     * @param locale  Locale
     * @return Formatted date
     */
    public static String formatWithPattern(Date date, String pattern, Locale locale) {
        SimpleDateFormat iso8601Format = new SimpleDateFormat(pattern, locale);
        iso8601Format.setTimeZone(TimeZone.getTimeZone(timeZone));
        return iso8601Format.format(date);
    }

    /**
     * Build a pattern for given style
     *
     * @param style DateTimeStyle
     * @return Pattern
     */
    private static String getPatternForStyle(DateTimeStyle style) {
        String pattern;
        if (style.equals(DateTimeStyle.LONG)) {
            pattern = "MMMM dd, yyyy";
        } else if (style.equals(DateTimeStyle.MEDIUM)) {
            pattern = "MMM dd, yyyy";
        } else if (style.equals(DateTimeStyle.SHORT)) {
            pattern = "MM/dd/yy";
        } else {
            pattern = "EEEE, MMMM dd, yyyy";
        }
        return pattern;
    }

    /**
     * Get Date or DateTime formatting pattern
     *
     * @param dateString Date String
     * @return Format Pattern
     */
    private static String getDatePattern(String dateString) {
        if (isDateTime(dateString)) {
            return (dateString.contains("/")) ? Template.DATE_TIME_PATTERN_2.get() : Template.DATE_TIME_PATTERN_1.get();
        } else {
            return (dateString.contains("/")) ? Template.DATE_PATTERN_2.get() : Template.DATE_PATTERN_1.get();
        }
    }

    /**
     * Extract time from date without seconds
     *
     * @param date Date object
     * @return Time String
     * @see Template#DATE_TIME_PATTERN_1
     */
    public static String formatTime(Date date, boolean forceShowHours) {
        SimpleDateFormat iso8601Format = new SimpleDateFormat(Template.DATE_TIME_PATTERN_1.get(), Locale.getDefault());
        iso8601Format.setTimeZone(TimeZone.getTimeZone(timeZone));
        String time = iso8601Format.format(date);
        String[] hhmmss = time.split(":");
        int hours = Integer.parseInt(hhmmss[0]);
        int minutes = Integer.parseInt(hhmmss[1]);
        int seconds = Integer.parseInt(hhmmss[2]);
        return (hours == 0 && !forceShowHours ? "" : hours < 10 ? String.valueOf("0" + hours) + ":" :
                String.valueOf(hours) + ":") +
                (minutes == 0 ? "00" : minutes < 10 ? String.valueOf("0" + minutes) :
                        String.valueOf(minutes)) + ":"
                + (seconds == 0 ? "00" : seconds < 10 ? String.valueOf("0" + seconds) : String.valueOf(seconds));
    }

    /**
     * Extract time from date without seconds
     *
     * @param date Date object
     * @return Time string
     */
    public static String formatTime(Date date) {
        return formatTime(date, false);
    }

    /**
     * Tell whether or not a given string represent a date time string or a simple date
     *
     * @param dateString Date String
     * @return True if given string is a date time False otherwise
     */
    public static boolean isDateTime(String dateString) {
        return (dateString != null) && (dateString.trim().split(" ").length > 1);
    }


    /**
     * Interface used to format dates before they were displayed (e.g. dialogs time, messages date headers etc.).
     */
    public interface Formatter {

        /**
         * Formats an string representation of the date object.
         *
         * @param date The date that should be formatted.
         * @return Formatted text.
         */
        String format(Date date);
    }

    public enum Template {
        STRING_DAY_MONTH_YEAR("d MMMM yyyy"),
        STRING_DAY_MONTH("d MMMM"),
        TIME("HH:mm"),
        DATE_TIME_PATTERN_0("yyyy-MM-dd'T'HH:mm:ss.sssZZZZ"),
        DATE_TIME_PATTERN_1("yyyy-MM-dd HH:mm:ss"),
        DATE_TIME_PATTERN_2("dd/MM/yyyy HH:mm:ss"),
        DATE_PATTERN_1("yyyy-MM-dd"),
        DATE_PATTERN_2("dd/MM/yyyy"),
        TIME_PATTERN_1("HH:mm:ss");


        private String template;

        Template(String template) {
            this.template = template;
        }

        public String get() {
            return template;
        }
    }

    public enum DateTimeStyle {
        /**
         * Style full e.g Tuesday, June 13, 2017
         */
        FULL,
        /**
         * Style long e.g June 13, 2017
         */
        LONG,
        /**
         * Style medium e.g Jun 13, 2017
         */
        MEDIUM,
        /**
         * Style short e.g 06/13/17
         */
        SHORT,
        /**
         * Style for ago time e.g 3h ago
         */
        AGO_SHORT_STRING,
        /**
         * Style for ago time e.g 3 hours ago
         */
        AGO_FULL_STRING
    }

    public enum DateTimeUnits {
        /**
         * Days
         */
        DAYS,
        /**
         * Hours
         */
        HOURS,
        /**
         * Minutes
         */
        MINUTES,
        /**
         * Seconds
         */
        SECONDS,
        /**
         * Milliseconds
         */
        MILLISECONDS,
    }
}
