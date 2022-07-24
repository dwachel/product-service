package com.demo.ppv.product.domain.model;

import com.demo.ppv.product.domain.model.constraint.Predicate;
import com.demo.ppv.product.domain.model.constraint.Constraint;
import lombok.Data;

import java.util.List;

@Data
public class Product {
    private String id;
    private Predicate constraint;

    public boolean hasAccess(List<Constraint> privileges) {
        return constraint.evaluate(privileges);
    }
}
