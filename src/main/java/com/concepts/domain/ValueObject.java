package com.concepts.domain;

import java.io.Serializable;


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
public interface ValueObject<T> extends Serializable {

    boolean sameValueAs(T other);

}
