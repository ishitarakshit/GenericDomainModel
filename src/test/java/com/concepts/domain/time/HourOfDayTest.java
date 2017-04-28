package com.concepts.domain.time;

import static com.concepts.domain.time.HourOfDay.AM_PM.AM;
import static com.concepts.domain.time.HourOfDay.AM_PM.PM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class HourOfDayTest {

    @Test
    public void testHourOfDayCreation() {
        HourOfDay timeAt9am = new HourOfDay(9);
        HourOfDay timeAt9pm = new HourOfDay(21);

        assertEquals(timeAt9am, HourOfDay.valueOf(9, AM));
        assertEquals(timeAt9pm, HourOfDay.valueOf(9, PM));
    }

    @Test
    public void testHourOfDayBoundaryConditions() {
        HourOfDay timeAt12am = new HourOfDay(0);
        HourOfDay timeAt12pm = new HourOfDay(12);

        assertEquals(timeAt12am, HourOfDay.valueOf(12, AM));
        assertEquals(timeAt12pm, HourOfDay.valueOf(12, PM));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalid24HourLowerValue() {
        HourOfDay.valueOf(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalid24HourUpperValue() {
        HourOfDay.valueOf(24);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test12HourLowerValue() {
        HourOfDay.valueOf(0, AM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test12HourUpper() {
        HourOfDay.valueOf(13, PM);
    }

    @Test
    public void testIsBefore() {
        assertTrue(HourOfDay.valueOf(9, AM).isBefore(HourOfDay.valueOf(9, PM)));
        assertFalse(HourOfDay.valueOf(9, PM).isBefore(HourOfDay.valueOf(9, AM)));
    }

    @Test
    public void testIsAfter() {
        assertTrue(HourOfDay.valueOf(9, PM).isAfter(HourOfDay.valueOf(9, AM)));
        assertFalse(HourOfDay.valueOf(9, AM).isAfter(HourOfDay.valueOf(9, PM)));
    }

    @Test
    public void testIsEqual() {
        assertTrue(HourOfDay.valueOf(9, PM).equals(HourOfDay.valueOf(9, PM)));
    }

    @Test
    public void testToString() {
        assertEquals("01", HourOfDay.valueOf(1, AM).toString());
        assertEquals("13", HourOfDay.valueOf(1, PM).toString());
    }

}
