package com.example.urllessweb;

import gateway.UrlGateway;
import gateway.UrlGatewayFake;
import interactor.ShortenerInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import usecase.ShortenerUseCase;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ShortenerUseCase shortener(UrlGateway urlGateway) {
        return new ShortenerInteractor(urlGateway);
    }

    @Bean
    public UrlGateway urlGateway() {
        return new UrlGatewayFake();
    }
}
