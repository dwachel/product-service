package com.demo.ppv.product.infrastructure.mapper;

import com.demo.ppv.product.domain.model.Product;
import com.demo.ppv.product.infrastructure.model.ProductResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-24T15:16:17+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4 (BellSoft)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toModel(ProductResponseDTO productResponseDTO) {
        if ( productResponseDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productResponseDTO.getId() );
        product.setConstraint( mapConstraintsToPredicate( productResponseDTO.getConstraint() ) );

        return product;
    }
}
