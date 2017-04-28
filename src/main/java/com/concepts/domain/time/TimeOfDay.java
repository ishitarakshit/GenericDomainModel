package com.concepts.domain.time;

public class TimeOfDay {

    private HourOfDay hour;
    private MinuteOfHour minute;

    public static final TimeOfDay MIDNIGHT = hourAndMinute(0, 0);
    public static final TimeOfDay MIDDAY = hourAndMinute(12, 0);

    public static TimeOfDay hourAndMinute(int hour, int minute) {
        return new TimeOfDay(HourOfDay.valueOf(hour), MinuteOfHour.valueOf(minute));
    }

    public static TimeOfDay hourAndMinute(int hour, int minute, HourOfDay.AM_PM amPm) {
        return new TimeOfDay(HourOfDay.valueOf(hour, amPm), MinuteOfHour.valueOf(minute));
    }

    TimeOfDay(HourOfDay hour, MinuteOfHour minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public boolean isAfter(TimeOfDay another) {
        return this.hour.isAfter(another.hour) || this.hour.equals(another) && this.minute.isAfter(another.minute);
    }

    public boolean isBefore(TimeOfDay another) {
        return this.hour.isBefore(another.hour) || this.hour.equals(another) && this.minute.isBefore(another.minute);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) { return false; }
        if (this == object) { return true; }
        if (!(object instanceof TimeOfDay)) { return false; }

        TimeOfDay other = (TimeOfDay) object;

        return this.hour.equals(other.hour) && this.minute.equals(other.minute);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + this.hour.hashCode();
        result = 37 * result + this.minute.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return this.hour.toString() + ":" + this.minute.toString();
    }

}
