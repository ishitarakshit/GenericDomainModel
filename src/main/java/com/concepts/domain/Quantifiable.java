package com.concepts.domain;

import java.io.Serializable;


/**
 * The {@link Quantifiable} interface defines the basic arithmetic operations that are possible on quantities. Quantity
 * is a kind of property that can be measured.
 * <p>
 * Common examples of quantities are money, dimensions, etc.
 * 
 * @author ishitarakshit
 * @version 1.0, 12/29/2009
 * @since 1.5
 * @param <T>
 *            Any thing/type that can be quantified.
 */
public interface Quantifiable<T> extends Comparable<T>, Serializable {

    T negate();

    T plus(T other);

    T minus(T other);

    T times(Number multiplier);

    boolean isLessThan(T other);

    boolean isGreaterThan(T other);

    boolean isEqualTo(T other);

}
