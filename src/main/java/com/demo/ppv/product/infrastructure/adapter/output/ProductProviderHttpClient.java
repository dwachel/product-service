package com.demo.ppv.product.infrastructure.adapter.output;

import com.demo.ppv.product.domain.model.Product;
import com.demo.ppv.product.domain.port.outbound.ProductProvider;
import com.demo.ppv.product.infrastructure.config.ProductsServiceProperties;
import com.demo.ppv.product.infrastructure.exception.HttpClientParseBodyException;
import com.demo.ppv.product.infrastructure.http.HttpClientGateway;
import com.demo.ppv.product.infrastructure.mapper.ProductMapper;
import com.demo.ppv.product.infrastructure.model.ProductResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductProviderHttpClient implements ProductProvider {

    private final HttpClientGateway httpClientGateway;
    private final ProductsServiceProperties httpClientConfig;
    private final ObjectMapper objectMapper;
    private final ProductMapper productMapper;

    @Override
    public Product fetch(String id) {
        var urlBuilder = Objects.requireNonNull(
                HttpUrl.parse(httpClientConfig.getUrl() + httpClientConfig.getPath())).newBuilder();
        urlBuilder.addPathSegment(id);

        var request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .build();

        var responseBody = httpClientGateway.get(request);

        return this.mapBodyToDto(responseBody);
    }

    private Product mapBodyToDto(String responseBody) {
        try {
            var productResponseDTO = objectMapper.readValue(responseBody, ProductResponseDTO.class);
            return productMapper.toModel(productResponseDTO);
        } catch (IOException e) {
            throw new HttpClientParseBodyException("Can not parse response body", e);
        }
    }
}
