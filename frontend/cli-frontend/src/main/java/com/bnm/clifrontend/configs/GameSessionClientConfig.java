package com.bnm.clifrontend.configs;

import com.bnm.clifrontend.clients.GameSessionClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GameSessionClientConfig extends ClientConfig{

    public GameSessionClientConfig(RestClient.Builder restClientBuilder) {
        super(restClientBuilder);
    }

    @Bean
    public GameSessionClient gameSessionClient() {
        String baseURL = "http://localhost:8081";
        RestClient client = createRestClient(baseURL);
        return createHTTPClient(GameSessionClient.class, client);
    }

}
