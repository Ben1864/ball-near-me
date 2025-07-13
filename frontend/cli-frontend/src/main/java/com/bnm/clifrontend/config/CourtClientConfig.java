package com.bnm.clifrontend.config;

import com.bnm.clifrontend.client.CourtClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class CourtClientConfig extends ClientConfig {


    public CourtClientConfig(RestClient.Builder restClientBuilder) {
        super(restClientBuilder);
    }

    @Bean
    public CourtClient courtClient() {
        String baseURL = "http://localhost:8081";
        RestClient client = createRestClient(baseURL);
        return createHTTPClient(CourtClient.class, client);
    }
}
