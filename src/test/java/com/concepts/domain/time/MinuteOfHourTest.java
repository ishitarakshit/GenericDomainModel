package com.concepts.domain.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class MinuteOfHourTest {

    @Test
    public void testMinuteOfHourCreation() {
        MinuteOfHour halfPastTheHour = new MinuteOfHour(30);

        assertEquals(halfPastTheHour, MinuteOfHour.valueOf(30));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMinuteLowerLimit() {
        MinuteOfHour.valueOf(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMinuteUpperLimit() {
        MinuteOfHour.valueOf(60);
    }

    @Test
    public void testIsBefore() {
        assertTrue(MinuteOfHour.valueOf(15).isBefore(MinuteOfHour.valueOf(30)));
        assertFalse(MinuteOfHour.valueOf(30).isBefore(MinuteOfHour.valueOf(15)));
    }

    @Test
    public void testIsAfter() {
        assertTrue(MinuteOfHour.valueOf(30).isAfter(MinuteOfHour.valueOf(15)));
        assertFalse(MinuteOfHour.valueOf(15).isAfter(MinuteOfHour.valueOf(30)));
    }

    @Test
    public void testToString() {
        assertEquals("01", MinuteOfHour.valueOf(1).toString());
    }
}
