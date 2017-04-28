package com.concepts.domain.specification;

/**
 * "A specification states a constraint on the state of another object which may or may not be present". The purpose of
 * a specification is to test any object to see if it satisfies the specified criteria.
 * <p>
 * Reference: DDD Tackling Complexity in the Heart of Software - Eric Evans.
 * <p>
 * Use {@link com.concepts.domain.specification.AbstractSpecification} as base for creating specifications, and only the
 * method {@link #isSatisfiedBy(Object)} must be implemented.
 * 
 * @author ishitarakshit
 * @version 1.0, 08/20/2013
 * @since 1.5
 * @param <T>
 *            Any object on which a specification needs to be created.
 */
public interface Specification<T> {

    /**
     * Check if {@code t} is satisfied by the specification.
     * 
     * @param t
     *            Object to test.
     * @return {@code true} if {@code t} satisfies the specification.
     */
    boolean isSatisfiedBy(T t);

    /**
     * Create a new specification that is the AND operation of {@code this} specification and another specification.
     * 
     * @param specification
     *            Specification to AND with.
     * @return A new specification.
     */
    Specification<T> and(Specification<T> specification);

    /**
     * Create a new specification that is the OR operation of {@code this} specification and another specification.
     * 
     * @param specification
     *            Specification to OR with.
     * @return A new specification.
     */
    Specification<T> or(Specification<T> specification);
    
}
