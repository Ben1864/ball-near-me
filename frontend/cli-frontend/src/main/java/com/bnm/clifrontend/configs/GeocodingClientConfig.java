package com.bnm.clifrontend.configs;

import com.bnm.clifrontend.clients.GeocodingClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GeocodingClientConfig extends ClientConfig {

    @Value("${google.maps.api.key}")
    private String apiKey;

    protected GeocodingClientConfig(RestClient.Builder restClientBuilder) {
        super(restClientBuilder);
    }

    @Bean
    public GeocodingClient geocodingClient() {
        String baseURL = "https://maps.googleapis.com/maps/api";
        RestClient client = createRestClient(baseURL);
        return createHTTPClient(GeocodingClient.class, client);
    }

    @Bean
    public String googleAPIKey() {
        return apiKey;
    }
}
