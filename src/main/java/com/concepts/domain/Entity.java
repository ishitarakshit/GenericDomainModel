package com.concepts.domain;

/**
 * 
 * <p>
 * Reference: DDD Tackling Complexity in the Heart of Software - Eric Evans.
 * <p>
 * 
 * @author ishitarakshit
 * @version 1.0, 08/20/2013
 * @since 1.5
 * @param <T>
 */
public interface Entity<T> {

    boolean sameIdentityAs(T other);

}
