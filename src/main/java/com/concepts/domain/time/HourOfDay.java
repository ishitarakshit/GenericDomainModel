package com.concepts.domain.time;

public class HourOfDay {

    public static enum AM_PM {
        AM, PM
    };

    private int valueIn24Hour;

    public static HourOfDay valueOf(int hour) {
        return new HourOfDay(hour);
    }

    public static HourOfDay valueOf(int hour, HourOfDay.AM_PM amPm) {
        return HourOfDay.valueOf(convert12To24hour(hour, amPm));
    }

    HourOfDay(int hour) {
        assertIsValidHour24HourFormat(hour);

        this.valueIn24Hour = hour;
    }

    public boolean isAfter(HourOfDay another) {
        return this.valueIn24Hour > another.valueIn24Hour;
    }

    public boolean isBefore(HourOfDay another) {
        return this.valueIn24Hour < another.valueIn24Hour;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) { return false; }
        if (this == object) { return true; }
        if (!(object instanceof HourOfDay)) { return false; }

        HourOfDay other = (HourOfDay) object;

        return this.valueIn24Hour == other.valueIn24Hour;
    }

    @Override
    public int hashCode() {
        return this.valueIn24Hour;
    }

    @Override
    public String toString() {
        return String.format("%1$02d", this.valueIn24Hour);
    }

    private static int convert12To24hour(int hour, HourOfDay.AM_PM amPm) {
        assertIsValidHourFor12HourFormat(hour);

        switch (amPm) {
        case AM:
            return (hour == 12) ? 0 : hour;
        case PM:
            return (hour == 12) ? 12 : hour + 12;
        default:
            return -1;
        }
    }

    private static void assertIsValidHour24HourFormat(int hour) {
        if (hour < 0 || hour > 23) { 
            throw new IllegalArgumentException("Hour of day" + hour + " is not between 0 and 23"); 
        }
    }

    private static void assertIsValidHourFor12HourFormat(int hour) {
        if (hour < 1 || hour > 12) { 
            throw new IllegalArgumentException("Hour of day" + hour + " is not between 1 and 12"); 
        }
    }

}
