package com.bnm.clifrontend.config;

import com.bnm.clifrontend.client.CourtClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class CourtClientConfig extends ClientConfig {


    public CourtClientConfig(RestClient.Builder restClientBuilder) {
        super(restClientBuilder);
    }

    @Bean
    public CourtClient courtClient() {
        RestClient client = createRestClient();
        return createHTTPClient(CourtClient.class, client);
    }
}
