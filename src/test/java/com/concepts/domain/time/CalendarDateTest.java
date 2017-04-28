package com.concepts.domain.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;


public class CalendarDateTest {

    private Calendar jan012010;

    @Before
    public void setUp() throws Exception {
        this.jan012010 = createDate(2010, 1, 1);
    }

    private Calendar createDate(int year, int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month - 1);
        date.set(Calendar.DATE, day);

        trimTimeComponent(date);

        return date;
    }

    private void trimTimeComponent(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    @Test
    public void testCreation() {
        CalendarDate day = new CalendarDate(2010, 1, 1);

        assertEquals(day, CalendarDate.from(2010, 1, 1));
        assertEquals(day, CalendarDate.from(this.jan012010));
        assertEquals(day, CalendarDate.from(this.jan012010.getTime()));
        assertEquals(day, CalendarDate.from(TimePoint.from(this.jan012010)));
        assertEquals(day, CalendarDate.from("2010-01-01", "yyyy-MM-dd"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMonthError() {
        CalendarDate.from(2011, 13, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDayError() {
        CalendarDate.from(2011, 12, 32);
    }

    @Test
    public void testIsBefore() {
        assertFalse(CalendarDate.from(2011, 3, 1).isBefore(CalendarDate.from(this.jan012010)));
        assertFalse(CalendarDate.from(2011, 1, 1).isBefore(CalendarDate.from(this.jan012010)));
        assertTrue(CalendarDate.from(2009, 1, 1).isBefore(CalendarDate.from(this.jan012010)));
    }

    @Test
    public void testIsAfter() {
        assertFalse(CalendarDate.from(2009, 3, 1).isAfter(CalendarDate.from(this.jan012010)));
        assertFalse(CalendarDate.from(2010, 1, 1).isAfter(CalendarDate.from(this.jan012010)));
        assertTrue(CalendarDate.from(2011, 1, 1).isAfter(CalendarDate.from(this.jan012010)));
    }

    @Test
    public void testNextDay() {
        assertEquals(CalendarDate.from(2010, 3, 1), CalendarDate.from(2010, 2, 28).nextDay());
        assertEquals(CalendarDate.from(2011, 1, 1), CalendarDate.from(2010, 12, 31).nextDay());
    }

    @Test
    public void testPreviousDay() {
        assertEquals(CalendarDate.from(2010, 2, 28), CalendarDate.from(2010, 3, 1).previousDay());
        assertEquals(CalendarDate.from(2009, 12, 31), CalendarDate.from(2010, 1, 1).previousDay());
    }

    @Test
    public void testPlusDays() {
        assertEquals(CalendarDate.from(this.jan012010), CalendarDate.from(this.jan012010).plusDays(0));
        assertEquals(CalendarDate.from(2011, 1, 1), CalendarDate.from(2010, 12, 31).plusDays(1));
    }

    @Test
    public void testPlusMonths() {
        assertEquals(CalendarDate.from(this.jan012010), CalendarDate.from(this.jan012010).plusMonths(0));
        assertEquals(CalendarDate.from(2010, 2, 28), CalendarDate.from(2010, 1, 31).plusMonths(1));
        assertEquals(CalendarDate.from(2011, 1, 1), CalendarDate.from(this.jan012010).plusMonths(12));
    }

    @Test
    public void testPlusYears() {
        assertEquals(CalendarDate.from(this.jan012010), CalendarDate.from(this.jan012010).plusYears(0));
        assertEquals(CalendarDate.from(2015, 1, 1), CalendarDate.from(this.jan012010).plusYears(5));
    }

    @Test
    public void testToString() {
        assertEquals("01-Jan-2010", CalendarDate.from(this.jan012010).toString());
    }
}
