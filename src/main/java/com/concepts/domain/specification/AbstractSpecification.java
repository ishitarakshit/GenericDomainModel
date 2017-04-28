package com.concepts.domain.specification;

/**
 * Abstract base implementation of composite {@link Specification} with default implementations for {@code and},
 * {@code or} and {@code not}.
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

    public abstract boolean isSatisfiedBy(T t);

    public Specification<T> and(Specification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }

    public Specification<T> or(Specification<T> specification) {
        throw new UnsupportedOperationException();
    }
    
}
