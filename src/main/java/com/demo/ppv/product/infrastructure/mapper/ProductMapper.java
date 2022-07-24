package com.demo.ppv.product.infrastructure.mapper;

import com.demo.ppv.product.domain.model.Product;
import com.demo.ppv.product.domain.model.constraint.Constraint;
import com.demo.ppv.product.domain.model.constraint.Predicate;
import com.demo.ppv.product.domain.model.constraint.PrivilegePredicate;
import com.demo.ppv.product.infrastructure.model.ProductResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toModel(ProductResponseDTO productResponseDTO);

    default Predicate mapConstraintsToPredicate(String constraint) {
        /*
         * Business logic to parse constraint's string expression and convert it to predicate model.
         * Mock value is returned right now.
         */
        return new PrivilegePredicate(Constraint.VIP_ONLY);
    }
}
