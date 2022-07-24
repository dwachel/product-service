package com.demo.ppv.product.domain.model.constraint;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PrivilegePredicate extends Predicate {

    private final Constraint constraint;

    @Override
    public boolean evaluate(List<Constraint> privileges) {
        return privileges.contains(constraint);
    }
}
