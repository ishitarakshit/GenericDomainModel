package com.concepts.domain.time;

import static com.concepts.domain.time.HourOfDay.AM_PM.AM;
import static com.concepts.domain.time.HourOfDay.AM_PM.PM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class TimeOfDayTest {

    @Test
    public void testIsBefore() {
        assertTrue(TimeOfDay.MIDNIGHT.isBefore(TimeOfDay.MIDDAY));
        assertFalse(TimeOfDay.hourAndMinute(9, 30).isBefore(TimeOfDay.hourAndMinute(9, 30, AM)));
        assertFalse(TimeOfDay.hourAndMinute(9, 30, PM).isBefore(TimeOfDay.hourAndMinute(9, 30, AM)));
        assertFalse(TimeOfDay.hourAndMinute(9, 00, AM).isBefore(TimeOfDay.hourAndMinute(9, 00, AM)));
    }

    @Test
    public void testIsAfter() {
        assertTrue(TimeOfDay.MIDDAY.isAfter(TimeOfDay.MIDNIGHT));
        assertTrue(TimeOfDay.hourAndMinute(9, 00, PM).isAfter(TimeOfDay.hourAndMinute(9, 00, AM)));
        assertFalse(TimeOfDay.hourAndMinute(9, 00, AM).isAfter(TimeOfDay.hourAndMinute(9, 00, AM)));
    }

    @Test
    public void testEquals() {
        assertTrue(TimeOfDay.MIDNIGHT.equals(TimeOfDay.hourAndMinute(12, 0, AM)));
        assertTrue(TimeOfDay.MIDDAY.equals(TimeOfDay.hourAndMinute(12, 0, PM)));
    }

    @Test
    public void testToString() {
        assertEquals("00:00", TimeOfDay.MIDNIGHT.toString());
        assertEquals("12:00", TimeOfDay.MIDDAY.toString());
    }
}
