package com.demo.ppv.product.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "services.products")
public class ProductsServiceProperties {
    private String url;
    private String path;
}
