package com.concepts.domain.time;

import com.concepts.domain.Quantifiable;


/**
 * {@link Duration} is a type of quantity which can be measured and compared. This fact is signified by the
 * {@link Quantifiable} interface that it implements. Like all quantities , {@link Duration} has a magnitude represented
 * by the amount and a unit represented by {@link TimeUnit}. This Duration object simply refers to a specific amount of
 * time.
 * <p>
 * Reference: Time and Money in Domain Model - Eric Evans.
 * 
 * @author ishitarakshit
 * @version 1.0, 12/29/2009
 * @since 1.5
 */
public class Duration implements Quantifiable<Duration> {

    private static final long serialVersionUID = -8035547231657120738L;

    public static final Duration NONE = milliseconds(0);

    private long amount;
    private TimeUnit timeUnit;

    public static Duration milliseconds(long amount) {
        return Duration.of(amount, TimeUnit.MILLISECOND);
    }

    public static Duration seconds(int amount) {
        return Duration.of(amount, TimeUnit.SECOND);
    }

    public static Duration minutes(int amount) {
        return Duration.of(amount, TimeUnit.MINUTE);
    }

    public static Duration hours(int amount) {
        return Duration.of(amount, TimeUnit.HOUR);
    }

    public static Duration days(int amount) {
        return Duration.of(amount, TimeUnit.DAY);
    }

    public static Duration months(int amount) {
        return Duration.of(amount, TimeUnit.MONTH);
    }

    public static Duration quarters(int amount) {
        return Duration.of(amount, TimeUnit.QUARTER);
    }

    public static Duration years(int amount) {
        return Duration.of(amount, TimeUnit.YEAR);
    }

    private static Duration of(long amount, TimeUnit unit) {
        return new Duration(amount, unit);
    }

    public Duration(long amount, TimeUnit unit) {
        this.amount = amount;
        this.timeUnit = unit;

        validateState();
    }

    public long inBaseUnits() {
        return this.amount * this.timeUnit.getToBaseTimeUnitConversionFactor();
    }

    public boolean hasConvertibleTimeUnit(Duration other) {
        if (this.amount == 0 || other.amount == 0) { return true; }

        return other.timeUnit.isConvertibleTo(this.timeUnit);
    }

    public Duration plus(Duration other) {
        assertHasConvertibleTimeUnit(other);

        long newAmount = this.inBaseUnits() + other.inBaseUnits();
        TimeUnit newTimeUnit = (this.amount == 0) ? other.timeUnit.getBaseTimeUnit() : this.timeUnit.getBaseTimeUnit();
        return new Duration(newAmount, newTimeUnit);
    }

    public Duration minus(Duration other) {
        assertHasConvertibleTimeUnit(other);

        long newAmount = this.inBaseUnits() - other.inBaseUnits();
        return new Duration(newAmount, this.timeUnit.getBaseTimeUnit());
    }

    public int compareTo(Duration duration) {
        assertHasConvertibleTimeUnit(duration);

        long timeDifference = this.inBaseUnits() - duration.inBaseUnits();
        if (timeDifference > 0) { return 1; }
        if (timeDifference < 0) { return -1; }
        return 0;
    }

    public Duration negate() {
        throw new UnsupportedOperationException("Duration cannot be negated");
    }

    public Duration times(Number multiplier) {
        return of(this.amount * multiplier.longValue(), this.timeUnit);
    }

    public boolean isLessThan(Duration other) {
        return compareTo(other) < 0;
    }

    public boolean isGreaterThan(Duration other) {
        return compareTo(other) > 0;
    }

    public boolean isEqualTo(Duration other) {
        return compareTo(other) == 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) { return false; }
        if (this == object) { return true; }
        if (!(object instanceof Duration)) { return false; }

        Duration other = (Duration) object;

        return hasConvertibleTimeUnit(other) && this.inBaseUnits() == other.inBaseUnits();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + (int) this.amount;
        result = 37 * result + this.timeUnit.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return this.amount + " " + this.timeUnit.name().toLowerCase();
    }

    private void validateState() {
        if (this.amount < 0) { throw new IllegalArgumentException("Duration cannot be negative"); }
        if (this.timeUnit == null) { throw new IllegalArgumentException("Time unit cannot be null"); }
    }

    private void assertHasConvertibleTimeUnit(Duration other) {
        if (!hasConvertibleTimeUnit(other)) { 
            throw new IllegalArgumentException(other.toString() + " is not convertible to: " + toString());
       }
    }

}
