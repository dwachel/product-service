package com.demo.ppv.product.domain.model.constraint;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class NotPredicate extends Predicate {

    private final Predicate predicate;

    @Override
    public boolean evaluate(List<Constraint> privileges) {
        return !predicate.evaluate(privileges);
    }
}
