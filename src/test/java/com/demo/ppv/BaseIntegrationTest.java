package com.demo.ppv;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@SpringBootTest
public class BaseIntegrationTest {
    private static final WireMockServer WIRE_MOCK_SERVER = new WireMockServer(options().port(8888));

    @BeforeAll
    public static void beforeAll() {
        WIRE_MOCK_SERVER.start();
    }

    @AfterAll
    public static void afterAll() {
        WIRE_MOCK_SERVER.stop();
    }

    public static WireMockServer getMockServer() {
        return WIRE_MOCK_SERVER;
    }
}
