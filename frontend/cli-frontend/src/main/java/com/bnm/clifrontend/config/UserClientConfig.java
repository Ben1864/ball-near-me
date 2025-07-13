package com.bnm.clifrontend.config;

import com.bnm.clifrontend.client.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class UserClientConfig extends ClientConfig {

    public UserClientConfig(RestClient.Builder restClientBuilder) {
        super(restClientBuilder);
    }

    @Bean
    public UserClient userClient() {
        RestClient restClient = createRestClient();
        return createHTTPClient(UserClient.class, restClient);
    }
}
