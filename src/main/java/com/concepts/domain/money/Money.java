package com.concepts.domain.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import com.concepts.domain.Quantifiable;


/**
 * {@link Money} is a type of quantity which can be measured and compared. This fact is signified by the
 * {@link Quantifiable} interface that it implements. Like all quantities , {@link Money} has a magnitude represented by
 * the amount and a unit represented by {@link Currency}. This Money object refers to a specific amount of a particular
 * currency.
 * <p>
 * Reference: Analysis Patterns: Reusable Object Models - Martin Fowler.
 * 
 * @author ishitarakshit
 * @version 1.0, 12/29/2009
 * @since 1.5
 */
public class Money implements Quantifiable<Money> {

    private static final long serialVersionUID = 8305351128868301581L;

    private static final Currency USD = Currency.getInstance("USD");
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    public static final Money ZERO_DOLLARS = dollars(BigDecimal.ZERO);

    private BigDecimal amount;
    private Currency currency;

    public static Money dollars(long amount) {
        return valueOf(BigDecimal.valueOf(amount), USD);
    }

    public static Money dollars(double amount) {
        return valueOf(BigDecimal.valueOf(amount), USD);
    }

    public static Money dollars(BigDecimal amount) {
        return valueOf(amount, USD);
    }

    public static Money dollars(long amount, RoundingMode rounding) {
        return valueOf(amount, USD, rounding);
    }

    public static Money dollars(double amount, RoundingMode rounding) {
        return valueOf(amount, USD, rounding);
    }

    public static Money dollars(BigDecimal amount, RoundingMode rounding) {
        return valueOf(amount, USD, rounding);
    }

    public static Money valueOf(long amount, Currency currency) {
        return valueOf(BigDecimal.valueOf(amount), currency);
    }

    public static Money valueOf(double amount, Currency currency) {
        return valueOf(BigDecimal.valueOf(amount), currency);
    }

    public static Money valueOf(BigDecimal amount, Currency currency) {
        return new Money(amount, currency);
    }

    public static Money valueOf(long amount, Currency currency, RoundingMode rounding) {
        return valueOf(BigDecimal.valueOf(amount), currency, rounding);
    }

    public static Money valueOf(double amount, Currency currency, RoundingMode rounding) {
        return valueOf(BigDecimal.valueOf(amount), currency, rounding);
    }

    public static Money valueOf(BigDecimal amount, Currency currency, RoundingMode rounding) {
        return new Money(amount, currency, rounding);
    }

    Money(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING);
    }

    Money(BigDecimal amount, Currency currency, RoundingMode rounding) {
        this.amount = amount;
        this.currency = currency;

        validateState();

        this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public boolean hasSameCurrency(Money other) {
        return getCurrency().equals(other.getCurrency());
    }

    public Money negate() {
        return valueOf(getAmount().negate(), getCurrency());
    }

    public Money plus(Money other) {
        assertHasSameCurrencyAs(other);
        return valueOf(getAmount().add(other.getAmount()), getCurrency());
    }

    public Money minus(Money other) {
        return plus(other.negate());
    }

    public Money times(Number multiplicand) {
        return valueOf(getAmount().multiply(BigDecimal.valueOf(multiplicand.doubleValue())), getCurrency());
    }

    public boolean isGreaterThan(Money other) {
        return compareTo(other) > 0;
    }

    public boolean isLessThan(Money other) {
        return compareTo(other) < 0;
    }

    public boolean isEqualTo(Money other) {
        return compareTo(other) == 0;
    }

    public int compareTo(Money money) {
        assertHasSameCurrencyAs(money);
        return getAmount().compareTo(money.getAmount());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) { return false; }
        if (this == object) { return true; }
        if (!(object instanceof Money)) { return false; }

        Money other = (Money) object;

        return hasSameCurrency(other) && getAmount().equals(other.getAmount());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + getAmount().hashCode();
        result = 37 * result + getCurrency().hashCode();

        return result;
    }

    @Override
    public String toString() {
        return getCurrency().getCurrencyCode() + " " + getAmount();
    }

    private void validateState() {
        if (this.amount == null) { throw new IllegalArgumentException("Monetary amount cannot be null"); }

        if (this.currency == null) { throw new IllegalArgumentException("Currency cannot be null"); }
    }

    private void assertHasSameCurrencyAs(Money other) {
        if (!hasSameCurrency(other)) { 
            throw new IllegalArgumentException(other.toString() + " is not same currency as " + this.toString());
        }
    }

}
