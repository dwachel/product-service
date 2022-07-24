package com.demo.ppv.product.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "http.client")
public class HttpClientProperties {

    private HttpTimeoutProperties timeout;
    private HttpRetryProperties retry;

    @Data
    public static class HttpTimeoutProperties {
        private int read;
        private int call;
    }

    @Data
    public static class HttpRetryProperties {
        private int maxRetries;
        private int retryDelay;
        private int maxRetryDelay;
    }
}
