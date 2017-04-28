package com.concepts.domain.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import org.junit.Test;


public class MoneyTest {

    @Test
    public void testMoneyCreation() {
        Money dollars100 = new Money(new BigDecimal(100), Currency.getInstance("USD"), RoundingMode.UP);

        assertEquals(dollars100, Money.dollars(100L));
        assertEquals(dollars100, Money.dollars(100D));
        assertEquals(dollars100, Money.dollars(new BigDecimal(100)));
        assertEquals(dollars100, Money.dollars(100L, RoundingMode.UP));
        assertEquals(dollars100, Money.dollars(100D, RoundingMode.UP));
        assertEquals(dollars100, Money.dollars(new BigDecimal(100), RoundingMode.UP));
        assertEquals(dollars100, Money.valueOf(100L, Currency.getInstance("USD")));
        assertEquals(dollars100, Money.valueOf(100D, Currency.getInstance("USD")));
        assertEquals(dollars100, Money.valueOf(new BigDecimal(100), Currency.getInstance("USD")));
        assertEquals(dollars100, Money.valueOf(100L, Currency.getInstance("USD"), RoundingMode.UP));
        assertEquals(dollars100, Money.valueOf(100D, Currency.getInstance("USD"), RoundingMode.UP));
        assertEquals(dollars100, Money.valueOf(new BigDecimal(100), Currency.getInstance("USD"), RoundingMode.UP));
    }

    @Test
    public void testFractionalDigits() {
        assertEquals(Money.dollars(2.50), Money.dollars(2.5012));
    }

    @Test
    public void testMoneyRounding() {
        assertEquals(Money.dollars(20.75), Money.dollars(20.7575, RoundingMode.DOWN));
        assertEquals(Money.dollars(20.76), Money.dollars(20.7556, RoundingMode.UP));
    }

    @Test
    public void testHasSameCurrencyForSameCurrencies() {
        assertTrue(Money.dollars(100).hasSameCurrency(Money.dollars(10)));
    }

    @Test
    public void testHasSameCurrencyForDifferentCurrencies() {
        assertFalse(Money.dollars(100).hasSameCurrency(Money.valueOf(100, Currency.getInstance("INR"))));
    }

    @Test
    public void testNegate() {
        assertEquals(Money.dollars(-100.00), Money.dollars(100).negate());
        assertEquals(Money.dollars(100.00), Money.dollars(-100).negate());
    }

    @Test
    public void testPlus() {
        assertEquals(Money.dollars(400.00), Money.dollars(250.00).plus(Money.dollars(150.00)));
        assertEquals(Money.dollars(100.00), Money.dollars(-250.00).plus(Money.dollars(350.00)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlusWithDifferentCurrency() throws Exception {
        Money.dollars(100).plus(Money.valueOf(100, Currency.getInstance("EUR")));
    }

    @Test
    public void testMinus() {
        assertEquals(Money.dollars(100.00), Money.dollars(250.00).minus(Money.dollars(150.00)));
        assertEquals(Money.dollars(-100.00), Money.dollars(250.00).minus(Money.dollars(350.00)));
        assertEquals(Money.dollars(-400.00), Money.dollars(-250.00).minus(Money.dollars(150.00)));
        assertEquals(Money.dollars(400.00), Money.dollars(250.00).minus(Money.dollars(-150.00)));
    }

    @Test
    public void testTimes() {
        assertEquals(Money.dollars(250.10), Money.dollars(125.05).times(new BigDecimal(2)));
        assertEquals(Money.dollars(268.86), Money.dollars(125.05).times(new Double(2.15)));
    }

    @Test
    public void testIsGreaterThan() {
        assertTrue(Money.dollars(150.00).isGreaterThan(Money.dollars(100.00)));
        assertFalse(Money.dollars(150.00).isGreaterThan(Money.dollars(300.00)));
    }

    @Test
    public void testIsLessThan() {
        assertTrue(Money.dollars(250.50).isLessThan(Money.dollars(300.00)));
        assertFalse(Money.dollars(250.50).isLessThan(Money.dollars(250.00)));
    }

    @Test
    public void testMoniesAreEqual() {
        assertEquals(Money.dollars(200), Money.dollars(200));
        assertEquals(Money.dollars(200), Money.valueOf(new BigDecimal(200), Currency.getInstance("USD")));
        assertEquals(Money.dollars(200), Money.valueOf(200, Currency.getInstance("USD"), RoundingMode.UP));
    }

    @Test
    public void testMoniesAreNotEqual() {
        assertFalse(Money.dollars(100).equals(Money.dollars(200)));
        assertFalse(Money.dollars(100).equals(Money.valueOf(new BigDecimal(100), Currency.getInstance("EUR"))));
    }

    @Test
    public void testToString() {
        assertEquals("USD 100.00", Money.dollars(100).toString());
    }

}
