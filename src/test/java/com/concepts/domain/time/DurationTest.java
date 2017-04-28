package com.concepts.domain.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class DurationTest {

    private Duration milliseconds100 = new Duration(100, TimeUnit.MILLISECOND);
    private Duration seconds100 = new Duration(100, TimeUnit.SECOND);
    private Duration minutes100 = new Duration(100, TimeUnit.MINUTE);
    private Duration hours100 = new Duration(100, TimeUnit.HOUR);
    private Duration days100 = new Duration(100, TimeUnit.DAY);

    private Duration months10 = new Duration(10, TimeUnit.MONTH);
    private Duration years10 = new Duration(10, TimeUnit.YEAR);

    @Test
    public void testDurationCreation() {
        assertEquals(this.milliseconds100, Duration.milliseconds(100));
        assertEquals(this.seconds100, Duration.seconds(100));
        assertEquals(this.minutes100, Duration.minutes(100));
        assertEquals(this.hours100, Duration.hours(100));
        assertEquals(this.days100, Duration.days(100));
        assertEquals(this.months10, Duration.months(10));
        assertEquals(this.years10, Duration.years(10));
    }

    @Test
    public void testHasConvertibleTimeUnits() {
        assertTrue(this.hours100.hasConvertibleTimeUnit(this.seconds100));
        assertTrue(this.years10.hasConvertibleTimeUnit(this.months10));
        assertFalse(this.months10.hasConvertibleTimeUnit(this.days100));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testNegate() {
        this.months10.negate();
    }

    @Test
    public void testPlus() {
        assertEquals(Duration.days(12), Duration.days(10).plus(Duration.days(2)));
        assertEquals(this.days100, this.days100.plus(Duration.NONE));
        assertEquals(this.months10, Duration.NONE.plus(this.months10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlusWithDifferentBaseTimeUnits() throws Exception {
        this.months10.plus(this.milliseconds100);
    }

    @Test
    public void testMinus() {
        assertEquals(Duration.NONE, this.months10.minus(this.months10));
        assertEquals(Duration.NONE, this.hours100.minus(this.hours100));
        assertEquals(Duration.minutes(75), this.minutes100.minus(Duration.minutes(25)));
        assertEquals(this.months10, this.months10.minus(Duration.NONE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinusWithResultLessThanZero() {
        Duration.NONE.minus(this.milliseconds100);
    }

    @Test
    public void testTimes() {
        assertEquals(Duration.minutes(60), Duration.minutes(10).times(6L));
        assertEquals(this.years10, this.months10.times(12L));
        assertEquals(Duration.NONE, Duration.NONE.times(10L));
    }

    @Test
    public void testIsGreaterThan() {
        assertTrue(this.minutes100.isGreaterThan(this.seconds100));
        assertTrue(this.years10.isGreaterThan(this.months10));
        assertFalse(this.minutes100.isGreaterThan(this.hours100));
    }

    @Test
    public void testIsLessThan() {
        assertTrue(this.hours100.isLessThan(this.days100));
        assertTrue(this.months10.isLessThan(this.years10));
        assertFalse(this.seconds100.isLessThan(this.milliseconds100));
    }

    @Test
    public void testDurationsAreEqual() {
        assertTrue(this.months10.isEqualTo(this.months10));
        assertTrue(this.days100.isEqualTo(Duration.hours(2400)));
    }

    @Test
    public void testDurationsAreNotEqual() {
        assertFalse(this.seconds100.isEqualTo(this.milliseconds100));
    }

    @Test
    public void testToString() {
        assertEquals("100 minute", this.minutes100.toString());
    }

}
