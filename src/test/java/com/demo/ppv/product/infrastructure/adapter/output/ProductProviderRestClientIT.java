package com.demo.ppv.product.infrastructure.adapter.output;

import com.demo.ppv.BaseIntegrationTest;
import com.demo.ppv.product.domain.port.outbound.ProductProvider;
import com.demo.ppv.product.infrastructure.exception.HttpClientConnectionException;
import com.demo.ppv.product.infrastructure.exception.HttpClientResponseException;
import com.demo.ppv.product.infrastructure.model.ProductResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductProviderRestClientIT extends BaseIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductProvider productProvider;

    @Test
    void shouldReturnProduct() throws JsonProcessingException {
        //given
        var productId = "1";
        mockResponse(200);

        //when
        var product = productProvider.fetch(productId);

        //then
        assertEquals(productId, product.getId());

        // + other assertions
    }

    @Test
    void shouldThrowExceptionWhenTimeout() throws JsonProcessingException {
        //given
        var productId = "1";
        mockTimeout();

        //when
        //then
        assertThrows(HttpClientConnectionException.class, () -> productProvider.fetch(productId));
    }

    @Test
    void shouldThrowExceptionWhenInternalServerError() throws JsonProcessingException {
        //given
        var productId = "1";
        mockResponse(500);

        //when
        //then
        assertThrows(HttpClientResponseException.class, () -> productProvider.fetch(productId));
    }

    @Test
    void shouldThrowExceptionWhenBadRequest() throws JsonProcessingException {
        //given
        var productId = "1";
        mockResponse(400);

        //when
        //then
        assertThrows(HttpClientResponseException.class, () -> productProvider.fetch(productId));
    }

    private void mockResponse(int status) throws JsonProcessingException {
        var response = ProductResponseDTO.builder()
                .id("1")
                .constraint("OR('VIP_ONLY',AND(NOT('FOR_RICH_PEOPLE'),'WITH_RELATIONS'))")
                .build();

        getMockServer().stubFor(WireMock.get(urlEqualTo("/products/1"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(response))
                        .withStatus(status))
        );
    }

    private void mockTimeout() {
        getMockServer().stubFor(WireMock.get(urlEqualTo("/products/1"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withFixedDelay(1000 * 30)
                        .withStatus(200))
        );
    }
}
