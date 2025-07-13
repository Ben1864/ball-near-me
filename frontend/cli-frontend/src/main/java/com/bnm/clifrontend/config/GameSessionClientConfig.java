package com.bnm.clifrontend.config;

import com.bnm.clifrontend.client.GameSessionClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

public class GameSessionClientConfig extends ClientConfig{

    public GameSessionClientConfig(RestClient.Builder restClientBuilder) {
        super(restClientBuilder);
    }

    @Bean
    public GameSessionClient gameSessionClient() {
        RestClient client = createRestClient();
        return createHTTPClient(GameSessionClient.class, client);
    }

}
