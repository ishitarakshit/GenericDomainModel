package com.concepts.domain.time;

import java.util.Calendar;
import java.util.Set;


/**
 * This object implements the concept of business days which is simply any day of the week which is neither a weekend
 * nor a holiday. The class is implemented as abstract class to allow for company to specify all holidays as per
 * corporate policy.
 * <p>
 * Reference: Time and Money in Domain Model - Eric Evans.
 * 
 * @author ishitarakshit
 * @version 1.0, 12/29/2009
 * @since 1.5
 * @see CalendarDate
 */
public abstract class BusinessCalendar {

    private Set<CalendarDate> holidays;

    public BusinessCalendar() {
        this.holidays = setCorporateHolidays();
    }

    public abstract Set<CalendarDate> setCorporateHolidays();

    public void addHolidays(Set<CalendarDate> days) {
        this.holidays.addAll(days);
    }

    public void removeHolidays(Set<CalendarDate> days) {
        this.holidays.removeAll(days);
    }

    public boolean isHoliday(CalendarDate day) {
        return this.holidays.contains(day);
    }

    public boolean isWeekend(CalendarDate day) {
        Calendar dayAsJavaCalendar = day.asJavaCalendar();

        return (dayAsJavaCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || dayAsJavaCalendar
                .get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

    public boolean isBusinessDay(CalendarDate day) {
        return !isWeekend(day) && !isHoliday(day);
    }

    public CalendarDate nextBusinessDay(CalendarDate from) {
        return plusBusinessDays(from, 1);
    }

    public CalendarDate previousBusinessDay(CalendarDate from) {
        return minusBusinessDays(from, 1);
    }

    public CalendarDate plusBusinessDays(CalendarDate from, int numberOfBusinessDays) {
        if (!isBusinessDay(from) && numberOfBusinessDays == 0) { 
            throw new IllegalArgumentException("0 day increment/decrement from a holiday or weekend in not valid");
        }

        if (numberOfBusinessDays >= 0) {
            CalendarDate lookAhead = from;
            while (numberOfBusinessDays != 0) {
                lookAhead = lookAhead.nextDay();
                if (isBusinessDay(lookAhead)) {
                    numberOfBusinessDays--;
                }
            }

            return lookAhead;
        }
        else {
            CalendarDate lookBack = from;
            while (numberOfBusinessDays != 0) {
                lookBack = lookBack.previousDay();
                if (isBusinessDay(lookBack)) {
                    numberOfBusinessDays++;
                }
            }

            return lookBack;
        }
    }

    public CalendarDate minusBusinessDays(CalendarDate from, int numberOfBusinessDays) {
        return plusBusinessDays(from, -numberOfBusinessDays);
    }

    public int getNumberOfDaysBetween(CalendarDate from, CalendarDate to) {
        final long fromInMilliseconds = from.asJavaDate().getTime();
        final long toInMilliseconds = to.asJavaDate().getTime();

        long timeDifferenceInMilliseconds = Math.abs(toInMilliseconds - fromInMilliseconds);
        long differenceInDays = timeDifferenceInMilliseconds / TimeUnitConversionFactors.MILLISECONDS_PER_DAY;

        return (int) differenceInDays;
    }

    public int getNumberOfBusinessDaysBetween(CalendarDate from, CalendarDate to) {
        CalendarDate earlierDate = (from.isBefore(to)) ? from : to;
        CalendarDate laterDate = (from.isBefore(to)) ? to : from;

        int count = 0;
        while (!earlierDate.equals(laterDate)) {
            if (isBusinessDay(earlierDate)) {
                count++;
            }
            earlierDate = earlierDate.nextDay();
        }

        return count;
    }

    public CalendarDate firstDayOfMonth(CalendarDate date) {
        Calendar c = date.asJavaCalendar();

        return CalendarDate.from(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, 1);
    }

    public CalendarDate lastDayOfMonth(CalendarDate date) {
        Calendar c = date.asJavaCalendar();

        return CalendarDate.from(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1,
                c.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    public CalendarDate firstBusinessDayOfMonth(CalendarDate date) {
        CalendarDate firstDayOfMonth = firstDayOfMonth(date);

        return (isBusinessDay(firstDayOfMonth)) ? firstDayOfMonth : nextBusinessDay(firstDayOfMonth);
    }

    public CalendarDate lastBusinessDayOfMonth(CalendarDate date) {
        CalendarDate lastDayOfMonth = lastDayOfMonth(date);

        return (isBusinessDay(lastDayOfMonth)) ? lastDayOfMonth : previousBusinessDay(lastDayOfMonth);
    }

}
