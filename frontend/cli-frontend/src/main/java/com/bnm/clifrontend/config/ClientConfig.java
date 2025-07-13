package com.bnm.clifrontend.config;

import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public abstract class ClientConfig {
    private final RestClient.Builder restClientBuilder;

    protected ClientConfig(RestClient.Builder restClientBuilder) {
        this.restClientBuilder = restClientBuilder;
    }

    protected RestClient createRestClient(String baseURL ) {
        return restClientBuilder.baseUrl(baseURL).build();
    }

    protected <T> T createHTTPClient(Class<T> classType, RestClient restClient) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();
        return factory.createClient(classType);
    }

}
