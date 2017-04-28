package com.concepts.domain.time;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class TimeUnitTest {

    @Test
    public void testConvertibleToMilliseconds() {
        assertTrue(TimeUnit.MILLISECOND.isConvertibleToMilliseconds());
        assertTrue(TimeUnit.HOUR.isConvertibleToMilliseconds());
        assertTrue(TimeUnit.DAY.isConvertibleToMilliseconds());
        assertFalse(TimeUnit.MONTH.isConvertibleToMilliseconds());
        assertFalse(TimeUnit.YEAR.isConvertibleToMilliseconds());
    }

    @Test
    public void testIsConvertableTo() {
        assertTrue(TimeUnit.SECOND.isConvertibleTo(TimeUnit.MINUTE));
        assertTrue(TimeUnit.MINUTE.isConvertibleTo(TimeUnit.DAY));
        assertTrue(TimeUnit.DAY.isConvertibleTo(TimeUnit.SECOND));
        assertTrue(TimeUnit.YEAR.isConvertibleTo(TimeUnit.MONTH));
        assertFalse(TimeUnit.MONTH.isConvertibleTo(TimeUnit.DAY));
        assertFalse(TimeUnit.YEAR.isConvertibleTo(TimeUnit.HOUR));
    }

}
