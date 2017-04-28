package com.concepts.domain.time;

public class MinuteOfHour {

    private int minute;

    public static MinuteOfHour valueOf(int minute) {
        return new MinuteOfHour(minute);
    }

    MinuteOfHour(int minute) {
        this.minute = minute;

        validateState();
    }

    public boolean isAfter(MinuteOfHour another) {
        return this.minute > another.minute;
    }

    public boolean isBefore(MinuteOfHour another) {
        return this.minute < another.minute;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) { return false; }
        if (this == object) { return true; }
        if (!(object instanceof MinuteOfHour)) { return false; }

        MinuteOfHour other = (MinuteOfHour) object;

        return this.minute == other.minute;
    }

    @Override
    public int hashCode() {
        return this.minute;
    }

    @Override
    public String toString() {
        return String.format("%1$02d", this.minute);
    }

    private void validateState() {
        if (this.minute < 0 || this.minute > 59) { 
            throw new IllegalArgumentException("Minute of hour" + this.minute + " is not between 0 and 59");
        }
    }

}
