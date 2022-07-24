package com.demo.ppv.product.domain.model.constraint;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrPredicate extends Predicate {

    private final Predicate left;
    private final Predicate right;

    @Override
    public boolean evaluate(List<Constraint> privileges) {
        return left.evaluate(privileges) || right.evaluate(privileges);
    }
}
