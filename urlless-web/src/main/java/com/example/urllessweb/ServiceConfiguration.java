package com.example.urllessweb;

import gateway.UrlGateway;
import gateway.UrlGatewayFake;
import generator.IdGenerator;
import generator.SHA1IdGenerator;
import interactor.ShortenerInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import usecase.ShortenerUseCase;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ShortenerUseCase shortener(UrlGateway urlGateway, IdGenerator idGenerator) {
        return new ShortenerInteractor(urlGateway, idGenerator);
    }

    @Bean
    public UrlGateway urlGateway() {
        return new UrlGatewayFake();
    }

    @Bean
    public IdGenerator sha1IdGenerator() {
        return new SHA1IdGenerator();
    }
}
