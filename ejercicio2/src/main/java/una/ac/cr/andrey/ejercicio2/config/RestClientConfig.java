package una.ac.cr.andrey.ejercicio2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    
    @Bean
    RestClient createRestBuilder() {
        return RestClient.create();
    }
}
