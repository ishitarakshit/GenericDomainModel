package com.concepts.domain.time;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * This object represents time points in the system at a day level precision. In other words, dates where the actual
 * time (hours, minutes, seconds) is of no significance may use this class. The months start from 1 (January = 1,
 * February = 2, March = 3 and so on)
 * <p>
 * Reference: Time and Money in Domain Model - Eric Evans.
 * 
 * @author ishitarakshit
 * @version 1.0, 12/29/2009
 * @since 1.5
 */
public class CalendarDate implements Comparable<CalendarDate>, Serializable {

    private static final long serialVersionUID = -8748238650853571108L;

    private int year;
    private int month;
    private int day;

    public static CalendarDate from(int year, int month, int date) {
        return new CalendarDate(year, month, date);
    }

    public static CalendarDate from(Calendar javaCalendar) {
        int year = javaCalendar.get(Calendar.YEAR);
        int month = javaCalendar.get(Calendar.MONTH) + 1;
        int day = javaCalendar.get(Calendar.DATE);

        return CalendarDate.from(year, month, day);
    }

    public static CalendarDate from(TimePoint timepoint) {
        return from(timepoint.asJavaCalendar());
    }

    public static CalendarDate from(Date javaDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(javaDate);

        return from(calendar);
    }

    public static CalendarDate from(String dateString, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        Date date = format.parse(dateString, new ParsePosition(0));

        return from(date);
    }

    CalendarDate(int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.day = date;

        validateState();
    }

    public Calendar asJavaCalendar() {
        TimePoint day = TimePoint.atMidnight(this.year, this.month, this.day);

        return day.asJavaCalendar();
    }

    public Date asJavaDate() {
        return this.asJavaCalendar().getTime();
    }

    public boolean isBefore(CalendarDate other) {
        if (other == null) { return false; }

        if (this.year < other.year) { return true; }
        if (this.year > other.year) { return false; }
        if (this.month < other.month) { return true; }
        if (this.month > other.month) { return false; }

        return this.day < other.day;
    }

    public boolean isAfter(CalendarDate other) {
        if (other == null) { return false; }

        return !isBefore(other) && !this.equals(other);
    }

    public CalendarDate nextDay() {
        return plusDays(1);
    }

    public CalendarDate previousDay() {
        return plusDays(-1);
    }

    public CalendarDate plusDays(int increment) {
        Calendar calendar = this.asJavaCalendar();
        calendar.add(Calendar.DATE, increment);

        return from(calendar);
    }

    public CalendarDate plusMonths(int increment) {
        Calendar calendar = this.asJavaCalendar();
        calendar.add(Calendar.MONTH, increment);

        return from(calendar);
    }

    public CalendarDate plusYears(int increment) {
        Calendar calendar = this.asJavaCalendar();
        calendar.add(Calendar.YEAR, increment);

        return from(calendar);
    }

    public int compareTo(CalendarDate other) {
        if (isBefore(other)) { return -1; }
        if (isAfter(other)) { return 1; }

        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) { return false; }
        if (this == object) { return true; }
        if (!(object instanceof CalendarDate)) { return false; }

        CalendarDate other = (CalendarDate) object;

        return this.year == other.year && this.month == other.month && this.day == other.day;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + this.year;
        result = 37 * result + this.month;
        result = 37 * result + this.day;

        return result;
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("dd-MMM-yyyy").format(this.asJavaDate());
    }

    private void validateState() {
        if (this.day < 1 || this.day > 31) {
            throw new IllegalArgumentException("Day of the month must be between 1 and 31");
        }
        else if (this.month < 1 || this.month > 12) {
            throw new IllegalArgumentException("Month of the year must be between 1 and 12");
        }
        else if (this.year < 0) { 
            throw new IllegalArgumentException("Year must be positive integer"); 
        }
    }

}
