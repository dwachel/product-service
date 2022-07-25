package com.demo.ppv.product.domain.model.constraint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PredicateTest {

    @Test
    void shouldReturnTrueWhenAllPrivileges() {
        var userPermissions = List.of(
                Constraint.VIP_ONLY, Constraint.FOR_RICH_PEOPLE, Constraint.WITH_RELATIONS);
        Predicate constraint = getConstraintPredicate();

        assertTrue(constraint.evaluate(userPermissions));
    }

    @Test
    void shouldReturnTrueWhenIsVip() {
        var userPermissions = List.of(Constraint.VIP_ONLY);
        Predicate constraint = getConstraintPredicate();

        assertTrue(constraint.evaluate(userPermissions));
    }

    @Test
    void shouldReturnTrueWhenHasRelations() {
        var userPermissions = List.of(Constraint.WITH_RELATIONS);
        Predicate constraint = getConstraintPredicate();

        assertTrue(constraint.evaluate(userPermissions));
    }

    @Test
    void shouldReturnFalseWhenIsNotVipAndIsRich() {
        var userPermissions = List.of(Constraint.FOR_RICH_PEOPLE, Constraint.WITH_RELATIONS);
        Predicate constraint = getConstraintPredicate();

        assertFalse(constraint.evaluate(userPermissions));
    }

    @NotNull
    private Predicate getConstraintPredicate() {
        var vipPredicate = new PrivilegePredicate(Constraint.VIP_ONLY);
        var richPredicate = new PrivilegePredicate(Constraint.FOR_RICH_PEOPLE);
        var withRelationsPredicate = new PrivilegePredicate(Constraint.WITH_RELATIONS);

        return Predicate.or(
                vipPredicate,
                Predicate.and(
                        Predicate.not(richPredicate),
                        withRelationsPredicate)
        );
    }
}
