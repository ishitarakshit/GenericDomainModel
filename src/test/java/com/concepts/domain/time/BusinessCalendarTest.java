package com.concepts.domain.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;


public class BusinessCalendarTest {

    private CalendarDate newYearOf2012 = CalendarDate.from(2012, 1, 1);
    private CalendarDate christmasOf2011 = CalendarDate.from(2011, 12, 25);
    private CalendarDate christmasEveOf2011 = CalendarDate.from(2011, 12, 24);
    private CalendarDate thanksGivingOf2011 = CalendarDate.from(2011, 11, 24);
    private CalendarDate laborDayOf2011 = CalendarDate.from(2011, 9, 5);
    private CalendarDate independenceDayOf2011 = CalendarDate.from(2011, 7, 4);

    private BusinessCalendar businessCalendar;

    @Before
    public void setUp() throws Exception {
        this.businessCalendar = new BusinessCalendar() {

            @Override
            public Set<CalendarDate> setCorporateHolidays() {
                Set<CalendarDate> holidays = new HashSet<CalendarDate>();
                holidays.add(BusinessCalendarTest.this.independenceDayOf2011);
                holidays.add(BusinessCalendarTest.this.laborDayOf2011);
                holidays.add(BusinessCalendarTest.this.thanksGivingOf2011);
                holidays.add(BusinessCalendarTest.this.christmasEveOf2011);
                holidays.add(BusinessCalendarTest.this.christmasOf2011);
                holidays.add(BusinessCalendarTest.this.newYearOf2012);

                return holidays;
            }
        };
    }

    @Test
    public void testIsHoliday() {
        assertTrue(this.businessCalendar.isHoliday(this.laborDayOf2011));
        assertTrue(this.businessCalendar.isHoliday(this.christmasEveOf2011));
        assertFalse(this.businessCalendar.isHoliday(CalendarDate.from(2011, 10, 1)));
    }

    @Test
    public void testIsWeekend() {
        CalendarDate aSaturday = CalendarDate.from(2011, 8, 20);
        CalendarDate aThursday = CalendarDate.from(2011, 8, 11);

        assertTrue(this.businessCalendar.isWeekend(this.christmasEveOf2011));
        assertTrue(this.businessCalendar.isWeekend(aSaturday));
        assertFalse(this.businessCalendar.isWeekend(aThursday));
    }

    @Test
    public void testIsBusinessDay() {
        CalendarDate aSaturday = CalendarDate.from(2011, 8, 20);
        CalendarDate aThursday = CalendarDate.from(2011, 8, 11);

        assertFalse(this.businessCalendar.isBusinessDay(this.independenceDayOf2011));
        assertFalse(this.businessCalendar.isBusinessDay(aSaturday));
        assertTrue(this.businessCalendar.isBusinessDay(aThursday));
    }

    @Test
    public void testNextBusinessDayWithHolidayAndWeekendInBetween() {
        assertEquals(CalendarDate.from(2011, 7, 5),
                this.businessCalendar.nextBusinessDay(CalendarDate.from(2011, 7, 1)));
        assertEquals(CalendarDate.from(2011, 8, 15),
                this.businessCalendar.nextBusinessDay(CalendarDate.from(2011, 8, 12)));
    }

    @Test
    public void testNextBusinessDayWithNoHolidaysInBetween() {
        assertEquals(CalendarDate.from(2011, 8, 11),
                this.businessCalendar.nextBusinessDay(CalendarDate.from(2011, 8, 10)));
    }

    @Test
    public void testPlusBusinessDays() {
        assertEquals(CalendarDate.from(2011, 7, 1),
                this.businessCalendar.plusBusinessDays(CalendarDate.from(2011, 7, 1), 0));
        assertEquals(CalendarDate.from(2011, 7, 11),
                this.businessCalendar.plusBusinessDays(CalendarDate.from(2011, 7, 1), 5));
        assertEquals(CalendarDate.from(2011, 12, 26),
                this.businessCalendar.plusBusinessDays(this.christmasEveOf2011, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlusZeroBusinessDaysFromAHoliday() {
        this.businessCalendar.plusBusinessDays(this.laborDayOf2011, 0);
    }

    @Test
    public void testMinusBusinessDays() {
        assertEquals(CalendarDate.from(2011, 7, 1),
                this.businessCalendar.minusBusinessDays(CalendarDate.from(2011, 7, 1), 0));
        assertEquals(CalendarDate.from(2011, 7, 1),
                this.businessCalendar.minusBusinessDays(CalendarDate.from(2011, 7, 11), 5));
        assertEquals(CalendarDate.from(2011, 12, 23),
                this.businessCalendar.minusBusinessDays(this.christmasEveOf2011, 1));
    }

    @Test
    public void testPlusMinusWithNegativeIncrementOrDecrement() {
        assertEquals(CalendarDate.from(2011, 12, 22),
                this.businessCalendar.plusBusinessDays(this.christmasEveOf2011, -2));
        assertEquals(CalendarDate.from(2011, 12, 27),
                this.businessCalendar.minusBusinessDays(this.christmasEveOf2011, -2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinusZeroBusinessDaysFromAHoliday() {
        this.businessCalendar.minusBusinessDays(this.independenceDayOf2011, 0);
    }

    @Test
    public void testFirstDayOfMonth() {
        assertEquals(CalendarDate.from(2011, 5, 1),
                this.businessCalendar.firstDayOfMonth(CalendarDate.from(2011, 5, 21)));
    }

    @Test
    public void testLastDayOfMonth() {
        assertEquals(CalendarDate.from(2011, 5, 31),
                this.businessCalendar.lastDayOfMonth(CalendarDate.from(2011, 5, 21)));
        assertEquals(CalendarDate.from(2011, 9, 30),
                this.businessCalendar.lastDayOfMonth(CalendarDate.from(2011, 9, 21)));
    }

    public void testLastDayOfFebruaryInLeapAndNonLeapYears() {
        assertEquals(CalendarDate.from(2011, 2, 28),
                this.businessCalendar.lastDayOfMonth(CalendarDate.from(2011, 2, 21)));
        assertEquals(CalendarDate.from(2008, 2, 29),
                this.businessCalendar.lastDayOfMonth(CalendarDate.from(2008, 2, 21)));
    }

    @Test
    public void testGetCalendarDaysBetweenTwoDates() {
        CalendarDate firstDate = CalendarDate.from(2010, 9, 21);
        CalendarDate secondDate = CalendarDate.from(2010, 10, 11);

        assertEquals(20, this.businessCalendar.getNumberOfDaysBetween(firstDate, secondDate));
        assertEquals(20, this.businessCalendar.getNumberOfDaysBetween(secondDate, firstDate));
        assertEquals(1, this.businessCalendar.getNumberOfDaysBetween(this.christmasEveOf2011, this.christmasOf2011));
    }

    @Test
    public void testGetCalendarDaysBetweenDatesAcrossLeapYear() {
        CalendarDate firstDate = CalendarDate.from(2012, 2, 1);
        CalendarDate secondDate = CalendarDate.from(2013, 2, 1);

        assertEquals(366, this.businessCalendar.getNumberOfDaysBetween(firstDate, secondDate));
        assertEquals(366, this.businessCalendar.getNumberOfDaysBetween(secondDate, firstDate));
    }

    @Test
    public void testGetCalendarDaysBetweenDatesAcrossNonLeapYear() {
        CalendarDate firstDate = CalendarDate.from(2009, 1, 1);
        CalendarDate secondDate = CalendarDate.from(2010, 1, 1);

        assertEquals(365, this.businessCalendar.getNumberOfDaysBetween(firstDate, secondDate));
        assertEquals(365, this.businessCalendar.getNumberOfDaysBetween(secondDate, firstDate));
    }

    @Test
    public void testFirstBusinessDayOfTheMonth() {
        assertEquals(CalendarDate.from(2011, 5, 2),
                this.businessCalendar.firstBusinessDayOfMonth(CalendarDate.from(2011, 5, 21)));
        assertEquals(CalendarDate.from(2011, 8, 1),
                this.businessCalendar.firstBusinessDayOfMonth(CalendarDate.from(2011, 8, 21)));
    }

    @Test
    public void testLastBusinessDayOfMonth() {
        assertEquals(CalendarDate.from(2011, 5, 31),
                this.businessCalendar.lastBusinessDayOfMonth(CalendarDate.from(2011, 5, 21)));
        assertEquals(CalendarDate.from(2011, 7, 29),
                this.businessCalendar.lastBusinessDayOfMonth(CalendarDate.from(2011, 7, 21)));
    }

    @Test
    public void testNumberOfBusinessDaysSpanningHolidays() {
        assertEquals(0,
                this.businessCalendar.getNumberOfBusinessDaysBetween(this.christmasEveOf2011, this.christmasOf2011));
        assertEquals(0,
                this.businessCalendar.getNumberOfBusinessDaysBetween(this.christmasEveOf2011, this.christmasEveOf2011));
        assertEquals(
                0,
                this.businessCalendar.getNumberOfBusinessDaysBetween(this.christmasEveOf2011,
                        CalendarDate.from(2011, 12, 26)));
    }

    @Test
    public void testNumberOfBusinessDaysSpanningHolidaysWeekendsAndWeekdays() {
        assertEquals(
                6,
                this.businessCalendar.getNumberOfBusinessDaysBetween(CalendarDate.from(2011, 9, 1),
                        CalendarDate.from(2011, 9, 12)));
    }

    @Test
    public void testNumberOfBusinessDaysSpanningAFullWorkingWeek() {
        assertEquals(
                5,
                this.businessCalendar.getNumberOfBusinessDaysBetween(CalendarDate.from(2011, 9, 11),
                        CalendarDate.from(2011, 9, 17)));
    }

}
