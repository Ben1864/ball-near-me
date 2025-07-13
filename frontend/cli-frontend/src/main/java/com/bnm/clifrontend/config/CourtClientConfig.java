package com.bnm.clifrontend.config;

import com.bnm.clifrontend.client.CourtClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class CourtClientConfig {

    private final RestClient.Builder restClientBuilder;

    public CourtClientConfig(RestClient.Builder restClientBuilder) {
        this.restClientBuilder = restClientBuilder;
    }

    @Bean
    public CourtClient courtClient() {
        RestClient restClient = restClientBuilder.baseUrl("http://localhost:8081").build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(CourtClient.class);

    }
}
