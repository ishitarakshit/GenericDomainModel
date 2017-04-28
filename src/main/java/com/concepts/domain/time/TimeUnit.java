package com.concepts.domain.time;

import static com.concepts.domain.time.TimeUnitConversionFactors.MILLISECONDS_PER_DAY;
import static com.concepts.domain.time.TimeUnitConversionFactors.MILLISECONDS_PER_HOUR;
import static com.concepts.domain.time.TimeUnitConversionFactors.MILLISECONDS_PER_MINUTE;
import static com.concepts.domain.time.TimeUnitConversionFactors.MILLISECONDS_PER_SECOND;
import static com.concepts.domain.time.TimeUnitConversionFactors.MONTHS_PER_QUARTER;
import static com.concepts.domain.time.TimeUnitConversionFactors.MONTHS_PER_YEAR;

import java.io.Serializable;


/**
 * This object represents unit of time. The time unit can be categorically classified into two groups, time and day. The
 * base unit of time is millisecond where as the base unit of day is month. While a day can be definitively expressed in
 * milliseconds, a month cannot due to varying number of days in a month. Hence the two base units.
 * <p>
 * Reference: Time and Money in Domain Model - Eric Evans.
 * 
 * @author ishitarakshit
 * @version 1.0, 12/29/2009
 * @since 1.5
 */
public enum TimeUnit implements Serializable {

    MILLISECOND(1), SECOND(MILLISECONDS_PER_SECOND), MINUTE(MILLISECONDS_PER_MINUTE), HOUR(MILLISECONDS_PER_HOUR), DAY(
            MILLISECONDS_PER_DAY),

    MONTH(1), QUARTER(MONTHS_PER_QUARTER), YEAR(MONTHS_PER_YEAR);

    private long toBaseTimeUnitConversionFactor;

    private TimeUnit(long toBaseTimeUnitConversionFactor) {
        this.toBaseTimeUnitConversionFactor = toBaseTimeUnitConversionFactor;
    }

    public TimeUnit getBaseTimeUnit() {
        switch (this) {
        case MILLISECOND:
        case SECOND:
        case MINUTE:
        case HOUR:
        case DAY:
            return TimeUnit.MILLISECOND;

        case MONTH:
        case QUARTER:
        case YEAR:
            return TimeUnit.MONTH;

        default:
            return null;
        }
    }

    public boolean isConvertibleToMilliseconds() {
        return isConvertibleTo(MILLISECOND);
    }

    public boolean isConvertibleTo(TimeUnit other) {
        return this.getBaseTimeUnit().equals(other.getBaseTimeUnit());
    }

    public long getToBaseTimeUnitConversionFactor() {
        return this.toBaseTimeUnitConversionFactor;
    }

}
