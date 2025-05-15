package com.claro.clientesapi.config;

import com.claro.clientesapi.controller.ClienteController;
import com.claro.clientesapi.exception.ClienteBadRequestExceptionMapper;
import com.claro.clientesapi.exception.ClienteNoEncontradoExceptionMapper;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ClienteController.class);
        register(ClienteBadRequestExceptionMapper.class);
        register(ClienteNoEncontradoExceptionMapper.class);
    }

    @Bean
    public Client jerseyClient(){
        return ClientBuilder.newClient();
    }
}
