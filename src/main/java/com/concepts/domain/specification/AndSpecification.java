package com.concepts.domain.specification;

public class AndSpecification<T> extends AbstractSpecification<T> {

    private Specification<T> one;
    private Specification<T> two;

    public AndSpecification(Specification<T> one, Specification<T> two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return this.one.isSatisfiedBy(t) && this.two.isSatisfiedBy(t);
    }

}
