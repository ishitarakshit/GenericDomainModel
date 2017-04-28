package com.concepts.domain.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;


public class TimePointTest {

    private Calendar dec012009;
    private Calendar jan012010;
    private Calendar feb012010;

    @Before
    public void setUp() throws Exception {
        this.dec012009 = createDate(2009, 12, 1);
        this.jan012010 = createDate(2010, 1, 1);
        this.feb012010 = createDate(2010, 2, 1);
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
    public void testCreationFromDateString() {
        TimePoint t = new TimePoint(this.jan012010.getTimeInMillis());

        assertEquals(t, TimePoint.parseFrom("2010/01/01", "yyyy/MM/dd"));
    }

    @Test
    public void testCreation() {
        TimePoint t = new TimePoint(this.jan012010.getTimeInMillis());

        assertEquals(t, TimePoint.from(this.jan012010));
        assertEquals(t, TimePoint.from(this.jan012010.getTime()));
        assertEquals(t, TimePoint.from(this.jan012010.getTimeInMillis()));
    }

    @Test
    public void testTimePointIsBefore() {
        assertTrue(TimePoint.from(this.jan012010).isBefore(TimePoint.from(this.feb012010)));
        assertFalse(TimePoint.from(this.jan012010).isBefore(TimePoint.from(this.dec012009)));
        assertFalse(TimePoint.from(this.jan012010).isBefore(TimePoint.from(this.jan012010)));
    }

    @Test
    public void testTimePointIsAfter() {
        assertTrue(TimePoint.from(this.feb012010).isAfter(TimePoint.from(this.jan012010)));
        assertFalse(TimePoint.from(this.dec012009).isAfter(TimePoint.from(this.jan012010)));
        assertFalse(TimePoint.from(this.jan012010).isAfter(TimePoint.from(this.jan012010)));
    }

    @Test
    public void testSameTimePoint() {
        assertTrue(TimePoint.from(this.feb012010).equals(TimePoint.from(this.feb012010)));
        assertFalse(TimePoint.from(this.jan012010).equals(TimePoint.from(this.feb012010)));
    }

}
