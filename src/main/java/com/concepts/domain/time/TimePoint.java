package com.concepts.domain.time;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * {@link TimePoint} is simply a point in time. This object provides the most precise representation of a point in time
 * in the domain model. An obvious, and common, service for this class is to get the current time point. Usually this is
 * done by interrogating the system clock using the operating system. However it's a good idea to add indirection here.
 * Testing often requires stable times, and often even operations may require to run the system on 'as of' some date in
 * the past.
 * <p>
 * Reference: Time and Money in Domain Model - Eric Evans. <br/>
 * Reference: Patterns of Enterprise Application Architecture - Martin Fowler. <br/>
 * 
 * @author ishitarakshit
 * @version 1.0, 12/29/2009
 * @since 1.5
 * @see CalendarDate
 */
public class TimePoint implements Comparable<TimePoint>, Serializable {

    private static final long serialVersionUID = 6932630506064102184L;

    private long millisecondsFromEpoc;

    public static TimePoint atMidnight(int year, int month, int date) {
        return at(year, month, date, 0, 0, 0, 0);
    }

    public static TimePoint at(int year, int month, int date, int hour, int minute, int second) {
        return at(year, month, date, hour, minute, second, 0);
    }

    public static TimePoint at(int year, int month, int date, int hour, int minute, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);

        return from(calendar);
    }

    public static TimePoint parseFrom(String dateString, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        Date date = format.parse(dateString, new ParsePosition(0));

        return from(date);
    }

    public static TimePoint from(Date javaDate) {
        return from(javaDate.getTime());
    }

    public static TimePoint from(Calendar javaCalendar) {
        return from(javaCalendar.getTime());
    }

    public static TimePoint from(long milliseconds) {
        TimePoint result = new TimePoint(milliseconds);
        return result;
    }

    TimePoint(long milliseconds) {
        this.millisecondsFromEpoc = milliseconds;
    }

    public Date asJavaDate() {
        return new Date(this.millisecondsFromEpoc);
    }

    public Calendar asJavaCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.millisecondsFromEpoc);

        return calendar;
    }

    public boolean isAfter(TimePoint other) {
        return this.millisecondsFromEpoc > other.millisecondsFromEpoc;
    }

    public boolean isBefore(TimePoint other) {
        return this.millisecondsFromEpoc < other.millisecondsFromEpoc;
    }

    public int compareTo(TimePoint other) {
        if (this.isBefore(other)) { return -1; }
        if (this.isAfter(other)) { return 1; }

        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) { return false; }
        if (this == object) { return true; }
        if (!(object instanceof TimePoint)) { return false; }

        TimePoint other = (TimePoint) object;

        return this.millisecondsFromEpoc == other.millisecondsFromEpoc;
    }

    @Override
    public int hashCode() {
        return (int) this.millisecondsFromEpoc;
    }

}
