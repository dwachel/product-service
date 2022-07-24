package com.demo.ppv.product.domain.model.constraint;

import java.util.List;

public abstract class Predicate {
    public abstract boolean evaluate(List<Constraint> privileges);

    public static Predicate or(Predicate left, Predicate right) {
        return new OrPredicate(left, right);
    }

    public static Predicate and(Predicate left, Predicate right) {
        return new AndPredicate(left, right);
    }

    public static Predicate not(Predicate predicate) {
        return new NotPredicate(predicate);
    }
}
