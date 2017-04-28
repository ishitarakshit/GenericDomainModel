package com.concepts.domain.time;

public final class TimeUnitConversionFactors {

    public static final long MILLISECONDS_PER_SECOND = 1000;
    public static final long MILLISECONDS_PER_MINUTE = 60 * MILLISECONDS_PER_SECOND;
    public static final long MILLISECONDS_PER_HOUR = 60 * MILLISECONDS_PER_MINUTE;
    public static final long MILLISECONDS_PER_DAY = 24 * MILLISECONDS_PER_HOUR;
    public static final long MILLISECONDS_PER_WEEK = 7 * MILLISECONDS_PER_DAY;

    public static final long MONTHS_PER_QUARTER = 3;
    public static final long MONTHS_PER_YEAR = 12;
    public static final long MONTHS_PER_DECADE = 10 * MONTHS_PER_YEAR;

    private TimeUnitConversionFactors() {
    }

}
