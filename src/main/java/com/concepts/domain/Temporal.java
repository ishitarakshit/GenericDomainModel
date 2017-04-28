package com.concepts.domain;

import com.concepts.domain.time.TimePoint;


/**
 * The {@link Temporal} interface defines the contract for a property or object that changes over time.
 * <p>
 * Reference: Patterns of Enterprise Application Architecture - Martin Fowler.
 * 
 * @author ishitarakshit
 * @version 1.0, 12/29/2009
 * @since 1.5
 * @param <T>
 */
public interface Temporal<T> {

    T get();

    T get(TimePoint at);

    void add(T property, TimePoint at);

    void remove(T property);

}
